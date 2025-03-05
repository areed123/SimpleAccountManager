package acctMgr.view;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import acctMgr.controller.AccountListController;
import acctMgr.model.AccountList;
import acctMgr.model.Model;
import acctMgr.model.ModelEvent;
import acctMgr.view.JFrameView;

public class AccountListView extends JFrameView{
	public static final String Edit = "edit in $";
	public static final String EditYen = "edit in \u00a5";
	public static final String EditEuro = "edit in \u20ac";
	public static final String Save = "Save";
	public static final String Exit = "Exit";
	public JComboBox<String> accountBox;
	public AccountListView(AccountList model, AccountListController controller) {
		super(model, controller);
		addWindowListener(new java.awt.event.WindowAdapter() {
		    public void windowClosing(java.awt.event.WindowEvent evt) {
		        dispose();
		        super.windowClosing(evt);
		        model.exit();//(AccountList)exit() saves the AccountList
		        System.exit(0);
		    }
		});
		String [] testList = model.listAccounts();
		Handler handler = new Handler();
		accountBox = new JComboBox<String>(testList);
		this.getContentPane().setLayout(new GridLayout(3,1,5,5));
		this.getContentPane().add(accountBox, null);
		JPanel editPanel = new JPanel();
		JButton dollarEdit = new JButton(Edit);
		dollarEdit.addActionListener(handler);
		JButton yenEdit = new JButton(EditYen);
		yenEdit.addActionListener(handler);
		JButton euroEdit = new JButton(EditEuro);
		euroEdit.addActionListener(handler);
		editPanel.setLayout(new GridLayout(1,3,3,3));
		editPanel.add(dollarEdit, null);
		editPanel.add(yenEdit, null);
		editPanel.add(euroEdit, null);
		this.getContentPane().add(editPanel, null);
		JPanel saveExitPanel = new JPanel();
		JButton saveButton = new JButton(Save);
		saveButton.addActionListener(handler);
		JButton exitButton = new JButton(Exit);
		exitButton.addActionListener(handler);
		saveExitPanel.setLayout(new GridLayout(1,2,10,3));
		saveExitPanel.add(saveButton, null);		
		saveExitPanel.add(exitButton, null);
		this.getContentPane().add(saveExitPanel, null);
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
	}
	
	public String getSelection(){
		return(accountBox.getSelectedItem().toString());
	}
	@Override
	public void modelChanged(ModelEvent me) {
		// TODO Auto-generated method stub
		
	}
	
	class Handler implements ActionListener { 
		// Event handling is handled locally
		public void actionPerformed(ActionEvent e) {
			((AccountListController)getController()).operation(e.getActionCommand()); 
	    } }

}
