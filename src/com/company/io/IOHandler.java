package com.company.io;


import com.company.controllers.command_control.ParamType;
import com.company.exceptions.IncorrectInputException;
import com.company.exceptions.InflateException;
import com.company.io.inflaters.EnumInflater;
import com.company.io.inflaters.Inflater;

public abstract class IOHandler { //reader, writer, object reader, object writer, good interface for it >_< ,____inflater class?//Object reader?
    private Reader reader;// присваивается какой то тип, у хэндлера есть режим выводить коммантарии или нет
    private Writer writer;

    public IOHandler() {
        reader = new Reader();
        writer = new Writer();
    }

    public Object readObject(ParamType objType) { //по ходу собирает пак для инфлетера

        Inflater inflater = getInflater(objType);

        for (int i = 0; i < inflater.getParamCount(); i++) {
            ParamType type = inflater.getParam(i).getType();
            writer.write(Messenger.getRequestByName(inflater.getParam(i).getName()));

            Object val;
            while (true) {
                try {   //gets errors of input
                    if (type.isPrimitive()) {
                        val = reader.readPrimitiveParam(type, Reader.WHOLE_STRING_MODE);
                    } else {
                        if (type == ParamType.ENUM) {
                            val = reader.readEnum(((EnumInflater) inflater).getEnumClass());
                        } else {
                            val = readObject(type);
                        }
                    }
                    inflater.setField(i, val);
                    break;
                } catch (InflateException | IncorrectInputException e) {
                    writer.writeln("Некоректный ввод данных. Повторите снова.");
                }
            }
        }
        return inflater.inflate();
    }


    public Reader getReader() {
        return reader;
    }

    public Writer getWriter() {
        return writer;
    }

    public abstract Inflater getInflater(ParamType type); //для каждого типа указываются свои инфлетеры

}
