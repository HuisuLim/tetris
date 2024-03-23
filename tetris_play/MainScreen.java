import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

// 그냥 구현해볼려고 만들어놓은거임
public class MainScreen extends JFrame implements ActionListener {

    private JButton startButton, settingButton;
    private static final String SETTINGS_FILE = "settings.properties";
    private static final String SQUARE_SIZE_KEY = "ScreenSize";
    private static final String COLOR_MODE_KEY = "ColorMode";
    private static final int MEDIUM_SIZE = 30;


    public MainScreen() {
        setTitle("Tetris");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        startButton = new JButton("Start");
        startButton.addActionListener(this);
        panel.add(startButton);

        settingButton = new JButton("Settings");
        settingButton.addActionListener(this);
        panel.add(settingButton);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == startButton) {
            int squareSize = loadScreenSize();
            boolean colorBlindMode = loadColorBlindMode();
            TetrisPlay tetris = new TetrisPlay(squareSize, colorBlindMode);
            tetris.setVisible(true);
            dispose();
        } else if (e.getSource() == settingButton) {
            SettingScreen settingScreen = new SettingScreen();
            settingScreen.setVisible(true);
            dispose();
        }
    }

    private int loadScreenSize() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
            String screenSizeStr = properties.getProperty(SQUARE_SIZE_KEY);
            return Integer.parseInt(screenSizeStr);
        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
            // 기본값인 MEDIUM_SIZE를 반환하거나 적절한 기본값을 선택합니다.
            return MEDIUM_SIZE;
        }
    }
    private boolean loadColorBlindMode() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(SETTINGS_FILE)) {
            properties.load(input);
            String mode = properties.getProperty(COLOR_MODE_KEY);
            return mode != null && mode.equals("true");
        } catch (IOException ex) {
            ex.printStackTrace();
            // 기본값인 일반 모드를 반환하거나 적절한 기본값을 선택합니다.
            return false;
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainScreen mainScreen = new MainScreen();
            mainScreen.setVisible(true);
        });
    }
}
