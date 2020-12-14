package com.revature;

import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.service.BankService;

public class UserActions {
	private User user;
	Scanner scanner = new Scanner(System.in);
	BankService bank = new BankService();

	public UserActions() {
		
	}

	// Login
	public User login() {
		System.out.println("Please enter your username: ");
		String username = scanner.nextLine();
		System.out.println("Please enter your password: ");
		String password = scanner.nextLine();
		user = bank.validateUser(username, password);
		return user;
	}

	// Sign-up as customer
	public User customerRegister() {
		System.out.println("Please enter a FirstName: ");
		String firstName = scanner.nextLine();
		System.out.println("Please enter a LastName: ");
		String lastName = scanner.nextLine();
		System.out.println("Please enter a username: ");
		String username = scanner.nextLine();
		User checkUser = bank.getUser(username);
		if (checkUser != null) {
			System.out.println("Username Already Exists");
			return null;
		}
		System.out.println("Please enter a password:");
		String password = scanner.nextLine();
		user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setPassword(password);
		user.setRole("Customer");
		bank.insertUser(user);
		System.out.println(user);
		return user;
	}

	// Sign-up as employee
	public User employeeRegister() {
		System.out.println("Please enter a FirstName: ");
		String firstName = scanner.nextLine();
		System.out.println("Please enter a LastName: ");
		String lastName = scanner.nextLine();
		System.out.println("Please enter a username: ");
		String username = scanner.nextLine();
		User checkUser = bank.getUser(username);
		if (checkUser != null) {
			System.out.println("Username Already Exists");
			return null;
		}
		System.out.println("Please enter a password:");
		user = new User();
		String password = scanner.nextLine();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setPassword(password);
		user.setRole("Employee");
		bank.insertUser(user);
		System.out.println(user);
		return user;
	}

	// Open account
	public Account openAccount() {
		Account insertedAccount = null;
		if (user.getRole().equals("Customer")) {
			insertedAccount = bank.makeAccount(user.getUsername());
			if (insertedAccount != null) {
				user.addAccount(insertedAccount);
			}
		}
		return insertedAccount;
	}

	/*
	 * 1) View accounts for specific user 2) View accounts for all users
	 */
	public void viewAllAccounts() {
		Hashtable<Integer, Account> userAccounts = new Hashtable<Integer, Account>();
		Hashtable<Account, String> allAccounts = new Hashtable<Account, String>();
		if (user.getRole().equals("Customer")) {
			userAccounts = bank.getAllAccounts(user.getUsername());
			if (userAccounts != null && userAccounts.size() > 0) {
				userAccounts.forEach( 
			            (k, v) -> System.out.println( k+ " " + v)); 
			} else {
				System.out.println("No Accounts to View");
			}
		} else {
			allAccounts = bank.getAllAccounts();
			if (allAccounts != null && allAccounts.size() > 0) {
				allAccounts.forEach( 
			            (k, v) -> System.out.println(k+ " " + v)); 
			} else {
				System.out.println("No Accounts to View");
			}
		}

		return;
	}

	public boolean isValidAcount(int accountNo) {
		Account account = bank.getAccount(accountNo);
		if (account != null && account.getStatus().equals("Active")) {
			return true;
		}
		return false;
	}

	// Get account number
	public Account getAccount(int accountNo) {
		Account account = null;
		if (user.getRole().equals("Customer")) {
			account = user.getAccount(accountNo);
		} else {
			account = bank.getAccount(accountNo);
		}
		return account;
	}

	// Approve bank account
	public void approveAccount(Account account) {
		if (user.getRole().equals("Customer") || user.getStatus().equals("Inactive")) {
			System.out.println("You do not have access to approve account");
			return;
		}
		bank.approveAccount(account);
	}

	// Deny bank account
	public void denyAccount(Account account) {
		if (user.getRole().equals("Customer") || user.getStatus().equals("Inactive")) {
			System.out.println("You do not have access to Deny account");
			return;
		}
		bank.denyAccount(account);

	}

	// Get login id
	public User getUserInfo(String username) {
		// customer can not call this method
		if (user.getRole().equals("Customer")) {
			return null;
		}
		return bank.getUser(username);
	}

	// Approve employee
	public User approveEmployee(User employee) {
		if (!user.getRole().equals("Admin")) {
			System.out.println("You do not have access to approve Employee");
			return null;
		}
		return bank.approveEmployee(employee);

	}

	// Deny employee
	public User denyEmployee(User employee) {
		if (!user.getRole().equals("Admin")) {
			System.out.println("You do not have access to Deny Employee");
			return null;
		}
		return bank.denyEmployee(employee);
	}

	// Withdraw, deposit, transfer for accounts
	public void WithDraw(Account account, double amount) {
		bank.WithDraw(account, amount);
		return;
	}

	public void Deposit(Account account, double amount) {
		bank.Deposit(account, amount);
		return;
	}

	public void Transfer(Account from_account, Account to_account, double amount) {
		bank.Transfer(from_account, to_account, amount);
		return;
	}

	// Cancel open account
	public void deleteAccount(int accountNo) {
		if (!user.getRole().equals("Admin")) {
			System.out.println("You do not have access to Cancel Account");
			return;
		}
		bank.deleteAccount(accountNo);
	}

	public boolean isValidUser(String login_id) {
		User validUser = bank.getUser(login_id);

		if (validUser == null) {
			return false;
		}

		if (user.getRole().equals("Customer")) {
			if (!validUser.getRole().equals("Customer")) {
				return false;
			}
		}

		return true;
	}

	public Account OpenJointAccount(String login_id) {
		Account insertedAccount = openAccount();
		if (insertedAccount != null) {
			bank.insertUserAccountMap(login_id, insertedAccount.getAccountNo());
		}
		return insertedAccount;
	}

}
