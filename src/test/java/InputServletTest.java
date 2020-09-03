import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class InputServletTest {

    @InjectMocks
    InputServlet inputServlet;

    private HttpServletRequest request;
    private HttpServletResponse response;

    @BeforeEach
    public void setUp() {
        inputServlet = new InputServlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
    }
    @Test
    public void testRandomInput_1() throws Exception {
        when(request.getParameter("input")).thenReturn("10,3,1,5,2,8,100,3,4");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        inputServlet.doPost(request, response);

        verify(request, atLeast(1)).getParameter("input"); // only if you want to verify username was called...
        writer.flush(); // it may not have been flushed yet...
        assertTrue(stringWriter.toString().contains("100,10,8,5,4"));
    }

    @Test
    public void testRandomInput_2() throws Exception {
        when(request.getParameter("input")).thenReturn("-10000000,3,1,5,2,8,10000000,3,4,-10000000,3,1,5,2,8,10000000,3,4,-10000000,3,1,5,2,8,10000000,3,4,-10000000,3,1,5,2,8,10000000,3,4");

        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);

        inputServlet.doPost(request, response);

        verify(request, atLeast(1)).getParameter("input"); // only if you want to verify username was called...
        writer.flush(); // it may not have been flushed yet...
        assertTrue(stringWriter.toString().contains("10000000,10000000,10000000,10000000,8"));
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme