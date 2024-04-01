package settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Properties;
import startscreen.Setting;

public class SettingScreen extends JFrame implements ActionListener {
    private JRadioButton smallButton, mediumButton, largeButton;
    private JButton checkButton;
    private static final int SMALL_SIZE = 1;
    private static final int MEDIUM_SIZE = 2;
    private static final int LARGE_SIZE = 3;

    private static final String SETTINGS_FILE = "src/Settings/settings.properties";
    private static final String SCREEN_RATIO_KEY = "ScreenSize";


    private void setRadioButton(){
        LoadData loadData = new LoadData();
        int screenSize = loadData.loadScreenSize();
        switch (screenSize) {
            case SMALL_SIZE:
                smallButton.setSelected(true);
                break;
            case MEDIUM_SIZE:
                mediumButton.setSelected(true);
                break;
            case LARGE_SIZE:
                largeButton.setSelected(true);
                break;
            default:
                // Default to medium size if no valid setting found
                mediumButton.setSelected(true);
                break;
        }
    }
    public SettingScreen() {
        setTitle("Screen Setting");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
        setRadioButton();

        // 버튼 생성
        checkButton = new JButton("check");
        checkButton.addActionListener(this);
        add(checkButton, BorderLayout.SOUTH); // 아래쪽에 배치

        add(panel);
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                LoadData key = new LoadData();
                if (keyCode == key.getLeftKey() || keyCode == key.getRightKey()) {
                    if (smallButton.isSelected()) {
                        if (keyCode == key.getRightKey()) {
                            mediumButton.setSelected(true);
                        }
                        else if (keyCode == key.getLeftKey()) {
                            largeButton.setSelected(true);
                        }
                    } else if (mediumButton.isSelected()) {
                        if (keyCode == key.getLeftKey()) {
                            smallButton.setSelected(true);
                        }
                        else if (keyCode == key.getRightKey()) {
                            largeButton.setSelected(true);
                        }
                    } else if (largeButton.isSelected()) {
                        if (keyCode == key.getLeftKey()) {
                            mediumButton.setSelected(true);
                        }
                        else if (keyCode == key.getRightKey()) {
                            smallButton.setSelected(true);
                        }
                    }
                }
            }
        });

        // "check" 버튼에 대한 키 바인딩 설정
        checkButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "checkPressed");
        checkButton.getActionMap().put("checkPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkButton.doClick();
            }
        });

        // "ESC" 키에 대한 이벤트 처리
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
