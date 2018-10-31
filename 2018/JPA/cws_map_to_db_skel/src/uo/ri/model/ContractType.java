package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TCONTRACTCATEGORYS")
public class ContractType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private double compensationDays;

	@OneToMany(mappedBy = "type")
	private Set<Contract> contracts = new HashSet<>();

	ContractType() {
	}

	public ContractType(String name, int compensationDays) {
		this.name = name;
		this.compensationDays = compensationDays;
	}
	
	public Long getId() {
		return id;
	}

	Set<Contract> _getContract() {
		return contracts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCompensationDays() {
		return compensationDays;
	}

	public void setCompensationDays(double compensationDays) {
		this.compensationDays = compensationDays;
	}

	public Set<Contract> getContract() {
		return new HashSet<>(contracts);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contracts == null) ? 0 : contracts.hashCode());
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
		ContractType other = (ContractType) obj;
		if (contracts == null) {
			if (other.contracts != null)
				return false;
		} else if (!contracts.equals(other.contracts))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContractType [name=" + name + ", compensationDays="
				+ compensationDays + ", contract=" + contracts + "]";
	}

}
