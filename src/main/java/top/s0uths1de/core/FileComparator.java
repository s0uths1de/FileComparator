package top.s0uths1de.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileComparator {

    private final Map<String, String> csvList;
    private final List<String> directoryList;
    public static final String matchTenConsecutiveDigits = "\\d{10}";
    public static final String matchChineseCharacter = "[\u4e00-\u9fff]+";

    public FileComparator(String csvFilePath, String directoryPath) {
        this.csvList = readInfo(csvFilePath);
        this.directoryList = listFiles(directoryPath);
    }

    public Map<String, String> getCsvMap() {
        return csvList;
    }

    public List<String> getDirectoryList() {
        return directoryList;
    }

    public static Map<String, String> readInfo(String csvFilePath) {
        Map<String, String> idAndNameMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String id = match(line, matchTenConsecutiveDigits);
                String name = match(line, matchChineseCharacter);
                idAndNameMap.put(id, name);
            }
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
        return idAndNameMap;

    }

    private static String match(String string, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }

    private static List<String> listFiles(String directoryPath) {
        List<String> fileList = new ArrayList<>();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    fileList.add(match(fileName, matchTenConsecutiveDigits)+match(fileName, matchChineseCharacter));
                }
            }
        }
        return fileList;
    }
}
