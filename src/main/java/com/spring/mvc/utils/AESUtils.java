package com.spring.mvc.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.*;
import javax.crypto.spec.*;

/**
 * Created by liluoqi on 15/5/4.
 */
public class AESUtils {

    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    private static final String KEY_ALGORITHM = "AES";

    private static KeyGenerator keyGen;

    private static Cipher cipher;

    private static boolean isInitiated = false;

    private static Logger logger = Logger.getLogger(AESUtils.class);

    //初始化
    private static void init() {

        //初始化keyGen
        try {
            keyGen = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            logger.error(String.format("NoSuchAlgorithmException happens,message:%s,stackTrace:%s", e.getMessage(), e.getStackTrace()));
        }
        keyGen.init(128);

        //初始化cipher
        try {
            cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            logger.error(String.format("NoSuchAlgorithmException happens,message:%s,stackTrace:%s", e.getMessage(), e.getStackTrace()));
        } catch (NoSuchPaddingException e) {
            logger.error(String.format("NoSuchPaddingException happens,message:%s,stackTrace:%s", e.getMessage(), e.getStackTrace()));
        }

        isInitiated = true;
    }

    public static byte[] GenKey() {
        if (!isInitiated)//如果没有初始化过,则初始化
        {
            init();
        }
        return keyGen.generateKey().getEncoded();
    }

    public static byte[] Encrypt(byte[] content, byte[] keyBytes) {
        byte[] encryptedText = null;

        if (!isInitiated)//为初始化
        {
            init();
        }

        Key key = new SecretKeySpec(keyBytes, KEY_ALGORITHM);

        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            logger.error(String.format("InvalidKeyException happens,message:%s,stackTrace:%s", e.getMessage(), e.getStackTrace()));
        }

        try {
            encryptedText = cipher.doFinal(content);
        } catch (IllegalBlockSizeException e) {
            logger.error(String.format("IllegalBlockSizeException happens,message:%s,stackTrace:%s", e.getMessage(), e.getStackTrace()));
        } catch (BadPaddingException e) {
            logger.error(String.format("BadPaddingException happens,message:%s,stackTrace:%s", e.getMessage(), e.getStackTrace()));
        }

        return encryptedText;
    }


    //解密为byte[]
    public static byte[] decryptToBytes(byte[] content, byte[] keyBytes) {
        byte[] originBytes = null;
//        if (!isInitiated) {
//            init();
//        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, KEY_ALGORITHM);

        try {
            Cipher cipherOne = Cipher.getInstance(CIPHER_ALGORITHM);
            cipherOne.init(Cipher.DECRYPT_MODE, secretKeySpec);
            //解密
            originBytes = cipherOne.doFinal(content);
        } catch (IllegalBlockSizeException e) {
            logger.error(String.format("IllegalBlockSizeException happens,message:%s,stackTrace:%s", e.getMessage(), e.getStackTrace()));
        } catch (BadPaddingException e) {
            logger.error(String.format("BadPaddingException happens,message:%s,stackTrace:%s", e.getMessage(), e.getStackTrace()));
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            logger.error(String.format("InvalidKeyException happens,message:%s,stackTrace:%s", e.getMessage(), e.getStackTrace()));
        } catch (NoSuchAlgorithmException e) {
            logger.error(String.format("NoSuchAlgorithmException happens,message:%s,stackTrace:%s", e.getMessage(), e.getStackTrace()));
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            logger.error(String.format("NoSuchPaddingException happens,message:%s,stackTrace:%s", e.getMessage(), e.getStackTrace()));
            e.printStackTrace();
        }

        return originBytes;
    }

    //解密为byte[]
    public static byte[] decryptToBytes(String content, String keyString) {
        byte[] originBytes = null;
        if (!isInitiated) {
            init();
        }
        try {
            Key key = new SecretKeySpec(Base64.decodeBase64(keyString.getBytes("utf-8")), KEY_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, key);
            //解密
            originBytes = cipher.doFinal(Base64.decodeBase64(content.getBytes("utf-8")));
        } catch (IllegalBlockSizeException e) {
            logger.error(String.format("IllegalBlockSizeException happens,message:%s,stackTrace:%s", e.getMessage(), e.getStackTrace()));
        } catch (BadPaddingException e) {
            logger.error(String.format("BadPaddingException happens,message:%s,stackTrace:%s", e.getMessage(), e.getStackTrace()));
        } catch (UnsupportedEncodingException e) {
            logger.error(String.format("UnsupportedEncodingException happens,message:%s,stackTrace:%s", e.getMessage(), e.getStackTrace()));
        } catch (InvalidKeyException e) {
            logger.error(String.format("InvalidKeyException happens,message:%s,stackTrace:%s", e.getMessage(), e.getStackTrace()));
        }
        return originBytes;
    }
}
