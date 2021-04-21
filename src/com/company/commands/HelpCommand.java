package com.company.commands;

import com.company.commands.templer.Command;
import com.company.commands.templer.ParamBox;
import com.company.controllers.command_control.Param;
import com.company.controllers.command_control.ParamType;
import com.company.io.Messenger;

public class HelpCommand extends Command {
    @Override
    public ParamBox execute() {
        return new ParamBox(1).add(new Param(ParamType.STRING, Param.NO_NAME_FIELD,
                Messenger.getCommandsDescription()));
    }
}
