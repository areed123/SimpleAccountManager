package acctMgr.controller;


import acctMgr.model.Account;
import acctMgr.model.Model;
import acctMgr.model.AccountList;

import java.math.BigDecimal;

import acctMgr.controller.AccountController;
import acctMgr.view.AccountListView;


public class AccountListController extends AbstractController{
	AccountController account;
	
	public void operation(String option){
		if(option == AccountListView.Edit){
			System.out.println("edit pressed");
			account = new AccountController(((AccountList)getModel()).getAccount(((AccountListView)getView()).getSelection()), BigDecimal.valueOf(1));
		}else if(option == AccountListView.EditYen){
				//1 YEN IS EQUAL TO ABOUT 3/4 A CENT 1 YEN = 0.0074 DOLLARS
				account = new AccountController(((AccountList)getModel()).getAccount(((AccountListView)getView()).getSelection()), BigDecimal.valueOf(0.0074));
			}else if(option == AccountListView.EditEuro){
				//1 EURO IS EQUAL TO ABOUT 1.10 AMERICAN DOLLAR
				account = new AccountController(((AccountList)getModel()).getAccount(((AccountListView)getView()).getSelection()), BigDecimal.valueOf(1.10));
				}else if(option == AccountListView.Save){
					((AccountList)getModel()).save();
					System.out.println("save pressed");
					}else if(option == AccountListView.Exit){
						System.out.println("Exit pressed");
						((AccountListView)getView()).dispose();			        
						((AccountList)getModel()).exit();
			        	System.exit(0);
					
				}
	
	}
}
