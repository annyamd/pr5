package com.company.io;

import com.company.controllers.command_control.CommandManager;
import com.company.controllers.command_control.CommandType;
import com.company.commands.templer.ParamBox;
import com.company.controllers.command_control.Param;
import com.company.controllers.command_control.ParamType;
import com.company.db.MusicBandHashSet;
import com.company.exceptions.IncorrectInputException;
import com.company.exceptions.InflateException;
import com.company.exceptions.NoSuchCommandException;
import com.company.io.inflaters.*;
import com.company.model.MusicGenre;

public class MBTerminal { //OR COMMAND TERMINAL?

    private IOHandler handler;
    private boolean toContinue = true;
    private CommandManager commandManager;
    private String fileName;

    private MusicBandHashSet musicBandHashSet;

    public MBTerminal(MusicBandHashSet musicBandHashSet, String inputFile){
        this.fileName = inputFile;
        this.musicBandHashSet = musicBandHashSet;
    }

    public void start() {

        init();

        while (toContinue) {
            handler.getWriter().writeln("Write next command: ");

            try {
                CommandType cmd = handler.getReader().readCommand();
                ParamType[] params = cmd.getParamTypes();
                ParamBox paramBox = new ParamBox(params.length);
                Object val;

                for (int i = 0; i < paramBox.size(); i++) {
                    ParamType type = params[i];
                    while (true) {
                        try {
                            if (type.isPrimitive())
                                val = handler.getReader().readPrimitiveParam(type, Reader.WORD_MODE);
                            else {
                                handler.getReader().nextLine();
                                val = handler.readObject(type);
                            }
                            break;
                        } catch (IncorrectInputException e) {
                            handler.getWriter().writeln("Некоректный ввод данных. Повторите снова.");
                        }
                    }
                    paramBox.add(new Param(type, val));
                }
                ParamBox res = commandManager.execute(cmd, paramBox);
                handler.getWriter().writeParamBox("Result", res);
                handler.getWriter().writeln("Command completed execution.");
            } catch (NoSuchCommandException e) {
                handler.getWriter().writeln(e.toString());
            }
        }
    }

    private void init() {
        commandManager = new CommandManager(this, musicBandHashSet);

        handler = new IOHandler() {
            @Override
            public Inflater getInflater(ParamType type) {
                switch (type) {
                    case MUSIC_BOX:
                        return new MusicBandInflater();
                    case COORDINATES:
                        return new CoordinatesInflater();
                    case STUDIO:
                        return new StudioInflater();
                    case MUSIC_GENRE:
                        return new EnumInflater<>("music genre", MusicGenre.class);
                }
                return null;
            }
        };
    }

    public String getFileName() {
        return fileName;
    }

    public void exit() {
        toContinue = false;
        handler.getReader().close();
    }
}
