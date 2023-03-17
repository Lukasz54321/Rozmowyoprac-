package na_zajeciach;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import javax.swing.JOptionPane;

/* To jest rozwiązanie niewydajne.
 * (Typowy błąd początkującego programisty).
 * Baza odczytuje wszystkie dane z tabeli, przesyła do aplikacji wszystkie rekordy,
 * a dopiero aplikacja analizuje dane i wybiera interesujące rekordy.
 *
 * A przecież bazy danych SQL są zoptymalizowane pod kątem wyszukiwania danych.
 * Dla kolumn, po których wyszukujemy, definiuje się indeksy - wtedy baza danych
 * znajdzie rekordy zgodne z warunkiem WHERE wielokrotnie szybciej niż aplikacja kliencka.
 *
 * Nawet w przypadku braku indeksów w tym rozwiązaniu
 * niepotrzebnie przesyłamy dane przez sieć i obciążamy klienta.
 */

public class Parametry1 {

	public static void main(String[] args) {
		String kogoSzukam = JOptionPane.showInputDialog("Podaj job id, np. IT_PROG");
		// TODO Program ma wypisać tylko tych pracowników z bazy, których job_id = kogoSzukam
		
		try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/hr", "kurs", "abc123");
				PreparedStatement stmt = c.prepareStatement("SELECT * FROM employees");
				ResultSet rs = stmt.executeQuery()) {
				
				while(rs.next()) {
					int id = rs.getInt("employee_id");
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");
					String jobId = rs.getString("job_id");
					java.sql.Date hireDate = rs.getDate("hire_date");
					BigDecimal salary = rs.getBigDecimal("salary");
					
					if (Objects.equals(jobId, kogoSzukam)) {
						System.out.printf("%3d | %-11s | %-11s | %10s | %10s | %8s\n", id, firstName, lastName, jobId,
								hireDate, salary);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		

	}

}
