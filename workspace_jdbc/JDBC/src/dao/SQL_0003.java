package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQL_0003 {

	public static void main(String args[]) {

		String bloodType = "O";
		int maxAge = 10;

		execute(bloodType, maxAge);
	}

	public static void execute(String bloodType, int maxAge) {

		Connection conn = null;
		PreparedStatement pre = null;

		try {
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres",
					"postgres",
					"postgres");

			StringBuilder sb = new StringBuilder();
			sb.append("select ");
			sb.append("  *");
			sb.append("from public.tb_mst_family ");
			sb.append("where blood  = ? ");
			sb.append(" and age < ? ");
			sb.append(";");
			/* SQL_0001 */
			String sql = sb.toString();

			pre = conn.prepareStatement(sql);
			pre.setString(1, bloodType);
			pre.setInt(2, maxAge);
			ResultSet rs = pre.executeQuery();

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
				if (pre != null) {
					pre.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
