import org.junit.Before;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CDAccountTest {
    private CDAccount cdAccount;

    @Before
    public void setUp() {
        // Assuming CDAccount constructor takes account number, interest rate, and initial deposit
        cdAccount = new CDAccount("23456789", 1.2, 2000);
    }

    @Test
    public void testAccountCreation() {
        assertEquals("23456789", cdAccount.getAccountNumber());
        assertEquals(2000.00, cdAccount.getBalance(), 0.01);
    }



    @Test
    public void testDeposit() {
        cdAccount.deposit(500);
        assertEquals(2500.00, cdAccount.getBalance(), 0.01);
    }
}
