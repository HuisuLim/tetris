/*
package settings;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Properties;



public class settingModel {
    public static String SETTINGS_FILE = "settings.properties";
    public static String SCOREBOARD_FILE = "scoreboard.txt";
    private static final String SCREEN_SIZE_KEY = "ScreenSize";
    private static final String COLOR_MODE_KEY = "ColorMode";
    private static final String CONTROL_KEY = "MOVEMENT";
    private static final String DIFFICULTY_KEY = "Difficulty";


    //data들 가지고있는 클래스 -찬영
    public double screenSize;
    public boolean colorBlindMode;
    public String difficulty;
    public final int[] keys;
    public settingModel() {
        screenSize = loadScreenSize();
        colorBlindMode = loadColorBlindMode();
        difficulty = loadDifficulty();
        keys = loadKeys();
    }

    public void setSettingsFile(String fileName){
        SETTINGS_FILE = fileName;
    }
    public void setScoreboardFile(String fileName){
        SCOREBOARD_FILE = fileName;
    }

    public String loadKeySettings() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
            String controlKey = properties.getProperty(CONTROL_KEY);
            return controlKey;
        } catch (Exception ex) {
            return "ArrowKeys";
        }
    }
    public int getLeftKey() {
        String movement = loadKeySettings();
        if (movement.equals("ArrowKeys")) {
            return KeyEvent.VK_LEFT;
        } else if (movement.equals("WASD")) {
            return KeyEvent.VK_A;
        }
        return KeyEvent.VK_LEFT; // 기본적으로는 화살표 키를 반환하도록 설정
    }

    public int getRightKey() {
        String movement = loadKeySettings();
        if (movement.equals("ArrowKeys")) {
            return KeyEvent.VK_RIGHT;
        } else if (movement.equals("WASD")) {
            return KeyEvent.VK_D;
        }
        return KeyEvent.VK_RIGHT; // 기본적으로는 화살표 키를 반환하도록 설정
    }

    public int getUpKey() {
        String movement = loadKeySettings();
        if (movement.equals("ArrowKeys")) {
            return KeyEvent.VK_UP;
        } else if (movement.equals("WASD")) {
            return KeyEvent.VK_W;
        }
        return KeyEvent.VK_UP; // 기본적으로는 화살표 키를 반환하도록 설정
    }

    public int getDownKey() {
        String movement = loadKeySettings();
        if (movement.equals("ArrowKeys")) {
            return KeyEvent.VK_DOWN;
        } else if (movement.equals("WASD")) {
            return KeyEvent.VK_S;
        }
        return KeyEvent.VK_DOWN; // 기본적으로는 화살표 키를 반환하도록 설정
    }

    public int[] loadKeys() {
        int[] keys = new int[5];

        keys[0] = getUpKey();
        keys[1] = getRightKey();
        keys[2] = getDownKey();
        keys[3] = getLeftKey();
        keys[4] = KeyEvent.VK_SPACE;

        return keys;
    }


    public double loadScreenSize() {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(SETTINGS_FILE)) {
            properties.load(inputStream);
            return Double.parseDouble(properties.getProperty(SCREEN_SIZE_KEY, "1.6")); // 기본값은 중간 크기
        } catch (Exception e) {
            e.printStackTrace();
            return 1.6; // 파일 읽기 실패시 기본값 반환
        }
    }
    public boolean loadColorBlindMode() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
            String mode = properties.getProperty(COLOR_MODE_KEY);
            return Boolean.parseBoolean(mode);
        } catch (Exception ex) {
            ex.printStackTrace();
            // 기본값인 일반 모드를 반환하거나 적절한 기본값을 선택합니다.
            return false;
        }
    }
    public String loadDifficulty() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
            String difficulty = properties.getProperty(DIFFICULTY_KEY);
            if (difficulty != null) {
                return difficulty;
            } else {
                return "normal";
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "normal";
        }
    }
    public void clearScoreboard() {
        File file = new File(SCOREBOARD_FILE);
        try {
            new FileWriter(file, false).close();
            if(SCOREBOARD_FILE.equals("scoreboard.txt") ) {
                JOptionPane.showMessageDialog(null, "스코어보드가 초기화되었습니다.", "완료", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ioException) {
            if(SCOREBOARD_FILE.equals("scoreboard.txt")) {
                JOptionPane.showMessageDialog(null, "스코어보드 초기화 실패.", "오류", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void saveSetting(String key, String value) {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(SETTINGS_FILE)) {
            properties.load(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        properties.setProperty(key, value);
        try (OutputStream outputStream = new FileOutputStream(SETTINGS_FILE)) {
            properties.store(outputStream, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

 */
package settingscreen;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class settingModel {
    private static final Logger LOGGER = Logger.getLogger(settingModel.class.getName());
    public static String SETTINGS_FILE = "settings.properties";
    public static String SCOREBOARD_FILE = "scoreboard.txt";
    private static final String SCREEN_SIZE_KEY = "ScreenSize";
    private static final String COLOR_MODE_KEY = "ColorMode";
    private static final String CONTROL_KEY = "MOVEMENT";
    private static final String DIFFICULTY_KEY = "Difficulty";

    //data들 가지고있는 클래스 -찬영
    public double screenSize;
    public boolean colorBlindMode;
    public String difficulty;
    public final int[] keys;

    public settingModel() {
        setDefaultSetting();
        screenSize = loadScreenSize();
        colorBlindMode = loadColorBlindMode();
        difficulty = loadDifficulty();
        keys = loadKeys();
    }

    public void setSettingsFile(String fileName){
        SETTINGS_FILE = fileName;
    }
    public void setScoreboardFile(String fileName){
        SCOREBOARD_FILE = fileName;
    }

    public void setDefaultSetting() {
        Properties properties = new Properties();
        File file = new File(SETTINGS_FILE);
        if (!file.exists() || file.length() == 0) {
            try {
                if (!file.exists()) {
                    if (!file.createNewFile()) {
                        throw new IOException("Failed to create new settings file.");
                    }
                }
                try (OutputStream output = new FileOutputStream(SETTINGS_FILE)) {
                    properties.setProperty(SCREEN_SIZE_KEY, "1.6");
                    properties.setProperty(COLOR_MODE_KEY, "false");
                    properties.setProperty(CONTROL_KEY, "ArrowKeys");
                    properties.setProperty(DIFFICULTY_KEY, "normal");
                    properties.store(output, null);
                }
            } catch (IOException e) {
                LOGGER.log(Level.SEVERE, "Failed to create or write default settings", e);
            }
        }
    }

    public String loadKeySettings() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
            return properties.getProperty(CONTROL_KEY, "ArrowKeys");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to load key settings", ex);
            return "ArrowKeys";
        }
    }

    public int getLeftKey() {
        String movement = loadKeySettings();
        if (movement.equals("ArrowKeys")) {
            return KeyEvent.VK_LEFT;
        } else if (movement.equals("WASD")) {
            return KeyEvent.VK_A;
        }
        return KeyEvent.VK_LEFT; // 기본적으로는 화살표 키를 반환하도록 설정
    }

    public int getRightKey() {
        String movement = loadKeySettings();
        if (movement.equals("ArrowKeys")) {
            return KeyEvent.VK_RIGHT;
        } else if (movement.equals("WASD")) {
            return KeyEvent.VK_D;
        }
        return KeyEvent.VK_RIGHT; // 기본적으로는 화살표 키를 반환하도록 설정
    }

    public int getUpKey() {
        String movement = loadKeySettings();
        if (movement.equals("ArrowKeys")) {
            return KeyEvent.VK_UP;
        } else if (movement.equals("WASD")) {
            return KeyEvent.VK_W;
        }
        return KeyEvent.VK_UP; // 기본적으로는 화살표 키를 반환하도록 설정
    }

    public int getDownKey() {
        String movement = loadKeySettings();
        if (movement.equals("ArrowKeys")) {
            return KeyEvent.VK_DOWN;
        } else if (movement.equals("WASD")) {
            return KeyEvent.VK_S;
        }
        return KeyEvent.VK_DOWN; // 기본적으로는 화살표 키를 반환하도록 설정
    }

    public int[] loadKeys() {
        int[] keys = new int[5];

        keys[0] = getUpKey();
        keys[1] = getRightKey();
        keys[2] = getDownKey();
        keys[3] = getLeftKey();
        keys[4] = KeyEvent.VK_SPACE;

        return keys;
    }

    public double loadScreenSize() {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(SETTINGS_FILE)) {
            properties.load(inputStream);
            return Double.parseDouble(properties.getProperty(SCREEN_SIZE_KEY, "1.6")); // 기본값은 중간 크기
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to load screen size", e);
            return 1.6; // 파일 읽기 실패시 기본값 반환
        }
    }

    public boolean loadColorBlindMode() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
            String mode = properties.getProperty(COLOR_MODE_KEY);
            return Boolean.parseBoolean(mode);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "스코어보드가 초기화되었습니다.", "완료", JOptionPane.INFORMATION_MESSAGE);
            LOGGER.log(Level.SEVERE, "Failed to load color blind mode", ex);
            // 기본값인 일반 모드를 반환하거나 적절한 기본값을 선택합니다.
            return false;
        }
    }

    public String loadDifficulty() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
            return properties.getProperty(DIFFICULTY_KEY, "normal");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "Failed to load difficulty", ex);
            return "normal";
        }
    }

    public void clearScoreboard() {
        File file = new File(SCOREBOARD_FILE);
        try {
            new FileWriter(file, false).close();
            if (SCOREBOARD_FILE.equals("scoreboard.txt")) {
                JOptionPane.showMessageDialog(null, "스코어보드가 초기화되었습니다.", "완료", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ioException) {
            if (SCOREBOARD_FILE.equals("scoreboard.txt")) {
                JOptionPane.showMessageDialog(null, "스코어보드 초기화 실패.", "오류", JOptionPane.ERROR_MESSAGE);
            }
            LOGGER.log(Level.SEVERE, "Failed to clear scoreboard", ioException);
        }
    }

    public void saveSetting(String key, String value) {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(SETTINGS_FILE)) {
            properties.load(inputStream);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to load settings for saving", e);
        }
        properties.setProperty(key, value);
        try (OutputStream outputStream = new FileOutputStream(SETTINGS_FILE)) {
            properties.store(outputStream, null);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Failed to save setting", e);
        }
    }
}

