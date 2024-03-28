package top.s0uths1de.entity;

import java.io.File;

public class FileEntity {
    File info;
    File homework;

    public FileEntity(File info, File homework) {
        this.info = info;
        this.homework = homework;
    }

    public File getInfo() {
        return info;
    }

    public void setInfo(File info) {
        this.info = info;
    }

    public File getHomework() {
        return homework;
    }

    public void setHomework(File homework) {
        this.homework = homework;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "info=" + info +
                ", homework=" + homework +
                '}';
    }
}
