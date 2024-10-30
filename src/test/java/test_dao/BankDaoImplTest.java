package test_dao;

import com.artemhodas.aston.rest_service.dao.BankDaoImpl;
import com.artemhodas.aston.rest_service.models.Bank;
import com.artemhodas.aston.rest_service.utils.ConnectionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BankDaoImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;


    private BankDaoImpl bankDao;

    public void setUp() throws SQLException {
        MockitoAnnotations.openMocks(this);
        bankDao = new BankDaoImpl();

        mockStatic(ConnectionManager.class);
        when(ConnectionManager.open()).thenReturn(connection);

    }

    @Test
    public void testSaveBank() throws SQLException {

        Bank bank = new Bank(1, "Test Bank", "Test Location");
        String sql = """
                INSERT INTO banks(id_bank,name,location)
                VALUES (?,?,?);
                """;
        when(ConnectionManager.open().prepareStatement(sql)).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).executeUpdate();
        Bank savedBank = bankDao.saveBank(bank);

        assertNotNull(savedBank);
        assertEquals(bank.getIdBank(), savedBank.getIdBank());
        assertEquals(bank.getName(), savedBank.getName());
        assertEquals(bank.getLocation(), savedBank.getLocation());

        verify(preparedStatement).setLong(1, bank.getIdBank());
        verify(preparedStatement).setString(2, bank.getName());
        verify(preparedStatement).setString(3, bank.getLocation());
        verify(preparedStatement).executeUpdate();





    }
}
