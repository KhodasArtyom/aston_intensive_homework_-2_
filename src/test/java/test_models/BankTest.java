package test_models;

import com.artemhodas.aston.rest_service.models.Bank;
import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankTest {
    private Bank bank;

    @BeforeEach
    public void setUp() {
        bank = new Bank(1, "Example Bank", "New York");
    }

    @Test
    public void testGetIdBank(){
        Assert.assertEquals("Example Bank",bank.getName());
    }
}
