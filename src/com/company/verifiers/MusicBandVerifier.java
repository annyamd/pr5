package com.company.verifiers;

import com.company.model.Coordinates;
import com.company.model.MusicGenre;
import com.company.model.Studio;

public class MusicBandVerifier implements IVerifier{

    public static boolean verifyId(long id){
        if (id <= 0) return false;
        return true;
    }

    public static boolean verifyName(String name){
        if (name == null || name.trim().isEmpty()) return false;
        return true;
    }

    public static boolean verifyCoordinates(Coordinates coordinates){
        if (coordinates == null) return false;
        return true;
    }

    public static boolean verifyCreationDate(java.time.LocalDateTime localDateTime){
        if (localDateTime == null) return false;
        return true;
    }

    public static boolean verifyNumberOfParticipants(int numberOfParticipants){
        if (numberOfParticipants <= 0) return false;
        return true;
    }

    public static boolean verifyGenre(MusicGenre musicGenre){
        return true;
    }

    public static boolean verifyStudio(Studio studio){
        return true;
    }

}
