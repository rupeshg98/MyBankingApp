package com.revature.model;

import java.util.Hashtable;


public class User {
	private String username; 
	private String password;
	private String firstName;
	private String lastName;
	private String status; // Active or Inactive
	private String role; // Customer, Employee, or Admin
	private Hashtable<Integer, Account> accountsList;

	public User() {
		super();
	}

	public User(String username, String password, String firstName, String lastName, String status, String role,
			Hashtable<Integer, Account> accountsList) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.status = status;
		this.role = role;
		this.accountsList = accountsList;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public double getBalance(int accountNumber) {
		Account account = accountsList.get(accountNumber);
		return account.getBalance();
	}

	public void addAccount(Account account) {
		accountsList.put(account.getAccountNo(), account);
	}

	
	public Account getAccount(int accountNumber) {
		if (accountsList.containsKey(accountNumber)) {
			return accountsList.get(accountNumber);
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", firstName=" + firstName + ", lastName="
				+ lastName + ", status=" + status + ", role=" + role + "]";
	}

}
