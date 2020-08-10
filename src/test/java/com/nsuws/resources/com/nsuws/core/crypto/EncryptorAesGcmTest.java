package com.nsuws.resources.com.nsuws.core.crypto;

import com.nsuws.core.crypto.CryptoException;
import com.nsuws.core.crypto.EncryptorAesGcm;
import org.junit.Assert;
import org.junit.Test;

public class EncryptorAesGcmTest {

    @Test
    public void aesGCMEncryptionDecryptionOfAText() throws CryptoException {
        String text = "Aes256GCM Encryption test string";
        EncryptorAesGcm en=new EncryptorAesGcm("UkXp2s5v8y/A?D(G+KbPeShVmYq3t6w9");
        String encryptedWord=en.encrypt(text);
        Assert.assertEquals(text,en.decrypt(encryptedWord));
    }

    @Test
    public void aesGCMEncryptionDecryptionOfANumber() throws CryptoException {
        String text = "22.0";
        EncryptorAesGcm en=new EncryptorAesGcm("UkXp2s5v8y/A?D(G+KbPeShVmYq3t6w9");
        String encryptedWord=en.encrypt(text);
        Assert.assertEquals(text,en.decrypt(encryptedWord));
    }


}
