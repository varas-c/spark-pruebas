package model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Company {
	

	private Long id;
	private List<Account> accounts = null;
	

	private String name;

	private int weight = 0;
	
	private int openingYear;
	
	public void increaseWeight() {
		this.weight++;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight=weight;
	}

	public Company(String name, int year) {
		this.name = name;
		this.openingYear = year;
	}
	
	public Company() {
		
	};
	
	public Long getId() {
		return id;
	}
	
	public String getName(){
		return name;
	}
	public void setName(String name) {
		this.name = name;
		}
	
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	public Set<String> getYearsOfAccounts() {
		Set<String> years = new HashSet<String>();
		accounts.stream().map(account -> years.add(account.getDate())).collect(Collectors.toList());
		return years;
	}
	
	public Account getAccountByNameAndYear(String name, String year) {
		return this.accounts.stream().filter(x -> x.getName().equals(name) && x.getDate().equals(year)).findFirst().orElse(null);
	}
	
	public List<Account> getAccountsByYear(String year) {
		return this.accounts.stream().filter( x -> x.getDate().equals(year)).collect(Collectors.toList());
	}
	
	public void addAccount(Account newAccount) {
		
		if(hasAccount(newAccount.getName())) {
			removeAccount(newAccount.getName());
		}
		
		this.accounts.add(newAccount);
	}
	
	private void removeAccount(String name) {
		this.accounts.removeIf(account -> account.getName().equals(name));
	}
	
	public Boolean hasAccount(String accountName) {
		return this.accounts.stream().anyMatch(account -> account.getName().equals(accountName));
	}
	
	public void addAccounts(List<Account> newAccounts) {
		newAccounts.forEach(newAccount -> addAccount(newAccount));
	}
	
	

	public void setOpeningYear(int openingYear) {
		this.openingYear = openingYear;
	}
	public int getOpeningYear() {
		return openingYear;
	}
}