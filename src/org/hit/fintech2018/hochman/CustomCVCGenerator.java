package org.hit.fintech2018.hochman;

public class CustomCVCGenerator implements CVCGenerator {
    @Override
    public byte[] getCVCValue(byte[] data, byte[] key1, byte[] key2, int digits) {
        // copy base data array
        byte[] new_data = new byte[16];
        System.arraycopy(data, 0, new_data, 0, data.length);

        // split field into two 64-bit blocks
        byte[] block1 = new byte[8];
        byte[] block2 = new byte[8];

        int half = new_data.length/2;

        System.arraycopy(new_data, 0, block1, 0, new_data.length/2);
        System.arraycopy(new_data, half, block2, half, new_data.length);

        // encrypt block1 with key1
        block1 = UtilDES.DESEncrypt(key1, block1);

        // xor block1 with block two, then with key1
        byte[] xored1 = new byte[8];

        for (int i = 0; i < xored1.length; i++){
            xored1[i] = (byte) (block1[i] ^ block2[i]);
            xored1[i] = (byte) (xored1[i] ^ key1[i]);
        }

        // 3des block1 with key

        byte[] key_for_3des = new byte[24];
        System.arraycopy(key1, 0, key_for_3des, 0, 8);
        System.arraycopy(key2, 8, key_for_3des, 8, 16);
        System.arraycopy(key1, 16, key_for_3des, 16, 24);

        block1 = UtilDES.DES3(key_for_3des, block1);

        String.format("%02x", block1[0]);

        return new byte[0];
    }

    @Override
    public byte[] getCVCValue(byte[] pan, byte[] expiry, byte[] serviceCode, byte[] key1, byte[] key2, int digits) {
        return new byte[0];
    }

    @Override
    public boolean checkCVCValue(byte[] data, byte[] key1, byte[] key2, byte[] cvcValue) {
        return false;
    }
}
