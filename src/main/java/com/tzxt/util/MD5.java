package com.tzxt.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5加密算法
 *
 * @since 2012-08-08
 */

public class MD5 {

    /**
     * MD5普通加密
     *
     * @param rawPass 明文
     * @return
     */
    public static String encode(String rawPass) {
        return encode(rawPass, null);
    }

    /**
     * * MD5盐值加密
     *
     * @param rawPass 明文
     * @param salt    盐值
     * @return
     */
    public static String encode(String rawPass, Object salt) {
        String saltedPass = mergePasswordAndSalt(rawPass, salt);
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] digest = messageDigest.digest(saltedPass.getBytes("UTF-8"));
            return new String(encode(digest));
        } catch (Exception e) {
            return rawPass;
        }
    }

    /**
     * 拼接密码与盐值
     *
     * @param password
     * @param salt
     * @return 密码{盐值}
     */
    private static String mergePasswordAndSalt(String password, Object salt) {
        if (salt == null || "".equals(salt.toString().trim())) {
            return password;
        }
        return password + "{" + salt.toString() + "}";
    }

    /**
     * encode
     *
     * @param bytes
     * @return
     */
    private static char[] encode(byte[] bytes) {
        char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        int nBytes = bytes.length;
        char[] result = new char[2 * nBytes];
        int j = 0;
        for (byte aByte : bytes) { // Char for top 4 bits result[j++] = HEX[(0xF0 & bytes[i]) >>> 4];

            // Bottom 4
            result[j++] = HEX[(0x0F & aByte)];
        }
        return result;
    }

    public static String encode1(String str) throws NoSuchAlgorithmException {
        MessageDigest digester = MessageDigest.getInstance("MD5");
        digester.update(str.getBytes());
        byte[] hash = digester.digest();
        StringBuilder hexString = new StringBuilder();
        for (byte aHash : hash) {
            if ((0xff & aHash) < 0x10) {
                hexString.append("0").append(Integer.toHexString((0xFF & aHash)));
            } else {
                hexString.append(Integer.toHexString(0xFF & aHash));
            }
        }
        return hexString.toString();
    }

    /**
     * Test
     *
     * @param args
     */
    public static void main(String[] args) {
        // 21232f297a57a5a743894a0e4a801fc3
        // 1ac99a9be6072f8e                
        System.out.println(MD5.encode("123456"));
        System.out.println(MD5.encode("admin").equals(MD5.encode("admin")));
        System.out.println(MD5.encode("admin", "zhanqi"));
    }
}