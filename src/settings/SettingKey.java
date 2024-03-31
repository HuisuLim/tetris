package settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Properties;

public class SettingKey extends JFrame {
    private JRadioButton key1RadioButton, key2RadioButton;
    private JButton checkButton;
    private Properties settings;
    private static final String SETTINGS_FILE = "src/Settings/settings.properties";
    private static final String CONTROL_KEY = "MOVEMENT";

    public SettingKey() {
        setTitle("Key Settings");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        key1RadioButton = new JRadioButton("Arrow Keys");
        key2RadioButton = new JRadioButton("WASD");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(key1RadioButton);
        buttonGroup.add(key2RadioButton);

        checkButton = new JButton("Save");

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1));
        panel.add(key1RadioButton);
        panel.add(key2RadioButton);
        panel.add(checkButton);
        add(panel, BorderLayout.CENTER);
        setRadioButton();
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                LoadData key = new LoadData();
                if (keyCode == key.getUpKey() || keyCode == key.getDownKey()) {
                    if (key1RadioButton.isSelected()) {
                        key2RadioButton.setSelected(true);
                    } else if (key2RadioButton.isSelected()) {
                        key1RadioButton.setSelected(true);
                    }
                }
            }
        });

        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String movement;
                if (key1RadioButton.isSelected()) {
                    movement = "ArrowKeys";
                } else {
                    movement = "WASD";
                }
                saveSettings(CONTROL_KEY,movement);
                dispose(); // 설정 화면 종료
            }
        });
        // "Save" 버튼에 대한 키 바인딩 설정
        checkButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "Save");
        checkButton.getActionMap().put("Save", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkButton.doClick();
            }
        });
    }

    private void setRadioButton(){
        LoadData loadData = new LoadData();
        String keySettings = loadData.loadKeySettings();
        switch (keySettings) {
            case "ArrowKeys":
                key1RadioButton.setSelected(true);
                break;
            case "WASD":
                key2RadioButton.setSelected(true);
                break;
            default:
                // Default to medium size if no valid setting found
                key1RadioButton.setSelected(true);
                break;
        }
    }


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
