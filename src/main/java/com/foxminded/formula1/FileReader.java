package com.foxminded.formula1;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileReader {

    public List<String> convertFileToList(File file) {

        if (file.length() == 0) {
            throw new IllegalArgumentException("File is empty");
        }

        List<String> list;

        try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
            list = stream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new IllegalArgumentException("File not found");
        }

        return list;
    }
}
