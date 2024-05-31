package settingscreen;

import javax.swing.*;
import java.awt.*;

public class settingView extends JFrame {
    private final String settingName;
    JRadioButton Button1, Button2, Button3;
    JButton checkButton;
    JPanel panel;

    public settingView(String settingName) {
        this.settingName = settingName;

        setSize(400, 100);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel label = new JLabel();
        panel.add(label);

        Button1 = new JRadioButton();
        Button2 = new JRadioButton();
        Button3 = new JRadioButton();


        ButtonGroup buttonGroup = new ButtonGroup();

        switch (settingName) {
            case "Reset":
                //setSize(200, 70);
                setTitle("Setting Reset");
                checkButton = new JButton("Reset");
                add(checkButton);
                break;
            case "ScoreBoardReset":
                setTitle("스코어보드 초기화");
                label.setText("스코어보드를 초기화 하겠습니까?");
                add(panel);
                checkButton = new JButton("Yes");
                add(checkButton, BorderLayout.SOUTH);
                break;
            case "ScreenSize":
                setTitle("Screen Setting");
                label.setText("Screen Size:");
                Button1.setText("Small");
                Button2.setText("Medium");
                Button3.setText("Large");
                panel.add(Button1);
                panel.add(Button2);
                panel.add(Button3);
                buttonGroup.add(Button1);
                buttonGroup.add(Button2);
                buttonGroup.add(Button3);
                add(panel);
                checkButton = new JButton("Check");
                add(checkButton, BorderLayout.SOUTH);
                break;

            case "ColorMode":
                setTitle("ColorBlindness Setting");
                label.setText("Color Mode: ");
                Button1.setText("Normal");
                Button2.setText("Color Blind");
                panel.add(Button1);
                panel.add(Button2);
                buttonGroup.add(Button1);
                buttonGroup.add(Button2);
                add(panel);
                checkButton = new JButton("Check");
                add(checkButton, BorderLayout.SOUTH);
                break;

            case "Difficulty":
                setTitle("Difficulty Setting");
                label.setText("Difficulty Mode: ");
                Button1.setText("Easy");
                Button2.setText("Normal");
                Button3.setText("Hard");
                panel.add(Button1);
                panel.add(Button2);
                panel.add(Button3);
                buttonGroup.add(Button1);
                buttonGroup.add(Button2);
                buttonGroup.add(Button3);
                add(panel);
                checkButton = new JButton("Check");
                add(checkButton, BorderLayout.SOUTH);
                break;

            case "MOVEMENT":
                setTitle("Key Setting");
                label.setText("Key Control: ");
                Button1.setText("Arrow Keys");
                Button2.setText("WASD");
                panel.add(Button1);
                panel.add(Button2);
                buttonGroup.add(Button1);
                buttonGroup.add(Button2);
                add(panel);
                checkButton = new JButton("Check");
                add(checkButton, BorderLayout.SOUTH);
                break;


            default:
                System.out.println("Unrecognized setting name: " + settingName);
                setTitle("Invalid Setting");
                label.setText("Invalid Setting");
                break;
        }

        setVisible(true);
    }
    public String getSettingName() {
        return settingName;
    }
}

