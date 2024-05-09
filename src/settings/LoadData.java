package settings;

import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;



// test
public class LoadData {

    private static final String SETTINGS_FILE = "settings.properties";
    private static final String SQUARE_SIZE_KEY = "ScreenSize";
    private static final String COLOR_MODE_KEY = "ColorMode";
    private static final String CONTROL_KEY = "MOVEMENT";
    private static final String DIFFICULTY_KEY = "Difficulty";
    private static final String GAME_MODE_KEY = "GameMode";




    public double loadScreenSize() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
            String screenSizeStr = properties.getProperty(SQUARE_SIZE_KEY);
            return Double.parseDouble(screenSizeStr);
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
            // 기본값인 MEDIUM_SIZE를 반환하거나 적절한 기본값을 선택합니다.
            return 2;
        }
    }
    public boolean loadColorBlindMode() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
            String mode = properties.getProperty(COLOR_MODE_KEY);
            return mode != null && mode.equals("true");
        } catch (IOException ex) {
            ex.printStackTrace();
            // 기본값인 일반 모드를 반환하거나 적절한 기본값을 선택합니다.
            return false;
        }
    }
    public String loadKeySettings() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
            String controlKey = properties.getProperty(CONTROL_KEY);
            return controlKey;
        } catch (IOException ex) {
            ex.printStackTrace();
            return "ArrowKeys";
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
        } catch (IOException ex) {
            ex.printStackTrace();
            return "normal";
        }
    }

    public int[] loadKeys() {
        int[] keys = new int[4];
        keys[0] = getUpKey();
        keys[1] = getRightKey();
        keys[2] = getDownKey();
        keys[3] = getLeftKey();
    }

    public String loadGameMode() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
            String gameMode = properties.getProperty(GAME_MODE_KEY);
            if (gameMode != null) {
                return gameMode;
            } else {
                return "normalMode";
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return "normalMode";
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

}
