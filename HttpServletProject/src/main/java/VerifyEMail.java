import java.io.*;
import java.sql.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/VerifyEMail")
public class VerifyEMail extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
		String query = "select * from teca42.userinformation where email=?";
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(query);
			String mail = req.getParameter("mail");
			ps.setString(1, mail);
			
			//session object
			HttpSession session = req.getSession();
			session.setAttribute("email", mail);
			//set session time
			session.setMaxInactiveInterval(10);
			
			ResultSet rs = ps.executeQuery();
			PrintWriter writer = resp.getWriter();
			if (rs.next())
			{
				RequestDispatcher dispatcher = req.getRequestDispatcher("Password.html");
				dispatcher.forward(req, resp);
			}
			else
			{
				writer.println("<center><h2>Invalid EMail..!!!</h2></center>");
				RequestDispatcher dispatcher = req.getRequestDispatcher("Email.html");
				dispatcher.include(req, resp);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
