package com.company.controllers.command_control;

public enum CommandType{
    ADD(ParamType.MUSIC_BOX),
    CLEAR(),
    EXIT(),
    INFO(),
    EXECUTE_SCRIPT(ParamType.STRING),
    FILTER_BY_NUMBER_OF_PARTICIPANTS(ParamType.INTEGER),
    HELP(),
    HISTORY(),
    MAX_BY_STUDIO(),
    PRINT_DESCENDING(),
    REMOVE_BY_ID(ParamType.LONG),
    REMOVE_GREATER(ParamType.MUSIC_BOX),
    REMOVE_LOWER(ParamType.MUSIC_BOX),
    SAVE(),
    SHOW(),
    UPDATE(ParamType.LONG, ParamType.MUSIC_BOX);

    private ParamType[] paramTypes;

    CommandType(ParamType... paramTypes){
        this.paramTypes = paramTypes;
    }

    public ParamType[] getParamTypes(){
        return paramTypes;
    }

    public int getPrimitivesCount(){
        int c = 0;
        for (ParamType paramType : paramTypes){
            if (paramType.isPrimitive()) c++;
        }
        return c;
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }

}
