package acctMgr.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import acctMgr.model.Account;
import acctMgr.model.AccountList;

public class AccountListTest {
	
	AccountList accountList;
	Account jackAcct;
	Account janeAcct;
	@Before
	public void setUp() throws Exception {
		accountList = new AccountList();
		jackAcct = new Account("Joe", "474832", new BigDecimal("200.45"));
		janeAcct = new Account("Jane", "748290", new BigDecimal("310.28"));
		accountList.addAccount(jackAcct);
		accountList.addAccount(janeAcct);
	}

	@After
	public void tearDown() throws Exception {
		jackAcct = null;
		janeAcct = null;
		accountList = null;	
	}

	@Test
	public void testAddAccount() {
		Account bobAcct = new Account("Bob", "8626436", new BigDecimal("430.45"));
		accountList.addAccount(bobAcct);
		Account acct = accountList.getAccount("Bob");
		assertEquals(acct.getName(), "Bob");
	}
	@Test
	public void testRemoveAccount() {
		accountList.removeAccount("Joe");
		Account acct = accountList.getAccount("Joe");
		assertNull(acct);
	}
}
