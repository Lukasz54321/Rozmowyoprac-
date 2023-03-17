package na_zajeciach;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Odczyt1 {

	public static void main(String[] args) {
		try {
			Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/hr", "kurs", "abc123");
			// Connection c = DriverManager.getConnection("jdbc:postgresql://vps497901.ovh.net/hr", "hr", "vps497901_abc123");
			System.out.println("Udało się połączyć. Obiekt połączenia: " + c);
			
			Statement stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("SELECT * FROM employees");
			System.out.println("Mam wyniki: " + rs);
			
			while(rs.next()) {
				// teraz jesteśmy w jakimś rekordzie i za pomocą getString / getInt itd. możemy odczytywać pola tego rekordu
				// w tej wersji odczytujemy pola podając numer kolumny, numeracja od 1
				int id = rs.getInt(1);
				String firstName = rs.getString(2);
				String lastName = rs.getString(3);
				
				System.out.println(id + " " + firstName + " " + lastName);
			}
			
			rs.close();
			stmt.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
