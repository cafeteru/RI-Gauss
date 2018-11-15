package uo.ri.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import alb.util.date.Dates;
import uo.ri.ui.util.DateUtil;

@Entity
@Table(name = "TPAYROLLS", uniqueConstraints = {
		@UniqueConstraint(columnNames = "CONTRACT_ID, DATE") })
public class Payroll {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Temporal(TemporalType.DATE)
	private Date date;

	private double baseSalary;
	private double extraSalary;
	private double productivity;
	private double triennium;
	private double irpf;
	private double socialSecurity;
	private double netTotal;

	@ManyToOne
	private Contract contract;

	Payroll() {
	}

	public Payroll(Contract contract, Date date, double numberInterventions) {
		date = Dates.lastDayOfMonth(date);
		if (Dates.isBefore(contract.getStartDate(),
				Dates.addMonths(date, -1))) {
			Association.Perceive.link(contract, this);
			setDate(date);
			calculatePrices(numberInterventions);
		} else {
			throw new IllegalArgumentException(" No se puede crear una nómina "
					+ "para un mes antes de la fecha de inicio del contrato");
		}
	}

	public Long getId() {
		return id;
	}

	private void calculatePrices(double numberInterventions) {
		this.baseSalary = contract.getBaseSalaryPerYear() / 14;
		calculateExtraSalary();
		calculateProductivity(numberInterventions);
		getIrpf();
		getNetTotal();
		getGrossTotal();
		getDiscountTotal();
	}

	private void calculateExtraSalary() {
		int month = Dates.month(date);
		if (month == 6 || month == 12) {
			this.extraSalary = this.baseSalary;
		}
	}

	private void calculateProductivity(double numberInterventions) {
		if (contract.getContractCategory() != null && numberInterventions > 0) {
			this.productivity = numberInterventions
					* contract.getContractCategory().getProductivityPlus();
		}
	}

	Date _getDate() {
		return date;
	}

	void _setContract(Contract contract) {
		this.contract = contract;
	}

	public Date getDate() {
		return new Date(date.getTime());
	}

	public void setDate(Date date) {
		this.date = Dates.lastDayOfMonth(Dates.addMonths(date, -1));
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

	public double getProductivity() {
		return productivity;
	}

	public double getTriennium() {
		triennium = DateUtil.getNumMonths(contract._getStartDate(), date);
		if (contract.getContractCategory() != null)
			return ((int) triennium / 36)
					* contract.getContractCategory().getTrieniumSalary();
		return 0;
	}

	public double getIrpfPercent() {
		return contract.getIrpfPercent();
	}

	public double getSocialSecurity() {
		socialSecurity = contract.getBaseSalaryPerYear() / 12 * 0.05;
		return socialSecurity;
	}

	public Contract getContract() {
		return contract;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((contract == null) ? 0 : contract.hashCode());
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		Payroll other = (Payroll) obj;
		if (contract == null) {
			if (other.contract != null)
				return false;
		} else if (!contract.equals(other.contract))
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Payroll [date=" + date + ", baseSalary=" + baseSalary
				+ ", extraSalary=" + extraSalary + ", productivity="
				+ productivity + ", triennium=" + triennium + ", irpf=" + irpf
				+ ", socialSecurity=" + socialSecurity + ", netTotal="
				+ netTotal + ", contract=" + contract + "]";
	}

}
