package uo.ri.util;

import java.util.Date;

import uo.ri.model.Association;
import uo.ri.model.Contract;
import uo.ri.model.ContractCategory;
import uo.ri.model.Mecanico;

public class ContractBuilder {

	private Contract contract;

	public ContractBuilder() {
		this.contract = new Contract();
	}

	public Contract build() {
		return contract;
	}

	public ContractBuilder forMechanic(Mecanico mecanico) {
		Association.Vincular.link(mecanico, contract);
		return this;
	}

	public ContractBuilder withStartDate(Date startDate) {
		contract.setStartDate(startDate);
		return this;
	}

	public ContractBuilder withBaseSalary(double baseSalary) {
		contract.setBaseSalary(baseSalary);
		return this;
	}

	public ContractBuilder withCategory(ContractCategory contractCategory) {
		Association.Categorizar.link(contract, contractCategory);
		return this;
	}

}
