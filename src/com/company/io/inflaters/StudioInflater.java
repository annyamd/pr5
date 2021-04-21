package com.company.io.inflaters;

import com.company.controllers.command_control.Param;
import com.company.controllers.command_control.ParamType;
import com.company.exceptions.InflateException;
import com.company.model.Coordinates;
import com.company.model.MusicGenre;
import com.company.model.Studio;

public class StudioInflater extends Inflater<Studio>{

    private Studio studio;

    public StudioInflater() {
        super(new Param[]{
                new Param(ParamType.STRING, "studio_name"),
                new Param(ParamType.STRING, "studio_address")
        });
        studio = new Studio();
    }

    @Override
    public Studio inflate() {
        return studio;
    }

    @Override
    public void setField(int i, Object val) throws InflateException {
        String name = paramsToInflate[i].getName();
        switch (name){
            case "studio_name":
                if (val == null) studio.setName(null);
                else studio.setName((String)val);
                break;
            case "studio_address":
                if (val == null) studio.setAddress(null);
                else studio.setAddress((String)val);
                break;
        }
    }
}
