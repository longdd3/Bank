package assigments;
import java.util.Date;

class account { 
	private int id = 0;
	private double balance = 0.0;
	private double annualInterestRate =0.0;
	private Date dateCreated; 
	public account() {
		
	}
	public account(int id, double balance ) {
		this.id = id;
		this.balance = balance;

	}
	public int getId(){
		return this.id;
	}
	public double getBalance()
	{
		return this.balance;
	}
	public double getAnnualInterestRate()
	{
		return this.annualInterestRate;
	}
	public void setID(int id) {
			this.id=id;
	}
	public void setBalance(double balance) {
		this.balance= balance;
	}
	public void setAnnualInterestRate(double annualInterestRate ) {
		this.annualInterestRate = annualInterestRate;
	}
	public String getDateCreated(){
		return this.dateCreated.toString();
	}
	public double getMonthlyInterestRate() {
		return (annualInterestRate / 100) / 12;
		
	}
	public double getMonthlyInterest(){
		return balance * getMonthlyInterestRate();
	}
	public void widthdraw(double amount) {
		this.balance -= amount;
	}
	public void  deposit(double amount) {
		this.balance += amount;
	}
}
class SavingAccount extends account{
	double interest;
	public SavingAccount(int id, double balance, double interest) {
		super(id,balance);
		this.interest = interest;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest() {
		this.interest = interest;
	}
	public void addsinterest() {
		double amount = getBalance();
		deposit(amount * interest);
	}
	
}


class CurrentAccount extends account{
	double overdraftlimit;
	public CurrentAccount(int id,double balance, double overdraftlimit) {
		super(id, balance);
		this.overdraftlimit = overdraftlimit;
	}
	public double getOverdraftlimit() {
		return overdraftlimit;
	}
	public void setOverdraftlimit() {
		this.overdraftlimit = overdraftlimit;
	}
}
class Bank {
	account Accounts[];
	int numofaccounts = 0;
	public Bank() {
		this.Accounts= new account[200];
	}
	
	public void update() {
		for (account acc : Accounts) {
		if (acc != null) {
			if (acc instanceof SavingAccount ) {
				SavingAccount SA = (SavingAccount)acc;
				SA.addsinterest();
				System.out.println("The interest added to Saving account "+SA.getId());
			}
			if (acc instanceof CurrentAccount) {
				CurrentAccount CA = (CurrentAccount)acc;
			if (CA.getBalance() > CA.getOverdraftlimit()) {
				System.out.println("Letter sent to Current Account" + CA.getId());
			}
			}
		}
		
		
	}
}
	public void openingAccount(account acc) {
		Accounts[++numofaccounts]=acc ; 
	}
	public void closingAccount(account acc) {
		int idx = 0;
		for (int i = 0; i< numofaccounts; i++) {
			if (Accounts[i].getId()  == acc.getId()){
				idx = i;
				break;
			}
		}
		for (int i=idx; i< numofaccounts-1;i++) {
			Accounts[i] = Accounts[i+1];
		}
		Accounts[numofaccounts] = null;
		numofaccounts--;
	}
	
	
}

public class assign1 {
	 public static void main(String[] args) {
		 SavingAccount sa1 = new SavingAccount(1,25,12);  
	        sa1.deposit(1000);  
	        SavingAccount sa2 = new SavingAccount(2,48,14);  
	        sa2.deposit(1500);  

	        CurrentAccount ca1 = new CurrentAccount(3,800,1800);    
	        ca1.deposit(2000);  
	        CurrentAccount ca2 = new CurrentAccount(4,480,3000);   
	        ca2.deposit(2000);  

	        Bank bank = new Bank(); //create a Bank object
	        bank.openingAccount(sa1);  //add previously created accounts
	        bank.openingAccount(sa2);
	        bank.openingAccount(ca1);
	        bank.openingAccount(ca2);

	        bank.update();  
}
}
