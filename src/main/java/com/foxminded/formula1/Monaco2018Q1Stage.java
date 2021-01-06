package com.foxminded.formula1;

import java.io.File;
import java.util.List;

public class Monaco2018Q1Stage {

    public void showResultOfQ1Stage(String abbreviationFileName, String startFileName, String endFileName) {

        FileReader fileReader = new FileReader();

        File abbreviationFile = new File(abbreviationFileName);
        List<String> racersList = fileReader.convertFileToList(abbreviationFile);
        File startFile = new File(startFileName);
        List<String> startsList = fileReader.convertFileToList(startFile);
        File endFile = new File(endFileName);
        List<String> endsList = fileReader.convertFileToList(endFile);

        ResultTableBuilder creator = new ResultTableBuilder();
        List<Racer> racersTable = creator.listsToRacers(racersList, startsList, endsList);

        ResultTableFormatter formatter = new ResultTableFormatter();
        List<String> formattedRacersTable = formatter.reformTheTableToTheSpecifiedForm(racersTable);

        formattedRacersTable.forEach( e -> System.out.print(e + "\n"));
    }
}
