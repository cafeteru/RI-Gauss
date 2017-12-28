package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import uo.ri.model.exception.BusinessException;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TMEDIOSPAGO")
public abstract class MedioPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	protected double acumulado = 0.0;

	@ManyToOne
	private Cliente cliente;

	@OneToMany(mappedBy = "medioPago")
	private Set<Cargo> cargos = new HashSet<Cargo>();

	MedioPago() {
	}

	public Long getId() {
		return id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	void _setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Cargo> getCargos() {
		return new HashSet<Cargo>(cargos);
	}

	Set<Cargo> _getCargos() {
		return cargos;
	}

	public double getAcumulado() {
		return acumulado;
	}

	public void setAcumulado(double acumulado) {
		this.acumulado = acumulado;
	}

	public abstract void pagar(double importe) throws BusinessException;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cliente == null) ? 0 : cliente.hashCode());
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
		MedioPago other = (MedioPago) obj;
		if (cliente == null) {
			if (other.cliente != null)
				return false;
		} else if (!cliente.equals(other.cliente))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MedioPago [acumulado=" + acumulado + ", cliente=" + cliente
				+ "]";
	}

}
