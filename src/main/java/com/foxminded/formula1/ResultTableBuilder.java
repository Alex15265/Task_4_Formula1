package com.foxminded.formula1;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResultTableBuilder {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
    private static final String CONTROL_DATE_TIME_PATTERN = "[A-Z]{3}\\d{4}-\\d{2}-\\d{2}_(\\d{2}:){2}\\d{2}\\.\\d{3}";
    private static final String CONTROL_ABBREVIATION_PATTERN = "[A-Z]{3}_[A-Z][a-z]*\\s[A-Z][a-z]+_([A-Z]+\\s)*[A-Z]+";

    public List<Racer> listsToRacers(List<String> racersList, List<String> startsList, List<String> endsList) {

        if(racersList.size() != startsList.size() || racersList.size() != endsList.size()) {
            throw new IllegalArgumentException("Lists sizes do not match");
        }

        List<Racer> racersTable = new ArrayList<>();

        Collections.sort(racersList);
        Collections.sort(startsList);
        Collections.sort(endsList);

        for (int i = 0; i < racersList.size(); i++) {
            racersTable.add(racerCreation(racersList.get(i), startsList.get(i), endsList.get(i)));
        }
        return racersTable;
    }


    private Racer racerCreation(String racerData, String start, String end) {

        Pattern patternAbbreviation = Pattern.compile(CONTROL_ABBREVIATION_PATTERN);
        Matcher matcherRacerData = patternAbbreviation.matcher(racerData);
        boolean foundRacer = matcherRacerData.matches();

        Pattern patternDateTime = Pattern.compile(CONTROL_DATE_TIME_PATTERN);
        Matcher matcherStart = patternDateTime.matcher(start);
        Matcher matcherEnd = patternDateTime.matcher(end);
        boolean foundStart = matcherStart.matches();
        boolean foundEnd = matcherEnd.matches();

        if(!foundStart || !foundEnd || !foundRacer) throw new IllegalArgumentException("Invalid string format");


        String[] splittedRacerData = racerData.split("_");

        String name = splittedRacerData[1];
        String team = splittedRacerData[2];
        LocalDateTime startLapTime = LocalDateTime.parse(start.substring(3), formatter);
        LocalDateTime endLapTime = LocalDateTime.parse(end.substring(3), formatter);
        Duration bestLapTime = Duration.between(startLapTime, endLapTime);

        return new Racer(name, team, bestLapTime);
    }
}
