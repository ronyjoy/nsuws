package com.nsuws.resources.com.nsuws.core.crypto;

import com.nsuws.core.crypto.CryptoAesCbc;
import org.junit.Assert;
import org.junit.Test;

public class CryptoAesCbcTest {

    @Test
    public void aesCbcEncryptionDecryptionOfAText() throws Exception {
        String text = "Aes256CBC";
        CryptoAesCbc en=new CryptoAesCbc();
        String encryptedWord=en.encrypt(text);
        Assert.assertEquals(text,en.decrypt(encryptedWord));
    }
    @Test
    public void aesCbcEncryptionDecryptionOfANumber() throws Exception {
        String text = "22.0";
        CryptoAesCbc en=new CryptoAesCbc();
        String encryptedWord=en.encrypt(text);
        Assert.assertEquals(text,en.decrypt(encryptedWord));
    }
}
