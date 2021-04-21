package com.company.controllers.command_control;

import com.company.controllers.Envoker;
import com.company.db.MusicBandHashSet;
import com.company.commands.templer.Command;
import com.company.commands.templer.ParamBox;
import com.company.io.MBTerminal;

import java.util.ArrayList;

public class CommandManager implements Envoker { //envoker
    private CommandFactory commandFactory;

    private ArrayList<String> commands;

    public CommandManager(MBTerminal terminal, MusicBandHashSet musicBandHashSet){
        commandFactory = new CommandFactory();
        commandFactory.setStandardParams(musicBandHashSet, terminal, this);
        commands = new ArrayList<>();
    }

    public ParamBox execute(CommandType commandType, ParamBox paramBox){
        Command command = commandFactory.getCommand(commandType, paramBox);
        ParamBox res = command.execute();

        addCommand(commandType.toString());

        return res;
    }

    private void addCommand(String newElem){
        commands.add(newElem);
        if (commands.size() > 10){
            commands.remove(0);
        }
    }

    public ArrayList<String> getCommandList() {
        return commands;
    }
}
