package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

public class ContractType {
	private String name;

	private double compensationDays;

	private Set<Contract> contract = new HashSet<>();

	public ContractType(String name, double compensationDays) {
		this.name = name;
		this.compensationDays = compensationDays;
	}

	private ContractType(String name, double compensationDaysPerYear,
			Contract contract) {

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

	Set<Contract> _getContract() {
		return contract;
	}

}
