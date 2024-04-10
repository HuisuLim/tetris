package settings;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Properties;
import startscreen.Setting;

public class SettingReset extends JFrame implements ActionListener {
    private JButton checkButton;

    private static final String SETTINGS_FILE = "src/Settings/settings.properties";
    private static final String COLOR_MODE_KEY = "ColorMode";
    private static final String SCREEN_RATIO_KEY = "ScreenSize";
    private static final String CONTROL_KEY = "MOVEMENT";
    private static final String GAME_MODE_KEY = "GameMode";
    private static final String DIFFICULTY_KEY = "Difficulty";

    public SettingReset() {
        setTitle("세팅 리셋");
        setSize(200, 70);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        // 버튼 생성
        checkButton = new JButton("Reset");
        checkButton.addActionListener(this);
        add(checkButton);

        checkButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "checkPressed");
        checkButton.getActionMap().put("checkPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkButton.doClick();
            }
        });

        // ESC 키에 대한 이벤트 처리
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escapePressed");
        getRootPane().getActionMap().put("escapePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 창 닫기
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkButton) {
            double screenRatio = 1.5;
            boolean isColorBlindMode = false;
            String keySetting = "ArrowKeys";
            String gameMode = "normalMode";
            String difficulty = "normal";
            saveSettings(SCREEN_RATIO_KEY, String.valueOf(screenRatio));
            saveSettings(COLOR_MODE_KEY, String.valueOf(isColorBlindMode));
            saveSettings(CONTROL_KEY, keySetting);
            saveSettings(GAME_MODE_KEY, gameMode);
            saveSettings(DIFFICULTY_KEY, difficulty);
            dispose(); // 설정 화면 종료
        }
    }

    // 설정을 파일에 저장하는 메서드
    private void saveSettings(String key, String value) {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(SETTINGS_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.setProperty(key, value);
        try (OutputStream outputStream = new FileOutputStream(SETTINGS_FILE)) {
            properties.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}