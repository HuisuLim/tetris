package temp;

import javax.swing.*;
import java.awt.*;

import startscreen.StartMenu;
import temp.TetrisPlayPanel;


class GameStart extends JFrame {

    private int screenRatio = 2; //화면 비율 조절
    private boolean isColorBlindness = false;

    private String keySetting = "ArrowKeys";


    public GameStart() {
        screenRatio = StartMenu.screenRatio;
        isColorBlindness = StartMenu.isColorblindness;
        keySetting = StartMenu.keySetting;

        setTitle("테트리스 게임");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500*screenRatio, 400*screenRatio);
        setLocationRelativeTo(null); // 화면 중앙에 표시

        // 테트리스 게임 화면 패널
        JPanel gamePanel = new JPanel(new BorderLayout());
        JLabel gameLabel = new JLabel("테트리스 게임 화면", SwingConstants.CENTER);
        TetrisPlayPanel tetris = new TetrisPlayPanel(screenRatio, isColorBlindness, keySetting);
        add(tetris);
        setVisible(true);


        // 점수 화면 패널
        JPanel scorePanel = new JPanel(new BorderLayout());
        JLabel scoreLabel = new JLabel("현재 점수 및 다음 블록 송출", SwingConstants.CENTER);
        scorePanel.add(scoreLabel, BorderLayout.CENTER);
        add(scorePanel);

        // JSplitPane 생성
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gamePanel, scorePanel);
        splitPane.setDividerLocation(250*screenRatio); // 화면의 중앙에 분리선 설정
        splitPane.setDividerSize(2); // 분리선의 크기 설정
        splitPane.setBackground(Color.BLACK); // 분리선의 색상 설정

        // 뒤로가기 버튼 추가
        JButton backButton = new JButton("뒤로가기");
        backButton.addActionListener(e -> {
            this.setVisible(false); // 현재 창 숨기기
            this.dispose(); // 현재 창 자원 해제
            new StartMenu().setVisible(true); // StartMenu 인스턴스 생성 및 보이기
        });

        // 뒤로가기 버튼을 위한 패널 생성 및 레이아웃 설정
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(backButton);

        // 최상위 컨테이너에 패널 추가
        getContentPane().add(topPanel, BorderLayout.NORTH);
        getContentPane().add(splitPane, BorderLayout.CENTER);
    }
}


