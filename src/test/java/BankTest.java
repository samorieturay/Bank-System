import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BankTest {
	@Test
	void addUniqueAccounts() {
		Bank bank = new Bank();
		bank.addAccount(new CheckingAccount("12345678", 0.5, 1000));
		bank.addAccount(new SavingsAccount("87654321", 0.6, 500));
		assertEquals(2, bank.getAccounts().size(), "There should be two unique accounts.");
	}

	@Test
	void addDuplicateAccount() {
		Bank bank = new Bank();
		bank.addAccount(new CheckingAccount("12345678", 0.5, 1000));
		Exception exception = assertThrows(IllegalArgumentException.class, () -> bank.addAccount(new CheckingAccount("12345678", 0.5, 1000)));
		assertEquals("Account ID must be unique.", exception.getMessage());
	}

	@Test
	void validDeposit() {
		Bank bank = new Bank();
		bank.addAccount(new SavingsAccount("87654321", 0.6, 100));
		bank.deposit("87654321", 400);
		assertEquals(500, bank.getAccountById("87654321").getBalance(), "Balance should be 500 after deposit.");
	}

	@Test
	void validWithdrawal() {
		Bank bank = new Bank();
		bank.addAccount(new CheckingAccount("12345678", 0.5, 1000));
		bank.withdraw("12345678", 500);
		assertEquals(500, bank.getAccountById("12345678").getBalance(), "Balance should be 500 after withdrawal.");
	}

	@Test
	void validTransfer() {
		Bank bank = new Bank();
		bank.addAccount(new CheckingAccount("12345678", 0.5, 1000));
		bank.addAccount(new SavingsAccount("87654321", 0.6, 500));
		bank.transfer("12345678", "87654321", 500);
		assertEquals(500, bank.getAccountById("12345678").getBalance(), "Checking account balance should be 500 after transfer.");
		assertEquals(1000, bank.getAccountById("87654321").getBalance(), "Savings account balance should be 1000 after transfer.");
	}

	@Test
	void invalidTransfer() {
		Bank bank = new Bank();
		bank.addAccount(new CheckingAccount("12345678", 0.5, 500));
		bank.addAccount(new SavingsAccount("87654321", 0.6, 500));
		assertThrows(IllegalArgumentException.class, () -> bank.transfer("12345678", "87654321", 1000), "Transfer should fail if funds are insufficient.");
	}
}
