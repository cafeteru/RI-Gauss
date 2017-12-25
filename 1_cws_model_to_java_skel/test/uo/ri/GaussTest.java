package uo.ri;

import static org.junit.Assert.*;

import org.junit.Test;

import uo.ri.model.*;
import uo.ri.model.exception.BusinessException;

public class GaussTest {
	private Cliente cliente;
	private Mecanico mecanico;
	private Vehiculo vehiculo;
	private TipoVehiculo tipo;
	private Averia averia;

	@Test(expected = BusinessException.class)
	public void test() throws BusinessException {
		cliente = new Cliente("");
		assertNull(cliente);
	}

	@Test(expected = BusinessException.class)
	public void test2() throws BusinessException {
		cliente = new Cliente(null);
		assertNull(cliente);
	}

	@Test(expected = BusinessException.class)
	public void test3() throws BusinessException {
		mecanico = new Mecanico("");
		assertNull(mecanico);
	}

	@Test(expected = BusinessException.class)
	public void test4() throws BusinessException {
		mecanico = new Mecanico(null);
		assertNull(mecanico);
	}

	@Test(expected = BusinessException.class)
	public void test5() throws BusinessException {
		vehiculo = new Vehiculo("");
	}

	@Test(expected = BusinessException.class)
	public void test6() throws BusinessException {
		vehiculo = new Vehiculo(null);
	}

	@Test(expected = BusinessException.class)
	public void test7() throws BusinessException {
		tipo = new TipoVehiculo("");
		assertNull(tipo);
	}

	@Test(expected = BusinessException.class)
	public void test8() throws BusinessException {
		tipo = new TipoVehiculo(null);
		assertNull(tipo);
	}

	@Test(expected = BusinessException.class)
	public void test9() throws BusinessException {
		vehiculo = new Vehiculo("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(null, vehiculo);
		assertNull(averia);
	}

	@Test(expected = BusinessException.class)
	public void test10() throws BusinessException {
		vehiculo = new Vehiculo("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(vehiculo);
		assertNotNull(averia);
		assertEquals(1, vehiculo.getNumAverias());
		averia.assignTo(null);
	}

	@Test(expected = BusinessException.class)
	public void test11() throws BusinessException {
	
	}

}
