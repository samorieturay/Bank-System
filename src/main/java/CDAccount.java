public class CDAccount extends Account {
	private final long creationTime;

	public CDAccount(String accountId, double apr, double initialBalance) {
		super(accountId, apr, initialBalance);
		if (initialBalance < 1000 || initialBalance > 10000) {
			throw new IllegalArgumentException("Initial balance must be between $1,000 and $10,000.");
		}
		this.creationTime = System.currentTimeMillis();
	}

	@Override
	public void withdraw(double amount) {
		if (!canWithdraw()) {
			throw new IllegalStateException("Withdrawal not allowed until 12 months have passed.");
		}
		if (amount != getBalance()) {
			throw new IllegalArgumentException("You must withdraw the full balance.");
		}
		super.setBalance(0);
	}

	private boolean canWithdraw() {
		long currentTime = System.currentTimeMillis();
		return (currentTime - creationTime) >= 31536000000L; // 365 days in milliseconds
	}

	@Override
	public void applyInterest(int months) {
		for (int i = 0; i < months * 4; i++) { // CD interest is calculated 4 times per month
			double interest = calculateMonthlyInterest();
			setBalance(getBalance() + interest);
		}
	}

	@Override
	public double attemptWithdrawal(double amount) {
		if (!canWithdraw()) {
			return 0;  // No withdrawals allowed before 12 months
		}
		if (amount < getBalance()) {
			return 0;  // Partial withdrawals are not allowed
		}
		withdraw(getBalance());  // Withdraw the full balance
		return getBalance();  // Return the withdrawn amount (full balance)
	}


	public String getAccountNumber() {
		super.getAccountId();
		return getAccountId();
	}

	public double calculationCDAPR(double balance, double apr) {
		double monthlyRate = (apr / 100) / 12;

		for (int i = 0; i < 4; i++) {
			double interest = balance * monthlyRate;
			balance += interest;
		}

		return balance; // Return the new balance after compounding
	}

}
