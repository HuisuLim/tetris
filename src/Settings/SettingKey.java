package Settings;
import startScreen.Setting;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Properties;

public class SettingKey extends JFrame {
    private JRadioButton key1RadioButton, key2RadioButton;
    private JButton checkButton;
    private Properties settings;
    private static final String SETTINGS_FILE = "Settings/settings.properties";
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
    }

    private void loadSettings() {
        settings = new Properties();
        try (InputStream input = new FileInputStream("settings.properties")) {
            settings.load(input);
            String movement = settings.getProperty("movement", "Arrow Keys");
            if (movement.equals("Arrow Keys")) {
                key1RadioButton.setSelected(true);
            } else {
                key2RadioButton.setSelected(true);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
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
