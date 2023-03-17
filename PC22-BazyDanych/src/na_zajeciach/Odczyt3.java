package na_zajeciach;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Odczyt3 {

	public static void main(String[] args) {
		try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost/hr", "kurs", "abc123");
			PreparedStatement stmt = c.prepareStatement("SELECT * FROM employees");
			ResultSet rs = stmt.executeQuery()) {
			
			while(rs.next()) {
				// w tej wersji odczytujemy pola podając nazwy kolumn
				int id = rs.getInt("employee_id");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String jobId = rs.getString("job_id");
				java.sql.Date hireDate = rs.getDate("hire_date");
				BigDecimal salary = rs.getBigDecimal("salary");
				
				System.out.printf("%3d | %-11s | %-11s | %10s | %10s | %8s\n", id, firstName, lastName, jobId, hireDate, salary);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
