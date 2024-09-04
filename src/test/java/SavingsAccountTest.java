import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SavingsAccountTest {
    @Test
    void depositWithinLimit() {
        SavingsAccount account = new SavingsAccount("87654321", 0.6, 0.6);
        account.deposit(2000);
        assertEquals(2000, account.getBalance());
    }

    @Test
    void depositAboveLimit() {
        SavingsAccount account = new SavingsAccount("87654321", 0.6, 0.6);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(3000)); // Exceeds limit
    }

    @Test
    void monthlyWithdrawalLimit() {
        SavingsAccount account = new SavingsAccount("87654321", 0.6, 0.6);
        account.deposit(1500);
        account.withdraw(1000); // First withdrawal in the month
        assertThrows(IllegalStateException.class, () -> account.withdraw(500)); // Second withdrawal attempt
    }
}
