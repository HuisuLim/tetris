package Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;
import startScreen.Setting;

public class SettingScreen extends JFrame implements ActionListener {
    private JRadioButton smallButton, mediumButton, largeButton;
    private JButton checkButton;
    private static final int SMALL_SIZE = 1;
    private static final int MEDIUM_SIZE = 2;
    private static final int LARGE_SIZE = 3;

    private static final String SETTINGS_FILE = "src/Settings/settings.properties";
    private static final String SCREEN_RATIO_KEY = "ScreenSize";


    private int setScreenRatio(){
        LoadData loadData = new LoadData();
        return loadData.loadScreenSize();
    }
    public SettingScreen() {
        setTitle("Screen Setting");
        setSize(400, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 간격 조절

        JLabel label = new JLabel("Screen Size:");
        panel.add(label);

        // 라디오 버튼 생성
        smallButton = new JRadioButton("Small");
        smallButton.addActionListener(this);
        panel.add(smallButton);

        mediumButton = new JRadioButton("Medium");
        mediumButton.addActionListener(this);
        panel.add(mediumButton);

        largeButton = new JRadioButton("Large");
        largeButton.addActionListener(this);
        panel.add(largeButton);

        // 라디오 버튼 그룹 생성
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(smallButton);
        buttonGroup.add(mediumButton);
        buttonGroup.add(largeButton);

        // 버튼 생성
        checkButton = new JButton("check");
        checkButton.addActionListener(this);
        add(checkButton, BorderLayout.SOUTH); // 아래쪽에 배치

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkButton) {
            int screenRatio;
            if (smallButton.isSelected()) {
                screenRatio = SMALL_SIZE;
            } else if (mediumButton.isSelected()) {
                screenRatio = MEDIUM_SIZE;
            } else if (largeButton.isSelected()) {
                screenRatio = LARGE_SIZE;
            } else {
                screenRatio = MEDIUM_SIZE;
            }
            saveSettings(SCREEN_RATIO_KEY, String.valueOf(screenRatio)); // 동일한 설정 파일 사용
            dispose(); // 설정 화면 종료
            Setting test = new Setting();
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
