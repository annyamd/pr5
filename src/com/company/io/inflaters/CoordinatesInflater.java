package com.company.io.inflaters;

import com.company.exceptions.InflateException;
import com.company.model.Coordinates;
import com.company.verifiers.CoordinatesVerifier;
import com.company.controllers.command_control.Param;
import com.company.controllers.command_control.ParamType;

public class CoordinatesInflater extends Inflater<Coordinates>{

    private Coordinates coordinates;

    public CoordinatesInflater() {
        super(new Param[]{
                new Param(ParamType.FLOAT, "coordinate_x"),
                new Param(ParamType.INTEGER, "coordinate_y")
        });
        coordinates = new Coordinates();
    }

    @Override
    public Coordinates inflate() {
        return coordinates;
    }

    @Override
    public void setField(int i, Object val) throws InflateException {
        String name = paramsToInflate[i].getName();

        switch (name){
            case "coordinate_x":
                if (val == null) throw new InflateException();
                if (CoordinatesVerifier.verifyX((float)val)) coordinates.setX((float) val);
                else throw new InflateException();
                break;
            case "coordinate_y":
                if (CoordinatesVerifier.verifyY((Integer) val)) coordinates.setY((Integer) val);
                else throw new InflateException();
                break;
        }
    }
}
