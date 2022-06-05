package web;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class log
 */
@WebServlet("/log")
public class log extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String login = request.getParameter("username");
		String passw = request.getParameter("password");
	
		String url = "jdbc:mysql://localhost:3306/youtube" ;
		String user = "root";
		String pwd = "";
		
		try {
			//load the driver			
			Class.forName("com.mysql.cj.jdbc.Driver");			
			//connect objet
			Connection  con = DriverManager.getConnection(url, user , pwd);
			
					
			//creat statment
			PreparedStatement pst=con.prepareStatement("SELECT id FROM users WHERE uname=? AND upwd=?");
			//pour sasir les var dans sql 
			pst.setString(1, login);
			pst.setString(2, passw);
			//utiliser resultat si ona select(recuprer from bd)
			ResultSet rs = pst.executeQuery();
			
			if(rs.next()) { //il existe au moin un ligne dans bd
				
				
			session.setAttribute("login", login);
			
			
			response.sendRedirect("index.jsp");
			
			}else response.sendRedirect("login.jsp");
			
			rs.close();
			pst.close();
			con.close();
			
			
		}catch(Exception e){
			System.out.print(e);
		}
		
	}

}