package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * <p>[概要]レコード削除(ID)</p>
 * <p>[詳細]家族IDでレコードを削除します。</p>
 * <p>[備考]</p>
 * <p>[作成日]2020/10/22</p>
 * @author ken
 *
 */
public class SQL_0007 {

	public static void main(String args[]) {

		int id = 5;

		execute(id);
	}

	public static void execute(int id) {

		Connection conn = null;
		PreparedStatement pre = null;

		try {
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres",
					"postgres",
					"postgres");

			StringBuilder sb = new StringBuilder();
			sb.append("delete ");
			sb.append(" from tb_mst_family ");
			sb.append("where kazoku_id = ? ");
			sb.append(";");
			/* SQL_0001 */
			String sql = sb.toString();

			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			int count = pre.executeUpdate();

			System.out.println("削除件数：" + count);
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
