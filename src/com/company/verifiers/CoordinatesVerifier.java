package com.company.verifiers;

public class CoordinatesVerifier implements IVerifier{
    public static boolean verifyX(float x){
        if (x > 645) return false;
        return true;
    }

    public static boolean verifyY(Integer y){
        if (y == null || y <= -328) return false;
        return true;
    }
}
