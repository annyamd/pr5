package com.company.commands;

import com.company.commands.templer.Command;
import com.company.commands.templer.ParamBox;
import com.company.db.MusicBandHashSet;
import com.company.model.MusicBand;

public class RemoveLowerCommand extends Command {

    private MusicBand elem;

    public RemoveLowerCommand(MusicBandHashSet receiver, ParamBox params){
        super(receiver, params);
        if (params.size() == 1){
            elem = (MusicBand) params.toUnpack().get().getVal();
        }
    }

    @Override
    public ParamBox execute() {
        for (MusicBand mb: receiver){
            if (mb.compareTo(elem) < 0){
                receiver.remove(mb);
            }
        }
        return null;
    }
}