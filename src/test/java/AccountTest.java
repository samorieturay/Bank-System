
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AccountTest {

	private static abstract class TestAccount extends Account {
		public TestAccount(String accountId, double apr, double balance) {
			super(accountId, apr, balance);
		}
	}

	@Test
	void testValidAccountCreation() {
		TestAccount account = new TestAccount("12345678", 5.0, 1000.0) {
			@Override
			public double attemptWithdrawal(double amount) {
				return 0;
			}
		};
		assertEquals("12345678", account.getAccountId());
		assertEquals(5.0, account.getAPR());
		assertEquals(1000.0, account.getBalance());
	}

	@Test
	void testInvalidAccountId() {
		assertThrows(IllegalArgumentException.class, () -> new TestAccount("123", 5.0, 1000.0) {
			@Override
			public double attemptWithdrawal(double amount) {
				return 0;
			}
		});
	}

	@Test
	void testInvalidAPR() {
		assertThrows(IllegalArgumentException.class, () -> new TestAccount("12345678", 11.0, 1000.0) {
			@Override
			public double attemptWithdrawal(double amount) {
				return 0;
			}
		});
	}

	@Test
	void testDeposit() {
		TestAccount account = new TestAccount("12345678", 5.0, 1000.0) {
			@Override
			public double attemptWithdrawal(double amount) {
				return 0;
			}
		};
		account.deposit(500.0);
		assertEquals(1500.0, account.getBalance());
	}

	@Test
	void testNegativeDeposit() {
		TestAccount account = new TestAccount("12345678", 5.0, 1000.0) {
			@Override
			public double attemptWithdrawal(double amount) {
				return 0;
			}
		};
		assertThrows(IllegalArgumentException.class, () -> account.deposit(-100.0));
	}

	@Test
	void testIsValidAccountId() {
		assertTrue(Account.isValidAccountId("12345678"));
		assertFalse(Account.isValidAccountId("1234"));
	}

	@Test
	void testIsValidAPR() {
		assertTrue(Account.isValidAPR(5.0));
		assertFalse(Account.isValidAPR(15.0));
	}
}
