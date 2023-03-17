package na_zajeciach;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

/* Wersja poprawna, używająca PreparedStatement i parametrów ? 
 * 
 * Ta wersja jest i bezpieczna, i wydajna.
 */
public class Parametry3 {

	public static void main(String[] args) {
		String kogoSzukam = JOptionPane.showInputDialog("Podaj job id, np. IT_PROG");
		// TODO Program ma wypisać tylko tych pracowników z bazy, których job_id = kogoSzukam
		
		try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/hr", "kurs", "abc123");
			PreparedStatement stmt = c.prepareStatement("SELECT * FROM employees WHERE job_id = ?")) {
			
			// pomiędzy prepareStatement a executeQuery należy ustawić wartości parametrów
			stmt.setString(1, kogoSzukam);
			
			System.out.println("Za chwilę wykonam " + stmt);
			System.out.println();
			
			try(ResultSet rs = stmt.executeQuery()) {
				while(rs.next()) {
					int id = rs.getInt("employee_id");
					String firstName = rs.getString("first_name");
					String lastName = rs.getString("last_name");
					String jobId = rs.getString("job_id");
					java.sql.Date hireDate = rs.getDate("hire_date");
					BigDecimal salary = rs.getBigDecimal("salary");
					
					System.out.printf("%3d | %-11s | %-11s | %10s | %10s | %8s\n", id, firstName, lastName, jobId,
							hireDate, salary);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
