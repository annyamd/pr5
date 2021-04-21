package com.company.commands;

import com.company.commands.templer.Command;
import com.company.commands.templer.ParamBox;
import com.company.db.MusicBandHashSet;
import com.company.model.MusicBand;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
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

        try {
            List<MusicBand> list = new ArrayList(receiver);
            Writer writer = new FileWriter(fileName);
            StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
            beanToCsv.write(list);
            writer.close();
        } catch (IOException e){

        } catch (CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e2){

        }


        return null;
    }
}

