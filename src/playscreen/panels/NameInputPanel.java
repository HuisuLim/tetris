package playscreen.panels;

import playscreen.PlayFrame;

import javax.swing.*;
import java.awt.*;

public class NameInputPanel extends JPanel {
    public JTextField input;

    public NameInputPanel(PlayFrame parentFrame,double screenSize, int score, String gameMode, String difficulty) {
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

        //enter키 입력 안내하는 label 생성
        JLabel descriptionLabel = new JLabel("입력후 enter를 눌러주세요");
        descriptionLabel.setFont(new Font("Serif", Font.BOLD, (int)(15 * screenSize)));
        add(descriptionLabel);

        //enter키 입력했을때 동작. 스코어보드 창 생성하고 게임 창 닫기.
        input.addActionListener(e -> {
            String name = input.getText();
            if (name.isEmpty()) name = "unknown";
            new ScoreInput(name, score, difficulty, gameMode).setVisible(true);
            parentFrame.dispose();
        });
    }


    public static void main(String[] args) {

    }
}