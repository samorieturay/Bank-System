public class SavingsAccount extends Account {
	private static final double MAX_DEPOSIT_AMOUNT = 2500;
	private static final double MAX_WITHDRAW_AMOUNT = 1000;
	private int withdrawalsThisMonth;

	public SavingsAccount(String accountId, double v, double apr) {
		super(accountId, apr, 0);
		this.withdrawalsThisMonth = 0;
	}

	@Override
	public void deposit(double amount) {
		if (amount > MAX_DEPOSIT_AMOUNT) {
			throw new IllegalArgumentException("Deposit amount cannot exceed " + MAX_DEPOSIT_AMOUNT);
		}
		super.deposit(amount);
	}

	@Override
	public void withdraw(double amount) {
		if (amount > MAX_WITHDRAW_AMOUNT) {
			throw new IllegalArgumentException("Withdrawal amount cannot exceed " + MAX_WITHDRAW_AMOUNT);
		}
		if (withdrawalsThisMonth >= 1) {
			throw new IllegalStateException("Only one withdrawal per month is allowed.");
		}
		super.withdraw(amount);
		withdrawalsThisMonth++;
	}

	public void resetWithdrawalCount() {
		withdrawalsThisMonth = 0;
	}

	@Override
	public double attemptWithdrawal(double amount) {
		if (amount <= 0 || withdrawalsThisMonth >= 1) {
			return 0;  // No withdrawal possible if monthly limit exceeded or non-positive amount
		}
		if (amount > MAX_WITHDRAW_AMOUNT) {
			amount = MAX_WITHDRAW_AMOUNT;  // Enforce maximum withdrawal limit
		}
		if (amount > getBalance()) {
			amount = getBalance();  // Can only withdraw up to the current balance
		}
		withdraw(amount);  // Perform the withdrawal
		withdrawalsThisMonth++;  // Increment the withdrawal count for the month
		return amount;  // Return the actual withdrawn amount
	}

}
