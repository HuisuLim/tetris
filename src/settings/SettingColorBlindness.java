package settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.*;
import java.util.Properties;
import startscreen.Setting;
import java.awt.event.KeyEvent;

public class SettingColorBlindness extends JFrame implements ActionListener {
    private JRadioButton normalModeButton, colorBlindModeButton;
    private JButton checkButton;

    private static final String SETTINGS_FILE = "src/Settings/settings.properties";
    private static final String COLOR_MODE_KEY = "ColorMode";

    private void setRadioButton(){
        LoadData loadData = new LoadData();
        boolean isColorBlind = loadData.loadColorBlindMode();
        if(isColorBlind) {
            colorBlindModeButton.setSelected(true);
        }
        else {
            normalModeButton.setSelected(true);
        }
    }

    public SettingColorBlindness() {
        setTitle("색맹모드");
        setSize(400, 100);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 간격 조절

        // 색맹 모드 라디오 버튼
        JLabel modeLabel = new JLabel("Color Mode:");
        panel.add(modeLabel);

        normalModeButton = new JRadioButton("Normal");
        normalModeButton.addActionListener(this);
        panel.add(normalModeButton);

        colorBlindModeButton = new JRadioButton("Color Blind");
        colorBlindModeButton.addActionListener(this);
        panel.add(colorBlindModeButton);

        // 라디오 버튼 그룹 생성 및 추가
        ButtonGroup modeButtonGroup = new ButtonGroup();
        modeButtonGroup.add(normalModeButton);
        modeButtonGroup.add(colorBlindModeButton);
        setRadioButton();
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                LoadData key = new LoadData();
                if (keyCode == key.getRightKey() || keyCode == key.getLeftKey()) {
                    if (normalModeButton.isSelected()) {
                        colorBlindModeButton.setSelected(true);
                    } else if (colorBlindModeButton.isSelected()) {
                        normalModeButton.setSelected(true);
                    }
                }
            }
        });
        // 버튼 생성
        checkButton = new JButton("check");
        checkButton.addActionListener(this);
        add(checkButton, BorderLayout.SOUTH); // 아래쪽에 배치

        add(panel);


        // "check" 버튼에 엔터키 액션 바인딩
        checkButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "checkPressed");
        checkButton.getActionMap().put("checkPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkButton.doClick();
            }
        });

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
            boolean isColorBlindMode = colorBlindModeButton.isSelected();
            saveSettings(COLOR_MODE_KEY, String.valueOf(isColorBlindMode)); // 동일한 설정 파일 사용
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
