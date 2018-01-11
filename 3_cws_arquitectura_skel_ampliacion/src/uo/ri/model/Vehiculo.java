package uo.ri.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import uo.ri.util.exception.BusinessException;
import uo.ri.util.exception.Check;

@Entity
@Table(name = "TVEHICULOS")
public class Vehiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String marca;

	@Column(unique = true)
	private String matricula;

	private String modelo;

	@Column(name = "NUM_AVERIAS")
	private int numAverias = 0;

	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	private TipoVehiculo tipo;

	@OneToMany(mappedBy = "vehiculo")
	private Set<Averia> averias = new HashSet<Averia>();

	Vehiculo() {
	}

	public Vehiculo(String matricula) throws BusinessException {
		this.matricula = Check.checkString(matricula, "Matricula");
	}

	public Vehiculo(String matricula, TipoVehiculo tipo)
			throws BusinessException {
		this(matricula);
		Association.Clasificar.link(tipo, this);
	}

	public Vehiculo(String matricula, String marca) throws BusinessException {
		this(matricula);
		this.marca = marca;
	}

	public Vehiculo(String matricula, String marca, String modelo)
			throws BusinessException {
		this(matricula, marca);
		this.modelo = modelo;
	}

	public Long getId() {
		return id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public int getNumAverias() {
		return numAverias;
	}

	public void setNumAverias(int numAverias) {
		this.numAverias = numAverias;
	}

	public void incrementarAverias() {
		setNumAverias(++numAverias);
	}

	public Cliente getCliente() {
		return cliente;
	}

	void _setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public TipoVehiculo getTipo() {
		return tipo;
	}

	void _setTipo(TipoVehiculo tipo) {
		this.tipo = tipo;
	}

	public Set<Averia> getAverias() {
		return new HashSet<Averia>(averias);
	}

	Set<Averia> _getAverias() {
		return averias;
	}

	public String getMatricula() {
		return new String(matricula);
	}

	/**
	 * Comprueba que el vehiculo tiene averias para generar bonos.
	 * 
	 * @return Una lista con las averias que se pueden usar.
	 */
	public List<Averia> getAveriasBono3NoUsadas() {
		List<Averia> averias = new ArrayList<>();
		for (Averia a : this.averias) {
			if (a.esElegibleParaBono3())
				averias.add(a);
		}
		return averias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((matricula == null) ? 0 : matricula.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehiculo other = (Vehiculo) obj;
		if (matricula == null) {
			if (other.matricula != null)
				return false;
		} else if (!matricula.equals(other.matricula))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehiculo [marca=" + marca + ", matricula=" + matricula
				+ ", modelo=" + modelo + ", numAverias=" + numAverias + "]";
	}

}
