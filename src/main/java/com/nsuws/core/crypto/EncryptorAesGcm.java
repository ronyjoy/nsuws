package com.nsuws.core.crypto;

import com.nsuws.NsuwsAPIConfig;

import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * Doing AES GCM Encryption
 */
public class EncryptorAesGcm {

    //AES GCM is faster than AES CBC
    private static final String ENCRYPT_ALGO = "AES/GCM/NoPadding";
    private static final int TAG_LENGTH_BIT = 128;
    private static final int IV_LENGTH_BYTE = 12;
    private static final int AES_KEY_BIT = 256;
    private static final Charset UTF_8 = StandardCharsets.UTF_8;
    private SecretKey secret;

    public EncryptorAesGcm(String key) {
        byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
        if (keyBytes.length != 32) throw new IllegalArgumentException();
        this.secret = new SecretKeySpec(keyBytes, "AES");
    }

    public String encrypt(String plainText) throws CryptoException{
        byte[] cipher = encrypt(plainText.getBytes(UTF_8));
        byte[] encodedCipher = Base64.getEncoder().encode(cipher);
        return  new String(encodedCipher, StandardCharsets.UTF_8);
    }

    public  byte[] encrypt(byte[] plainText) throws CryptoException {
        byte[] iv = getRandomNonce(IV_LENGTH_BYTE);
        byte[] cipherText = encrypt(plainText, iv);
        byte[] cipherTextWithIv = ByteBuffer.allocate(iv.length + cipherText.length)
                .put(iv)
                .put(cipherText)
                .array();
        return cipherTextWithIv;
    }

    private byte[] encrypt(byte[] plainText, byte[] iv) throws CryptoException {
        Cipher cipher = null;
        byte[] encryptedText = null;
        try {
            cipher = Cipher.getInstance(ENCRYPT_ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
            encryptedText = cipher.doFinal(plainText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            throw new CryptoException(e);
        }
        return encryptedText;
    }

    public String decrypt(String cipherText) throws CryptoException{
        byte[] decodedCipherText = Base64.getDecoder().decode(cipherText);
        byte[] plainText = decrypt(decodedCipherText);
        return  new String(plainText, StandardCharsets.UTF_8);
    }

    public byte[] decrypt(byte[] cipherTextPrefixedIv) throws CryptoException {
        ByteBuffer bb = ByteBuffer.wrap(cipherTextPrefixedIv);
        byte[] iv = new byte[IV_LENGTH_BYTE];
        bb.get(iv);
        byte[] cipherText = new byte[bb.remaining()];
        bb.get(cipherText);
        return decrypt(cipherText, iv);
    }


    private  byte[] decrypt(byte[] cText, byte[] iv) throws CryptoException {
        Cipher cipher = null;
        byte[] plainTxt = null;
        try {
            cipher = Cipher.getInstance(ENCRYPT_ALGO);
            cipher.init(Cipher.DECRYPT_MODE, secret, new GCMParameterSpec(TAG_LENGTH_BIT, iv));
            plainTxt = cipher.doFinal(cText);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException |
                InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
        }
        return plainTxt;
    }


    public static byte[] getRandomNonce(int numBytes) {
        byte[] nonce = new byte[numBytes];
        new SecureRandom().nextBytes(nonce);
        return nonce;
    }
}