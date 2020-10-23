package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.SqlUtil;

public class SQL_0006 {

	public static void main(String args[]) {

		int id = 5;
		String blood = "Z";

		execute(id, blood);
	}

	public static void execute(int id, String blood) {

		Connection conn = null;
		PreparedStatement pre = null;

		List<Object> list = new ArrayList<Object>();

		try {
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres",
					"postgres",
					"postgres");

			StringBuilder sb = new StringBuilder();
			sb.append("update ");
			sb.append("  tb_mst_family ");
			sb.append("set blood = ? ");
			list.add(blood);
			sb.append("where kazoku_id = ? ");
			list.add(id);
			sb.append(";");
			/* SQL_0001 */
			String sql = sb.toString();

			pre = conn.prepareStatement(sql);
			SqlUtil.setPreparedInput(pre, list);
			int count = pre.executeUpdate();

			System.out.println("更新件数：" + count);
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
