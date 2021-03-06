package me.max.models;

import java.sql.Date;

public class Request {
	private int id;
	private int applyId;
	private int approveId;
	private int status;
	private double amount;
	private String rFor;
	private Date date;

	// Constructor w/ all starting fields
	public Request(int id, int applyId, int status, double amount, String rFor, Date date) {
		super();
		this.id = id;
		this.applyId = applyId;
		this.status = status;
		this.amount = amount;
		this.rFor = rFor;
		this.date = date;
	}

	// Constructor w/ all fields, for handled requests
	public Request(int id, int applyId, int approveId, int status, double amount, String rFor, Date date) {
		super();
		this.id = id;
		this.applyId = applyId;
		this.approveId = approveId;
		this.status = status;
		this.amount = amount;
		this.rFor = rFor;
		this.date = date;
	}

	// Getters for fixed values-
	// Should always be set by database/in dao
	public int getId() {
		return id;
	}

	public int getApplyId() {
		return applyId;
	}

	public double getAmount() {
		return amount;
	}

	public String getrFor() {
		return rFor;
	}

	public Date getDate() {
		return date;
	}

	// Getters/setters for values that could change w/ user input
	public int getApproveId() {
		return approveId;
	}

	public void setApproveId(int approvId) {
		this.approveId = approvId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	// hashCode, equals, toString
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + applyId;
		result = prime * result + approveId;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id;
		result = prime * result + ((rFor == null) ? 0 : rFor.hashCode());
		result = prime * result + status;
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
		Request other = (Request) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		if (applyId != other.applyId)
			return false;
		if (approveId != other.approveId)
			return false;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		if (id != other.id)
			return false;
		if (rFor == null) {
			if (other.rFor != null)
				return false;
		} else if (!rFor.equals(other.rFor))
			return false;
		if (status != other.status)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", applyId=" + applyId + ", approvId=" + approveId + ", status=" + status
				+ ", amount=" + amount + ", rFor=" + rFor + ", date=" + date + "]";
	}

}
