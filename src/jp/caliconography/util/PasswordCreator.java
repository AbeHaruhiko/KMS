package jp.caliconography.util;

import java.security.SecureRandom;

public class PasswordCreator {

	/**
	 * ランダムな英数字からなる指定サイズの文字列を返す。
	 * 
	 * @param size
	 *            文字列のサイズ
	 * @return str 生成された文字列
	 */
	static public String createRandomPassword(int size) {

		SecureRandom random = new SecureRandom();
		char[] pass = new char[size];

		for (int k = 0; k < pass.length; k++) {
			switch (random.nextInt(3)) {
			case 0: // 'a' - 'z'
				pass[k] = (char) (97 + random.nextInt(26));
				break;
			case 1: // 'A' - 'Z'
				pass[k] = (char) (65 + random.nextInt(26));
				break;
			case 2: // '0' - '9'
				pass[k] = (char) (48 + random.nextInt(10));
				break;
			default:
				pass[k] = 'a';
			}
		}
		// 文字の配列を文字列に変換
		return new String(pass);

	}
}
