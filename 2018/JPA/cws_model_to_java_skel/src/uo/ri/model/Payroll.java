package uo.ri.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import alb.util.date.Dates;
import alb.util.math.Round;

public class Payroll {

	private Date date;

	private double baseSalary;
	private double extraSalary;
	private double productivity;
	private double triennium;
	private double irpf;
	private double socialSecurity;
	private double netTotal;

	private Contract contract;

	public Payroll() {
		this.date = Dates.today();
	}

	public Payroll(Contract contract, Date date, double numberInterventions) {
		date = Dates.lastDayOfMonth(Dates.addMonths(date, -1));
		if (!Dates.isAfter(contract.getStartDate(), date)) {
			Association.Percibir.link(contract, this);
			this.date = date;
			this.baseSalary = contract.getBaseSalaryPerYear() / 14;
			int month = Dates.month(date);
			if (month == 6 || month == 12) {
				this.extraSalary = this.baseSalary;
			}
			if (contract.getContractCategory() != null
					&& numberInterventions > 0) {
				this.productivity = numberInterventions
						* contract.getContractCategory().getProductivityPlus();
			}
			getIrpf();
			getNetTotal();
			getGrossTotal();
			getDiscountTotal();
		} else {
			throw new IllegalArgumentException(" No se puede crear una nómina "
					+ "para un mes antes de la fecha de inicio del contrato");
		}
	}

	public Date getDate() {
		return new Date(date.getTime());
	}

	Date _getDate() {
		return date;
	}

	public double getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(double baseSalary) {
		this.baseSalary = baseSalary;
	}

	public double getExtraSalary() {
		return extraSalary;
	}

	public void setExtraSalary(double extraSalary) {
		this.extraSalary = extraSalary;
	}

	public double getProductivity() {
		return productivity;
	}

	public double getTriennium() {
		Calendar fin = new GregorianCalendar();
		Calendar inicio = new GregorianCalendar();
		fin.setTime(date);
		inicio.setTime(contract.getStartDate());
		int difA = fin.get(Calendar.YEAR) - inicio.get(Calendar.YEAR);
		triennium = difA * 12 + fin.get(Calendar.MONTH)
				- inicio.get(Calendar.MONTH);
		if (contract.getContractCategory() != null)
			return ((int) triennium / 36)
					* contract.getContractCategory().getTrieniumSalary();
		return 0;
	}

	public double getIrpfPercent() {
		double anual = Round.twoCents(baseSalary * 14);
		double percent = 0;
		if (anual < 12000) {
			percent = 0;
		} else if (anual < 30000) {
			percent = 10;
		} else if (anual < 40000) {
			percent = 15;
		} else if (anual < 50000) {
			percent = 20;
		} else if (anual < 60000) {
			percent = 30;
		} else {
			percent = 40;
		}
		return percent / 100;
	}

	public double getSocialSecurity() {
		socialSecurity = contract.getBaseSalaryPerYear() / 12 * 0.05;
		return socialSecurity;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Contract getContract() {
		return contract;
	}

	void _setContract(Contract contract) {
		this.contract = contract;
	}

	public double getIrpf() {
		irpf = getIrpfPercent() * getGrossTotal();
		return irpf;
	}

	public double getNetTotal() {
		netTotal = getGrossTotal() - getDiscountTotal();
		return netTotal;
	}

	public double getGrossTotal() {
		return getBaseSalary() + getExtraSalary() + getProductivity()
				+ getTriennium();
	}

	public double getDiscountTotal() {
		return getIrpf() + getSocialSecurity();
	}

}
