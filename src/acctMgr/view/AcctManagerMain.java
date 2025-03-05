package acctMgr.view;

import acctMgr.controller.AccountListController;
import acctMgr.model.AccountList;

import acctMgr.model.Account;

import java.math.BigDecimal;

import acctMgr.controller.AccountController;

public class AcctManagerMain {

	public static void main(String[] args) {
		if(args.length == 1) {
			AccountList accountList = null;
			try {
				accountList = new AccountList(args[0]);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
			AccountListController controller = new AccountListController();
			controller.setModel(accountList);
			AccountListView listView = new AccountListView(accountList, controller);
			controller.setView(listView);;
			listView.setVisible(true);
		}
		else {
			System.out.println("No given filepath");
			System.exit(0);
		}
	}

}
