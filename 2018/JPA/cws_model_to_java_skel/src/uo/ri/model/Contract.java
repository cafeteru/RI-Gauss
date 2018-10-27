package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import alb.util.date.Dates;
import uo.ri.model.types.ContractStatus;

public class Contract {

	private Date startDate;
	private Date endDate;

	private boolean finished;

	private double compensation;
	private double baseSalaryPerYear;

	private ContractStatus contractStatus;

	private Mecanico mechanic;

	private Set<Payroll> payrolls = new HashSet<>();

	private ContractType contractType;

	private ContractCategory contractCategory;

	public Contract() {
		this.startDate = Dates.firstDayOfMonth(Dates.today());
	}

	public Contract(Mecanico mecanico) {
		this();
		Association.Vincular.link(mecanico, this);
	}

	public Contract(Mecanico mechanic, Date startDate,
			double baseSalaryPerYear) {
		this(mechanic);
		this.startDate = Dates.firstDayOfMonth(startDate);
		this.baseSalaryPerYear = baseSalaryPerYear;
	}

	public Contract(Mecanico mechanic, Date startDate, Date endDate,
			double baseSalaryPerYear) {
		this(mechanic, startDate, baseSalaryPerYear);
		markAsFinished(endDate);
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

	public void setBaseSalary(double baseSalaryPerYear) {
		this.baseSalaryPerYear = baseSalaryPerYear;
	}

	public ContractStatus getContractStatus() {
		return contractStatus;
	}

	public void setContractStatus(ContractStatus contractStatus) {
		this.contractStatus = contractStatus;
	}

	public Mecanico getMechanic() {
		return mechanic;
	}

	public void _setMechanic(Mecanico mecanico) {
		this.mechanic = mecanico;
	}

	public Set<Payroll> getPayrolls() {
		return new HashSet<>(payrolls);
	}

	Set<Payroll> _getPayrolls() {
		return payrolls;
	}

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}

	public void markAsFinished(Date date) {
		this.endDate = Dates.lastDayOfMonth(date);
		this.finished = true;
	}

	public Payroll getLastPayroll() {
		Payroll result = null;
		Date date = Dates.fromDdMmYyyy(1, 1, 1970);
		for (Payroll payroll : payrolls) {
			if (Dates.isAfter(payroll.getDate(), date)) {
				date = payroll.getDate();
				result = payroll;
			}
		}
		return result;
	}

	public ContractCategory getContractCategory() {
		return contractCategory;
	}

	public void setContractCategory(ContractCategory contractCategory) {
		this.contractCategory = contractCategory;
	}

	public void setBaseSalaryPerYear(double baseSalaryPerYear) {
		this.baseSalaryPerYear = baseSalaryPerYear;
	}

	public double getIrpfPercent() {
		// TODO Auto-generated method stub
		return 0;
	}

}
