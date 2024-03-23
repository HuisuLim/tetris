import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Properties;

public class SettingScreen extends JFrame implements ActionListener {

    private JRadioButton smallButton, mediumButton, largeButton;
    private JButton checkButton;
    private static final int SMALL_SIZE = 20;
    private static final int MEDIUM_SIZE = 30;
    private static final int LARGE_SIZE = 50;
    private int squareSize;

    private static final String SETTINGS_FILE = "settings.properties";
    private static final String SQUARE_SIZE_KEY = "ScreenSize";

    public SettingScreen() {
        setTitle("Tetris Settings");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 3));

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
            int squareSize;
            if (smallButton.isSelected()) {
                squareSize = SMALL_SIZE;
            } else if (mediumButton.isSelected()) {
                squareSize = MEDIUM_SIZE;
            } else if (largeButton.isSelected()) {
                squareSize = LARGE_SIZE;
            } else {
                squareSize = MEDIUM_SIZE;
            }
            saveSettings(squareSize); // 설정 저장
            dispose(); // 설정 화면 종료
            MainScreen mainScreen = new MainScreen();
            mainScreen.setVisible(true);
        }
    }

    // 설정을 파일에 저장하는 메서드
    private void saveSettings(int squareSize) {
        Properties properties = new Properties();
        properties.setProperty(SQUARE_SIZE_KEY, String.valueOf(squareSize));
        try (OutputStream outputStream = new FileOutputStream(SETTINGS_FILE)) {
            properties.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
