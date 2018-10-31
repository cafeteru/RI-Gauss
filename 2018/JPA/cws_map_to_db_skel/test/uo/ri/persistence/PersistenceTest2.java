package uo.ri.persistence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import alb.util.date.Dates;
import uo.ri.model.Contract;
import uo.ri.model.ContractCategory;
import uo.ri.model.ContractType;
import uo.ri.model.Mecanico;
import uo.ri.model.Payroll;
import uo.ri.model.types.ContractStatus;
import uo.ri.util.CategoryBuilder;

public class PersistenceTest2 {

	private static final double BASE_SALARY = 10000;
	private static final double BETTER_BASE_SALARY = BASE_SALARY * 3;
	private static final double PRODUCTIVITY_PLUS = 0.05;
	private static final double TRIENNIUM_SALARY = 30;
	private static final double TOTAL_BY_INTERVENTIONS = 10000;

	private EntityManagerFactory factory;
	private Mecanico mechanic;
	private Contract contract;
	private ContractType type;
	private ContractCategory category;
	private Payroll payroll;

	List<Object> graph;

	@Before
	public void setUp() {
		factory = Persistence.createEntityManagerFactory("carworkshop");
		graph = createGraph();
		persistGraph(graph);
	}

	@After
	public void tearDown() {
		removeGraph();
		factory.close();
	}

	@Test
	public void testMecanico() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Mecanico cl = mapper.merge(mechanic);

		assertNotNull(cl.getId());
		assertEquals(cl.getApellidos(), "apellidos");
		assertEquals(cl.getNombre(), "nombre");
		assertEquals(cl.getDni(), "dni2");

		trx.commit();
		mapper.close();
	}

	@Test
	public void testContract() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Contract c = mapper.merge(contract);
		assertNotNull(c.getId());
		assertNull(c.getEndDate());
		assertFalse(c.isFinished());
		assertEquals(ContractStatus.ACTIVE, c.getContractStatus());
		assertEquals(1, c.getContractType().getContract().size());
		assertEquals(30000.0, c.getBaseSalaryPerYear(), 0.01);
		assertEquals(0.0, c.getCompensation(), 0.01);
		assertEquals(type.getName(), c.getContractType().getName());
		assertEquals(type.getCompensationDays(),
				c.getContractType().getCompensationDays(), 0.01);
		assertTrue(c.getContractType().getContract().contains(c));
		assertEquals(category.getName(), c.getContractCategory().getName());
		assertEquals(category.getTrieniumSalary(),
				c.getContractCategory().getTrieniumSalary(), 0.01);
		assertTrue(c.getContractCategory().getContracts().contains(c));
		trx.commit();
		mapper.close();
	}

	@Test
	public void testPayroll() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		Payroll p = mapper.merge(payroll);

		assertNotNull(p.getId());
		assertEquals(BETTER_BASE_SALARY / 14, p.getBaseSalary(), 0.01);
		assertEquals(BETTER_BASE_SALARY / 14, p.getExtraSalary(), 0.01);
		assertEquals(TOTAL_BY_INTERVENTIONS * PRODUCTIVITY_PLUS,
				p.getProductivity(), 0.01);
		assertEquals(3 * TRIENNIUM_SALARY, p.getTriennium(), 0.01);
		assertEquals(0.15 * (BETTER_BASE_SALARY / 14 + BETTER_BASE_SALARY / 14
				+ TOTAL_BY_INTERVENTIONS * PRODUCTIVITY_PLUS
				+ 3 * TRIENNIUM_SALARY), p.getIrpf(), 0.01);
		assertEquals(BETTER_BASE_SALARY / 12 * 0.05, p.getSocialSecurity(),
				0.01);

		trx.commit();
		mapper.close();
	}

	protected List<Object> createGraph() {
		Date january = Dates.fromDdMmYyyy(10, 01, 2010);
		Date january2020 = Dates.fromDdMmYyyy(02, 01, 2020);

		category = new CategoryBuilder().withProductivityPlus(PRODUCTIVITY_PLUS)
				.withTrienniumSalary(TRIENNIUM_SALARY).build();
		
		type = new ContractType("name", 3);	

		contract = new Contract(mechanic, january, BETTER_BASE_SALARY, type,
				category);
		contract.setContractCategory(category);
		
		mechanic = new Mecanico("dni2", "nombre", "apellidos");
		
		payroll = new Payroll(contract, january2020, TOTAL_BY_INTERVENTIONS);

		List<Object> res = new LinkedList<Object>();
		res.add(category);
		res.add(type);
		res.add(contract);
		res.add(mechanic);
		res.add(payroll);
		return res;
	}

	private void persistGraph(List<Object> graph) {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		for (Object o : graph) {
			mapper.persist(o);
		}

		trx.commit();
		mapper.close();
	}

	private void removeGraph() {
		EntityManager mapper = factory.createEntityManager();
		EntityTransaction trx = mapper.getTransaction();
		trx.begin();

		List<Object> merged = mergeGraph(mapper);

		for (Object o : merged) {
			mapper.remove(o);
		}

		trx.commit();
		mapper.close();
	}

	private List<Object> mergeGraph(EntityManager mapper) {
		List<Object> res = new LinkedList<Object>();
		Mecanico m = mapper.merge(mechanic);
		res.add(m);
		res.add(m.getActiveContract());
		res.add(m.getActiveContract().getLastPayroll());
		res.add(m.getActiveContract().getContractType());
		res.add(m.getActiveContract().getContractCategory());
		return res;
	}

}
