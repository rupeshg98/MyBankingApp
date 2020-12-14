package com.revature.model;

public class Account {
	private int accountNo;
	private double balance;
	private String status; // Active or Inactive 

	public Account() {
		super();
	}

	public Account(int accountNo, double balance, String status) {
		super();
		this.accountNo = accountNo;
		this.balance = balance;
		this.status = status;
	}

	public int getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(int accountNo) {
		this.accountNo = accountNo;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", balance=" + balance + ", status=" + status + "]";
	}

}
