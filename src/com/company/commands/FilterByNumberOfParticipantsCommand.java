package com.company.commands;

import com.company.commands.templer.Command;
import com.company.controllers.command_control.Param;
import com.company.controllers.command_control.ParamBox;
import com.company.controllers.command_control.ParamType;
import com.company.db.MusicBandHashSet;
import com.company.model.MusicBand;

import java.util.ArrayList;

public class FilterByNumberOfParticipantsCommand extends Command {

    private int numbOfParts;

    public FilterByNumberOfParticipantsCommand(MusicBandHashSet receiver, ParamBox params){
        super(receiver, params);
        if (params.size() == 1){
            numbOfParts = (int) params.toUnpack().get().getVal();
        }
    }

    @Override
    public ParamBox execute() {
        ArrayList<MusicBand> mbList = new ArrayList<>();

        for (MusicBand mb : receiver){
            if (mb.getNumberOfParticipants() == numbOfParts) mbList.add(mb);
        }

        return new ParamBox(1).add(new Param(ParamType.LIST, mbList)).toPack();
    }
}
