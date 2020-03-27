package com.pfxiong.demo.ipcp;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacUtils;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * 通用加密工具
 *
 * @author gzlibingjie
 */
public class EncryptUtil {

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
            e.printStackTrace();
        }
        return null;
    }


}
