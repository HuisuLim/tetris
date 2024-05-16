package settings;

import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Properties;



public class settingModel {
    public static String SETTINGS_FILE = "settings.properties";
    private static final String SCREEN_SIZE_KEY = "ScreenSize";
    private static final String COLOR_MODE_KEY = "ColorMode";
    private static final String CONTROL_KEY = "MOVEMENT";
    private static final String DIFFICULTY_KEY = "Difficulty";


    //data들 가지고있는 클래스 -찬영
    public final double screenSize;
    public final boolean colorBlindMode;
    public final String difficulty;
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
