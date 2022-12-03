package com.example.application.functions;

public class FileParser {
    public static String ParseText(String[] text) {
        StringBuilder result = new StringBuilder();
        int fixedSectionNumber = 0;
        for (var line : text) {
            int sectionNumber = 0;
            for (int i = 0; i < line.length(); i++) {
                if (line.toCharArray()[i] != '#') break;
                sectionNumber++;
            }

            if (sectionNumber != 0) {
                fixedSectionNumber = sectionNumber;
                result.append("\n").append("\t".repeat(sectionNumber - 1))
                        .append(line.substring(sectionNumber)).append("\n");
            } else
                result.append("\t".repeat(fixedSectionNumber)).append(line).append("\n");
        }
        return result.substring(1);
    }
}
