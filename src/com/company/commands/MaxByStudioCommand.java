package com.company.commands;

import com.company.commands.templer.Command;
import com.company.commands.templer.ParamBox;
import com.company.controllers.command_control.Param;
import com.company.controllers.command_control.ParamType;
import com.company.db.MusicBandHashSet;
import com.company.model.MusicBand;
import com.company.model.Studio;

public class MaxByStudioCommand extends Command {

    public Studio studio;

    public MaxByStudioCommand(MusicBandHashSet receiver, ParamBox params){
        super(receiver, params);
        if (params.size() == 1){
            studio = (Studio) params.toUnpack().get().getVal();
        }
    }

    @Override
    public ParamBox execute() {
        MusicBand max = null;
        for (MusicBand mb : receiver){
            if (mb.getStudio().compareTo(max.getStudio()) > 0){
                max = mb;
            }
        }
        return new ParamBox(1).add(new Param(ParamType.MUSIC_BOX, Param.NO_NAME_FIELD, max)).toPack();
    }
}
