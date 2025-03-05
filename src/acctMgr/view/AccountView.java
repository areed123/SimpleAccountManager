package acctMgr.view;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import acctMgr.controller.AccountController;
import acctMgr.controller.AccountListController;
import acctMgr.model.Model;
import acctMgr.model.ModelEvent;
import acctMgr.model.Account;
import acctMgr.view.JFrameView;

public class AccountView extends JFrameView{
	public static final String AvailF = "Available Funds";
	private String Bal = (((Account)getModel()).getBalance()).toString();
	public static final String Depo = "Deposit";
	public static final String With = "Withdraw";
	public static final String Dis = "Dismiss";
	public JFrame popUp = new JFrame();
	BigDecimal formattedBal = new BigDecimal(0.00);
	NumberFormat format = DecimalFormat.getInstance();
	public JFormattedTextField funds;
	public JFormattedTextField amount;
	public AccountView(Account model, AccountController controller) {
		super(model, controller);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent evt) {
		        dispose();
		        popUp.dispose();
		        super.windowClosing(evt);
		        //model.exit();
		        //System.exit(0);
		    }
		});
		format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        format.setGroupingUsed(false);
        amount = new JFormattedTextField(format);
        funds = new JFormattedTextField(format);
        funds.setColumns(10);
		this.setTitle((((Account)getModel()).getName()).toString()+" "+(((Account)getModel()).getID()).toString());
		Handler handler = new Handler();
		JLabel avail = new JLabel();
		amount.setText("0.00");
		funds.setText(Bal);
		avail.setText(AvailF);
		funds.setEditable(false);
		//this.getContentPane().setLayout(new GridLayout(3,1,5,5));
		JPanel fundPanel = new JPanel();
		fundPanel.add(avail, null);
		fundPanel.add(funds, null);
		this.getContentPane().add(fundPanel, BorderLayout.NORTH);
		this.getContentPane().add(amount, BorderLayout.CENTER);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1,3,3,3));
		JButton depo = new JButton(Depo);
		depo.addActionListener(handler);
		JButton with = new JButton(With);
		with.addActionListener(handler);
		JButton dis = new JButton(Dis);
		dis.addActionListener(handler);
		buttonPanel.add(depo);
		buttonPanel.add(with);
		buttonPanel.add(dis);
		JLabel message = new JLabel("Insufficient Funds!");
		JButton dis2 = new JButton(Dis);
		popUp.setBounds(0, 0, 200, 100);
		popUp.setLocationRelativeTo(null);
		popUp.add(message, BorderLayout.NORTH);
		popUp.add(dis2, BorderLayout.CENTER);
		dis2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				popUp.setVisible(false);
				System.out.println("made invisible");

			}
		});		
		
		this.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
	}
	
	
	@Override
	public void modelChanged(ModelEvent me) {
		// TODO Auto-generated method stub
		formattedBal=me.getBalance();
		formattedBal = formattedBal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		System.out.println(formattedBal);
		funds.setText(formattedBal+"");
	}
	
	
	class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			((AccountController)getController()).operation(e.getActionCommand()); 
	    } }
}
