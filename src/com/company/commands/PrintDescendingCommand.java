package com.company.commands;

import com.company.commands.templer.Command;
import com.company.commands.templer.ParamBox;
import com.company.controllers.command_control.Param;
import com.company.controllers.command_control.ParamType;
import com.company.db.MusicBandHashSet;
import com.company.model.MusicBand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PrintDescendingCommand extends Command {

    public PrintDescendingCommand(MusicBandHashSet musicBandHashSet){
        super(musicBandHashSet);
    }

    @Override
    public ParamBox execute() {
        ArrayList<MusicBand> musicBand = new ArrayList<>(receiver); //Sorted Set
        Collections.sort(musicBand);
        Collections.reverse(musicBand);
        return new ParamBox(1).add(new Param(ParamType.LIST, Param.NO_NAME_FIELD, musicBand)).toPack();
    }
}
