package sklep.serwlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Products0")
public class Products0 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();		

		try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/sklep", "kurs", "abc123");
			PreparedStatement stmt = c.prepareStatement("SELECT * FROM products ORDER BY product_id");
			ResultSet rs = stmt.executeQuery()) {
			
			out.println("Lista produktów:");
			while(rs.next()) {
				out.printf("Produkt nr %d: %s, cena %s, opis: %s\n",
						rs.getInt("product_id"), rs.getString("product_name"),
						rs.getBigDecimal("price"), rs.getString("description"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
