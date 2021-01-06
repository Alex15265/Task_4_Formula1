package com.foxminded.formula1;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;

public class ResultTableFormatter {

    private static final char SPACE = ' ';
    private static final char VERTICAL_LINE = '|';
    private static final char DASH = '-';
    private static final char DOT = '.';
    private static final int DOT_LENGTH = 1;
    private static final int SPACE_LENGTH = 1;
    private static final int VERTICAL_LINE_LENGTH = 1;
    private static final String DURATION_FORMAT = "mm:ss.SSS";
    List<Racer> racersTable;
    int sizeOfRacersTable;


    public List<String> reformTheTableToTheSpecifiedForm(List<Racer> racersTable) {
        this.racersTable = racersTable;
        sizeOfRacersTable = this.racersTable.size();
        List<String> formattedTable = new ArrayList<>();

        BestLapComparator myComparator = new BestLapComparator();
        racersTable.sort(myComparator);

        for (int i = 0; i < this.racersTable.size(); i++) {
            if (i == 15) {
                formattedTable.add(buildDashesLine());
            }
            formattedTable.add(buildRacerLine(this.racersTable.get(i), i));
        }

        return formattedTable;
    }


    private String buildDashesLine() {
        int positionPartLength = ((int) (Math.log10(sizeOfRacersTable))) + DOT_LENGTH + SPACE_LENGTH;
        int racerPartLength = calculateMaxRacerNameLength() + VERTICAL_LINE_LENGTH;
        int teamPartLength = calculateMaxTeamNameLength() + VERTICAL_LINE_LENGTH;
        int bestLapTimePartLength = DURATION_FORMAT.length() + SPACE_LENGTH;

        int dashesLineLength = positionPartLength + racerPartLength + teamPartLength + bestLapTimePartLength;
        String dash = "-";
        return addSymbolsToString(dash, dashesLineLength, DASH);
    }

    private String buildRacerLine(Racer racer, int position) {
        return buildPositionPart(position) + buildRacerPart(racer) + buildTeamPart(racer) + buildBestLapTimePart(racer);
    }

    private String buildPositionPart(int position) {
        StringBuilder positionPart = new StringBuilder();
        positionPart.append(position + 1);
        positionPart.append(DOT);
        if (((position + 1) / 10) > 0) {
            positionPart.append(SPACE);
        } else {
            positionPart.append(SPACE).append(SPACE);
        }
        return positionPart.toString();
    }

    private String buildRacerPart(Racer racer) {
        return addSymbolsToString(racer.getName(), calculateMaxRacerNameLength(), SPACE) + VERTICAL_LINE;
    }

    private String buildTeamPart(Racer racer) {
        return addSymbolsToString(racer.getTeam(), calculateMaxTeamNameLength(), SPACE) + VERTICAL_LINE;
    }

    private String buildBestLapTimePart(Racer racer) {
        return String.format("%2d:%02d.%03d",
                racer.getBestLapTime().toMinutes(),
                racer.getBestLapTime().getSeconds()%60,
                racer.getBestLapTime().toMillis()%1000);
    }

    private int calculateMaxRacerNameLength() {
        OptionalInt max = racersTable.stream()
                .map(Racer::getName)
                .mapToInt(String::length)
                .max();
        return max.orElse(0);
    }

    private int calculateMaxTeamNameLength() {
        OptionalInt max = racersTable.stream()
                .map(Racer::getTeam)
                .mapToInt(String::length)
                .max();
        return max.orElse(0);
    }

    private String addSymbolsToString(String string, int totalLength, char symbol) {
        StringBuilder result = new StringBuilder(string);
        int spaces = totalLength - string.length();
        for (int i = 0; i < spaces; i++) {
            result.append(symbol);
        }
        return result.toString();
    }
}
