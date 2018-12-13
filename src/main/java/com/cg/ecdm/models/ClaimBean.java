package com.cg.ecdm.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "claimbean")
public class ClaimBean {

	@Id
	private Integer claimCode;
	@NotEmpty(message="Shouldn't be empty")
	private Integer employeeId;
	@NotNull(message="Shouldn't be empty")
	private String employeeName;
	@NotEmpty(message="Shouldn't be empty")
	private Integer projectCode;
	@NotEmpty(message="Shouldn't be empty")
	private Integer expenseCode;
	@NotNull(message="Shouldn't be empty")
	private String startDate;
	@NotNull(message="Shouldn't be empty")
	private String endDate;
	@NotNull(message="Shouldn't be empty")
	private String expenseAmount;

	public Integer getClaimCode() {
		return claimCode;
	}

	public void setClaimCode(Integer claimCode) {
		this.claimCode = claimCode;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getProjectCode() {
		return projectCode;
	}

	public void setProjectCode(Integer projectCode) {
		this.projectCode = projectCode;
	}

	public Integer getExpenseCode() {
		return expenseCode;
	}

	public void setExpenseCode(Integer expenseCode) {
		this.expenseCode = expenseCode;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getExpenseAmount() {
		return expenseAmount;
	}

	public void setExpenseAmount(String expenseAmount) {
		this.expenseAmount = expenseAmount;
	}

	public ClaimBean() {
		// TODO Auto-generated constructor stub
	}

	public ClaimBean(Integer claimCode, Integer employeeId, String employeeName,
			Integer projectCode, Integer expenseCode, String startDate, String endDate,
			String expenseAmount) {
		super();
		this.claimCode = claimCode;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.projectCode = projectCode;
		this.expenseCode = expenseCode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.expenseAmount = expenseAmount;
	}

	@Override
	public String toString() {
		return "ClaimBean [claimCode=" + claimCode + ", employeeId="
				+ employeeId + ", employeeName=" + employeeName
				+ ", projectCode=" + projectCode + ", expenseCode="
				+ expenseCode + ", startDate=" + startDate + ", endDate="
				+ endDate + ", expenseAmount=" + expenseAmount + "]";
	}
	
}
