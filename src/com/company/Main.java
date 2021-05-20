package com.company;

import com.company.db.MusicBandHashSet;
import com.company.io.MBTerminal;
import com.company.model.MusicBand;
import com.company.verifiers.CoordinatesVerifier;
import com.company.verifiers.MusicBandVerifier;
import com.company.verifiers.StudioVerifier;
import com.company.verifiers.id.MusicBandIdHandler;
import com.opencsv.CSVReader;
import com.opencsv.bean.BeanVerifier;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.exceptionhandler.CsvExceptionHandler;
import com.opencsv.exceptions.CsvConstraintViolationException;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Scanner;

/**
 * @author Anna Mikhailova
 *
 */

public class Main {

    static final String inputFileExtension = "csv";

    public static void main(String[] args) {

        String fileName = args[0];
        if (fileName == null || !fileName.substring(fileName.length() - 3).equals(inputFileExtension)) {
            System.out.print("Incorrect format of file.");
        } else {
            try { //todo: set validator
                Scanner scanner = new Scanner(new File(fileName));
                String csv = "";
                while (scanner.hasNextLine()) {
                    csv += scanner.nextLine() + "\n";
                }

                CSVReader csvReader = new CSVReader(new StringReader(csv));
                List<MusicBand> list = new CsvToBeanBuilder(csvReader)
                        .withIgnoreEmptyLine(true)
                        .withIgnoreLeadingWhiteSpace(true)
                        .withOrderedResults(true)
                        .withVerifier(new BeanVerifier() {
                            @Override
                            public boolean verifyBean(Object o) throws CsvConstraintViolationException {
                                boolean verify = false;
                                MusicBand mb = (MusicBand) o;

                                mb.setName(convertNullableStrToNull(mb.getName()));
                                mb.getStudio().setName(convertNullableStrToNull(mb.getStudio().getName()));
                                mb.getStudio().setAddress(convertNullableStrToNull(mb.getStudio().getAddress()));

                                if (MusicBandVerifier.verifyId(mb.getId())
                                        && MusicBandVerifier.verifyName(mb.getName())
                                        && MusicBandVerifier.verifyCreationDate(mb.getCreationDate())
                                        && MusicBandVerifier.verifyNumberOfParticipants(mb.getNumberOfParticipants())
                                        && MusicBandVerifier.verifyGenre(mb.getGenre())
                                        && CoordinatesVerifier.verifyX(mb.getCoordinates().getX())
                                        && CoordinatesVerifier.verifyY(mb.getCoordinates().getY())
                                ) {
                                    if (StudioVerifier.verifyName(mb.getStudio().getName()) && StudioVerifier.verifyAddress(mb.getStudio().getAddress())){
                                        verify = true;
                                    } else if (mb.getStudio().getName() == null && mb.getStudio().getAddress() == null){
                                        mb.setStudio(null);
                                        verify = true;
                                    }
                                }
                                if (!MusicBandIdHandler.getInstance().getReceivedIdIfAllowed(((MusicBand) o).getId())) {//or check the list after with throwing same exception
                                    throw new CsvConstraintViolationException("Id Repeat.");
                                }

                                if (verify) return true;
                                return false;
                            }
                        })
                        .withType(MusicBand.class)
                        .withExceptionHandler(new CsvExceptionHandler() {
                            @Override
                            public CsvException handleException(CsvException e) throws CsvException {
                                System.out.println("Wrong data in the file \"" + fileName + "\".");
                                System.exit(1);
                                return null;
                            }
                        }).build().parse();

                MusicBandHashSet base = new MusicBandHashSet(list);
                new MBTerminal(base, fileName).start();

            } catch (IOException e) {
                System.out.print("Problems with opening the file (problem with i/o).");
            }
        }
    }

    private static String convertNullableStrToNull(String s){
        if (s == null  || s.trim().isEmpty()){
            return null;
        }
        return s;
    }

}
