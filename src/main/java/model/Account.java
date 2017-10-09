package model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {
	
	private Long id;
	
	
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name = "Company_id", nullable=false)
	//private Company company;
	
	public Long getId() {
		return id;
	}

	public Account() {
		
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	/*public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}*/

	private String name;
	private double value;
	@JsonProperty("period")
	private String date;
	
	public Account(String name, String date, double value/*, Company company*/) {
		this.name = name;
		this.date = date;
		this.value = value;
		//this.company = company;
	}
	
	public String getName(){
		return name;
	}
	
	public double getValue(){
		return value;
	}
	
	public void setValue(double value) {
		this.value = value;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}