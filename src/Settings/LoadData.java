package Settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import tetrisPlay.*;
import startScreen.*;
import java.awt.event.KeyEvent;


// test
public class LoadData extends JFrame implements ActionListener {

    private JButton startButton, settingButton;
    private static final String SETTINGS_FILE = "src/Settings/settings.properties";
    private static final String SQUARE_SIZE_KEY = "ScreenSize";
    private static final String COLOR_MODE_KEY = "ColorMode";
    private static final String CONTROL_KEY = "MOVEMENT";
    private static final int MEDIUM_SIZE = 30;


    public LoadData() {
        setTitle("Tetris");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        panel.add(startButton);

        settingButton = new JButton("Settings");
        settingButton.addActionListener(this);
        panel.add(settingButton);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            int squareSize = loadScreenSize();
            boolean colorBlindMode = loadColorBlindMode();
            TetrisPlay tetris = new TetrisPlay(squareSize, colorBlindMode);
            tetris.setVisible(true);
            dispose();
        } else if (e.getSource() == settingButton) {
            SettingScreen settingScreen = new SettingScreen();
            settingScreen.setVisible(true);
            dispose();
        }
    }

    public int loadScreenSize() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
            String screenSizeStr = properties.getProperty(SQUARE_SIZE_KEY);
            return Integer.parseInt(screenSizeStr);
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
            // 기본값인 MEDIUM_SIZE를 반환하거나 적절한 기본값을 선택합니다.
            return MEDIUM_SIZE;
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
