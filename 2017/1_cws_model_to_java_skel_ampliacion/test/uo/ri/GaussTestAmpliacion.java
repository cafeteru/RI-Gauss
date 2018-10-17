package uo.ri;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.ri.model.Association;
import uo.ri.model.Cliente;
import uo.ri.model.Recomendacion;
import uo.ri.model.exception.BusinessException;

public class GaussTestAmpliacion {
	private Recomendacion r1;

	@Test
	public void test01() throws BusinessException {
		Cliente a = new Cliente("a");
		Cliente b = new Cliente("b");
		Cliente c = new Cliente("c");
		r1 = new Recomendacion(a, b);
		Recomendacion r2 = new Recomendacion(a, c);

		assertEquals(2, a.getRecomendados().size());
		assertNotNull(b.getRecomendador());
		assertNotNull(c.getRecomendador());

		Association.Recomendar.unlink(r1);
		assertEquals(1, a.getRecomendados().size());
		assertNull(b.getRecomendador());
		assertNull(r1.getRecomendador());
		assertNull(r1.getRecomendado());

		Association.Recomendar.unlink(r2);
		assertEquals(0, a.getRecomendados().size());
		assertNull(c.getRecomendador());
		assertNull(r2.getRecomendador());
		assertNull(r2.getRecomendado());
	}

	@Test(expected = BusinessException.class)
	public void test02() throws BusinessException {
		r1 = new Recomendacion(null, null);
	}

	@Test(expected = BusinessException.class)
	public void test03() throws BusinessException {
		Cliente a = new Cliente("a");
		r1 = new Recomendacion(a, null);
	}

	@Test(expected = BusinessException.class)
	public void test04() throws BusinessException {
		Cliente a = new Cliente("a");
		r1 = new Recomendacion(null, a);
	}

}
