package gotowe.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class cw1 {
	public static void main (String[]args)
	{
		try(Connection c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/hr",
				"kurs",
				"abc123"))
		{
			try(Statement ps = c.createStatement() )
			{
				final String sql= "SELECT * FROM employees WHERE job_id = ? ORDER BY employee_id";
				try(ResultSet rs= ps.executeQuery(sql))
				{
					while(rs.next())
					{
						System.out.print(rs.getString("first_name"));
						System.out.print(" ");
						System.out.println(rs.getString("last_name"));

					}
				}
			}
		}
	}

}
