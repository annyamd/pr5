package com.company.io;


import com.company.controllers.command_control.ParamType;
import com.company.exceptions.IncorrectInputException;
import com.company.exceptions.InflateException;
import com.company.exceptions.NoSymbolToReadException;
import com.company.io.inflaters.EnumInflater;
import com.company.io.inflaters.Inflater;

public abstract class IOHandler { //reader, writer, object reader, object writer, good interface for it >_< ,____inflater class?//Object reader?
    private CommandReader reader;// присваивается какой то тип, у хэндлера есть режим выводить коммантарии или нет
    private Writer writer;
    private MBTerminal.ExceptionListener exceptionListener;

    private boolean quitIfException = false;

    public IOHandler(MBTerminal.ExceptionListener exceptionListener) {
        reader = new CommandReader();
        writer = new Writer();
        this.exceptionListener = exceptionListener;
    }

    public Object readObject(ParamType objType) {

        Inflater inflater = getInflater(objType);

        for (int i = 0; i < inflater.getParamCount(); i++) {
            ParamType type = inflater.getParam(i).getType();
            writer.write(Messenger.getRequestByName(inflater.getParam(i).getName()));

            Object val;
            while (true) {
                try {
                    if (type.isPrimitive()) {
                        val = reader.readlnPrimitiveParam(type);
                    } else {
                        if (type == ParamType.ENUM) {
                            val = reader.readEnum(((EnumInflater) inflater).getEnumClass());
                        } else {
                            val = readObject(type);
                        }
                    }
                    inflater.setField(i, val);
                    break;
                } catch (InflateException | IncorrectInputException | NoSymbolToReadException e) {
                    exceptionListener.onExceptionGet(e);
                }
            }
        }
        return inflater.inflate();
    }

    public CommandReader getReader() {
        return reader;
    }

    public Writer getWriter() {
        return writer;
    }

    public abstract Inflater getInflater(ParamType type); //для каждого типа указываются свои инфлетеры

    public void setReader(CommandReader reader){
        this.reader = reader;
    }

    public void setWriter(Writer writer) {
        this.writer = writer;
    }

    public void setExceptionListener(MBTerminal.ExceptionListener exceptionListener) {
        this.exceptionListener = exceptionListener;
    }
}
