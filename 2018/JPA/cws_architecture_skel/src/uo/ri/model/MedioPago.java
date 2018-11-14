package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "TMEDIOSPAGO")
public abstract class MedioPago {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	protected double acumulado = 0.0;

	@ManyToOne
	protected Cliente cliente;

	@OneToMany(mappedBy = "medioPago")
	protected Set<Cargo> cargos = new HashSet<>();

	MedioPago() {
	}
	
	public Long getId() {
		return id;
	}

	public void pagar(double importe) {
		this.acumulado += importe;
	}

	public double getAcumulado() {
		return acumulado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	void _setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

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

	public Set<Cargo> getCargos() {
		return new HashSet<>(cargos);
	}

	Set<Cargo> _getCargos() {
		return cargos;
	}

	public void setAcumulado(double acumulado) {
		this.acumulado = acumulado;
	}

	public abstract void isValid(double importe);
}
