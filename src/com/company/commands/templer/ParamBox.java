package com.company.commands.templer;

import com.company.controllers.command_control.Param;

public class ParamBox { //легче обычный лист с обжектами???

    private Param[] params; //складывать объекты типа Param
    private int curInd;
    private boolean packed;

    public ParamBox(int count){
        params = new Param[count];
        curInd = 0;
    }

    public int size(){
        return params.length;
    }

    public ParamBox add(Param param){
        if (packed) return null;

        if (curInd >= params.length) return null;
        params[curInd++] = param;
        return this;
    }

    public Param get(){
        if (packed) return null;

        int i = params.length - curInd--;
        if (i == params.length) return null;
        return params[i];
    }

    public Param[] getAll(){
        return params;
    }

    public ParamBox toPack(){
        packed = true;
        return this;
    }

    public ParamBox toUnpack(){
        packed = false;
        return this;
    }
}
