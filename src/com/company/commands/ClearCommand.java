package com.company.commands;

import com.company.commands.templer.ParamBox;
import com.company.db.MusicBandHashSet;
import com.company.commands.templer.Command;

public class ClearCommand extends Command {

    public ClearCommand(MusicBandHashSet receiver){
        super(receiver);
    }

    @Override
    public ParamBox execute() {
        receiver.clear();
        return null;
    }
}
