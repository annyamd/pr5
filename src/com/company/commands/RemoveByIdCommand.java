package com.company.commands;

import com.company.commands.templer.ParamBox;
import com.company.controllers.command_control.Param;
import com.company.db.MusicBandHashSet;
import com.company.commands.templer.Command;

public class RemoveByIdCommand extends Command {
    private long id;

    public RemoveByIdCommand(MusicBandHashSet receiverHashSet, ParamBox paramBox){
        super(receiverHashSet, paramBox);

        if (paramBox.size() == 1){
            id = (long) paramBox.toUnpack().get().getVal();
        }

    }

    @Override
    public ParamBox execute() {
        receiver.removeById(id);
        return null;
    }
}