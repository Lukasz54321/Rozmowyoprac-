package sklep.koszyk;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class ListenerSesji implements HttpSessionListener {
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		System.out.println("Początek sesji " + session.getId());
		// czas wygaśnięcia nieaktywnej sesji
		session.setMaxInactiveInterval(30);
		session.setAttribute("koszyk", new Koszyk());
	}

	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("Koniec sesji " + se.getSession().getId());
	}

}
