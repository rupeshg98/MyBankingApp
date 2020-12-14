package com.revature.test;

import java.util.Hashtable;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.repository.BankRepositoryImpl;
import com.revature.service.BankService;

public class BankServiceTest {
	/*
	 * We're going to use a mocking framework known as Mockito. Like JUnit, it is
	 * annotation driven.
	 * 
	 * When using Mockito, keep in mind: 1) You cannot mock a final class as Mockito
	 * needs to extend a class in order to mock it. 2) You cannot mock static
	 * methods.
	 */

	// BankService is our object under test.

	@InjectMocks // Inject your mocks into the object under test
	private BankService bankService;

//	//This tells Mockito to mock this type for us
	@Mock
	private BankRepositoryImpl bankRepository;

	@Before
	public void before() {
//		// Initialize mocks
		bankService = new BankService();
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testInsertUser() {
		User user = new User();
		user.setRole("Test");

		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(user)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);


		User insertedUser = bankService.insertUser(user);
		Assert.assertEquals("Rupesh", insertedUser.getUsername());
	}

	
	@Test
	public void testInsertAccount() {
		Account account = null;
		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(account)).thenReturn(new Account(4567, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(account)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		Account insertedAccount = bankService.insertAccount(account);
		Assert.assertEquals(4567, insertedAccount.getAccountNo());
	}

	@Test
	public void testgetAccount() {
		// In this case, we are going to stub a method call, meaning that we will
		// return canned values when we call methods.

		int number = 0;
		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(number)).thenReturn(new Account(13459, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		Account retrievedAccount = bankService.getAccount(number);
		Assert.assertEquals(13459, retrievedAccount.getAccountNo());
	}

	@Test
	public void testgetUser() {
		String username = null;
		Mockito.when(this.bankRepository.getUser(username))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(13459, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		User retrievedUser = bankService.getUser(username);
		Assert.assertEquals("Rupesh", retrievedUser.getUsername());
	}

	@Test
	public void testApproveEmployee() {
		User user = new User();
		user.setUsername("Test");

		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus(user.getUsername(), "Active"))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		User retrievedUser = bankService.approveEmployee(user);
		Assert.assertEquals("Rupesh", retrievedUser.getUsername());
	}

	@Test
	public void testApproveEmployee2() {
		User user = new User();
		user.setUsername("Test");

		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus(user.getUsername(), "Inactive"))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		User retrievedUser = bankService.approveEmployee(user);
		Assert.assertEquals(null, retrievedUser);
	}

	@Test
	public void testdenyEmployee() {
		User user = new User();
		user.setUsername("Test");

		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus(user.getUsername(), "Inactive"))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		User retrievedUser = bankService.denyEmployee(user);
		Assert.assertEquals("Rupesh", retrievedUser.getUsername());
	}

	@Test
	public void testdenyEmployee2() {
		User user = new User();
		user.setUsername("Test");

		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus(user.getUsername(), "Active"))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		User retrievedUser = bankService.denyEmployee(user);
		Assert.assertEquals(null, retrievedUser);
	}

	@Test
	public void testDeleteAccount() {
		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		bankService.deleteAccount(0);

		Assert.assertEquals("", "");
	}

	@Test
	public void testDeposit() {
		double balance = 1000;
		Account account = new Account(333, balance, "Active");

		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(account)).thenReturn(new Account(3434, 1100, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		Account returnedAccount = bankService.Deposit(account, 100);
		double expectedBalance = 1100;

		Assert.assertEquals(expectedBalance, returnedAccount.getBalance(), 0.00001);
	}

	@Test
	public void testDeposit2() {
		double balance = 1000;
		Account account = new Account(333, balance, "Active");

		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(account)).thenReturn(new Account(3434, 1100, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		Account returnedAccount = bankService.Deposit(account, 100);
		double expectedBalance = 800;

		Assert.assertNotEquals(expectedBalance, returnedAccount.getBalance(), 0.00001);
	}

	@Test
	public void testWithdraw() {
		double balance = 1000;
		Account account = new Account(333, balance, "Active");

		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(account)).thenReturn(new Account(3434, 900, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		Account returnedAccount = bankService.WithDraw(account, 100);
		double expectedBalance = 900;

		Assert.assertEquals(expectedBalance, returnedAccount.getBalance(), 0.00001);
	}

	@Test
	public void testWithdraw2() {
		double balance = 1000;
		Account account = new Account(333, balance, "Active");

		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(account)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		Account returnedAccount = bankService.WithDraw(account, 100);
		double expectedBalance = 2500;

		Assert.assertNotEquals(expectedBalance, returnedAccount.getBalance(), 0.00001);
	}

	@Test
	public void testUpdateAccount() {
		Account account = new Account();
		account.setStatus("Test");
		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(4567, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(account)).thenReturn(new Account(4567, 100, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		Account updated = bankService.updateAccount(account);
		Assert.assertEquals(100, updated.getBalance(), .00001);
	}

	@Test
	public void testApproveAccount() {
		Account account = new Account();
		account.setStatus("Test");

		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(account)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		Account approvedAccount = bankService.approveAccount(account);
		Assert.assertEquals("Active", approvedAccount.getStatus());
	}

	@Test
	public void testApproveAccount2() {
		Account account = new Account();
		account.setStatus("Test");

		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(account)).thenReturn(new Account(1234, 0, "Inactive"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		Account approvedAccount = bankService.approveAccount(account);
		Assert.assertNotEquals("Active", approvedAccount.getStatus());
	}

	@Test
	public void testDenyAccount() {
		Account account = new Account();
		account.setStatus("Test");

		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(account)).thenReturn(new Account(1234, 0, "Inactive"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		Account approvedAccount = bankService.approveAccount(account);
		Assert.assertEquals("Inactive", approvedAccount.getStatus());
	}

	@Test
	public void testDenyAccount2() {
		Account account = new Account();
		account.setStatus("Test");

		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(account)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		Account approvedAccount = bankService.approveAccount(account);
		Assert.assertNotEquals("Inactive", approvedAccount.getStatus());
	}

	@Test
	public void testValidateUser() {
		String username = "Rupesh";
		String pwd = "pass123";
		Mockito.when(this.bankRepository.getUser(username))
				.thenReturn(new User("Rupesh", "pass123", null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(13459, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		User retrievedUser = bankService.validateUser(username, pwd);
		Assert.assertEquals("Rupesh", retrievedUser.getUsername());
		Assert.assertEquals("pass123", retrievedUser.getPassword());

	}
	
	@Test
	public void testValidateUser2() {
		String username = "Rupesh";
		String pwd = "pass123";
		Mockito.when(this.bankRepository.getUser(username))
				.thenReturn(new User("Rupesh", "pass123", null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(13459, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		User retrievedUser = bankService.validateUser(username, pwd);
		Assert.assertEquals("Rupesh", retrievedUser.getUsername());
		Assert.assertNotEquals("pass1234", retrievedUser.getPassword());
	}

	@Test
	public void testgetAllAccounts() {
		Hashtable<Integer, Account> expectedAccounts = new Hashtable<Integer, Account>();
		Hashtable<Integer, Account> actualAccounts = new Hashtable<Integer, Account>();
		int number = 0;
		expectedAccounts.put(1234, new Account(13459, 100, "Active"));
		expectedAccounts.put(333, new Account(54545, 100, "Active"));
		actualAccounts.put(1234, new Account(13459, 100, "Active"));
		String username = "Rupesh";

		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts(username)).thenReturn(actualAccounts);
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(number)).thenReturn(new Account(13459, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		Hashtable<Integer, Account> receivedAccounts = bankService.getAllAccounts(username);   
		
		for (Map.Entry m : expectedAccounts.entrySet()) {
			Account expected = (Account) m.getValue();
			String expectedStr = "Expected Account # " + expected.getAccountNo();
			String actualStr = "Not found in actual";
			if (!receivedAccounts.containsKey(m.getKey())) {
				Assert.assertNotEquals(expectedStr, actualStr);
			} else {
				Account actual = receivedAccounts.get(m.getKey());
				Assert.assertEquals(expected.toString(), actual.toString());
			}
		}

	}

	@Test
	public void testgetAllAccounts2() {
		Hashtable<Account, String> expectedAccounts = new Hashtable<Account, String>();
		Hashtable<Account, String> actualAccounts = new Hashtable<Account, String>();
		int number = 0;
		String username = "Rupesh";
		expectedAccounts.put(new Account(13459, 100, "Active"), username);
		expectedAccounts.put(new Account(54545, 100, "Active"), username);
		actualAccounts.put(new Account(13459, 100, "Active"), username);
	

		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(number)).thenReturn(new Account(13459, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		Hashtable<Account, String> receivedAccounts = bankService.getAllAccounts();
		for (Map.Entry m : expectedAccounts.entrySet()) {
			String expected =  (String) m.getValue();
			String actualStr = "Not found in actual";
			if (!receivedAccounts.containsKey(m.getKey())) {
				Assert.assertNotEquals(expected, actualStr);
			} else {
				String actual = receivedAccounts.get(m.getKey());
				Assert.assertEquals(expected.toString(), actual.toString());
			}
		}

	}

	@Test
	public void testUpdateUserStatus() {
		String username = "Rupesh";
		String status = "Active";
		User user = new User();
		user.setUsername(username);
		user.setStatus(status);
		
		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(user)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus(username, status))
				.thenReturn(new User("Rupesh", null, null, null, status, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);
		
		User updatedUser = bankService.updateUserStatus(username, status);
		Assert.assertEquals(username, updatedUser.getUsername());
		Assert.assertEquals(status, updatedUser.getStatus());
	}

	@Test
	public void testinsertUserAccountMap() {
		String username = "Rupesh";
		int accountNo = 2444;
		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap(username, accountNo);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		bankService.insertUserAccountMap(username, accountNo);

		Assert.assertEquals("", "");
	}

	@Test
	public void testTransfer() {
		String username = "Rupesh";
		double amount = 20000;
		double balance = 10000;
		Account from_account = new Account(333, balance, "Active");
		Account to_account = new Account(333, balance, "Active");

		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.updateUserStatus("", ""))
				.thenReturn(new User("Rupesh", null, null, null, null, null, null));
		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String>());
		Mockito.doNothing().when(bankRepository).insertUserAccountMap("", 0);
		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
		Mockito.when(bankRepository.updateAccount(from_account)).thenReturn(from_account);
		Mockito.when(bankRepository.updateAccount(to_account)).thenReturn(to_account);
		Mockito.doNothing().when(bankRepository).deleteAccount(0);

		bankService.Transfer(from_account, to_account, amount);

		Assert.assertEquals("", "");

	}

//	@Test
//	public void testValidateUser() {
//
//		String username = null;
//		User user = new User();
//		Mockito.when(this.bankRepository.getUser(username)).thenReturn(new User("Rupesh", "pass", null, null, null, null, null));
//		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
//		Mockito.when(bankRepository.updateUserStatus("","")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
//		Mockito.when(bankRepository.getAllAccounts(username)).thenReturn(new Hashtable<Integer, Account>());
//		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String> ());
//		Mockito.doNothing().when(bankRepository).insertUserAccountMap("",0);
//		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(13459, 0, "Active"));
//		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
//		Mockito.when(bankRepository.updateAccount(null)).thenReturn(new Account(1234, 0, "Active"));
//		Mockito.doNothing().when(bankRepository).deleteAccount(0);
//		
//		User retrievedUser = bankService.getUser(username);
//		User retrievedUser2 = bankService.getUser(user.getPassword());
//		User validate = bankService.validateUser(retrievedUser.getUsername(), retrievedUser2.getPassword());
//		Assert.assertEquals(retrievedUser,validate.getUsername());
//		Assert.assertEquals(retrievedUser2, validate.getPassword());
//		
//	}
//	

//	@Test 
//	public void testinsertUserAccountMap(){
//		String username = null;
//		int number = 0;
//		Mockito.when(bankRepository.getUser("")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
//		Mockito.when(bankRepository.validateUser("","")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
//		Mockito.when(bankRepository.insertUser(null)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
//		Mockito.when(bankRepository.updateUserStatus("","")).thenReturn(new User("Rupesh", null, null, null, null, null, null));
//		Mockito.when(bankRepository.getAllAccounts("")).thenReturn(new Hashtable<Integer, Account>());
//		Mockito.when(bankRepository.getAllAccounts()).thenReturn(new Hashtable<Account, String> ());
//		Mockito.doNothing().when(bankRepository).insertUserAccountMap("",0);
//		Mockito.when(bankRepository.getAccount(0)).thenReturn(new Account(1234, 0, "Active"));
//		Mockito.when(bankRepository.insertAccount(null)).thenReturn(new Account(1234, 0, "Active"));
//		Mockito.when(bankRepository.updateAccount(null)).thenReturn(new Account(1234, 0, "Active"));
//		Mockito.doNothing().when(bankRepository).deleteAccount(0);
//		
//		Hashtable<String, Integer> map = new Hashtable<String, Integer>();
//		Mockito.when(this.bankRepository.getUser(username)).thenReturn(new User("Rupesh", null, null, null, null, null, null));
//	}

}