package com.company.io;

import com.company.controllers.command_control.CommandManager;
import com.company.controllers.command_control.ParamBox;
import com.company.controllers.command_control.CommandType;
import com.company.controllers.command_control.Param;
import com.company.controllers.command_control.ParamType;
import com.company.db.MusicBandHashSet;
import com.company.exceptions.IncorrectInputException;
import com.company.exceptions.InflateException;
import com.company.exceptions.NoSuchCommandException;
import com.company.exceptions.NoSymbolToReadException;
import com.company.io.inflaters.*;
import com.company.model.MusicGenre;

import java.io.File;
import java.io.IOException;

public class MBTerminal {

    private IOHandler handler;
    private boolean toContinue = true;
    private CommandManager commandManager;
    private String fileName;
    private ExceptionListener exceptionListener;
    private boolean isCommandCompleted;
    private Writer resultWriter;

    private MusicBandHashSet musicBandHashSet;

    public MBTerminal(MusicBandHashSet musicBandHashSet, String inputFile) {
        this.fileName = inputFile;
        this.musicBandHashSet = musicBandHashSet;
        init();
    }

    public void start() {

        while (toContinue) {
            handler.getWriter().writeln("Write next command: ");

            try {
                CommandType cmd = handler.getReader().readCommand();
                isCommandCompleted = false;
                ParamType[] params = cmd.getParamTypes();
                ParamBox paramBox = new ParamBox(params.length);
                Object val;
                int primitivesCount = cmd.getPrimitivesCount();
                String[] primitives = handler.getReader().readPrimitiveParams();

                if (primitives.length != primitivesCount) throw new IncorrectInputException();

                for (int i = 0; i < primitives.length; i++) {
                    val = handler.getReader().objToPrimitive(params[i], primitives[i]);
                    if (val == null) throw new IncorrectInputException();
                    paramBox.add(new Param(params[i], val));
                }

                for (int i = primitivesCount; i < paramBox.size(); i++) {
                    ParamType type = params[i];
                    while (true) {
                        val = handler.readObject(type);
                        break;
                    }
                    paramBox.add(new Param(type, val));
                }
                ParamBox res = commandManager.execute(cmd, paramBox);
                isCommandCompleted = true;
                resultWriter.writeParamBox("Result", res);
                handler.getWriter().writeln("Command completed execution.");
            } catch (IncorrectInputException | NoSymbolToReadException e) {
                getExceptionListener().onExceptionGet(e);
            }
        }
    }

    private void init() {
        commandManager = new CommandManager(this, musicBandHashSet);
        this.resultWriter = new Writer();

        this.exceptionListener = new ExceptionListener() {
            @Override
            public void onExceptionGet(Exception e) {
                if (e instanceof NoSuchCommandException) {
                    handler.getWriter().writeln(e.toString());
                } else if (e instanceof IncorrectInputException | e instanceof InflateException) {
                    handler.getWriter().writeln(e.toString() + " Try again.");
                }
            }
        };

        handler = new IOHandler(getExceptionListener()) {
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

    public void executeScript(String fileName) {

        try {
            CommandReader reader = new CommandReader(new File(fileName));

            Writer writer = new Writer(){
                @Override
                public void write(String str) {

                }

                @Override
                public void writeln(String str) {

                }
            };

            handler.setReader(reader);
            handler.setWriter(writer);

            ExceptionListener mainEL = getExceptionListener();
            exceptionListener = new ExceptionListener() {
                @Override
                public void onExceptionGet(Exception e) {
                    toContinue = false;
                }
            };

            handler.setExceptionListener(getExceptionListener());

            start();

            exceptionListener = mainEL;
            handler.setExceptionListener(mainEL);
            handler.setReader(new CommandReader());
            handler.setWriter(new Writer());

            if (toContinue || isCommandCompleted) {
                handler.getWriter().writeln("Script completed execution successfully.");
            } else handler.getWriter().writeln("Script execution error.");

            toContinue = true;

        } catch (IOException e) {
            handler.getWriter().writeln("Reading file error.");
        }
    }

    public ExceptionListener getExceptionListener() {
        return exceptionListener;
    }

    interface ExceptionListener {
        void onExceptionGet(Exception e);
    }
}
