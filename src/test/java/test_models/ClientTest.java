package test_models;

import com.artemhodas.aston.rest_service.models.Bank;
import com.artemhodas.aston.rest_service.models.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClientTest {

    private Client client;
    private Bank bank;

    @BeforeEach
    public void setUp(){
        bank = new Bank(1,"АльфаБанк","Минск");
        client = new Client(1,"David","Boreanaz",bank);
    }

    @Test
    public void testGetClientId(){
        assertEquals(1,client.getClientId());
    }

    @Test
    public void testGetClientFirstName(){
        assertEquals("David",client.getFirstName());
    }

    @Test
    public void testGetClientLastName(){
        assertEquals("Boreanaz",client.getLastName());
    }

    @Test
    public void testGetBankOfClient(){
        assertEquals(bank,client.getBank());
    }





}
