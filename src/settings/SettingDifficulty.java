package settings;

import startscreen.StartMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.Properties;

public class SettingDifficulty extends JFrame implements ActionListener {
    private JRadioButton easyButton, normalButton, hardButton;
    private JButton checkButton;
    private static final String EASY = "easy";
    private static final String NORMAL = "normal";
    private static final String HARD = "hard";

    private static final String SETTINGS_FILE = "src/Settings/settings.properties";
    private static final String DIFFICULTY = "Difficulty";


    private void setRadioButton(){
        LoadData loadData = new LoadData();
        String difficulty = loadData.loadDifficulty();
        switch (difficulty) {
            case EASY:
                easyButton.setSelected(true);
                break;
            case NORMAL:
                normalButton.setSelected(true);
                break;
            case HARD:
                hardButton.setSelected(true);
                break;
            default:
                // Default to medium size if no valid setting found
                normalButton.setSelected(true);
                break;
        }
    }
    public SettingDifficulty() {
        setTitle("Difficulty Setting");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 간격 조절

        JLabel label = new JLabel("Difficulty mode:");
        panel.add(label);

        // 라디오 버튼 생성
        easyButton = new JRadioButton("easy");
        easyButton.addActionListener(this);
        panel.add(easyButton);

        normalButton = new JRadioButton("normal");
        normalButton.addActionListener(this);
        panel.add(normalButton);

        hardButton = new JRadioButton("hard");
        hardButton.addActionListener(this);
        panel.add(hardButton);

        // 라디오 버튼 그룹 생성
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(easyButton);
        buttonGroup.add(normalButton);
        buttonGroup.add(hardButton);
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
                    if (easyButton.isSelected()) {
                        if (keyCode == key.getRightKey()) {
                            normalButton.setSelected(true);
                        }
                        else if (keyCode == key.getLeftKey()) {
                            hardButton.setSelected(true);
                        }
                    } else if (normalButton.isSelected()) {
                        if (keyCode == key.getLeftKey()) {
                            easyButton.setSelected(true);
                        }
                        else if (keyCode == key.getRightKey()) {
                            hardButton.setSelected(true);
                        }
                    } else if (hardButton.isSelected()) {
                        if (keyCode == key.getLeftKey()) {
                            normalButton.setSelected(true);
                        }
                        else if (keyCode == key.getRightKey()) {
                            easyButton.setSelected(true);
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
            String difficulty;
            if (easyButton.isSelected()) {
                difficulty = EASY;
            } else if (normalButton.isSelected()) {
                difficulty = NORMAL;
            } else if (hardButton.isSelected()) {
                difficulty = HARD;
            } else {
                difficulty = NORMAL;
            }
            saveSettings(DIFFICULTY, difficulty); // 동일한 설정 파일 사용
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

    // 테스트용
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SettingDifficulty menu = new SettingDifficulty();
                menu.setVisible(true);
            }
        });
    }
}

