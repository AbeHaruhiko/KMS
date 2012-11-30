package jp.caliconography.util;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class PBKDF2 {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {
        String p1 = args[0];
        String p2 = args[1];
        printfln("password 1: %s", p1);
        printfln("password 2: %s", p2);

        byte[] salt = createSalt();
        printfln("salt: %s", Arrays.toString(salt));

        byte[] d1 = pbkdf2(p1.toCharArray(), salt);
        byte[] d2 = pbkdf2(p2.toCharArray(), salt);
        printfln("derived 1: %s", Arrays.toString(d1));
        printfln("derived 2: %s", Arrays.toString(d2));
    }

    // ランダムなsaltを生成
    static byte[] createSalt() throws NoSuchAlgorithmException {
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[32];
        random.nextBytes(salt);
        return salt;
    }

    static byte[] pbkdf2(char[] password, byte[] salt) throws InvalidKeySpecException, NoSuchAlgorithmException {
        // Oracle JDKに含まれる PBKDF2 の唯一の実装
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        // 繰り返し回数：10000回、結果の長さ：256bit
        KeySpec ks = new PBEKeySpec(password, salt, 10000, 256);
        SecretKey s = f.generateSecret(ks);
        return s.getEncoded();
    }

    // 補助
    private static void printfln(String format, Object ... args) {
        System.out.printf(format + "%n", args);
    }
}