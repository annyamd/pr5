package com.company.verifiers.id;

import com.company.model.MusicBand;

public class MusicBandIdHandler extends IdHandler<MusicBand> {
    private static MusicBandIdHandler instance;

    public static MusicBandIdHandler getInstance(){
        synchronized (MusicBandIdHandler.class) {
            if (instance == null) {
                instance = new MusicBandIdHandler();
            }
        }
        return instance;
    }

    private MusicBandIdHandler(){}
}
