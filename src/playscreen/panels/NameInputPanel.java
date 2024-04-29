package playscreen.panels;

import playscreen.PlayFrame;
import settings.LoadData;
import startscreen.ScoreInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NameInputPanel extends JPanel {
    private PlayFrame parentFrame;
    private String gameMode = "normalMode";
    LoadData data = new LoadData();
    public JTextField input;

    public NameInputPanel(PlayFrame parentFrame, double screenSize, String mode) {
        this.parentFrame = parentFrame;
        gameMode = mode;
        setSize((int) (100 * screenSize), (int) (50 * screenSize));
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));
        JLabel label = new JLabel("이름을 입력하세요");
        input = new JTextField(20);
        JLabel descriptionLabel = new JLabel("입력후 enter를 눌러주세요");
        add(label);
        add(input);
        add(descriptionLabel);

        input.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = input.getText();
                if (name.isEmpty()) name = "unknown";
                System.out.println(parentFrame.gamePanel.difficulty);
                new ScoreInput(name, parentFrame.gamePanel.getScore(), data.loadDifficulty(), gameMode).setVisible(true);
                parentFrame.dispose();
            }
        });
    }

    public static void main(String[] args) {
        PlayFrame a = new PlayFrame("normalMode");
        a.add(new NameInputPanel(a, 1, "normalMode"));

    }
}
