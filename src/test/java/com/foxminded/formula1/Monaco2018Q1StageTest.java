package com.foxminded.formula1;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class Monaco2018Q1StageTest {

    protected ByteArrayOutputStream output;
    private PrintStream old;
    Monaco2018Q1Stage formula;

    @BeforeEach
    void setUpStreams() {
        old = System.out;
        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }

    @BeforeEach
    void init() {
        formula = new Monaco2018Q1Stage();
    }

    @AfterEach
    void cleanUpStreams() {
        System.setOut(old);
    }


    @Test
    void showResultOfQ1Stage_ShouldThrowExceptionWhenStringInAbbreviationFileHasAnInvalidFormat() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                formula.showResultOfQ1Stage("src/test/resources/abbreviations_invalidformat.txt", "src/test/resources/start.log", "src/test/resources/end.log"));
    }

    @Test
    void showResultOfQ1Stage_ShouldThrowExceptionWhenStringInStartFileHasAnInvalidFormat() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                formula.showResultOfQ1Stage("src/test/resources/abbreviations.txt", "src/test/resources/start_invalidformat.log", "src/test/resources/end.log"));
    }

    @Test
    void showResultOfQ1Stage_ShouldThrowExceptionWhenStringInEndFileHasAnInvalidFormat() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                formula.showResultOfQ1Stage("src/test/resources/abbreviations.txt", "src/test/resources/start.log", "src/test/resources/end_invalidformat.log"));
    }

    @Test
    void showResultOfQ1Stage_ShouldThrowExceptionWhenListSizesDoNotMatch() {
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                formula.showResultOfQ1Stage("src/test/resources/abbreviations_invalidstringssize.txt", "src/test/resources/start_invalidstringssize.log", "src/test/resources/end_invalidstringssize.log"));
    }

    @Test
    void showResultOfQ1Stage_ShouldPrintReportThatShowsTheTopRacersWhenListSizesLessThan15() {
        formula.showResultOfQ1Stage("src/test/resources/abbreviations_9.txt", "src/test/resources/start_9.log", "src/test/resources/end_9.log");
        String expected =   "1.  Daniel Ricciardo |RED BULL RACING TAG HEUER| 1:12.013\n" +
                            "2.  Sebastian Vettel |FERRARI                  | 1:12.415\n" +
                            "3.  Valtteri Bottas  |MERCEDES                 | 1:12.434\n" +
                            "4.  Lewis Hamilton   |MERCEDES                 | 1:12.460\n" +
                            "5.  Stoffel Vandoorne|MCLAREN RENAULT          | 1:12.463\n" +
                            "6.  Kimi Raikkonen   |FERRARI                  | 1:12.639\n" +
                            "7.  Fernando Alonso  |MCLAREN RENAULT          | 1:12.657\n" +
                            "8.  Sergey Sirotkin  |WILLIAMS MERCEDES        | 1:12.706\n" +
                            "9.  Charles Leclerc  |SAUBER FERRARI           | 1:12.829\n";

        Assert.assertEquals(expected, output.toString());
    }

    @Test
    void showResultOfQ1Stage_ShouldPrintReportThatShowsTheTopRacersWhenListSizesEquals15() {
        formula.showResultOfQ1Stage("src/test/resources/abbreviations_15.txt", "src/test/resources/start_15.log", "src/test/resources/end_15.log");
        String expected =   "1.  Daniel Ricciardo |RED BULL RACING TAG HEUER| 1:12.013\n" +
                            "2.  Sebastian Vettel |FERRARI                  | 1:12.415\n" +
                            "3.  Valtteri Bottas  |MERCEDES                 | 1:12.434\n" +
                            "4.  Lewis Hamilton   |MERCEDES                 | 1:12.460\n" +
                            "5.  Stoffel Vandoorne|MCLAREN RENAULT          | 1:12.463\n" +
                            "6.  Kimi Raikkonen   |FERRARI                  | 1:12.639\n" +
                            "7.  Fernando Alonso  |MCLAREN RENAULT          | 1:12.657\n" +
                            "8.  Sergey Sirotkin  |WILLIAMS MERCEDES        | 1:12.706\n" +
                            "9.  Charles Leclerc  |SAUBER FERRARI           | 1:12.829\n" +
                            "10. Sergio Perez     |FORCE INDIA MERCEDES     | 1:12.848\n" +
                            "11. Romain Grosjean  |HAAS FERRARI             | 1:12.930\n" +
                            "12. Pierre Gasly     |SCUDERIA TORO ROSSO HONDA| 1:12.941\n" +
                            "13. Carlos Sainz     |RENAULT                  | 1:12.950\n" +
                            "14. Esteban Ocon     |FORCE INDIA MERCEDES     | 1:13.028\n" +
                            "15. Nico Hulkenberg  |RENAULT                  | 1:13.065\n";

        Assert.assertEquals(expected, output.toString());
    }

    @Test
    void showResultOfQ1Stage_ShouldPrintReportThatShowsTheTop15RacersAndTheRestAfterUnderlineWhenListSizesMoreThan15() {
        formula.showResultOfQ1Stage("src/main/resources/abbreviations.txt", "src/main/resources/start.log", "src/main/resources/end.log");
        String expected =   "1.  Daniel Ricciardo |RED BULL RACING TAG HEUER| 1:12.013\n" +
                            "2.  Sebastian Vettel |FERRARI                  | 1:12.415\n" +
                            "3.  Valtteri Bottas  |MERCEDES                 | 1:12.434\n" +
                            "4.  Lewis Hamilton   |MERCEDES                 | 1:12.460\n" +
                            "5.  Stoffel Vandoorne|MCLAREN RENAULT          | 1:12.463\n" +
                            "6.  Kimi Raikkonen   |FERRARI                  | 1:12.639\n" +
                            "7.  Fernando Alonso  |MCLAREN RENAULT          | 1:12.657\n" +
                            "8.  Sergey Sirotkin  |WILLIAMS MERCEDES        | 1:12.706\n" +
                            "9.  Charles Leclerc  |SAUBER FERRARI           | 1:12.829\n" +
                            "10. Sergio Perez     |FORCE INDIA MERCEDES     | 1:12.848\n" +
                            "11. Romain Grosjean  |HAAS FERRARI             | 1:12.930\n" +
                            "12. Pierre Gasly     |SCUDERIA TORO ROSSO HONDA| 1:12.941\n" +
                            "13. Carlos Sainz     |RENAULT                  | 1:12.950\n" +
                            "14. Esteban Ocon     |FORCE INDIA MERCEDES     | 1:13.028\n" +
                            "15. Nico Hulkenberg  |RENAULT                  | 1:13.065\n" +
                            "---------------------------------------------------------\n" +
                            "16. Brendon Hartley  |SCUDERIA TORO ROSSO HONDA| 1:13.179\n" +
                            "17. Marcus Ericsson  |SAUBER FERRARI           | 1:13.265\n" +
                            "18. Lance Stroll     |WILLIAMS MERCEDES        | 1:13.323\n" +
                            "19. Kevin Magnussen  |HAAS FERRARI             | 1:13.393\n";

        Assert.assertEquals(expected, output.toString());
    }

    @Test
    void showResultOfQ1Stage_ShouldPrintReportThatShowsTheTop15RacersAndTheRestAfterUnderlineWhenListSizesMoreThan15AndInitialDataChanged() {
        formula.showResultOfQ1Stage("src/test/resources/abbreviations_ch.txt", "src/test/resources/start_ch.log", "src/test/resources/end_ch.log");
        String expected =   "1.  Alex Belyaev     |SAUBER FERRARI           | 0:30.265\n" +
                            "2.  Lewis Hamilton   |MERCEDES                 | 0:42.460\n" +
                            "3.  Pierre Gasly     |SCUDERIA TORO ROSSO HONDA| 0:52.941\n" +
                            "4.  Daniel Ricciardo |RED BULL RACING TAG HEUER| 1:12.013\n" +
                            "5.  Valtteri Bottas  |MERCEDES                 | 1:12.434\n" +
                            "6.  Stoffel Vandoorne|MCLAREN RENAULT          | 1:12.463\n" +
                            "7.  Kimi Raikkonen   |FERRARI                  | 1:12.639\n" +
                            "8.  Sergey Sirotkin  |WILLIAMS MERCEDES        | 1:12.706\n" +
                            "9.  Charles Leclerc  |SAUBER FERRARI           | 1:12.829\n" +
                            "10. Sergio Perez     |FORCE INDIA MERCEDES     | 1:12.848\n" +
                            "11. Romain Grosjean  |HAAS FERRARI             | 1:12.930\n" +
                            "12. Carlos Sainz     |RENAULT                  | 1:12.950\n" +
                            "13. Esteban Ocon     |FORCE INDIA MERCEDES     | 1:13.028\n" +
                            "14. Nico Hulkenberg  |RENAULT                  | 1:13.065\n" +
                            "15. Lance Stroll     |WILLIAMS MERCEDES        | 1:13.323\n" +
                            "---------------------------------------------------------\n" +
                            "16. Kevin Magnussen  |HAAS FERRARI             | 1:13.393\n" +
                            "17. Sebastian Vettel |FERRARI                  | 1:32.415\n" +
                            "18. Fernando Alonso  |MCLAREN RENAULT          | 1:32.657\n" +
                            "19. Brendon Hartley  |SCUDERIA TORO ROSSO HONDA| 1:33.179\n";

        Assert.assertEquals(expected, output.toString());
    }
}