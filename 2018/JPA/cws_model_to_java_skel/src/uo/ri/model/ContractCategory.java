package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

public class ContractCategory {
	private String name;

	private double trieniumSalary;
	private double productivityPlus;

	private Set<Contract> contracts = new HashSet<>();

	public ContractCategory() {
	}

	Set<Contract> _getContracts() {
		return contracts;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTrieniumSalary() {
		return trieniumSalary;
	}

	public void setTrieniumSalary(double trieniumSalary) {
		this.trieniumSalary = trieniumSalary;
	}

	public double getProductivityPlus() {
		return productivityPlus;
	}

	public void setProductivityPlus(double productivityPlus) {
		this.productivityPlus = productivityPlus;
	}

	public Set<Contract> getContracts() {
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
		ContractCategory other = (ContractCategory) obj;
		if (contracts == null) {
			if (other.contracts != null)
				return false;
		} else if (!contracts.equals(other.contracts))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ContractCategory [name=" + name + ", trieniumSalary="
				+ trieniumSalary + ", productivityPlus=" + productivityPlus
				+ ", contracts=" + contracts + "]";
	}

}
