import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/ExampleHttp")
public class ExampleHttp extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
		String query = "select * from teca42.userinformation where mobile=? and password=?";
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, req.getParameter("mobile"));
			ps.setString(2, req.getParameter("psw"));
			ResultSet rs = ps.executeQuery();
			PrintWriter writer = resp.getWriter();
			if (rs.next()) 
			{
				writer.println("<center><h2>login sucessfull..!!!</h2></center>");
			}
			else
			{
				writer.println("<center><h3>Invalid credentials..!!!</h3></center>");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}












