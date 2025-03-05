package acctMgr.model;
import java.math.BigDecimal;
import java.math.RoundingMode;
public class Account extends AbstractModel {
	private BigDecimal balance;
	private String name;
	private String ID;
	public Account(String name, String ID, BigDecimal balance){
		this.name = name;
		this.ID = ID;
		this.balance = balance;
		this.balance.setScale(2, RoundingMode.HALF_UP);
	}
	public BigDecimal getBalance(){return new BigDecimal(balance.toString());} // returns a clone of balance for safety
	public String getName() {return new String(name);}
	public String getID() {return new String(ID);}
	public void deposit(BigDecimal amount) {
		balance = balance.add(amount);
		balance = balance.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.BalanceUpdate, balance);
		notifyChanged(me);
		notifyChanged(me);
		
		
	}
	
	public synchronized void withdraw(BigDecimal amount) throws OverdrawException {
		BigDecimal newB = balance.add(BigDecimal.ZERO);
		newB = newB.subtract(amount);
		if(newB.signum() < 0) throw new OverdrawException(newB);
		balance = newB.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		
		final ModelEvent me = new ModelEvent(ModelEvent.EventKind.BalanceUpdate, balance);
		notifyChanged(me);
		
	}
	
}

