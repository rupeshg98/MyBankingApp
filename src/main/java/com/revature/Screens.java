package com.revature;


import java.util.InputMismatchException;
import java.util.Scanner;

import com.revature.model.Account;
import com.revature.model.User;


public class Screens {
	Scanner scanner = new Scanner(System.in);
	UserActions userActions = new UserActions();
	User user = new User();

	public Screens() {
		
	}

	public void mainScreen() {
		try {
			boolean exit = false;
			while (!exit) {
				int choice = 0;
				System.out.println("Please Choose an Option:");
				System.out.println("1 to login.");
				System.out.println("2 to sign up as customer.");
				System.out.println("3 to sign up as employee.");
				System.out.println("4 to Exit");
				choice = scanner.nextInt();

				switch (choice) {
				case 1: // Login
					user = userActions.login();
					if (user == null) {
						System.out.println("Incorrect credentials. Please try again.");
						break;
					} else if (user.getRole().equals("Employee") && user.getStatus().equals("Inactive")) {
						System.out.println(
								"Your registration is awaiting further approval at this time. Please check again later.");
						break;
					} else if (user.getRole().equals("Customer")) {
						System.out.println("Welcome " + user.getFirstName() + "!");
						customerScreen();
						break;
					} else {
						String role = user.getRole();
						System.out.println("Welcome " + role + ": " + user.getFirstName() + "!");
						nonCustomerScreen();
						break;
					}
				case 2: // Sign-up as Customer
					User customer = userActions.customerRegister();
					if (customer != null) {
						System.out.println("You have registered " + customer.getFirstName());
					}
					break;
				case 3: // Sign-up as Employee
					User employee = userActions.employeeRegister();
					if (employee != null) {
						System.out.println("You have registered" + employee.getFirstName()
								+ ". Your registration is awaiting further approval at this time.");
					}
					break;
				case 4:
					exit = true;
					System.out.println("Goodbye!");
					break;
				default:
					System.out.println("Please select an option.");
				}
			}
		} catch (InputMismatchException e) {
			System.out.println("You didn't enter a proper choice. Program will restart.");
			Driver.main(null);
		}
	}


	private void customerScreen() {

		boolean showOptions = true;
		while (showOptions) {
			int choice = 0;
			System.out.println("Please Choose an Option:");
			System.out.println("1 to Open Account.");
			System.out.println("2 to View Account Info.");
			System.out.println("3 to Make Transactions.");
			System.out.println("4 to Open Joint Account.");
			System.out.println("5 to Log Out.");

			choice = scanner.nextInt();
			switch (choice) {
			case 1: // Open Account
				Account account = userActions.openAccount();
				if (account == null) {
					System.out.println("We are unable to open your account for now.. Please try again later..");
				} else {
					System.out.println("Your Account " + account.getAccountNo()
							+ " is created and awaiting further approval at this time. "
							+ " Please check again later.");
				}
				break;
			case 2: // View Info for Only Your Accounts
				userActions.viewAllAccounts();
				break;
			case 3: // Transactions
				boolean showTransactionOptions = true;
				while (showTransactionOptions) {
					System.out.println("Please Choose an Option:");
					System.out.println("1 to Withdraw.");
					System.out.println("2 to Deposit.");
					System.out.println("3 to Transfer.");
					System.out.println("4 to Return to Previous Menu.");

					int choice2 = scanner.nextInt();
					switch (choice2) {
					case 1: // Withdraw
						withDraw();
						break;
					case 2:// Deposit
						deposit();
						break;
					case 3:// Transfer
						transferAccounts();
						break;
					case 4: // Return to Previous Menu
						showTransactionOptions = false;
						break;
					default:
						System.out.println("Please select an option.");
					}
				}
				break;
			case 4: // Add Joint Account
				System.out.println("Enter Joint User's Username : ");
				String username = scanner.next();
				if (!userActions.isValidUser(username)) {
					System.out.println("Invalid Username Entered..");
					break;
				}
				Account jointAccount = userActions.OpenJointAccount(username);
				if (jointAccount == null) {
					System.out.println("We are unable to open your account for now.. Please try later..");
				} else {
					System.out.println("Your Account " + jointAccount.getAccountNo()
							+ " is created and awaiting further approval at this time. "
							+ " Please check again later.");
				}
				break;
			case 5: // Log Out
				showOptions = false;
				break;
			default:
				System.out.println("Please select an option.");
				continue;
			}
		}

	}

	private void nonCustomerScreen() {

		boolean showOptions = true;
		while (showOptions) {
			int choice = 0;
			int accountNo = 0;
			Account account = null;
			User checkUser = null;
			if (user.getRole().equals("Employee")) {
				System.out.println("Please Choose an Option:");
				System.out.println("1 to View All Accounts Info.");
				System.out.println("2 to Approve / Deny Accounts.");
				System.out.println("3 to Log Out.");
				choice = scanner.nextInt();
				switch (choice) {
				case 1: // View account info
					userActions.viewAllAccounts();
					break;
				case 2: // Approve Accounts
					approveDenyAccounts();
					break;
				case 3:
					showOptions = false;
					break;
				}
			} else if (user.getRole().equals("Admin")) {
				System.out.println("Please Choose an Option:");
				System.out.println("11 to Change Employee Status.");
				System.out.println("22 to View Account Info.");
				System.out.println("33 to Approve / Deny Bank Accounts.");
				System.out.println("44 to Withdraw from Bank Account");
				System.out.println("55 to Deposit to Bank Account.");
				System.out.println("66 to Transfer between Bank Accounts.");
				System.out.println("77 to Cancel Bank Accounts.");
				System.out.println("88 to Log Out.");
				choice = scanner.nextInt();
				switch (choice) {
				case 11: // Change Employee Status
					System.out.println("Enter Employee's Username: ");
					String employeeUsername = scanner.next();
					checkUser = userActions.getUserInfo(employeeUsername);
					if (checkUser == null || checkUser.getRole().equals("Customer")) {
						System.out.println("Invalid username Entered..");
						break;
					}
					System.out.println("Enter Approve(A) / Deny (D) : ");
					String approveDeny = scanner.next();
					if (approveDeny.equals("A")) {
						if (checkUser.getStatus().equals("Active")) {
							System.out.println("This employee is already Active..");
						} else if (userActions.approveEmployee(checkUser) != null) {
							System.out.println("Employee status changed..");
						}
					} else if (approveDeny.equals("D")) {
						if (checkUser.getStatus().equals("Inactive")) {
							System.out.println("This emplolyee is already Inactive..");
						} else if (userActions.denyEmployee(checkUser) != null) {
							System.out.println("Employee status changed..");
						}
					} else {
						System.out.println("Invalid option entered");
					}
					break;
				case 22: // View account info
					userActions.viewAllAccounts();
					break;
				case 33: // Approve Accounts
					approveDenyAccounts();
					break;
				case 44: // Withdraw
					withDraw();
					break;
				case 55:// Deposit
					deposit();
					break;
				case 66:// Transfer
					transferAccounts();
					break;
				case 77: // Cancel accounts
					System.out.println("Enter Account#: ");
					accountNo = scanner.nextInt();
					account = userActions.getAccount(accountNo);
					if (account == null) {
						System.out.println("Invalid Account Entered..");
						break;
					}
					userActions.deleteAccount(accountNo);
					break;
				case 88:
					showOptions = false;
					break;
				default:
					System.out.println("Please select an option.");
					break;
				}
			} else {
				break;
			}
		}
	}

	void approveDenyAccounts() {
		System.out.println("Enter Account #: ");
		int accountNo = scanner.nextInt();
		Account account = userActions.getAccount(accountNo);
		if (account == null) {
			System.out.println("Invalid Account # Entered..");
		} else  {
			System.out.println("Enter Approve(A) / Deny (D) : ");
			String approveDeny = scanner.next();
			if (approveDeny.equals("A")) {
				if (account.getStatus().equals("Active")) {
					System.out.println("This account is already Approved.");
					return;
				}
				userActions.approveAccount(account);
				System.out.println("Account is Approved");
			} else if (approveDeny.equals("D")) {
				if (account.getStatus().equals("Inactive")) {
					System.out.println("This account is already Denied.");
					return;
				}
				userActions.denyAccount(account);
				System.out.println("Account is Denied");
			}
		}
		
	}

	private void withDraw() {
		System.out.println("Enter Account#: ");
		int accountNumber = scanner.nextInt();
		Account account = null;

		if (user.getRole().equals("Customer")) {
			account = user.getAccount(accountNumber);
		} else {
			account = userActions.getAccount(accountNumber);
		}
		if (account == null) {
			System.out.println("Invalid Account Entered..");
			return;
		} else if (account.getStatus().equals("Inactive")) {
			System.out.println("Account is not Activated.. you can't Withdraw from this account.");
			return;
		}
		System.out.println("Enter Amount.");
		double withDrawAmt = scanner.nextDouble();
		if (withDrawAmt > account.getBalance()) {
			System.out.println("WithDraw Amount larger than Balance. ");
			return;
		} else if (withDrawAmt <= 0) {
			System.out.println("WithDraw Amount Should be > 0. ");
			return;
		}

		userActions.WithDraw(account, withDrawAmt);

	}

	private void deposit() {
		System.out.println("Enter Account#: ");
		int accountNumber = scanner.nextInt();
		Account account = null;

		if (user.getRole().equals("Customer")) {
			account = user.getAccount(accountNumber);
		} else {
			account = userActions.getAccount(accountNumber);
		}
		if (account == null) {
			System.out.println("Invalid Account Entered..");
			return;
		} else if (account.getStatus().equals("Inactive")) {
			System.out.println("Account is not Activated.. you can't Deposit to this account.");
			return;
		}
		System.out.println("Enter Amount.");
		double depositAmt = scanner.nextDouble();
		if (depositAmt <= 0) {
			System.out.println("Deposit Amount Should be > 0. ");
			return;
		}
		userActions.Deposit(account, depositAmt);

	}

	private void transferAccounts() {
		System.out.println("Enter From Account#: ");
		int fromAccountNumber = scanner.nextInt();
		System.out.println("Enter To Account#: ");
		int toAccountNumber = scanner.nextInt();
		Account from_account = null;
		if (user.getRole().equals("Customer")) {
			from_account = user.getAccount(fromAccountNumber);
		} else {
			from_account = userActions.getAccount(fromAccountNumber);
		}
		if (from_account == null) {
			System.out.println("Invalid From Account Entered..");
			return;
		} else if (from_account.getStatus().equals("Inactive")) {
			System.out.println("From Account is not Activated.. you can't Transfer from this account.");
			return;
		}
		Account to_account = null;
		if (user.getRole().equals("Customer")) {
			to_account = user.getAccount(toAccountNumber);
		} else {
			to_account = userActions.getAccount(toAccountNumber);
		}
		if (to_account == null) {
			System.out.println("Invalid To Account Entered..");
			return;
		} else if (to_account.getStatus().equals("Inactive")) {
			System.out.println("To Account is not Activated.. you can't Transfer to this account.");
			return;
		}
		if (fromAccountNumber == toAccountNumber) {
			System.out.println("From & To Accounts can't be same");
			return;
		}
		System.out.println("Enter Amount: ");
		double transferAmt = scanner.nextDouble();
		double fromAccountBalance = from_account.getBalance();
		if (transferAmt > fromAccountBalance) {
			System.out.println("Transfer Amount larger than From Account Balance. " + fromAccountBalance);
			return;
		} else if (transferAmt <= 0) {
			System.out.println("WithDraw Amount Should be > 0. ");
			return;
		}

		userActions.Transfer(from_account, to_account, transferAmt);
	}

}
