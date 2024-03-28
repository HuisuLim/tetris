package startScreen;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Font; // 폰트 설정을 위해 추가
import java.awt.Color;
import Settings.LoadData;


public class StartMenu extends JFrame {
    private JFrame nextFrame; // 다음 화면에 해당하는 JFrame
    public static int screenRatio = 2; //화면 비율 조절
    public static boolean isColorblindness = false; //색맹모드
    public static String keySetting="ArrowKeys";//키설정

    public static void setScreenRatio(){
        LoadData loadData = new LoadData();
        screenRatio = loadData.loadScreenSize();
    }

    public static void setColorBlindness(){
        LoadData loadData = new LoadData();
        isColorblindness = loadData.loadColorBlindMode();
    }
    public static void setControlKey(){
        LoadData loadData = new LoadData();
        keySetting = loadData.loadKeySettings();
    }
    public StartMenu() {
        setScreenRatio();//화면 비율 조절
        setColorBlindness();
        setControlKey();

        setSize(500*screenRatio, 400*screenRatio);
        setTitle("테트리스 게임");
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙에 표시

        // 패널 생성
        JPanel panel = new JPanel(null); // 레이아웃 매니저를 null로 설정하여 수동으로 위치 및 크기 지정

        // 제목 라벨 생성
        JLabel titleLabel = new JLabel("SE Team9 Tetris", SwingConstants.CENTER); // 중앙 정렬
        titleLabel.setBounds(150*screenRatio, 50*screenRatio, 200*screenRatio, 25*screenRatio); // 제목 라벨 위치 및 크기 지정
        titleLabel.setFont(new Font("Arial", Font.BOLD, 12*screenRatio)); // 폰트 설정
        panel.add(titleLabel); // 패널에 제목 라벨 추가

        // 버튼 생성
        JButton startButton = new JButton("게임 시작");
        JButton settingsButton = new JButton("설정");
        JButton exitButton = new JButton("게임 종료");
        JButton scoreButton = new JButton("스코어보드");

        configureButton(startButton);
        configureButton(settingsButton);
        configureButton(exitButton);
        configureButton(scoreButton);

        // 패널에 버튼 추가 및 위치 설정
        panel.add(startButton);
        startButton.setBounds(200*screenRatio, 225*screenRatio, 100*screenRatio, 25*screenRatio); // 버튼 위치 및 크기 지정 (x, y, width, height)

        panel.add(settingsButton);
        settingsButton.setBounds(200*screenRatio, 260*screenRatio, 100*screenRatio, 25*screenRatio);

        panel.add(exitButton);
        exitButton.setBounds(200*screenRatio, 295*screenRatio, 100*screenRatio, 25*screenRatio);

        panel.add(scoreButton);
        scoreButton.setBounds(200*screenRatio, 330*screenRatio, 100*screenRatio, 25*screenRatio);


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
                nextFrame = new ScoreInput(); // ScoreBoard ->ScoreInput으로 이름 변경
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

