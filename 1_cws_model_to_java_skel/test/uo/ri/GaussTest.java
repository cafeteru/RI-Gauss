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
	public void test01() throws BusinessException {
		cliente = new Cliente("");
	}

	@Test(expected = BusinessException.class)
	public void test02() throws BusinessException {
		cliente = new Cliente(null);
	}

	@Test(expected = BusinessException.class)
	public void test03() throws BusinessException {
		mecanico = new Mecanico("");
	}

	@Test(expected = BusinessException.class)
	public void test04() throws BusinessException {
		mecanico = new Mecanico(null);
	}

	@Test(expected = BusinessException.class)
	public void test05() throws BusinessException {
		vehiculo = new Vehiculo("");
	}

	@Test(expected = BusinessException.class)
	public void test06() throws BusinessException {
		vehiculo = new Vehiculo(null);
	}

	@Test(expected = BusinessException.class)
	public void test07() throws BusinessException {
		tipo = new TipoVehiculo("");
	}

	@Test(expected = BusinessException.class)
	public void test08() throws BusinessException {
		tipo = new TipoVehiculo(null);
	}

	@Test(expected = BusinessException.class)
	public void test09() throws BusinessException {
		vehiculo = new Vehiculo("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(null, vehiculo);
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
		vehiculo = new Vehiculo("a");
		mecanico = new Mecanico("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(vehiculo);
		assertNotNull(averia);
		assertEquals(1, vehiculo.getNumAverias());
		averia.assignTo(mecanico);
		Mecanico m = new Mecanico("b");
		averia.assignTo(m);
	}

	@Test(expected = BusinessException.class)
	public void test12() throws BusinessException {
		vehiculo = new Vehiculo("a");
		mecanico = new Mecanico("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(vehiculo);
		assertNotNull(averia);
		assertEquals(1, vehiculo.getNumAverias());
		averia.assignTo(mecanico);
		averia.markAsFinished();
		averia.markAsFinished();
	}

	@Test(expected = BusinessException.class)
	public void test13() throws BusinessException {
		vehiculo = new Vehiculo("a");
		mecanico = new Mecanico("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(vehiculo);
		assertNotNull(averia);
		assertEquals(1, vehiculo.getNumAverias());
		averia.assignTo(mecanico);
		averia.markAsFinished();
		averia.assignTo(mecanico);
	}

	@Test(expected = BusinessException.class)
	public void test14() throws BusinessException {
		vehiculo = new Vehiculo("a");
		mecanico = new Mecanico("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(vehiculo);
		assertNotNull(averia);
		assertEquals(1, vehiculo.getNumAverias());
		averia.markAsFinished();
	}

	@Test(expected = BusinessException.class)
	public void test15() throws BusinessException {
		vehiculo = new Vehiculo("a");
		mecanico = new Mecanico("a");
		assertEquals(0, vehiculo.getNumAverias());
		averia = new Averia(vehiculo);
		assertNotNull(averia);
		assertEquals(1, vehiculo.getNumAverias());
		averia.assignTo(mecanico);
		averia.markAsFinished();
		averia.reopen();
		averia.reopen();
	}

}
