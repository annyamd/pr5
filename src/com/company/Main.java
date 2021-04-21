package com.company;

import com.company.db.MusicBandHashSet;
import com.company.io.MBTerminal;
import com.company.model.MusicBand;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {


    static final String inputFileExtension = "csv";

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        String fileName = args[0];
        if (fileName == null || !fileName.substring(fileName.length() - 3).equals(inputFileExtension)) {
            System.out.print("Incorrect format of file.");
        } else {
            try { //todo: set validator
                List<MusicBand> list = new CsvToBeanBuilder(new FileReader(fileName)).withType(MusicBand.class).build().parse();
                MusicBandHashSet base = new MusicBandHashSet(list);
                new MBTerminal(base, fileName).start();

            } catch (IOException e) {
                System.out.print("Problems with opening the file (problem with i/o).");
            }
        }
    }
}
