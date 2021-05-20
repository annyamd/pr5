package com.company.commands;

import com.company.commands.templer.Command;
import com.company.controllers.command_control.ParamBox;
import com.company.io.MBTerminal;

public class ExitCommand extends Command {
    private MBTerminal terminal;

    public ExitCommand(MBTerminal terminal){ //через commandManager
        this.terminal = terminal;
    }

    @Override
    public ParamBox execute() {
        terminal.exit();
        return null;
    }
}
