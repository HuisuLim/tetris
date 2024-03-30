package startscreen;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Font; // 폰트 설정을 위해 추가
import java.awt.Color;
import settings.LoadData;
import play_screen.PlayFrame;


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
                nextFrame = new PlayFrame(screenRatio);
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

        // 방향키 및 엔터키 처리를 위한 설정
        setupDirectionalFocusTraversal(startButton, settingsButton, exitButton, scoreButton);
    }
    private void configureButton(JButton button) {
        Color defaultColor = new Color(230, 230, 230); // 기본 배경색을 밝은 GRAY로 설정
        Color focusedColor = new Color(210, 210, 210); // 포커스가 있을 때의 배경색
        Color hoverColor = defaultColor.brighter(); // 마우스 오버 시 색상, 기본 색상보다 약간 밝게

        button.setBackground(defaultColor); // 버튼의 기본 배경색 설정
        button.setFocusPainted(false);
        button.setBorderPainted(false); // 버튼의 테두리를 그리지 않음

        // 마우스 리스너
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor); // 마우스가 버튼 위에 있을 때
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!button.isFocusOwner()) {
                    button.setBackground(defaultColor); // 마우스가 떠나면 기본 색상으로 복원
                }
            }
        });

        // 포커스 리스너
        button.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                button.setBackground(focusedColor); // 포커스를 얻으면 색상을 변경
            }

            @Override
            public void focusLost(FocusEvent e) {
                button.setBackground(defaultColor); // 포커스를 잃으면 기본 색상으로 복원
            }
        });
    }

    private void setupDirectionalFocusTraversal(JButton... buttons) {
        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            buttons[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP:
                        case KeyEvent.VK_LEFT:
                            // 위쪽 또는 왼쪽 방향키
                            int prevIndex = (index - 1 + buttons.length) % buttons.length;
                            buttons[prevIndex].requestFocus();
                            break;
                        case KeyEvent.VK_DOWN:
                        case KeyEvent.VK_RIGHT:
                            // 아래쪽 또는 오른쪽 방향키
                            int nextIndex = (index + 1) % buttons.length;
                            buttons[nextIndex].requestFocus();
                            break;
                        case KeyEvent.VK_ENTER:
                            // 엔터키
                            buttons[index].doClick();
                            break;
                    }
                }
            });
        }
    }


}