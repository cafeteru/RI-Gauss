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
public class ContractCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private double trieniumSalary;
	private double productivityPlus;

	@OneToMany(mappedBy = "category")
	private Set<Contract> contracts = new HashSet<>();

	ContractCategory() {
	}

	public ContractCategory(String name, double trienniumSalary,
			double productivityPlus) {
		this.name = name;
		this.trieniumSalary = trienniumSalary;
		this.productivityPlus = productivityPlus;
	}
	
	public Long getId() {
		return id;
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
