package com.company.verifiers;

public class StudioVerifier implements IVerifier{
    public static boolean verifyName(String name){
        return true;
    }

    public static boolean verifyAddress(String address){
        if (address == null  || address.trim().isEmpty()) return false;
        return true;
    }
}
