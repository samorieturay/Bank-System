import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CheckingAccountTest {
    @Test
    void depositWithinLimit() {
        CheckingAccount account = new CheckingAccount("12345678", 0.5, 0.5);
        account.deposit(500);
        assertEquals(500, account.getBalance());
    }

    @Test
    void depositAboveLimit() {
        CheckingAccount account = new CheckingAccount("12345678", 0.5, 0.5);
        assertThrows(IllegalArgumentException.class, () -> account.deposit(1500)); // Exceeds limit
    }

    @Test
    void withdrawalWithinLimit() {
        CheckingAccount account = new CheckingAccount("12345678", 0.5, 0.5);
        account.deposit(1000);
        account.withdraw(400);  // Max limit
        assertEquals(600, account.getBalance());
    }
}
