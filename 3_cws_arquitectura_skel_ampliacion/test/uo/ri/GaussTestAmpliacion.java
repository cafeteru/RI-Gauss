package uo.ri;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.ri.model.Association;
import uo.ri.model.Cliente;
import uo.ri.model.Recomendacion;
import uo.ri.util.exception.BusinessException;

public class GaussTestAmpliacion {
	private Recomendacion r1;

	@Test
	public void test01() throws BusinessException {
		Cliente a = new Cliente("a");
		Cliente b = new Cliente("b");
		Cliente c = new Cliente("c");
		r1 = new Recomendacion(a, b);
		Recomendacion r2 = new Recomendacion(a, c);

		assertEquals(2, a.getRecomendacionesHechas().size());
		assertNotNull(b.getRecomendacionRecibida());
		assertNotNull(c.getRecomendacionRecibida());

		Association.Recomendar.unlink(r1);
		assertEquals(1, a.getRecomendacionesHechas().size());
		assertNull(b.getRecomendacionRecibida());
		assertNull(r1.getRecomendador());
		assertNull(r1.getRecomendado());

		Association.Recomendar.unlink(r2);
		assertEquals(0, a.getRecomendacionesHechas().size());
		assertNull(c.getRecomendacionRecibida());
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

	@Test(expected = BusinessException.class)
	public void test05() throws BusinessException {
		Cliente a = new Cliente("a");
		r1 = new Recomendacion(a, a);
	}

	@Test(expected = BusinessException.class)
	public void test06() throws BusinessException {
		Cliente a = new Cliente("a");
		Cliente b = new Cliente("b");
		Cliente c = new Cliente("c");
		r1 = new Recomendacion(a, b);
		r1 = new Recomendacion(c, b);
	}

}
