package sklep.koszyk;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sklep.db.DBConnection;
import sklep.db.DBException;
import sklep.db.ProductDAO;
import sklep.db.RecordNotFound;
import sklep.model.Product;

@WebServlet("/DodajDoKoszyka")
public class DodajDoKoszyka extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parametrId = request.getParameter("id");
		int productId = Integer.parseInt(parametrId);
		
		try(DBConnection db = DBConnection.open()) {
			ProductDAO productDAO = db.productDAO();
			Product product = productDAO.findById(productId);
			
			HttpSession session = request.getSession();
			Koszyk koszyk = (Koszyk)session.getAttribute("koszyk");
			koszyk.dodajProdukt(product, 1);
			// teraz nie trzeba zapisywać, bo Javowe referencje :)
		} catch (RecordNotFound e) {
			// Gdy nie ma produktu, nie dodaję nic do koszyka, ale też nie wyrzucam błędu.
		} catch (DBException e) {
			e.printStackTrace();
		}
		
		// Po dodaniu towaru do koszyka, wracamy na stronę główną, do listy towarów
		response.sendRedirect("lista8.jsp");
	}

}
