package com.qa.jdbcDemo;

public class Drink {
	
	//JDBC will be creating for us a Drinks table
	//Primary key - int
	//when inserting data into a database, MySQL will generate a primary key for us
	//Auto incrementing ID
	
	//Fields
	private int  id;
	private String type;
	private String size;
	private boolean dairyFree;
	private float cost;
	
	//constructors - for pushing data into the database
	public Drink(String type, String size, boolean dairyFree, float cost) {
		super();
		this.type = type;
		this.size = size;
		this.dairyFree = dairyFree;
		this.cost = cost;
	}
	
	//overloading - multiple constructors (with different parameters) in one class
	//constructors - getting data out of the database
	public Drink(int id, String type, String size, boolean dairyFree, float cost) {
		super();
		this.id = id;
		this.type = type;
		this.size = size;
		this.dairyFree = dairyFree;
		this.cost = cost;
	}
	

	//getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public boolean isDairyFree() {
		return dairyFree;
	}

	public void setDairyFree(boolean dairyFree) {
		this.dairyFree = dairyFree;
	}

	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
	}

	//toString
	@Override
	public String toString() {
		return "Drink [id=" + id + ", type=" + type + ", size=" + size + ", dairyFree=" + dairyFree + ", cost=" + cost
				+ ", getId()=" + getId() + ", getType()=" + getType() + ", getSize()=" + getSize() + ", isDairyFree()="
				+ isDairyFree() + ", getCost()=" + getCost() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
	
	
	
	

}
