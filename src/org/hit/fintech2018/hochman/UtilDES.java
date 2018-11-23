package org.hit.fintech2018.hochman;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class UtilDES {
    public static byte[] DESEncrypt(byte[] key, byte[] message){
        SecretKey new_key = new SecretKeySpec(key, "DESEncrypt");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DESEncrypt/ECB/NoPadding");
            Objects.requireNonNull(cipher).init(Cipher.ENCRYPT_MODE, new_key);
            return  Objects.requireNonNull(cipher).doFinal(message);
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] DES3(byte[] key, byte[] message){
        SecretKey new_key = new SecretKeySpec(key, "DESede");
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("DESede/ECB/NoPadding");
            Objects.requireNonNull(cipher).init(Cipher.ENCRYPT_MODE, new_key);
            return  Objects.requireNonNull(cipher).doFinal(message);
        } catch (IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }
}
