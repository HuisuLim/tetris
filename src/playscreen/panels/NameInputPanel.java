package playscreen.panels;

import playscreen.SinglePlayFrame;
import scoreboard.ScoreboardController;

import javax.swing.*;
import java.awt.*;

public class NameInputPanel extends JPanel {
    public JTextField input;

    public NameInputPanel(SinglePlayFrame parentFrame, double screenSize, int score, String gameMode, String difficulty) {
        //Panel 기본설정
        setSize((int) (200 * screenSize), (int) (100 * screenSize));
        setLayout(new GridLayout(3, 1));
        setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));

        //빈칸에 이름 넣으라고 안내하는 Label 생성
        JLabel label = new JLabel("이름을 입력하세요");
        label.setFont(new Font("Serif", Font.BOLD, (int)(15 * screenSize)));
        add(label);

        //이름넣는곳 생성
        input = new JTextField(20);
        input.setFont(new Font("Serif", Font.BOLD, (int)(15 * screenSize)));
        add(input);

        //enter 입력 안내하는 label 생성
        JLabel descriptionLabel = new JLabel("입력후 enter 눌러주세요");
        descriptionLabel.setFont(new Font("Serif", Font.BOLD, (int)(15 * screenSize)));
        add(descriptionLabel);

        //enter 입력했을때 동작. 스코어보드 창 생성하고 게임 창 닫기.
        input.addActionListener(e -> {
            String name = input.getText();
            if (name.isEmpty()) name = "unknown";
            new ScoreboardController(name, score, difficulty, gameMode).setVisible(true);
            parentFrame.dispose();
        });
    }


    public static void main(String[] args) {

    }

    public int getScore() {
        return 0;
    }
}