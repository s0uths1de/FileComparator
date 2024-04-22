package top.s0uths1de.entity;

import java.io.File;

public class FileEntity {
    File info;
    File explorer;

    public FileEntity() {
    }

    public FileEntity(File info, File explorer) {
        this.info = info;
        this.explorer = explorer;
    }

    public File getInfo() {
        return info;
    }

    public void setInfo(File info) {
        this.info = info;
    }

    public File getHomework() {
        return explorer;
    }

    public void setHomework(File explorer) {
        this.explorer = explorer;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "info=" + info +
                ", explorer=" + explorer +
                '}';
    }
}
