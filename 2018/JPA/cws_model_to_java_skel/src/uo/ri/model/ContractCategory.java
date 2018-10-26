package uo.ri.model;

import java.util.HashSet;
import java.util.Set;

public class ContractCategory {
	private String name;

	private double trieniumSalary;
	private double productivityPlus;

	private Set<Contract> contracts = new HashSet<>();
}
