package top.s0uths1de.function;

import top.s0uths1de.Main;
import top.s0uths1de.core.FileComparator;

import java.io.File;
import java.io.IOException;

public class Permanently {
    static final String PATH = Main.class.getProtectionDomain().getCodeSource().getLocation().getPath();

    public static final String SECTION_CRITICAL = "SECTION_CRITICAL";
    public static final String LAST_TIME_FILE = "lastTimeFile";
    public static final String LAST_TIME_EXPLORER = "lastTimeExplorer";
    public static final String REGULAR_EXPRESSION_KEY = "regularExpressionKey";
    public static final String REGULAR_EXPRESSION_VALUE = "regularExpressionValue";
    public static File mainConfigFile;

    public static File getMainConfigFile() {
        return mainConfigFile;
    }

    public static void init() {
        // 获取当前类的加载器
        String p = PATH.substring(1);
        for (int i = PATH.length() - 1; i > -1; i--) {
            if (PATH.charAt(i) == '/') {
                p = PATH.substring(0, i);
                break;
            }
        }
        File main = new File(p + "\\.fileComparator");

        if (!main.exists() && !main.isDirectory()) {
            boolean mkdirs = main.mkdirs();
        }
        File config = new File(main.getAbsolutePath() + "\\config.ini");
        INIFileHandler ini = new INIFileHandler();
        mainConfigFile = config;

        if (!config.exists()) {
            try {
                boolean newFile = config.createNewFile();
                try {
                    ini.load(Permanently.getMainConfigFile().getAbsolutePath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                initINI(ini, config.getAbsolutePath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            ini.load(Permanently.getMainConfigFile().getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FileComparator.setMatchTenConsecutiveDigits(ini.getValue(SECTION_CRITICAL, "regularExpressionKey").replace("\"", ""));
        FileComparator.setMatchChineseCharacter(ini.getValue(SECTION_CRITICAL, "regularExpressionValue").replace("\"", ""));
    }

    private static void initINI(INIFileHandler ini, String config) {
        ini.setValue(SECTION_CRITICAL, LAST_TIME_FILE, "");
        ini.setValue(SECTION_CRITICAL, LAST_TIME_EXPLORER, "");
        ini.setValue(SECTION_CRITICAL, REGULAR_EXPRESSION_KEY, "\\d{10}");
        ini.setValue(SECTION_CRITICAL, REGULAR_EXPRESSION_VALUE, "[\\u4e00-\\u9fff]+");
        try {
            ini.save(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}