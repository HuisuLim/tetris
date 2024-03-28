package Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;
import startScreen.Setting;

public class SettingReset extends JFrame implements ActionListener {
    private JButton checkButton;

    private static final String SETTINGS_FILE = "src/Settings/settings.properties";
    private static final String COLOR_MODE_KEY = "ColorMode";
    private static final String SCREEN_RATIO_KEY = "ScreenSize";
    private static final String CONTROL_KEY = "MOVEMENT";

    private int setScreenRatio(){
        LoadData loadData = new LoadData();
        return loadData.loadScreenSize();
    }
    public SettingReset() {
        setTitle("세팅 리셋");
        setSize(200, 70);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        // 버튼 생성
        checkButton = new JButton("Reset");
        checkButton.addActionListener(this);
        add(checkButton);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkButton) {
            int screenRatio = 2;
            boolean isColorBlindMode = false;
            String keySetting = "ArrowKeys";
            saveSettings(SCREEN_RATIO_KEY, String.valueOf(screenRatio));
            saveSettings(COLOR_MODE_KEY, String.valueOf(isColorBlindMode));
            saveSettings(CONTROL_KEY, String.valueOf(keySetting));
            dispose(); // 설정 화면 종료
            Setting test = new Setting();
            test.setVisible(true);
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