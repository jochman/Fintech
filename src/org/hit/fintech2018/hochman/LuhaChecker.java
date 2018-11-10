package org.hit.fintech2018.hochman;

import java.util.Arrays;

class LuhnChecker {
    public static byte getLuhnDigit(byte[] data) throws Exception{
        if (!is_data_valid(data)){
            throw new Exception("Wrong input. null or contains values below 0 or above 9");
        }

        int sum = 0;
        for (int i = data.length-1; i > -1; i--){
            int complement = data.length - 1 -  i;
            byte b = data[i];
            Integer vof = Integer.valueOf(Byte.toString(b));
            if ((complement % 2) == 1){
                sum += vof;
            }else{
                vof = (vof*2)%9;
                sum += vof;
            }
        }
        sum %= 10;

        return (byte) ((10-sum)%10);
    }

    private static boolean is_data_valid(byte[] data) {
        if (data.length <= 1){
            return false;
        }
        for (int i = 0; i < data.length-1; i++){
            if (9 < data[i] || data[i] < 0 ){
                return false;
            }
        }
        return true;
    }

    public static boolean isLuhnValid(byte[] data) throws Exception {
        byte[] new_data = Arrays.copyOfRange(data, 0, data.length-1);
        byte dig = getLuhnDigit(new_data);
        byte bik = data[data.length-1];

        return bik == dig;
    }
}
