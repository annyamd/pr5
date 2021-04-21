package com.company.io;

import com.company.commands.templer.ParamBox;
import com.company.controllers.command_control.Param;
import com.company.controllers.command_control.ParamType;
import com.company.model.MusicGenre;

import java.io.IOException;
import java.util.List;

class Writer { //класс, ответственный за вывод в терминале


    public void write(String str) {
        System.out.print(str + " ");
    }

    public void writeln(String str){
        System.out.println(str);
    }

    public void writeParamBox(ParamBox paramBox){
        if (paramBox == null) return;
        for (int i = 0; i < paramBox.size(); i++) {
            Param p = paramBox.get();
            writeln(p.getName() + ": " + p.getVal());
        }
    }

    public void writeParamBox(String name, ParamBox paramBox){
        if (paramBox == null) return;
        writeln(name + ": ");

        for (Param p : paramBox.toUnpack().getAll()){
            writeParam(p);
        }
    }

    public void writeList(List list){
        for (Object o : list){
            writeln(o.toString());
        }
    }

    public void writeParam(Param p){
        if (Param.NO_NAME_FIELD.equals(p.getName())) writeln(p.getVal().toString());
        else if (p.getType() == ParamType.LIST) writeList((List) p.getVal());
        else writeln(p.getName() + ": "+ p.getVal());
    }

}
