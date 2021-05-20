package com.company.commands;

import com.company.controllers.command_control.ParamBox;
import com.company.commands.templer.Command;
import com.company.controllers.command_control.Param;
import com.company.controllers.command_control.ParamType;
import com.company.db.MusicBandHashSet;

public class ShowCommand extends Command {

    public ShowCommand(MusicBandHashSet receiver) {
        super(receiver);
    }

    @Override
    public ParamBox execute() {

        String strPresentation = "";

        for (Object elem : receiver){
            strPresentation += elem.toString() + "\n";
        }

        return new ParamBox(1).add(new Param(ParamType.STRING, "List", strPresentation)).toPack();
    }
}
