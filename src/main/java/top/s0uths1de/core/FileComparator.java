package top.s0uths1de.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileComparator {

    private final Map<String, String> infoMap;
    private final List<String> directoryList;
    private final List<String> value;
    private final List<String> key;
    private final List<String> explorerNames;
    private static String matchTenConsecutiveDigits;
    private static String matchChineseCharacter;
    private static File directory;

    public FileComparator(File info, File directory) {
        this.infoMap = readInfo(info.getPath());
        this.value = new ArrayList<>();
        this.key = new ArrayList<>();
        this.explorerNames = new ArrayList<>();
        this.directoryList = listFiles(directory.getPath());
        this.directory = directory;
    }

    public static String getMatchTenConsecutiveDigits() {
        return matchTenConsecutiveDigits;
    }

    public static void setMatchTenConsecutiveDigits(String matchTenConsecutiveDigits) {
        FileComparator.matchTenConsecutiveDigits = matchTenConsecutiveDigits;
    }

    public static String getMatchChineseCharacter() {
        return matchChineseCharacter;
    }

    public static void setMatchChineseCharacter(String matchChineseCharacter) {
        FileComparator.matchChineseCharacter = matchChineseCharacter;
    }


    /**
     * 获取信息文件的Key与Value或组成的MaP
     */
    public Map<String, String> getInfoMap() {
        return infoMap;
    }

    /**
     * 获取读取到的文件经过正则识别的文件名字List集合
     */
    public List<String> getDirectoryList() {
        return directoryList;
    }

    /**
     * 获取读取到文件未经过识别的文件名的List集合
     */
    public List<String> getExplorerNames() {
        return explorerNames;
    }

    public static Map<String, String> readInfo(String info) {
        Map<String, String> idAndNameMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(info, Charset.forName("GB2312")))) {
            String line;
            while ((line = br.readLine()) != null) {
                String key = match(line, matchTenConsecutiveDigits);
                String value = match(line, matchChineseCharacter);
                idAndNameMap.put(key, value);
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

    private List<String> listFiles(String path) {
        List<String> fileList = new ArrayList<>();
        File directory = new File(path);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    explorerNames.add(fileName);
                    String key = match(fileName, matchTenConsecutiveDigits);
                    String value = match(fileName, matchChineseCharacter);
                    if (key == null && value == null) {
                        fileList.add(file.getName());
                    } else {
                        key = key == null ? "" : key;
                        value = value == null ? "" : value;
                        this.key.add(key);
                        this.value.add(value);
                        fileList.add(key + value);
                    }
                }
            }
        }
        return fileList;
    }

    public static void renameFileName(String regex) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                String fileName = file.getName();
                String key = match(fileName, matchTenConsecutiveDigits);
                String value = match(fileName, matchChineseCharacter);
                String fileAbsolute = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.separator));
                fileName = regex;
                if (key != null)
                    fileName = fileName.replace("{KEY}", key);
                if (value != null)
                    fileName = fileName.replace("{VALUE}", value);
                File newFile = new File(fileAbsolute + "\\" + fileName);
                boolean result = file.renameTo(newFile);
            }
        }
    }

    public List<String> getValue() {
        return value;
    }

    public List<String> getKey() {
        return key;
    }
}
