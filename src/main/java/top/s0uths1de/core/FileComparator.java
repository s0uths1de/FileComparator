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
    private List<String> name;
    private List<String> id;
    public static final String matchTenConsecutiveDigits = "\\d+";
    public static final String matchChineseCharacter = "[\u4e00-\u9fff]+";

    public FileComparator(File info, File directory) {
        this.infoMap = readInfo(info.getPath());
        this.name = new ArrayList<>();
        this.id = new ArrayList<>();
        this.directoryList = listFiles(directory.getPath());
    }

    public Map<String, String> getInfoMap() {
        return infoMap;
    }

    public List<String> getDirectoryList() {
        return directoryList;
    }

    public static Map<String, String> readInfo(String info) {
        Map<String, String> idAndNameMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(info, Charset.forName("GB2312")))) {
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

    private List<String> listFiles(String path) {
        List<String> fileList = new ArrayList<>();
        File directory = new File(path);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    String fileName = file.getName();
                    String id = match(fileName, matchTenConsecutiveDigits);
                    String name = match(fileName, matchChineseCharacter);
                    if (id == null && name == null) {
                        fileList.add(file.getName());
                    } else {
                        id = id == null ? "" : id;
                        name = name == null ? "" : name;
                        this.id.add(id);
                        this.name.add(name);
                        fileList.add(id + name);
                    }
                }
            }
        }
        return fileList;
    }

    public List<String> getName() {
        return name;
    }

    public List<String> getId() {
        return id;
    }
}
