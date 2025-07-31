package first.webapp;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.MockedStatic;

import java.io.PrintWriter;
import java.io.StringWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServletTest {
	private RegisterServlet servlet;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Connection mockConnection;
	private PreparedStatement mockPreparedStatement;
	
	@BeforeEach
	public void setUp() {
		servlet = new RegisterServlet(); // this is the actual object to be tested
		request = mock(HttpServletRequest.class);
		response = mock(HttpServletResponse.class);
		mockConnection = mock(Connection.class);
		mockPreparedStatement = mock(PreparedStatement.class);
	}
	
	@Test
	void testDoPostSuccess() throws Exception {
		StringWriter stringWriter = new StringWriter();
		PrintWriter writer = new PrintWriter(stringWriter);
		when(response.getWriter()).thenReturn(writer);
		
		when(request.getParameter("userName")).thenReturn("eric");
		when(request.getParameter("password")).thenReturn("password123");
		when(request.getParameter("email")).thenReturn("eric@gmail.com");
		when(request.getParameter("language")).thenReturn("English");
		
		MockedStatic<DriverManager> mockedDriverManager = mockStatic(DriverManager.class);
		
		mockedDriverManager.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
		.thenReturn(mockConnection);
		
		when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
		when(mockPreparedStatement.executeUpdate()).thenReturn(1);
		
		servlet.doPost(request, response);
		
		writer.flush();
		String output = stringWriter.toString();
		assertTrue(output.contains("You are successfully registered"));
		
		verify(mockPreparedStatement).setString(1, "eric");
		verify(mockPreparedStatement).setString(2, "password123");
		verify(mockPreparedStatement).setString(3, "eric@gmail.com");
		verify(mockPreparedStatement).setString(4, "English");
	}
	
	
}
