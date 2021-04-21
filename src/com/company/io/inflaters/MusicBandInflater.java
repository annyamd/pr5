package com.company.io.inflaters;

import com.company.controllers.command_control.Param;
import com.company.controllers.command_control.ParamType;
import com.company.exceptions.InflateException;
import com.company.model.Coordinates;
import com.company.model.MusicBand;
import com.company.model.MusicGenre;
import com.company.model.Studio;

import java.time.LocalDateTime;

public class MusicBandInflater extends Inflater<MusicBand>{ //создает новый класс Musicband по полям, здесь и хранится список полей

    private MusicBand musicBand;

    public MusicBandInflater(){
        super(new Param[]{
                new Param(ParamType.STRING, "name"),
                new Param(ParamType.COORDINATES, "coordinates"),
                new Param(ParamType.INTEGER, "number of participants"),
                new Param(ParamType.MUSIC_GENRE, "genre"),
                new Param(ParamType.STUDIO, "studio")
        });

        musicBand = new MusicBand();
    }

    public void setField(int i, Object val) throws InflateException {
        String name = paramsToInflate[i].getName();
        switch (name){
            case "name":
                if (val == null) musicBand.setName(null);
                else musicBand.setName((String)val);
                break;
            case "coordinates":
                if (val == null) musicBand.setCoordinates(null);
                else musicBand.setCoordinates((Coordinates)val);
                break;
            case "number of participants":
                if (val == null) throw new InflateException();
                musicBand.setNumberOfParticipants((int) val);
                break;
            case "genre":
                if (val == null) musicBand.setGenre(null);
                else musicBand.setGenre((MusicGenre)val);
                break;
            case "studio":
                if (val == null) musicBand.setStudio(null);
            else musicBand.setStudio((Studio)val);
                break;
        }
    }

    @Override
    public MusicBand inflate() {// если поля введены, то они нужного типа(или все таки проверить на инстанс(или нет))
        return musicBand;
    }
}
