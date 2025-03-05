package acctMgr.model;
import java.math.BigDecimal;
public class ModelEvent {
	public enum EventKind {
		BalanceUpdate, AmountTransferredUpdate
	}
	private EventKind kind;
	private BigDecimal balance;
	public ModelEvent(EventKind kind, BigDecimal balance){
		this.balance = balance;
		this.kind = kind;
	}
	public EventKind getKind(){return kind;}
	public BigDecimal getBalance(){
		return balance;
	}
}
