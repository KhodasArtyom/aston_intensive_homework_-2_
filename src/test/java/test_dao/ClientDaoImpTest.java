package test_dao;

import com.artemhodas.aston.rest_service.dao.ClientDao;
import com.artemhodas.aston.rest_service.dao.ClientDaoImpl;
import com.artemhodas.aston.rest_service.models.Bank;
import com.artemhodas.aston.rest_service.models.Client;
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
import static org.mockito.Mockito.*;

public class ClientDaoImpTest {

    private ClientDao clientDao;

    @BeforeEach
    public void setUp() {
        clientDao = new ClientDaoImpl();
    }

    @Test
    public void getAllClients() throws SQLException {

        Client expectedClient1 = new Client(1, "Brad", "Pitt", new Bank(1, null, null));
        Client expectedClient2 = new Client(2, "Jhony", "Depp", new Bank(2, null, null));

        try (MockedStatic<ConnectionManager> mockedConnectionManager = Mockito.mockStatic(ConnectionManager.class)) {
            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockStatement = mock(PreparedStatement.class);
            ResultSet mockResultSet = mock(ResultSet.class);

            mockedConnectionManager.when(ConnectionManager::open).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockStatement);
            when(mockStatement.executeQuery()).thenReturn(mockResultSet);

            when(mockResultSet.next()).thenReturn(true, true, false);
            when(mockResultSet.getInt("id_client")).thenReturn(1, 2);
            when(mockResultSet.getString("first_name")).thenReturn("John", "Jane");
            when(mockResultSet.getString("last_name")).thenReturn("Doe", "Smith");
            when(mockResultSet.getInt("fk_bank_id")).thenReturn(1, 2);

            List<Client> clients = clientDao.showAllClients();
            assertEquals(2, clients.size());

            assertEquals(expectedClient1.getClientId(), clients.get(0).getClientId());
            assertEquals(expectedClient1.getFirstName(), clients.get(0).getFirstName());
            assertEquals(expectedClient1.getLastName(), clients.get(0).getLastName());
            assertEquals(expectedClient1.getBank().getIdBank(), clients.get(0).getBank().getIdBank());

            assertEquals(expectedClient2.getClientId(), clients.get(1).getClientId());
            assertEquals(expectedClient2.getFirstName(), clients.get(1).getFirstName());
            assertEquals(expectedClient2.getLastName(), clients.get(1).getLastName());
            assertEquals(expectedClient2.getBank().getIdBank(), clients.get(1).getBank().getIdBank());
        }
    }

    @Test
    public void testSaveClient() throws SQLException {
        try (MockedStatic<ConnectionManager> mockedConnectionManager =
                     Mockito.mockStatic(ConnectionManager.class)) {

            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockStatement = mock(PreparedStatement.class);

            mockedConnectionManager.when(ConnectionManager::open).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockStatement);

            Client client = new Client(1, "Carl", "Jhonson", new Bank(1, "Bank 1", "Location 1"));
            clientDao.saveClient(client);

            verify(mockStatement).setInt(1, client.getClientId());
            verify(mockStatement).setString(2, client.getFirstName());
            verify(mockStatement).setString(3, client.getLastName());
            verify(mockStatement).setInt(4, client.getBank().getIdBank());
            verify(mockStatement).executeUpdate();

        }
    }

    @Test
    public void testDeleteClient() throws SQLException {
        try (MockedStatic<ConnectionManager> mockedConnectionManager = Mockito.mockStatic(ConnectionManager.class)) {
            Connection mockConnection = mock(Connection.class);
            PreparedStatement mockStatement = mock(PreparedStatement.class);
            mockedConnectionManager.when(ConnectionManager::open).thenReturn(mockConnection);
            when(mockConnection.prepareStatement(any(String.class))).thenReturn(mockStatement);
            when(mockStatement.executeUpdate()).thenReturn(1);

            clientDao.deleteClient(1);

            verify(mockStatement).setInt(1, 1);
            verify(mockStatement).executeUpdate();
        }
    }
}

