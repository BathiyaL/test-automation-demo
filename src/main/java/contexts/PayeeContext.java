package contexts;

public class PayeeContext {

	private String payeeName;
	private String accountBank;
	
	private String accountBranch;
	private String accountNumber;
	private String accountSuffix;
	
	public String getPayeeName() {
		return payeeName;
	}
	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}
	public String getAccountBank() {
		return accountBank;
	}
	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}
	public String getAccountBranch() {
		return accountBranch;
	}
	public void setAccountBranch(String accountBranch) {
		this.accountBranch = accountBranch;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountSuffix() {
		return accountSuffix;
	}
	public void setAccountSuffix(String accountSuffix) {
		this.accountSuffix = accountSuffix;
	}
	

}
