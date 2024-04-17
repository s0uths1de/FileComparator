package top.s0uths1de.function;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class INIFileHandler {
    private Map<String, Map<String, String>> sections = new HashMap<>();

    public void load(String filename) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            String currentSection = null;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith(";") || line.startsWith("#")) {
                    // Ignore empty lines and comments
                    continue;
                } else if (line.startsWith("[") && line.endsWith("]")) {
                    // New section
                    currentSection = line.substring(1, line.length() - 1);
                    sections.put(currentSection, new HashMap<>());
                } else if (currentSection != null && line.contains("=")) {
                    // Key-value pair
                    int separatorIndex = line.indexOf("=");
                    String key = line.substring(0, separatorIndex).trim();
                    String value = line.substring(separatorIndex + 1).trim();
                    sections.get(currentSection).put(key, value);
                }
            }
        }
    }

    public void save(String filename) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Map.Entry<String, Map<String, String>> section : sections.entrySet()) {
                writer.write("[" + section.getKey() + "]\n");
                for (Map.Entry<String, String> entry : section.getValue().entrySet()) {
                    writer.write(entry.getKey() + " = \"" + entry.getValue() + "\"\n");
                }
                writer.write("\n");
            }
        }
    }

    public String getValue(String section, String key) {
        Map<String, String> sectionMap = sections.get(section);
        if (sectionMap != null) {
            return sectionMap.get(key);
        }
        return null;
    }

    public void setValue(String section, String key, String value) {
        sections.computeIfAbsent(section, k -> new HashMap<>()).put(key, value);
    }
}
