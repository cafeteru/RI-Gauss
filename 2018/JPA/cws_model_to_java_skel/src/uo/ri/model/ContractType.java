package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

public class ContractType {
	private String name;

	private double compensationDays;

	private Set<Contract> contract = new HashSet<>();

	public ContractType() {
	}

	public ContractType(String name, int compensationDays) {
		this.name = name;
		this.compensationDays = compensationDays;
	}

	Set<Contract> _getContract() {
		return contract;
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
		return new HashSet<>(contract);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contract == null) ? 0 : contract.hashCode());
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
		if (contract == null) {
			if (other.contract != null)
				return false;
		} else if (!contract.equals(other.contract))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContractType [name=" + name + ", compensationDays="
				+ compensationDays + ", contract=" + contract + "]";
	}

}
