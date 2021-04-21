package com.company.commands.templer;

import com.company.db.MusicBandHashSet;

public abstract class Command {

    protected MusicBandHashSet receiver; //не должно быть равно null
    protected ParamBox params;

    public Command(){}

    public Command(MusicBandHashSet receiver, ParamBox params){
        this.receiver = receiver;
        this.params = params;
    }

    public Command(MusicBandHashSet musicBandHashSet){
        this(musicBandHashSet, null);
    }

    public abstract ParamBox execute();

}