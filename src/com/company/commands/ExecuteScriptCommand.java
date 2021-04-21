package com.company.commands;

import com.company.commands.templer.Command;
import com.company.commands.templer.ParamBox;

public class ExecuteScriptCommand extends Command {

    private String fileName;

    public ExecuteScriptCommand(ParamBox paramBox){
        if (paramBox.size() == 1){
            fileName = (String) paramBox.toUnpack().get().getVal();
        }
    }

    @Override
    public ParamBox execute() {
        return null;
    }
}
