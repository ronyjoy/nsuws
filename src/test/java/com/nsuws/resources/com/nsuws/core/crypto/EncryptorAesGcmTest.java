package com.nsuws.resources.com.nsuws.core.crypto;

import com.nsuws.core.crypto.EncryptorAesGcm;
import org.junit.Assert;
import org.junit.Test;

public class EncryptorAesGcmTest {

    @Test
    public void aesGCMEncryptionDecryptionOfAText() throws Exception {
        String text = "Aes256GCM Encryption test string";
        EncryptorAesGcm en=new EncryptorAesGcm();
        String encryptedWord=en.encrypt(text);
        Assert.assertEquals(text,en.decrypt(encryptedWord));
    }
    @Test
    public void aesGCMEncryptionDecryptionOfANumber() throws Exception {
        String text = "22.0";
        EncryptorAesGcm en=new EncryptorAesGcm();
        String encryptedWord=en.encrypt(text);
        Assert.assertEquals(text,en.decrypt(encryptedWord));
    }


}
