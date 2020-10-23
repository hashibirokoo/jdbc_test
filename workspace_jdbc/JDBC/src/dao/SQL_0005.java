package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.SqlUtil;

/**
 * <p>[概要]レコード追加</p>
 * <p>[詳細]レコードを追加します。</p>
 * <p>[備考]</p>
 * <p>[作成日]2020/10/22</p>
 * @author ken
 *
 */
public class SQL_0005 {

	public static void main(String args[]) {

		List<Object> idList = new ArrayList<Object>();
		idList.add(5);
		idList.add("unknown");
		idList.add(99);
		idList.add("O");

		execute(idList);
	}

	public static void execute(List<Object> idList) {

		Connection conn = null;
		PreparedStatement pre = null;

		try {
			conn = DriverManager.getConnection(
					"jdbc:postgresql://localhost:5432/postgres",
					"postgres",
					"postgres");

			StringBuilder sb = new StringBuilder();
			sb.append("insert into ");
			sb.append("  tb_mst_family ( ");
			sb.append(" kazoku_id ");
			sb.append(" ,name ");
			sb.append(" ,age ");
			sb.append(" ,blood) ");
			sb.append("values ( ");
			String strColmunList = SqlUtil.createWhereInc(idList.size());
			sb.append(strColmunList);
			sb.append(" ) ");
			sb.append(";");
			/* SQL_0001 */
			String sql = sb.toString();

			pre = conn.prepareStatement(sql);
			SqlUtil.setPreparedInput(pre, idList);
			int count = pre.executeUpdate();

			System.out.println("追加件数：" + count);
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
