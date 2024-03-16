package top.s0uths1de.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileComparator {
    private String csvFilePath;
    private String directoryPath;

    private final List<String> csvList;
    private final List<String> directoryList;

    public FileComparator(String csvFilePath,String directoryPath ){
        this.csvFilePath = csvFilePath;
        this.directoryPath = directoryPath;
        // 读取CSV文件中的文件列表
        this.csvList = readCSV(csvFilePath);

        // 获取目录下的所有文件

        this.directoryList= listFiles(directoryPath);
    }

    public List<String> getCsvList() {
        return csvList;
    }

    public List<String> getDirectoryList() {
        return directoryList;
    }

    // 读取CSV文件中的文件列表
    public List<String> readCSV(String csvFilePath) {
        List<String> fileList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                fileList.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    // 获取目录下的所有文件
    private static List<String> listFiles(String directoryPath) {
        List<String> fileList = new ArrayList<>();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    fileList.add(file.getName());
                }
            }
        }
        return fileList;
    }
}
