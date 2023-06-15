import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/Student")
public class Student extends GenericServlet
{
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
	
		String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
		String query = "insert into teca42.student values(?,?,?,?)";
		PrintWriter writer = resp.getWriter();
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, req.getParameter("stdid"));
			ps.setString(2, req.getParameter("stdname"));
			ps.setString(3, req.getParameter("stdper"));
			ps.setString(4, req.getParameter("stdstr"));
			int res = ps.executeUpdate();
			writer.println("<center><h2>Registration sucessfull..!!!</h2><center>");
			connection.close();
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
}
