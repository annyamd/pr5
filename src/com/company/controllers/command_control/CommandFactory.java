package com.company.controllers.command_control;

import com.company.db.MusicBandHashSet;
import com.company.commands.*;
import com.company.commands.templer.Command;
import com.company.commands.templer.ParamBox;
import com.company.io.MBTerminal;

public class CommandFactory {

    private MusicBandHashSet musicBandHashSet;
    private MBTerminal mbTerminal;
    private CommandManager commandManager;

    public Command getCommand(CommandType commandType, ParamBox paramBox){
        switch (commandType) {
            case ADD: return new AddCommand(musicBandHashSet, paramBox);
            case CLEAR: return new ClearCommand(musicBandHashSet);
            case EXIT: return new ExitCommand(mbTerminal);
            case HELP: return new HelpCommand();
            case INFO: return new InfoCommand(musicBandHashSet);
            case SAVE: return new SaveCommand(musicBandHashSet, mbTerminal.getFileName());
            case SHOW: return new ShowCommand(musicBandHashSet);
            case UPDATE: return new UpdateCommand(musicBandHashSet, paramBox);
            case HISTORY: return new HistoryCommand(commandManager);
            case REMOVE_BY_ID: return new RemoveByIdCommand(musicBandHashSet, paramBox);
            case REMOVE_LOWER: return new RemoveLowerCommand(musicBandHashSet, paramBox);
            case MAX_BY_STUDIO: return new MaxByStudioCommand(musicBandHashSet, paramBox);
            case EXECUTE_SCRIPT: return new ExecuteScriptCommand(paramBox);
            case REMOVE_GREATER: return new RemoveGreaterCommand(musicBandHashSet, paramBox);
            case PRINT_DESCENDING: return new PrintDescendingCommand(musicBandHashSet);
            case FILTER_BY_NUMBER_OF_PARTICIPANTS: return new FilterByNumberOfParticipantsCommand(
                    musicBandHashSet, paramBox);
        }
        return null;
    }

    public void setStandardParams(MusicBandHashSet musicBandHashSet, MBTerminal mbTerminal, CommandManager commandManager){ //либо сохранение параметров с помощью переопределения get методов, а сам класс фабрики абстрактный
        this.musicBandHashSet = musicBandHashSet;
        this.mbTerminal = mbTerminal;
        this.commandManager = commandManager;
    }
}
