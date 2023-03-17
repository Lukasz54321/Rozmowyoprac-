package sklep.serwlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sklep.db.DBConnection;
import sklep.db.DBException;
import sklep.db.ProductDAO;
import sklep.model.Product;

@WebServlet("/Products2")
public class Products2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();	
		
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Lista produktów</title>");
		out.println("<link rel='stylesheet' type='text/css' href='styl.css'>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>Lista produktów</h1>");

		try(DBConnection db = DBConnection.open()) {
			ProductDAO productDAO = db.productDAO();
			List<Product> products = productDAO.readAll();
			
			out.println("<div>W bazie jest " + products.size() + " produktów.</div>");
			for (Product product : products) {
				out.println(product.getHtml());
			}
		} catch (DBException e) {
			// parametr out oznacza, że info o błędzie pójdą w odpowiedzi do klienta
			out.println("<pre class='error'>");
			e.printStackTrace(out);
			out.println("</pre>");
		}
		
		out.println("</body>");
		out.println("</html>");
	}

}
