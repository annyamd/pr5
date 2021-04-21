package com.company.io.inflaters;

import com.company.controllers.command_control.Param;
import com.company.exceptions.InflateException;

public abstract class Inflater<T> {
    Param[] paramsToInflate;

    public Inflater(Param[] paramsToInflate){
        this.paramsToInflate = paramsToInflate;
    }

    public Param[] getParamsToInflate() {
        return paramsToInflate;
    }

    public abstract T inflate();

    public abstract void setField(int i, Object val) throws InflateException;

    public Param getParam(int index){
        return paramsToInflate[index];
    }

    public int getParamCount(){
        return paramsToInflate.length;
    }

}