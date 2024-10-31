package test_controller;

import com.artemhodas.aston.rest_service.controllers.BankServlet;
import com.artemhodas.aston.rest_service.service.BankService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BankControllerTest {
    private BankServlet bankServlet;
    private BankService bankService;
    private HttpServletRequest req;
    private HttpServletResponse resp;
    private PrintWriter writer;

    @BeforeEach
    public void setUp() throws IOException {
        bankService = mock(BankService.class);
        bankServlet= new BankServlet();
        req = mock(HttpServletRequest.class);
        resp = mock(HttpServletResponse.class);
        writer = mock(PrintWriter.class);
        when(resp.getWriter()).thenReturn(writer);

    }

    @Test
    public void testDoPost(){

    }


}
