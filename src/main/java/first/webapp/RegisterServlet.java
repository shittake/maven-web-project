package first.webapp;

import java.io.IOException;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
			//code to display text as response
		 	response.setContentType("text/html");
	        PrintWriter out = response.getWriter();
	 
	        //get the data from the register.jsp
	        String n = request.getParameter("userName");
	        String p = request.getParameter("password");
	        String e = request.getParameter("email");
	        String c = request.getParameter("language");
	 
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            Connection con = DriverManager.getConnection(
	                    "jdbc:mysql://localhost:3306/testing", "root", ""); //use drive manager class from the sql connector depedenciy
	            								//testing is database name, "root" is default user, "" is blank password
	            PreparedStatement ps = con
	                    .prepareStatement("insert into USERDETAILS2 values(?,?,?,?)");
	 
	            ps.setString(1, n);
	            ps.setString(2, p);
	            ps.setString(3, e);
	            ps.setString(4, c);
	 
	            int i = ps.executeUpdate();
	            if (i > 0)
	                out.print("You are successfully registered... <button onclick=\"window.location.href='http://localhost:8085/lesson14/register.jsp';\"> Click to go to user registration </button>" ); //print out this message as response
	 
	        } catch (Exception e2) {
	            System.out.println(e2);
	        }
	 
	        out.close();
		
	}

}
