package me.max.models;

public class PostRIRequest {
	private int uId;
	private double amount;
	private String rFor;
	
	public PostRIRequest() {
		super();
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getrFor() {
		return rFor;
	}

	public void setrFor(String rFor) {
		this.rFor = rFor;
	}
	
	
	
}
