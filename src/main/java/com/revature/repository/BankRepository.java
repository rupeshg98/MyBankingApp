package com.revature.repository;

import java.util.Hashtable;


import com.revature.model.Account;
import com.revature.model.User;

public interface BankRepository {
	
	// CREATE
	User insertUser(User user); 
	Account insertAccount(Account account);
	void insertUserAccountMap(String username, Integer accountNo);

	// READ
	Account getAccount(int accountNo);
	User getUser(String username);
	Hashtable<Integer, Account> getAllAccounts(String username); 
	Hashtable<Account, String> getAllAccounts();

	
	// UPDATE
	User updateUserStatus(String username, String status);
	Account updateAccount(Account account);
	
	// DELETE
	void deleteAccount(int acountNo);
	
	
	
}
