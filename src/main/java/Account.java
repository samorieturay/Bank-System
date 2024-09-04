public abstract class Account {
	private String accountId;
	private double apr;
	private double balance;

	protected Account(String accountId, double apr, double balance) {
		if (!isValidAccountId(accountId)) {
			throw new IllegalArgumentException("Invalid Account ID. It must be exactly 8 digits.");
		}
		if (!isValidAPR(apr)) {
			throw new IllegalArgumentException("Invalid APR. It must be between 0 and 10.");
		}
		this.accountId = accountId;
		this.apr = apr;
		this.balance = balance;
	}

	public String checkID(String accountId) {
		System.out.println("Enter your ID: ");
		int length = accountId.length();
		if (length > 8) {
			System.out.println("Try again. ID entered is greater than 8 digits.");
		} else if (length < 8) {
			System.out.println("Try again. ID entered is less than 8 digits.");
		} else {
			System.out.println("Valid ID.");
		}
		return accountId;
	}

	public static boolean isValidAccountId(String accountId) {
		return accountId.matches("\\d{8}");
	}

	public static boolean isValidAPR(double apr) {
		return apr > 0 || apr < 10;
	}

	public String getAccountId() {
		return accountId;
	}

	public double getAPR() {
		return apr;
	}

	public void checkAPR() {
		if (apr < 0 || apr > 10) {
			System.out.println("APR ranges from 0-10");
		} else {
			System.out.println("true");
		}
	}

	public double calculationAPR(double balance, double apr) {
		double monthlyRate = (apr / 100) / 12;

		double interest = balance * monthlyRate;

		balance += interest;

		return balance; // Return the new balance
	}

	public double getBalance() {
		return balance;
	}

	protected void setBalance(double balance) {
		this.balance = balance;
	}

	public void deposit(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Deposit amount cannot be negative.");
		}
		balance += amount;
	}

	public void withdraw(double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Withdrawal amount cannot be negative.");
		}
		if (amount > balance) {
			balance = 0;
		} else {
			balance -= amount;
		}
	}

	public double calculateMonthlyInterest() {
		double monthlyRate = (apr / 100) / 12;
		return balance * monthlyRate;
	}

	public void applyInterest(int months) {
		for (int i = 0; i < months; i++) {
			balance += calculateMonthlyInterest();
		}
	}

	public abstract double attemptWithdrawal(double amount);



	public void passTime(int months) {
		if (months < 1 || months > 60) {
			throw new IllegalArgumentException("Months must be between 1 and 60.");
		}

		for (int i = 0; i < months; i++) {
			// Close the account if the balance is $0
			if (balance == 0) {
				System.out.println("Account " + accountId + " is closed due to $0 balance.");
				// Implement account closure logic here (remove account from the system)
				return;
			}

			// Deduct $25 if the balance is below $100
			if (balance < 100) {
				balance -= 25;
				System.out.println("Deducted $25 minimum balance fee. New balance: $" + balance);
				if (balance < 0) {
					balance = 0; // Avoid negative balances
				}
			}

			// Accrue interest
			balance = calculationAPR(balance, apr);
			System.out.println("Accrued interest. New balance: $" + balance);
		}
	}


}

