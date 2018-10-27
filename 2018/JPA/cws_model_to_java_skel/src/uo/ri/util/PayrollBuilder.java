package uo.ri.util;

import java.util.Date;

import uo.ri.model.Association;
import uo.ri.model.Contract;
import uo.ri.model.Payroll;

public class PayrollBuilder {
	private Payroll payroll;

	public PayrollBuilder() {
		payroll = new Payroll();
	}

	public PayrollBuilder forContract(Contract contract) {
		Association.Percibir.link(contract, payroll);
		return this;
	}

	public PayrollBuilder withDate(Date date) {
		payroll.setDate(date);
		return this;
	}

	public Payroll build() {
		return payroll;
	}

}
