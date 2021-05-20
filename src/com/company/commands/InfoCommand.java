package com.company.commands;

import com.company.controllers.command_control.ParamBox;
import com.company.commands.templer.Command;
import com.company.controllers.command_control.Param;
import com.company.controllers.command_control.ParamType;
import com.company.db.MusicBandHashSet;

public class InfoCommand extends Command { // у каждой команды свой ресивер?

    public InfoCommand(MusicBandHashSet receiver){
        super(receiver);
    }

    @Override
    public ParamBox execute() {

        String info = receiver.getInfo();

        return new ParamBox(1).add(new Param(ParamType.STRING, Param.NO_NAME_FIELD, info)).toPack();
    }
}
