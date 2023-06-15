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
@WebServlet("/Bank")
public class Bank extends GenericServlet
{
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException 
	{
		String accNo = req.getParameter("acc");
		String ahn = req.getParameter("ahn");
		String mobile = req.getParameter("mobile");
		String tempamount = req.getParameter("amount");
		double amount = Double.parseDouble(tempamount);
		
		PrintWriter writer = resp.getWriter();
//		writer.println("Account number : "+accNo);
//		writer.println("Account Holder Name : "+ahn);
//		writer.println("Mobile Number : "+mobile);
//		writer.println("Withraw amount : "+amount);
		String url = "jdbc:mysql://localhost:3306?user=root&password=12345";
		String query ="update teca42.bankinfo set bal=bal-? where accNo=? and a_h_n=? and mobile=? and bal>?";
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setDouble(1, amount);
			ps.setString(2, accNo);
			ps.setString(3, ahn);
			ps.setString(4, mobile);
			ps.setDouble(5, amount);
			int res = ps.executeUpdate();
			if (res > 0)
			{
				writer.println("<h1>Withraw sucessfull..!!!</h1>");
			}
			else
			{
				writer.println("<h1>Invalid credentials..!!!check the balance..!!!</h1>");
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}














