package com.reversehash.crypto;

import org.junit.Test;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class AESTest {
    @Test
    public void directEncryptDecryptTest() {
        String message = "Hello how is encrypting and decrypting?";
        byte[] cipherText = AESInputStream.directEncrypt("sudip".getBytes(), message.getBytes());
        byte[] msg = AESOutputStream.directDecrypt("sudip".getBytes(), cipherText);
        System.out.println("Output message :" + new String(msg));
        assert (Arrays.equals(msg, message.getBytes()));
    }

    @Test
    public void getInstanceTest() throws BadPaddingException, IllegalBlockSizeException {
        Cipher encoder = AESInputStream.getAESInstance("sudip".getBytes());
        Cipher decoder = AESOutputStream.getAESInstance("sudip".getBytes());
        String message="Hello how is encrypting and decrypting?";
        byte[] msg=decoder.doFinal(encoder.doFinal(message.getBytes()));
        System.out.println("Output message :" + new String(msg));
        assert (Arrays.equals(msg, message.getBytes()));



    }

}