package test_models;

import com.artemhodas.aston.rest_service.models.Bank;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTest {
    private Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank(1, "Example Bank", "New York");
    }

    @Test
    public void testGetIdBank() {
        assertEquals(1, bank.getIdBank());
    }

    @Test
    public void testGetNameBank() {
        assertEquals("Example Bank", bank.getName());
    }

    @Test
    public void testGetLocation() {
        assertEquals("New York", bank.getLocation());
    }
}