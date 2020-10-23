package util;

public class CheckUtil {

	/**
	 * <p>[概要] 未設定チェック(String)。</p>
	 * <p>[詳細] Stringの値がnullであるか判定する。</p>
	 * <p>[備考] </p>
	 * @param val 対象
	 * @return チェック結果
	 */
	static boolean checkEmpty(String val) {
		return (val == null) ? true : val.isEmpty();
	}
}
