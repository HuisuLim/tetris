package startScreen;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Font; // 폰트 설정을 위해 추가
import java.awt.Color;


public class StartMenu extends JFrame {
    private JFrame nextFrame; // 다음 화면에 해당하는 JFrame

    public StartMenu() {
        setSize(1000, 800);
        setTitle("테트리스 게임");
        getContentPane().setBackground(Color.LIGHT_GRAY); // 배경색을 밝은 회색으로 설정
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙에 표시

        // 패널 생성
        JPanel panel = new JPanel(null); // 레이아웃 매니저를 null로 설정하여 수동으로 위치 및 크기 지정

        // 제목 라벨 생성
        JLabel titleLabel = new JLabel("SE Team9 Tetris", SwingConstants.CENTER); // 중앙 정렬
        titleLabel.setBounds(300, 100, 400, 50); // 제목 라벨 위치 및 크기 지정
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // 폰트 설정
        panel.add(titleLabel); // 패널에 제목 라벨 추가

        // 버튼 생성
        JButton startButton = new JButton("게임 시작");
        startButton.setBounds(400, 450, 200, 50); // 버튼 위치 및 크기 지정 (x, y, width, height)

        JButton settingsButton = new JButton("설정");
        settingsButton.setBounds(400, 520, 200, 50);

        JButton exitButton = new JButton("게임 종료");
        exitButton.setBounds(400, 590, 200, 50);

        JButton scoreButton = new JButton("스코어보드");
        scoreButton.setBounds(400, 660, 200, 50);

        // 패널에 버튼 추가
        panel.add(startButton);
        panel.add(settingsButton);
        panel.add(exitButton);
        panel.add(scoreButton);

        // 프레임에 패널 추가
        add(panel);

        // 각 버튼의 이벤트 처리는 이전과 동일합니다.
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextFrame = new GameStart();
                nextFrame.setVisible(true);
                setVisible(false);
            }
        });

        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextFrame = new Setting();
                nextFrame.setVisible(true);
                setVisible(false);
            }
        });

        scoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                nextFrame = new ScoreBoard();
                nextFrame.setVisible(true);
                setVisible(false);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}

