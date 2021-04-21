package com.company.io;

import com.company.controllers.command_control.CommandType;
import com.company.controllers.command_control.ParamType;
import com.company.exceptions.IncorrectInputException;
import com.company.exceptions.NoSuchCommandException;

import java.util.Scanner;

class Reader { //класс, ответственный за ввод в терминале //command reader, убрать ненужные методы, выделить под класс родителя взаимодействие со сканером
    public final static int WHOLE_STRING_MODE = 0;
    public final static int WORD_MODE = 1;


    private Scanner scanner = new Scanner(System.in);

    public CommandType readCommand() throws NoSuchCommandException{
        try {
            return CommandType.valueOf(read().toUpperCase());
        } catch (IllegalArgumentException e){
            throw new NoSuchCommandException();
        }
    }

    public Object readPrimitiveParam(ParamType paramType, int mode) throws IncorrectInputException {
        String in = readByMode(mode);
        if (in == null) return null;
        if (paramType.isPrimitive()) {
            try {
                switch (paramType) {
                    case INTEGER:
                        return Integer.parseInt(in);
                    case FLOAT:
                        return Float.parseFloat(in);
                    case STRING:
                        return in;
                    case LONG:
                        Long l = Long.parseLong(in);
                        return l;
                }
            } catch (IllegalArgumentException ex) {
                throw new IncorrectInputException();
            }
        }
        return null;
    }

    public <T extends Enum> String readEnum(Class<T> enumClass) throws IncorrectInputException { //дженерики для просерки класса на енам
        String str = (String) readPrimitiveParam(ParamType.STRING, Reader.WHOLE_STRING_MODE);
        if (str == null) return null;
        try {
            if (Enum.valueOf(enumClass, str) == null) throw new IncorrectInputException();
            return str;
        } catch (IllegalArgumentException e) {
            throw new IncorrectInputException();
        }
    }

    public void close() {
        scanner.close();
    }

    private String readByMode(int mode) {
        if (mode == WHOLE_STRING_MODE) return readln();
        if (mode == WORD_MODE) return read();
        return null;
    }

    public void nextLine() {
        scanner.nextLine();
    }

    public String readln(){
        String s = scanner.nextLine();
        if (s == null || s.equals("")) return null;
        return s;
    }

    public String read(){
        String s = scanner.next();
        if (s == null || s.equals("")) return null;
        return s;
    }
}
