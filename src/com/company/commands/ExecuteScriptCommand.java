package com.company.commands;

import com.company.commands.templer.Command;
import com.company.controllers.command_control.ParamBox;
import com.company.io.MBTerminal;

public class ExecuteScriptCommand extends Command {

    private String fileName;
    private MBTerminal mbTerminal;

    public ExecuteScriptCommand(MBTerminal mbTerminal, ParamBox paramBox){
        this.mbTerminal = mbTerminal;
        if (paramBox.size() == 1){
            fileName = (String) paramBox.toUnpack().get().getVal();
        }
    }

    @Override
    public ParamBox execute() {
        mbTerminal.executeScript(fileName);
        return null;
    }
}
