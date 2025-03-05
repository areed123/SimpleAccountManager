package acctMgr.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;



public class AccountList extends AbstractModel
{
	/**
	 * Path to accounts file.
	 */
	private String accountFile;
	
	/**
	 * Dynamic list of accounts.
	 */
	private List<Account> accountList = new ArrayList<Account>();
	private Map<String, Account> accountMap = new HashMap<String, Account>();
	
	private Account currentAccount;
	
	//private ExecutorService fixedPool;
	
	/**
	 * Dynamic list of children.
	 */
	//public ArrayList<AccountController> children = new ArrayList<AccountController>();
	
	/**
	 * AccountManagerModel constructor.
	 */
	public AccountList () { super(); }
	
	/**
	 * AccountManagerModel constructor. Generates array of accounts from file.
	 * @param filePath - path to accounts file
	 */
	public AccountList (String filePath) throws Exception
	{
		accountFile = filePath;
		load(filePath);
		if(accountList.size() > 0) currentAccount = accountList.get(0);
		else {
			System.out.println("The account file is empty");
			System.exit(1);
		}
		
	}
	public void addAccount(Account account) {
		accountList.add(account);
		accountMap.put(account.getName(), account);
	}
	
	public void removeAccount(String name) {
		Account account = accountMap.get(name);
		accountList.remove(account);
		accountMap.remove(name);
	}
	
	public void load(String filePath) throws Exception {
		int lineNumber = 0;
		try {
			BufferedReader inputFile = new BufferedReader(new FileReader(filePath));
			String temp = "";
			while ((temp = inputFile.readLine()) != null)
			{
				++lineNumber;
				System.out.println(temp);
				String [] tempArray = temp.split("\\s+");
				System.out.println(tempArray[0] + ";");
				System.out.println(tempArray[1] + ";");
				System.out.println(tempArray[2] + ";");
				Account account = new Account(tempArray[0], tempArray[1], new BigDecimal(tempArray[2]));
				
				accountList.add(account);
				accountMap.put(tempArray[0], account);
			}
			inputFile.close();
		} catch (FileNotFoundException e) {
			throw new Exception("Invalid file: input file '" + filePath + "' not found");
		} catch (Exception e) {
			throw new Exception("Invalid file: error reading '" + filePath + "' at line " + lineNumber);
		}
		
	}
	public Account getAccount(String name) {
		return accountMap.get(name);
	}
	public Iterator<Account> accountsIterator() {
		return accountList.iterator();
	}
	/**
	 * Enumerates account list as an array of strings.
	 */
	public String [] listAccounts ()
	{
		String [] accounts = new String[accountList.size()];
		for (int i = 0; i < accountList.size(); ++i) { accounts[i] = accountList.get(i).getName(); }
		return accounts;
	}
	

	
	/**
	 * Save accounts to file.
	 */
	public void save ()
	{
		try {
			PrintWriter outputFile = new PrintWriter(new FileWriter(accountFile));
			Iterator<Account> it = accountList.iterator();
			// for (Account account : accountList) {...}
			// for (i=0; i< ... ; i++) {}
			while (it.hasNext())
			{
				Account temp = (Account)it.next();
				//outputFile.printf("%s\t%d\t%s\n", temp.getName(), temp.getID(), temp.getBalance().toString());
				outputFile.println(temp.getName() + "\t" + temp.getID() + "\t" + temp.getBalance());
			}
			outputFile.close();
		} catch (Exception e) { e.printStackTrace(); }
	}
	
	/**
	 * Save accounts to file and dismiss all windows.
	 */
	public void exit ()
	{
		save(); // save current state 
		System.exit(0);
	}
}