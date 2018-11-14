package uo.ri.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import alb.util.date.Dates;
import alb.util.math.Round;
import uo.ri.model.types.ContractStatus;
import uo.ri.ui.util.DateUtil;

@Entity
@Table(name = "TCONTRACTS", uniqueConstraints = {
		@UniqueConstraint(columnNames = "START_DATE, END_DATE, MECHANIC_ID") })
public class Contract {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "START_DATE")
	private Date startDate;

	@Column(name = "END_DATE")
	private Date endDate;

	private boolean finished;

	private double compensation;

	@Column(name = "BASE_SALARY_PER_YEAR")
	private double baseSalaryPerYear;

	@Enumerated(EnumType.STRING)
	private ContractStatus status;

	@ManyToOne
	private Mecanico mechanic;

	@OneToMany(mappedBy = "contract")
	private Set<Payroll> payrolls = new HashSet<>();

	@ManyToOne
	private ContractType type;

	@ManyToOne
	private ContractCategory category;

	Contract() {
	}

	public Contract(Mecanico mecanico) {
		setStartDate(Dates.today());
		type = new ContractType();
		Association.Link.link(mecanico, this);
		setContractStatus(ContractStatus.ACTIVE);
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
		if (endDate != null)
			markAsFinished(endDate);
	}

	public Contract(Mecanico mechanic, Date startDate, double baseSalaryPerYear,
			ContractType contractType) {
		this(mechanic, startDate, baseSalaryPerYear);
		Association.Typefy.link(this, contractType);
	}

	public Contract(Mecanico mechanic, Date startDate, double baseSalaryPerYear,
			ContractType contractType, ContractCategory contractCategory) {
		this(mechanic, startDate, baseSalaryPerYear, contractType);
		Association.Categorize.link(this, contractCategory);
	}

	public Long getId() {
		return id;
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
		if (endDate != null)
			return new Date(endDate.getTime());
		return null;
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
		if (startDate != null && endDate != null) {
			int months = DateUtil.getNumMonths(startDate, endDate);
			if (months >= 11) {
				compensation = baseSalaryPerYear / 365
						* getContractType().getCompensationDays();
				return compensation;
			}
		}
		return 0;
	}

	public double getBaseSalaryPerYear() {
		return baseSalaryPerYear;
	}

	public void setBaseSalaryPerYear(double baseSalaryPerYear) {
		this.baseSalaryPerYear = baseSalaryPerYear;
	}

	public ContractStatus getContractStatus() {
		return status;
	}

	public void setContractStatus(ContractStatus contractStatus) {
		this.status = contractStatus;
	}

	public Mecanico getMechanic() {
		return mechanic;
	}

	public Set<Payroll> getPayrolls() {
		return new HashSet<>(payrolls);
	}

	public ContractType getContractType() {
		return type;
	}

	public void _setContractType(ContractType contractType) {
		this.type = contractType;
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
		return category;
	}

	public void _setContractCategory(ContractCategory contractCategory) {
		this.category = contractCategory;
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
		return "Contract [id=" + id + ", startDate=" + startDate + ", endDate="
				+ endDate + ", finished=" + finished + ", compensation="
				+ compensation + ", baseSalaryPerYear=" + baseSalaryPerYear
				+ ", status=" + status + "]";
	}

}
