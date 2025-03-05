package acctMgr.controller;

import acctMgr.view.AccountView;
import acctMgr.model.Account;
import acctMgr.view.AccountListView;
import acctMgr.view.JFrameView;

import java.math.BigDecimal;

import acctMgr.model.Account;
import acctMgr.model.Model;
import acctMgr.model.AccountList;

public class AccountController extends AbstractController{
	private BigDecimal Conversion;
	public AccountController(Account opAcct, BigDecimal conv){
		setModel(opAcct);
		Conversion = conv;
		setView(new AccountView((Account)getModel(), this));
		((JFrameView)getView()).setVisible(true);
	}
	public void operation(String option){
		if(option == AccountView.Depo){
			System.out.println("Deposit pressed");
			if(new BigDecimal(((AccountView)getView()).amount.getText()).floatValue() > 0.0)
				((Account)getModel()).deposit(new BigDecimal(((AccountView)getView()).amount.getText()).multiply(Conversion));
		}else if(option == AccountView.With){
				System.out.println("Withdraw pressed");
				if(new BigDecimal(((AccountView)getView()).amount.getText()).floatValue() > 0.0)
				{
					try {
						((Account)getModel()).withdraw(new BigDecimal(((AccountView)getView()).amount.getText()).multiply(Conversion));
					}
					catch(Exception e)
					{
						((AccountView)getView()).popUp.setVisible(true);
					}
				}
			}else if(option == AccountView.Dis){
					System.out.println("Dismiss pressed");
					((AccountView)getView()).setVisible(false);
					((AccountView)getView()).dispose();
				}
	
	}
}
