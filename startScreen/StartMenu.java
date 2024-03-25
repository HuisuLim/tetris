package startScreen;
import javax.swing.*;
import java.awt.event.*;
import java.awt.Font; // 폰트 설정을 위해 추가
import java.awt.Color; // 색상 변경을 위해 추가

public class StartMenu extends JFrame {
    private JFrame nextFrame; // 다음 화면에 해당하는 JFrame

    public StartMenu() {
        setSize(1000, 800);
        setTitle("테트리스 게임");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙에 표시

        // 패널 생성
        JPanel panel = new JPanel(null); // 레이아웃 매니저를 null로 설정하여 수동으로 위치 및 크기 지정

        // 제목 라벨 생성 및 추가
        JLabel titleLabel = new JLabel("SE Team9 Tetris", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // 폰트 설정
        titleLabel.setBounds(300, 50, 400, 50); // 위치 및 크기 지정
        panel.add(titleLabel); // 패널에 제목 라벨 추가

        // 버튼 생성 및 스타일 설정
        JButton startButton = new JButton("게임 시작");
        JButton settingsButton = new JButton("설정");
        JButton exitButton = new JButton("게임 종료");
        JButton scoreButton = new JButton("스코어보드");

        configureButton(startButton);
        configureButton(settingsButton);
        configureButton(exitButton);
        configureButton(scoreButton);

        // 패널에 버튼 추가 및 버튼 위치 설정
        panel.add(startButton);
        startButton.setBounds(400, 450, 200, 50);

        panel.add(settingsButton);
        settingsButton.setBounds(400, 520, 200, 50);

        panel.add(exitButton);
        exitButton.setBounds(400, 590, 200, 50);

        panel.add(scoreButton);
        scoreButton.setBounds(400, 660, 200, 50);

        // 프레임에 패널 추가
        add(panel);
        // 게임 시작 버튼 이벤트 처리
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 다음 화면으로 넘어가기 위해 새로운 JFrame 생성
                nextFrame = new GameStart();
                nextFrame.setVisible(true);
                setVisible(false); // 현재 화면 숨기기
            }
        });

        // 설정 버튼 이벤트 처리
        settingsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 다음 화면으로 넘어가기 위해 새로운 JFrame 생성
                nextFrame = new Setting();
                nextFrame.setVisible(true);
                setVisible(false); // 현재 화면 숨기기
            }
        });
        // 스코어보드 버튼 이벤트 처리
        scoreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 다음 화면으로 넘어가기 위해 새로운 JFrame 생성
                nextFrame = new ScoreBoard();
                nextFrame.setVisible(true);
                setVisible(false); // 현재 화면 숨기기
            }
        });

        // 게임 종료 버튼 이벤트 처리
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 프로그램 종료
                System.exit(0);
            }
        });
    }
    private void configureButton(JButton button) {
        // 초기 색상 설정
        button.setBackground(new Color(200, 200, 200)); // 예시 색상
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(button.getBackground().darker()); // 마우스가 올라갔을 때 색상을 진하게
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(200, 200, 200)); // 마우스가 내려갔을 때 원래 색상으로 복원
            }
        });
    }
}
