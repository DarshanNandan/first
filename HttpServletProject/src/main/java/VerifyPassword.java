import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebServlet("/VerifyPassword")
public class VerifyPassword extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
		String query = "select * from teca42.userinformation where password=? and email=?";
		HttpSession session = req.getSession();
		String mail = (String)session.getAttribute("email");
		PrintWriter writer = resp.getWriter();
		resp.setContentType("text/html");
		if(mail != null)
		{
			try 
			{
				Class.forName("com.mysql.jdbc.Driver");
				Connection connection = DriverManager.getConnection(url);
				PreparedStatement ps = connection.prepareStatement(query);
				ps.setString(1, req.getParameter("pass"));
				//session object
				//down casting from Object to String
				ps.setString(2, mail);
				ResultSet rs = ps.executeQuery();
				System.out.println(req.getAttribute("mail1"));
				System.out.println(req.getParameter("pass"));
				if (rs.next())
				{
					//resp.sendRedirect("https://www.udemy.com");
					writer.println("<center><h2>Login Successful..!!!</h2></center>");
	
				}
				else
				{
					writer.println("<center><h2>Invalid Password..!!!</h2></center>");
					RequestDispatcher dispatcher = req.getRequestDispatcher("Password.html");
					dispatcher.include(req, resp);
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		else
		{
			writer.println("The given Session time is : "+(session.getMaxInactiveInterval()/60));
			writer.println("<h2>Session Time Out..!!!</h2>");
		}
	}
}
