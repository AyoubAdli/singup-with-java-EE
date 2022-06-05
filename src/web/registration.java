package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class registration
 */
@WebServlet("/registration")
public class registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String pass = request.getParameter("pass");		
		String contact = request.getParameter("contact");
		
		String url = "jdbc:mysql://localhost:3306/youtube" ;
		String user = "root";
		String pwd = "";
		
		try {
			//load the driver			
			Class.forName("com.mysql.cj.jdbc.Driver");			
			//connect objet
			Connection  con = DriverManager.getConnection(url, user , pwd);
			
					
			//creat statment
			PreparedStatement pst=con.prepareStatement("INSERT INTO users(uname, uemail, upwd,  umobile) VALUES (?,?,?,?)");
			//pour sasir les car dans sql 
			pst.setString(1, name);
			pst.setString(2, email);
			pst.setString(3, pass);
			pst.setString(4, contact);
			
			pst.executeUpdate();
			
			response.sendRedirect("index.jsp");
			pst.close();
			con.close();
			
			
		}catch(Exception e){
			System.out.print(e);
		}
		
	}
	
}


