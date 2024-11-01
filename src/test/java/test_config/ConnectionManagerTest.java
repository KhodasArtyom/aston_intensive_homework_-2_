package test_config;

import com.artemhodas.aston.rest_service.utils.ConnectionManager;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;

public class ConnectionManagerTest {

    @Test
    public void testOpenConnection() throws SQLException {
        Connection mockConnection = mock(Connection.class);
        DriverManager.setLoginTimeout(1);

        try (MockedStatic<DriverManager> mockedDriverManager = Mockito.mockStatic(DriverManager.class)) {
            mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                    .thenReturn(mockConnection);

            Connection connection = ConnectionManager.open();
            assertNotNull(connection);
        }


    }
}
