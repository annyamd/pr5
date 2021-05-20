package com.company.io.inflaters;

import com.company.controllers.command_control.Param;
import com.company.controllers.command_control.ParamType;
import com.company.exceptions.InflateException;
import com.company.model.Studio;
import com.company.verifiers.StudioVerifier;

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
                if (StudioVerifier.verifyName((String)val)) studio.setName((String) val);
                else throw new InflateException();
                break;
            case "studio_address":
                if (StudioVerifier.verifyAddress((String)val)) studio.setAddress((String) val);
                else throw new InflateException();
                break;
        }
    }
}
