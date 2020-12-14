package com.revature.repository;

import java.sql.Connection; 
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;


import com.revature.model.Account;
import com.revature.model.User;
import com.revature.utility.ConnectionFactoryUtil;

public class BankRepositoryImpl implements BankRepository {

	private Connection getConnection() {
		Connection conn = null;
		try {
			conn = ConnectionFactoryUtil.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			conn = null;
		}
		return conn;
	}

	public User getUser(String username) {
		PreparedStatement stmt = null;
		final String SQL = "select * from users where username = ?";
		Connection conn = getConnection();
		User user = null;
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, username);
				ResultSet results = stmt.executeQuery();
				while (results != null && results.next()) {
					String pwd = results.getString(2);
					String firstName = results.getString(3);
					String lastName = results.getString(4);
					String status = results.getString(5);
					String role = results.getString(6);
					Hashtable<Integer, Account> accounts = getAllAccounts(username);
					user = new User(username, pwd, firstName, lastName, status, role, accounts);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}



	public User insertUser(User user) {

		PreparedStatement stmt = null;
		final String SQL = "insert into users values(?,?,?,?,?,?)";
		Connection conn = getConnection();
		User insertedUser = null;
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, user.getUsername());
				stmt.setString(2, user.getPassword());
				stmt.setString(3, user.getFirstName());
				stmt.setString(4, user.getLastName());
				stmt.setString(5, user.getStatus());
				stmt.setString(6, user.getRole());
				stmt.execute();
				insertedUser = user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return insertedUser;
	}

	public User updateUserStatus(String username, String status) {

		PreparedStatement stmt = null;
		final String SQL = "update users set status = ? where username = ?";
		Connection conn = getConnection();
		User user = null;
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, status);
				stmt.setString(2, username);
				stmt.execute();
				user = getUser(username);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return user;
	}

	// @Override
	public Hashtable<Integer, Account> getAllAccounts(String username) {
		PreparedStatement stmt = null;
		final String SQL = "select acct.account_No, acct.balance, acct.status from UserAccountsMap map, Accounts acct where map.username = ? and map.account_No = acct.account_No order by acct.status asc";
		Connection conn = getConnection();
		Hashtable<Integer, Account> accounts = new Hashtable<Integer, Account>();
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, username);
				ResultSet results = stmt.executeQuery();
				while (results != null && results.next()) {
					Integer accountNo = results.getInt(1);
					Double balance = results.getDouble(2);
					String status = results.getString(3);
					Account account = new Account();
					account.setAccountNo(accountNo);
					account.setBalance(balance);
					account.setStatus(status);
					accounts.put(accountNo, account);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return accounts;
	}

	public Hashtable<Account, String> getAllAccounts() {
		PreparedStatement stmt = null;
		final String SQL = "select acct.account_No, acct.balance, acct.status, map.username from UserAccountsMap map, accounts acct where map.account_No = acct.account_no order by map.username asc";

		Connection conn = getConnection();
		Hashtable<Account, String> userAccounts = new Hashtable<Account, String>();
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				ResultSet results = stmt.executeQuery();
				while (results != null && results.next()) {
					Account account = new Account();
					int accountNo = results.getInt(1);
					Double balance = results.getDouble(2);
					String status = results.getString(3);
					String username = results.getString(4);
					account.setAccountNo(accountNo);
					account.setBalance(balance);
					account.setStatus(status);
					userAccounts.put(account, username);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userAccounts;
	}

	// @Override
	public void insertUserAccountMap(String username, Integer accountNo) {
		PreparedStatement stmt = null;
		final String SQL = "insert into UserAccountsMap values(?,?)";
		Connection conn = getConnection();

		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				stmt.setString(1, username);
				stmt.setInt(2, accountNo);
				stmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return;
	}

	// @Override
	public Account getAccount(int accountNo) {
		PreparedStatement stmt = null;
		final String SQL = "select * from Accounts where account_no = ?";
		Connection conn = getConnection();
		Account account = null;
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				stmt.setInt(1, accountNo);
				ResultSet results = stmt.executeQuery();
				while (results != null && results.next()) {
					Integer account_No = results.getInt(1);
					Double balance = results.getDouble(2);
					String status = results.getString(3);
					account = new Account();
					account.setAccountNo(account_No);
					account.setBalance(balance);
					account.setStatus(status);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return account;

	}

	public Account insertAccount(Account account) {
		PreparedStatement stmt = null;
		final String SQL = "insert into Accounts values(?,?,?)";
		Connection conn = getConnection();
		Account insertedAccount = null;
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				stmt.setInt(1, account.getAccountNo());
				stmt.setDouble(2, account.getBalance());
				stmt.setString(3, account.getStatus());
				stmt.execute();
				insertedAccount = account;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return insertedAccount;
	}



	public Account updateAccount(Account account) {
		
		PreparedStatement stmt = null;
		final String SQL = "update Accounts set balance = ?, status = ? where account_no = ?";
		Connection conn = getConnection();
		Account updatedAccount = null;
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				stmt.setDouble(1, account.getBalance());
				stmt.setString(2, account.getStatus());
				stmt.setInt(3, account.getAccountNo());
				stmt.execute();
				updatedAccount = getAccount(account.getAccountNo());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return updatedAccount;
	}

	public void deleteAccount(int accountNo) {
		PreparedStatement stmt = null;
		final String SQL = "delete from Accounts where account_no = ?";
		Connection conn = getConnection();
		try {
			if (conn != null) {
				stmt = conn.prepareStatement(SQL);
				stmt.setInt(1, accountNo);
				stmt.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return;
	}

}
