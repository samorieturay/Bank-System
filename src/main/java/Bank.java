import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Bank {
	private final List<Account> accounts;
	private final List<String> invalidCommands;

	public Bank() {
		accounts = new ArrayList<>();
		invalidCommands = new ArrayList<>();
	}
	public int getAccountsSize() {
		return accounts.size();
	}
	public void addAccount(Account account) {
		// Check for ID uniqueness
		if (getAccountById(account.getAccountId()) != null) {
			invalidCommands.add("Duplicate account ID: " + account.getAccountId());
			return;
		}
		accounts.add(account);
	}

	public Account getAccountById(String accountId) {
		for (Account account : accounts) {
			if (account.getAccountId().equals(accountId)) {
				return account;
			}
		}
		return null;
	}

	public void deposit(String accountId, double amount) {
		Account account = getAccountById(accountId);
		if (account == null || amount < 0) {
			invalidCommands.add("Invalid deposit command: " + accountId + " " + amount);
			return;
		}
		account.deposit(amount);
	}

	public void withdraw(String accountId, double amount) {
		Account account = getAccountById(accountId);
		if (account == null || amount < 0) {
			invalidCommands.add("Invalid withdraw command: " + accountId + " " + amount);
			return;
		}
		account.withdraw(amount);
	}

	public void transfer(String fromId, String toId, double amount) {
		Account fromAccount = getAccountById(fromId);
		Account toAccount = getAccountById(toId);
		if (fromAccount == null || toAccount == null || fromAccount == toAccount || amount <= 0) {
			invalidCommands.add("Invalid transfer command");
			return;
		}
		double withdrawnAmount = fromAccount.attemptWithdrawal(amount);
		toAccount.deposit(withdrawnAmount);
	}

	public void passTime(int months) {
		if (months < 1 || months > 60) {
			invalidCommands.add("Invalid time command");
			return;
		}
		for (Account account : new ArrayList<>(accounts)) { // Create a copy to avoid modification exceptions
			if (account.getBalance() == 0) {
				accounts.remove(account);
			} else {
				if (account.getBalance() < 100) {
					account.setBalance(account.getBalance() - 25);
				}
				account.applyInterest(months);
			}
		}
	}

	// Additional helper methods if necessary
	public List<String> getInvalidCommands() {
		return invalidCommands;
	}


	public Collection<Account> getAccounts() {
		return accounts;
	}

}
