package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import alb.util.date.Dates;
import alb.util.math.Round;
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
		setStartDate(Dates.today());
		contractType = new ContractType();
	}

	public Contract(Mecanico mecanico) {
		this();
		Association.Vincular.link(mecanico, this);
	}

	public Contract(Mecanico mechanic, Date startDate,
			double baseSalaryPerYear) {
		this(mechanic);
		if (baseSalaryPerYear >= 0) {
			setStartDate(startDate);
			setBaseSalaryPerYear(baseSalaryPerYear);
		} else {
			throw new IllegalArgumentException(
					"yearBaseSalary passed must be positive");
		}
	}

	public Contract(Mecanico mechanic, Date startDate, Date endDate,
			double baseSalaryPerYear) {
		this(mechanic, startDate, baseSalaryPerYear);
		markAsFinished(endDate);
	}

	public Date getStartDate() {
		return new Date(startDate.getTime());
	}

	Date _getStartDate() {
		return startDate;
	}

	Date _getEndDate() {
		return endDate;
	}

	void _setMechanic(Mecanico mecanico) {
		this.mechanic = mecanico;
	}

	Set<Payroll> _getPayrolls() {
		return payrolls;
	}

	public void setStartDate(Date startDate) {
		this.startDate = Dates.firstDayOfMonth(startDate);
	}

	public Date getEndDate() {
		return new Date(endDate.getTime());
	}

	public void setEndDate(Date endDate) {
		this.endDate = Dates.lastDayOfMonth(endDate);
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public double getCompensation() {
		compensation = baseSalaryPerYear / 365
				* getContractType().getCompensationDays();
		return compensation;
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

	public Mecanico getMechanic() {
		return mechanic;
	}

	public Set<Payroll> getPayrolls() {
		return new HashSet<>(payrolls);
	}

	public ContractType getContractType() {
		return contractType;
	}

	public void setContractType(ContractType contractType) {
		this.contractType = contractType;
	}

	public void markAsFinished(Date date) {
		if (!finished) {
			if (Dates.isBefore(startDate, Dates.lastDayOfMonth(date))) {
				setEndDate(date);
				this.finished = true;
			} else {
				throw new IllegalArgumentException(
						"The end date cannot be before the start date");
			}
		} else {
			throw new IllegalStateException(
					"An already finished contract raises exception trying"
							+ "to mark it as finished again");
		}
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

	public double getIrpfPercent() {
		double anual = Round.twoCents(baseSalaryPerYear);
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result
				+ ((mechanic == null) ? 0 : mechanic.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
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
		Contract other = (Contract) obj;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (mechanic == null) {
			if (other.mechanic != null)
				return false;
		} else if (!mechanic.equals(other.mechanic))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Contract [startDate=" + startDate + ", endDate=" + endDate
				+ ", finished=" + finished + ", compensation=" + compensation
				+ ", baseSalaryPerYear=" + baseSalaryPerYear
				+ ", contractStatus=" + contractStatus + ", mechanic="
				+ mechanic + ", payrolls=" + payrolls + ", contractType="
				+ contractType + ", contractCategory=" + contractCategory + "]";
	}

}
