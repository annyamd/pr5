package com.company.commands;

import com.company.commands.templer.Command;
import com.company.controllers.command_control.ParamBox;
import com.company.db.MusicBandHashSet;
import com.company.model.MusicBand;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SaveCommand extends Command {

    private String fileName;

    public SaveCommand(MusicBandHashSet musicBandHashSet, String fileName){
        super(musicBandHashSet);
        this.fileName = fileName;
    }

    @Override
    public ParamBox execute() {

        try (OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileName));) {
            List<MusicBand> list = new ArrayList(receiver);

            StringWriter stringWriter = new StringWriter();
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(stringWriter).build();
            beanToCsv.write(list);

            writer.write(stringWriter.toString());
        } catch (IOException e){
            System.out.println("Can't save, problem with file.");
        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e2){}

        return null;
    }
}

