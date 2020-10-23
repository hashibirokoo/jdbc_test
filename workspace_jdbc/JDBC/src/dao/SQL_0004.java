package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.SqlUtil;

public class SQL_0004 {

	public static void main(String args[]) {

		List<Integer> idList = new ArrayList<Integer>();
		idList.add(1);
		idList.add(3);

		execute(idList);
	}

	public static void execute(List<Integer> idList) {

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
			sb.append("where kazoku_id in ( ");
			String strIdList = SqlUtil.createWhereInc(idList.size());
			sb.append(strIdList);
			sb.append(" ) ");
			sb.append(";");
			/* SQL_0001 */
			String sql = sb.toString();

			pre = conn.prepareStatement(sql);
			SqlUtil.setPreparedInput(pre, idList);
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
