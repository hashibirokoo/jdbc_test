package util;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class SqlUtil {

	public static final String PREFIX_AND = "and";

	public static final String PREFIX_OR = "or";

	public static final String DELIMETER_COMMA = ", ";

	public static String createWhereInc(int ilength) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < ilength; i++) {
			if (!"".equals(sb.toString())) {
				sb.append(DELIMETER_COMMA);
			}

			sb.append("?");

		}

		return sb.toString();
	}

	public static int setPreparedInput(PreparedStatement pre, List<?> list) throws SQLException {
		return setPreparedInput(pre, list, 0);
	}

	public static int setPreparedInput(PreparedStatement pre, List<?> list, int iStart) throws SQLException {

		int iCnt = iStart;
		Object obj;
		for (int i = iCnt; i < list.size(); i++) {
			obj = list.get(i);

			//String
			if (obj instanceof String) {
				pre.setString(++iCnt, (String) obj);
			}

			//Integer
			else if (obj instanceof Integer) {
				pre.setInt(++iCnt, (Integer) obj);
			}
			//Long
			else if (obj instanceof Long) {
				pre.setLong(++iCnt, (Long) obj);
			}
			//java.util.Date
			else if (obj instanceof java.util.Date) {
				pre.setTimestamp(++iCnt, toTimestamp(((java.util.Date) obj)));
			}
			// String[]
			else if (obj instanceof String[]) {
				String[] strArray = (String[]) obj;
				for (int j = 0; j < strArray.length; j++) {
					pre.setString(++iCnt, strArray[j]);
				}
			}
			// Integer[]
			else if (obj instanceof Integer[]) {
				Integer[] integerArray = (Integer[]) obj;
				for (int j = 0; j < integerArray.length; j++) {
					pre.setInt(++iCnt, integerArray[j]);
				}
			}
			// Long[]
			else if (obj instanceof Long[]) {
				Long[] longArray = (Long[]) obj;
				for (int j = 0; j < longArray.length; j++) {
					pre.setLong(++iCnt, longArray[j]);
				}
			}
			// java.util.Date[]
			else if (obj instanceof java.util.Date[]) {
				java.util.Date[] dayArray = (java.util.Date[]) obj;
				for (int j = 0; j < dayArray.length; j++) {
					pre.setTimestamp(++iCnt, toTimestamp(dayArray[j]));
				}
			}
			// java.sql.Date[]
			else if (obj instanceof java.sql.Date[]) {
				java.sql.Date[] dayArray = (java.sql.Date[]) obj;
				for (int j = 0; j < dayArray.length; j++) {
					pre.setDate(++iCnt, dayArray[j]);
				}
			}
			// List]
			else if (obj instanceof List) {
				int step = setPreparedInput2(pre, (List<?>) obj, iCnt + 1);
				iCnt = iCnt + step;
			}
		}
		return iCnt - iStart;
	}

	public static int setPreparedInput2(PreparedStatement pre, List<?> list, int iStart) throws SQLException {

		int iCnt = iStart;
		Object obj;
		for (int i = iCnt; i < list.size(); i++) {
			obj = list.get(i);

			//String
			if (obj instanceof String) {
				pre.setString(++iCnt, (String) obj);
			}

			//Integer
			else if (obj instanceof Integer) {
				pre.setInt(++iCnt, (Integer) obj);
			}
			//Long
			else if (obj instanceof Long) {
				pre.setLong(++iCnt, (Long) obj);
			}
			//java.util.Date
			else if (obj instanceof java.util.Date) {
				pre.setTimestamp(++iCnt, toTimestamp(((java.util.Date) obj)));
			}
			// String[]
			else if (obj instanceof String[]) {
				String[] strArray = (String[]) obj;
				for (int j = 0; j < strArray.length; j++) {
					pre.setString(++iCnt, strArray[j]);
				}
			}
			// Integer[]
			else if (obj instanceof Integer[]) {
				Integer[] integerArray = (Integer[]) obj;
				for (int j = 0; j < integerArray.length; j++) {
					pre.setInt(++iCnt, integerArray[j]);
				}
			}
			// Long[]
			else if (obj instanceof Long[]) {
				Long[] longArray = (Long[]) obj;
				for (int j = 0; j < longArray.length; j++) {
					pre.setLong(++iCnt, longArray[j]);
				}
			}
			// java.util.Date[]
			else if (obj instanceof java.util.Date[]) {
				java.util.Date[] dayArray = (java.util.Date[]) obj;
				for (int j = 0; j < dayArray.length; j++) {
					pre.setTimestamp(++iCnt, toTimestamp(dayArray[j]));
				}
			}
			// java.sql.Date[]
			else if (obj instanceof java.sql.Date[]) {
				java.sql.Date[] dayArray = (java.sql.Date[]) obj;
				for (int j = 0; j < dayArray.length; j++) {
					pre.setDate(++iCnt, dayArray[j]);
				}
			}
			// List]
			else if (obj instanceof List) {
				int step = setPreparedInput2(pre, (List<?>) obj, iCnt + 1);
				iCnt = iCnt + step;
			}
		}
		return iCnt - iStart;
	}

	public static Timestamp toTimestamp(Date val) {
		return (val == null) ? null : new Timestamp(val.getTime());
	}

	public static Date toDate(Timestamp val) {
		return (val == null) ? null : new Date(val.getTime());
	}

	public static Integer toInteger(String val) {
		return (CheckUtil.checkEmpty(val) ? null : Integer.valueOf(val));
	}
}
