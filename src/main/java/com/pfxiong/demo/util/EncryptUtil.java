package com.pfxiong.demo.util;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

/**
 * 通用加密工具
 *
 * @author gzlibingjie
 */
public class EncryptUtil {

    public static final String MD5 = "MD5";

    public static final String SHA = "SHA";

    public static final String SHA256 = "SHA-256";

    public static final String SHA_1 = "SHA-1";

    private static final Logger logger = Logger.getLogger(EncryptUtil.class);

    /**
     * HMAC-SHA1 加密
     *
     * @param content 明文
     * @return 密文
     */
    public static String md5(String content) {
        if (content == null) {
            return content;
        }
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(MD5);
        } catch (NoSuchAlgorithmException e) {
            // ignore, impossible, just rethrow
            throw new RuntimeException(e);
        }
        byte[] result = digest.digest(content.getBytes());
        return bytesToHex(result);
    }

    /**
     * HMAC-SHA1 加密
     *
     * @param content 明文
     * @return 密文
     */
    public static String md5(byte[] content) {
        if (content == null) {
            return null;
        }
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(MD5);
        } catch (NoSuchAlgorithmException e) {
            // ignore, impossible, just rethrow
            throw new RuntimeException(e);
        }
        byte[] result = digest.digest(content);
        return bytesToHex(result);
    }
    public static String sha256(String content) {
        if (content == null) {
            return content;
        }
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance(SHA256);
        } catch (NoSuchAlgorithmException e) {
            // ignore, impossible, just rethrow
            throw new RuntimeException(e);
        }
        byte[] result = digest.digest(content.getBytes());
        return bytesToHex(result);
    }

    /**
     * HMAC-SHA1 加密
     *
     * @param key     秘钥
     * @param content 明文
     * @return 密文
     */
    public static byte[] hmacSha1(String key, String content) {
        Mac mac = HmacUtils.getHmacSha1(key.getBytes());
        HmacUtils.updateHmac(mac, content);
        return mac.doFinal();
    }

    /**
     * HMAC-MD5 加密
     *
     * @param key
     * @param content
     * @return
     */
    public static byte[] hmacMd5(String key, String content) {
        Mac mac = HmacUtils.getHmacMd5(key.getBytes());
        HmacUtils.updateHmac(mac, content);
        return mac.doFinal();
    }

    /**
     * HMAC-SHA256 加密
     *
     * @param key     秘钥
     * @param content 明文
     * @return 密文
     */
    public static byte[] hmacSha256(String key, String content) {
        Mac mac = HmacUtils.getHmacSha256(key.getBytes());
        HmacUtils.updateHmac(mac, content);
        return mac.doFinal();
    }

    /**
     * hash加密，支持MD5和SHA和SHA-1
     *
     * @param source   需要加密的字符串
     * @param hashType 加密类型 （MD5、SHA和SHA-1）
     * @return 加密后的字符串
     */
    public static String getHash(String source, String hashType) {
        // 用来将字节转换成 16 进制表示的字符
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

        try {
            MessageDigest md = MessageDigest.getInstance(hashType);
            md.update(source.getBytes());    // 通过使用 update 方法处理数据,使指定的 byte数组更新摘要
            byte[] encryptStr = md.digest();    // 获得密文完成哈希计算,产生128 位的长整数
            char[] str = new char[16 * 2];    // 每个字节用 16 进制表示的话，使用两个字符
            int k = 0; // 表示转换结果中对应的字符位置
            for (int i = 0; i < 16; i++) {    // 从第一个字节开始，对每一个字节,转换成 16 进制字符的转换
                byte byte0 = encryptStr[i];    // 取第 i 个字节
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];    // 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移
                str[k++] = hexDigits[byte0 & 0xf];    // 取字节中低 4 位的数字转换
            }
            return new String(str);    // 换后的结果转换为字符串
        } catch (NoSuchAlgorithmException e) {
            logger.error("hash方法未找到", e);
        }
        return null;
    }

    /**
     * 将字节数值转化为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    public static String bytesToHex(byte[] bytes) {
        final StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }

    /**
     * 128位AES加密
     *
     * @param keyInHexString 十六进制密钥
     * @param content        内容
     * @return 加密后的十六进制字符串
     */
    public static String aes128Encode(String keyInHexString, String content) {
        if (keyInHexString == null || content == null || keyInHexString.length() != 32) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("UTF-8");

            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Hex.decodeHex(keyInHexString.toCharArray()), "AES"));
            byte[] result = cipher.doFinal(byteContent);

            return Hex.encodeHexString(result);
        } catch (Exception e) {
            logger.error("AES加密失败", e);
        }
        return null;
    }

    /**
     * 128位AES解密
     *
     * @param keyInHexString   十六进制密钥
     * @param hexContentString 加密的十六进制字符串内容
     * @return 解密后的内容
     */
    public static String aes128Decode(String keyInHexString, String hexContentString) {
        if (keyInHexString == null || hexContentString == null) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Hex.decodeHex(keyInHexString.toCharArray()), "AES"));
            return new String(cipher.doFinal(Hex.decodeHex(hexContentString.toCharArray())), "UTF-8");
        } catch (Exception e) {
            logger.error("AES解密失败", e);
        }
        return null;
    }

    private static final Pattern BASE64_PATTERN = Pattern.compile("([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)");

    /**
     * 检查是否baase64编码
     *
     * @param c 字符串
     * @return 是否base64编码
     */
    public static boolean isBase64(String c) {
        if (c == null) {
            return false;
        }

        return BASE64_PATTERN.matcher(c).matches();
    }

    /**
     * SHA1加密
     * @param s 原始字符串
     * @return 加密后的十六进制字符串
     */
    public static String sha1(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        return DigestUtils.sha1Hex(s);
    }
}
