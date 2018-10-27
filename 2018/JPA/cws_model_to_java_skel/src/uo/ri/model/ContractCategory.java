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

	Set<Contract> _getContracts() {
		return contracts;
	}

}
