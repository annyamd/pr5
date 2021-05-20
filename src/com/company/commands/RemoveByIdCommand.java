package com.company.commands;

import com.company.controllers.command_control.ParamBox;
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