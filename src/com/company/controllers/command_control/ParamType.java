package com.company.controllers.command_control;

public enum ParamType {
    INTEGER(true),
    STRING(true),
    MUSIC_BOX(false),
    COORDINATES(false),
    LOCAL_DATE_TIME(true),
    MUSIC_GENRE(false),
    STUDIO(false),
    FLOAT(true),
    ENUM(false),
    LONG(true),
    LIST(false);

    private final boolean isPrimitive;

    ParamType(boolean isPrimitive){
        this.isPrimitive = isPrimitive;
    }

    public boolean isPrimitive() {
        return isPrimitive;
    }
}
