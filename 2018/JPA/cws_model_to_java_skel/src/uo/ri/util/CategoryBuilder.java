package uo.ri.util;

import uo.ri.model.ContractCategory;

public class CategoryBuilder {

	private ContractCategory contractCategory;

	public CategoryBuilder() {
		contractCategory = new ContractCategory();
	}

	public CategoryBuilder withProductivityPlus(double productivityPlus) {
		contractCategory.setProductivityPlus(productivityPlus);
		return this;
	}

	public CategoryBuilder withTrienniumSalary(double trienniumSalary) {
		contractCategory.setTrieniumSalary(trienniumSalary);
		return this;
	}

	public ContractCategory build() {
		return contractCategory;
	}

}
