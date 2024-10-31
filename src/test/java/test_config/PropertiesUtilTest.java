package test_config;

import com.artemhodas.aston.rest_service.utils.PropertiesUtil;
import org.junit.jupiter.api.Test;

import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PropertiesUtilTest {

    @Test
    public void testGetProperty(){
        Properties mockProperties = mock(Properties.class);

        when(mockProperties.getProperty("db.url")).thenReturn("jdbc:postgresql://localhost:5432/postgres");

        String url = PropertiesUtil.get("db.url");

        assertEquals("jdbc:postgresql://localhost:5432/postgres",url);
    }


}
