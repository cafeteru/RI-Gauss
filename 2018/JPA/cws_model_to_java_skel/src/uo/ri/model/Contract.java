package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import uo.ri.model.types.ContractStatus;

public class Contract {

	private Date startDate;
	private Date endDate;

	private boolean finished;

	private double compensation;
	private double baseSalaryPerYear;

	private ContractStatus contractStatus;

	private Mecanico mecanico;

	private Set<Payroll> payrolls = new HashSet<>();

	private ContractType contractType;

	public Contract(Mecanico mechanic, Date startDate, Date endDate,
			double baseSalaryPerYear) {
		// TODO Auto-generated constructor stub
	}

	public Contract(Mecanico mechanic, Date startDate,
			double baseSalaryPerYear) {
		// TODO Auto-generated constructor stub
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public double getCompensation() {
		return compensation;
	}

	public void setCompensation(double compensation) {
		this.compensation = compensation;
	}

	public double getBaseSalaryPerYear() {
		return baseSalaryPerYear;
	}

	public void setBaseSalaryPerYear(double baseSalaryPerYear) {
		this.baseSalaryPerYear = baseSalaryPerYear;
	}

	public ContractStatus getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(ContractStatus contractStatus) {
		this.contractStatus = contractStatus;
	}

	public Mecanico getMecanico() {
		return mecanico;
	}

	public void setMecanico(Mecanico mecanico) {
		this.mecanico = mecanico;
	}

	public Set<Payroll> getPayrolls() {
		return payrolls;
	}

	public void setPayrolls(Set<Payroll> payrolls) {
		this.payrolls = payrolls;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}

	public void markAsFinished(Date addDays) {
		// TODO Auto-generated method stub

	}

}
