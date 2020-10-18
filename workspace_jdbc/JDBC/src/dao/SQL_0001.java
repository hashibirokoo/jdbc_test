package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQL_0001 {

	public static void main(String args[]) {
		execute();
	}

	public static void execute() {

		Connection conn = null;
		Statement stmt = null;

		try {
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres",
					"postgres",
					"postgres");

			StringBuilder sb = new StringBuilder();
			sb.append("select ");
			sb.append("  *");
			sb.append("from public.tb_mst_family ");
			sb.append(";");
			/* SQL_0001 */
			String sql = sb.toString();

			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
			    int kazoku_id = rs.getInt(1);
			    String name = rs.getString("name");
			    int age = rs.getInt(3);
			    String blood = rs.getString("blood");

			    System.out.println(
			    		kazoku_id
			    		+ ", "
			    		+ name
			    		+ ", "
			    		+ age
			    		+ ", "
			    		+ blood);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			try {
				if (stmt != null) {
					stmt.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
