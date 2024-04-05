package temp;

import java.io.*;
import java.util.*;

public class TetrisProperties {
    private final String propertiesFile = "tetris.properties";
    private Map<String, SettingItem> settings = new HashMap<>();
    private Properties properties = new Properties();

    public TetrisProperties() {
        loadProperties();
        addSettingItem(new SettingItem("화면크기", List.of("1", "2", "3"), properties.getProperty("화면크기", "2")));
        addSettingItem(new SettingItem("난이도", List.of("쉬움", "보통", "어려움"), properties.getProperty("난이도", "보통")));
        addSettingItem(new SettingItem("모드", List.of("일반", "아이템"), properties.getProperty("모드", "일반")));
        addSettingItem(new SettingItem("조작키", List.of("방향키", "WASD"), properties.getProperty("조작키", "방향키")));
        addSettingItem(new SettingItem("색맹", List.of("꺼짐", "켜짐"), properties.getProperty("색맹", "꺼짐")));
    }

    private void loadProperties() {
        try {
            File file = new File(propertiesFile);
            if (file.exists()) {
                try (FileInputStream in = new FileInputStream(file)) {
                    properties.load(in);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveProperties() {
        try (FileOutputStream out = new FileOutputStream(propertiesFile)) {
            for (Map.Entry<String, SettingItem> entry : settings.entrySet()) {
                properties.setProperty(entry.getKey(), entry.getValue().getCurrentValue());
            }
            properties.store(out, "Tetris Settings");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addSettingItem(SettingItem item) {
        settings.put(item.getName(), item);
        properties.setProperty(item.getName(), item.getCurrentValue()); // Default to initial value
    }

    public SettingItem getSettingItem(String name) {
        return settings.get(name);
    }

    public Map<String, SettingItem> getAllSettings() {
        return settings;
    }

    public void setProperty(String name, String currentValue) {
        SettingItem item = settings.get(name);
        if (item != null) {
            item.setCurrentValue(currentValue);
            properties.setProperty(name, currentValue);
        }
    }
}
