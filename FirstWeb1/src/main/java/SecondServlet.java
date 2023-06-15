import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
@WebServlet("/SecondServlet")
public class SecondServlet extends GenericServlet
{
	@Override
	public void service(ServletRequest req, ServletResponse resp) throws ServletException, IOException {
		//System.out.println("Login successful...!!!");
		PrintWriter writer = resp.getWriter();
		writer.println("Login successfull...!!!");
		
		//Fetch the data from front-end
		String uName = req.getParameter("un");
		String pass = req.getParameter("psw");
		System.out.println("The user name is : "+uName);
		System.out.println("The password is : "+pass);
		
	}
}
