package settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.io.*;
import java.util.Properties;
import java.awt.event.KeyEvent;

public class SettingGameMode extends JFrame implements ActionListener {
    private JRadioButton normalModeButton, itemModeButton;
    private JButton checkButton;

    private static final String SETTINGS_FILE = "src/Settings/settings.properties";
    private static final String GAME_MODE_KEY = "GameMode";
    private static final String NORMAL_MODE = "normalMode";
    private static final String ITEM_MODE = "itemMode";


    private void setRadioButton() {
        LoadData loadData = new LoadData();
        String gameMode = loadData.loadGameMode();
        switch (gameMode) {
            case NORMAL_MODE:
                normalModeButton.setSelected(true);
                break;
            case ITEM_MODE:
                itemModeButton.setSelected(true);
                break;
            default:
                // Default to medium size if no valid setting found
                normalModeButton.setSelected(true);
                break;
        }
    }

    public SettingGameMode() {
        setTitle("Game Mode");
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // 간격 조절

        // 색맹 모드 라디오 버튼
        JLabel modeLabel = new JLabel("Game Mode:");
        panel.add(modeLabel);

        normalModeButton = new JRadioButton("Normal Mode");
        normalModeButton.addActionListener(this);
        panel.add(normalModeButton);

        itemModeButton = new JRadioButton("Item Mode");
        itemModeButton.addActionListener(this);
        panel.add(itemModeButton);

        // 라디오 버튼 그룹 생성 및 추가
        ButtonGroup modeButtonGroup = new ButtonGroup();
        modeButtonGroup.add(normalModeButton);
        modeButtonGroup.add(itemModeButton);
        setRadioButton();
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                LoadData key = new LoadData();
                if (keyCode == key.getRightKey() || keyCode == key.getLeftKey()) {
                    if (normalModeButton.isSelected()) {
                        itemModeButton.setSelected(true);
                    }
                    else if (itemModeButton.isSelected()) {
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
        checkButton.getActionMap().put("checkPressed", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                checkButton.doClick();
            }
        });

        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escapePressed");
        getRootPane().getActionMap().put("escapePressed", new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // 창 닫기
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == checkButton) {
            String gameMode;
            if (normalModeButton.isSelected()) {
                gameMode = NORMAL_MODE;
            }
            else if (itemModeButton.isSelected()) {
                gameMode = ITEM_MODE;
            }
            else {
                gameMode = NORMAL_MODE;
            }
            saveSettings(GAME_MODE_KEY, gameMode); // 동일한 설정 파일 사용
            dispose(); // 설정 화면 종료
        }
    }

    // 설정을 파일에 저장하는 메서드
    private void saveSettings(String key, String value) {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(SETTINGS_FILE)) {
            properties.load(inputStream);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        properties.setProperty(key, value);
        try (OutputStream outputStream = new FileOutputStream(SETTINGS_FILE)) {
            properties.store(outputStream, null);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 테스트용
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                SettingGameMode menu = new SettingGameMode();
                menu.setVisible(true);
            }
        });
    }
}