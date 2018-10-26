package uo.ri.model;

import java.util.Date;

public class Payroll {

	private Date date;

	private double baseSalary;
	private double extraSalary;
	private double productivity;
	private double trienniums;
	private double irpfPercent;
	private double socialSecurity;

	private Contract contract;

	public Payroll(Contract c, Date beginningOfFebruary, double d) {
		// TODO Auto-generated constructor stub
	}

	public Date getDate() {
		// TODO Auto-generated method stub
		return null;
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

	public void setProductivity(double productivity) {
		this.productivity = productivity;
	}

	public double getTrienniums() {
		return trienniums;
	}

	public void setTrienniums(double trienniums) {
		this.trienniums = trienniums;
	}

	public double getIrpfPercent() {
		return irpfPercent;
	}

	public void setIrpfPercent(double irpfPercent) {
		this.irpfPercent = irpfPercent;
	}

	public double getSocialSecurity() {
		return socialSecurity;
	}

	public void setSocialSecurity(double socialSecurity) {
		this.socialSecurity = socialSecurity;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
