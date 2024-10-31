package test_dao;

import com.artemhodas.aston.rest_service.dao.BankDaoImpl;
import com.artemhodas.aston.rest_service.models.Bank;
import com.artemhodas.aston.rest_service.utils.ConnectionManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BankDaoImplTest {

    private BankDaoImpl bankDao;

    @BeforeEach
    public void setUp() {
        bankDao = new BankDaoImpl();
    }

    @Test
    public void testGetAllBanks() throws SQLException {
        Bank bank1 = new Bank(1, "Bank 1", "Location 1");
        Bank bank2 = new Bank(2, "Bank 2", "Location 2");

        try (MockedStatic<ConnectionManager> mockedConnectionManager = Mockito.mockStatic(ConnectionManager.class)) {

            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockStatement = mock(PreparedStatement.class);
            ResultSet mockResultSet = mock(ResultSet.class);

            mockedConnectionManager.when(ConnectionManager::open).thenReturn(mockConnection);

            when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockStatement);
            when(mockStatement.executeQuery()).thenReturn(mockResultSet);
            when(mockResultSet.next()).thenReturn(true, true, false); // Два банка
            when(mockResultSet.getInt("id_bank")).thenReturn(bank1.getIdBank(), bank2.getIdBank());
            when(mockResultSet.getString("name")).thenReturn(bank1.getName(), bank2.getName());
            when(mockResultSet.getString("location")).thenReturn(bank1.getLocation(), bank2.getLocation());

            List<Bank> banks = bankDao.getAllBanks();
            assertEquals(2, banks.size());
            assertEquals(bank1.getName(), banks.get(0).getName());
            assertEquals(bank2.getName(), banks.get(1).getName());


        }

    }
}
