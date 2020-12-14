package com.revature.service;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

import com.revature.model.Account;


/*
 * This is our service layer. It is designed to perform the "business logic" of an application. 
 * For example, if you want to sanitize data or mutate it in some way for presentation purposes, you 
 * perform those tasks here. Tasks related to computations, and security, for instance, might also be present here. 
 * 
 * Typically, you will move data through your service layer once you've retrieved it from the
 * data source and transform that data here if needed. 
 */

import com.revature.model.User;
import com.revature.repository.BankRepositoryImpl;

public class BankService  {

	/*
	 * We often refer to fields such as this one as "dependencies" because the
	 * implementation of the BankService depends on the implementation of
	 * BankRepository That is to say, if i were to change the implementation of the
	 * BankRepository I would have to possibly modify the BankService.
	 * 
	 * These two classes are tightly coupled.
	 */
	
	private BankRepositoryImpl bankRepository;
	public BankService() {
		bankRepository = new BankRepositoryImpl();
	}

	public Account getAccount(int accountNo) {
		return bankRepository.getAccount(accountNo);
	}


	public Account insertAccount(Account account) {
		return bankRepository.insertAccount(account);
	}

	public Account makeAccount(String username) {
		Random rand = new Random();
		int accountNo = 0;
		while(true) {
			accountNo = rand.nextInt(((9999-100) + 1) + 10);
			Account account = bankRepository.getAccount(accountNo);
			if (account == null) {
				break;
			}
		}
		Account account = new Account();
		account.setAccountNo(accountNo);
		account.setBalance(0);
		account.setStatus("Inactive");
		Account madeAccount = bankRepository.insertAccount(account);
		if (madeAccount != null) {
			bankRepository.insertUserAccountMap(username, accountNo);
		}
		return madeAccount;
	}


	public void insertUserAccountMap(String username, Integer accountNo) {
		bankRepository.insertUserAccountMap(username, accountNo);
	}


	public User getUser(String username) {
		return bankRepository.getUser(username);
	}

	public User updateUserStatus(String username, String status) {
		return bankRepository.updateUserStatus(username, status);
	}

	public Hashtable<Integer, Account> getAllAccounts(String username) {
		return bankRepository.getAllAccounts(username);
	}
	
	public Hashtable<Account, String> getAllAccounts() {
		return bankRepository.getAllAccounts();
	}

	public User insertUser(User user) {
		if (user.getRole().equals("Employee")) {
			user.setStatus("Inactive");
		} else if (user.getRole().equals("Customer")) {
			user.setStatus("Active");
		}
		
		User insertedUser = bankRepository.insertUser(user);
		return insertedUser;
	}

	public User validateUser(String username, String pwd) {
		User user = null;
		try {
			User validateUser = getUser(username);
			if (validateUser != null) {
				if (pwd.equals(validateUser.getPassword())) {
					user = validateUser;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public Account updateAccount(Account account) {

		return bankRepository.updateAccount(account);
	}

	public Account WithDraw(Account account, double amount) {
		account.setBalance(account.getBalance() - amount);
		Account updatedAccount = bankRepository.updateAccount(account);
		return updatedAccount;
	}
	
	public Account Deposit(Account account, double amount) {
		account.setBalance(account.getBalance() + amount);
		Account updatedAccount = bankRepository.updateAccount(account);
		return updatedAccount;	
	}
	
	public void Transfer(Account from_account, Account to_account, double amount) {
		from_account.setBalance(from_account.getBalance() - amount);
		to_account.setBalance(to_account.getBalance() + amount);
		bankRepository.updateAccount(from_account);
		bankRepository.updateAccount(to_account);
		return;
	}

	public Account approveAccount(Account account) {
		account.setStatus("Active");
		return bankRepository.updateAccount(account);
	}
	
	public Account denyAccount(Account account) {
		account.setStatus("Inactive");
		return bankRepository.updateAccount(account);
	}

	public User approveEmployee(User user) {
		return bankRepository.updateUserStatus(user.getUsername(), "Active");
	}

	public User denyEmployee(User user) {
		return bankRepository.updateUserStatus(user.getUsername(), "Inactive");
	}

	public void deleteAccount(int accountNo) {
		bankRepository.deleteAccount(accountNo);
	}
	
}
