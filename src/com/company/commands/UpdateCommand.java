package com.company.commands;

import com.company.controllers.command_control.ParamBox;
import com.company.db.MusicBandHashSet;
import com.company.commands.templer.Command;
import com.company.model.MusicBand;

public class UpdateCommand extends Command {

    private long id;
    private MusicBand elem;

    public UpdateCommand(MusicBandHashSet receiver, ParamBox paramBox){
        super(receiver, paramBox);

        if (paramBox.size() == 2) {
            this.id = (long) paramBox.toUnpack().get().getVal();
            this.elem = (MusicBand) paramBox.get().getVal();
        }
    }

    @Override
    public ParamBox execute() {
        if (receiver.removeById(id)){
            receiver.add(elem);
        }
        return null;
    }
}
