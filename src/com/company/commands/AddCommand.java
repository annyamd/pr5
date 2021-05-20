package com.company.commands;

import com.company.controllers.command_control.ParamBox;
import com.company.controllers.command_control.Param;
import com.company.db.MusicBandHashSet;
import com.company.commands.templer.Command;
import com.company.model.MusicBand;

public class AddCommand extends Command {

    private MusicBand elem;

    public AddCommand(MusicBandHashSet receiver, ParamBox params){
        super(receiver, params);
        Param p;
        if (params.size() == 1){//.....крч должно быть поле с названием параметра и проверка при распаковке
            this.elem = (MusicBand) (params.toUnpack().get().getVal());
        }
    }

    @Override
    public ParamBox execute() {
        receiver.add(elem);
        return null;
    }

}