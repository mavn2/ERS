package me.max.models;

public class UpdateEmployee {
	private int id;
	private String col;
	private String val;
	
	public UpdateEmployee() {
		super();
	}

	public String getCol() {
		return col;
	}

	public void setCol(String col) {
		this.col = col;
	}

	public String getVal() {
		return val;
	}

	public void setVal(String val) {
		this.val = val;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
	
}
