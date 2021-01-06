package com.foxminded.formula1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

class FileReaderTest {

    FileReader reader;

    @BeforeEach
    void init() {
        reader = new FileReader();
    }

    @Test
    void convertFileToList_ShouldThrowExceptionWhenFileIsNotFound() {
        File file = new File("src/test/resources/nonexistentfile.txt");
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                reader.convertFileToList(file));
    }

    @Test
    void convertFileToList_ShouldThrowExceptionWhenFileIsEmpty() {
        File file = new File("src/test/resources/emptyabbreviations.txt");
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                reader.convertFileToList(file));
    }

    @Test
    void convertFileToList_ShouldThrowExceptionWhenInputIsNull() {
        Assertions.assertThrows(NullPointerException.class, () ->
                reader.convertFileToList(null));
    }
}