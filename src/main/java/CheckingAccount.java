public class CheckingAccount extends Account {
	private static final double MAX_DEPOSIT_AMOUNT = 1000;
	private static final double MAX_WITHDRAW_AMOUNT = 400;

	public CheckingAccount(String accountId, double v, double apr) {
		super(accountId, apr, 0);
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
		super.withdraw(amount);
	}
	@Override
	public double attemptWithdrawal(double amount) {
		if (amount <= 0) {
			return 0;  // No withdrawal possible for non-positive amounts
		}
		if (amount > MAX_WITHDRAW_AMOUNT) {
			amount = MAX_WITHDRAW_AMOUNT;  // Enforce maximum withdrawal limit
		}
		if (amount > getBalance()) {
			amount = getBalance();  // Can only withdraw up to the current balance
		}
		withdraw(amount);  // Perform the withdrawal
		return amount;  // Return the actual withdrawn amount
	}

}
