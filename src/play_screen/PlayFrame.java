package play_screen;

import settings.LoadData;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayFrame extends JFrame {
    private LoadData data = new LoadData();
    private int screenSize = data.loadScreenSize();
    public TetrisPanel gamePanel;
    public ScorePanel scorePanel;
    public NextBlockPanel nextBlockPanel;
    public PausePanel pausePanel;
    private Timer timer;
    private boolean isGameOver = false;
    private boolean isPaused = false;

    private TetrisKeyListener listener = new TetrisKeyListener(this);


    public PlayFrame() {
        //JFrame 설정.
        setTitle("Play Frame");
        setSize(screenSize * 20 * 20, screenSize * 20 * 20);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        this.addKeyListener(listener);
        initUI();
        createTimer();
        timer.start();


        pack();
        setLocationRelativeTo(null); // 화면 가운데에 위치
        setVisible(true);
    }

    private void initUI() {
        setLayout(new GridLayout(1, 2)); // 프레임을 가로로 2등분
        // 왼쪽 패널 : 테트리스 패널
        gamePanel = new TetrisPanel();
        add(gamePanel);

        // 오른쪽 패널 (세로로 4등분)
        JPanel rightPanel = new JPanel(new GridLayout(4, 1));

        // ScorePanel 추가
        scorePanel = new ScorePanel(screenSize, 0);
        rightPanel.add(scorePanel);

        // NextBlockPanel 추가
        nextBlockPanel = new NextBlockPanel(gamePanel.getNextBlock());
        rightPanel.add(nextBlockPanel);

        // 나머지 두 개의 패널은 비워둡니다.
        rightPanel.add(new JPanel());
        rightPanel.add(new JPanel());

        add(rightPanel);

        pausePanel = new PausePanel(screenSize); // PausePanel 인스턴스 생성
        pausePanel.setSize(200, 100); // 적당한 크기 설정
        pausePanel.setLocation((getWidth() - pausePanel.getWidth()) / 2, (getHeight() - pausePanel.getHeight()) / 2); // 위치 중앙으로 설정
        pausePanel.setVisible(false); // 초기에는 보이지 않게 설정

        getLayeredPane().add(pausePanel, JLayeredPane.POPUP_LAYER); // JLayeredPane에 PausePanel 추가
    }

    public void createTimer() {
        // ActionListener 정의: 점수를 업데이트하고 타이머의 지연 시간 조정
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isGameOver) {
                    timer.stop();
                }
                else {
                    gamePanel.goDown();
                    updateGame();
                    // 타이머의 지연 시간 조정
                    //timer.setDelay(50); //디버그용 딜레이
                    timer.setDelay(1000 - (int)(0.01 * gamePanel.getScore()));
                }
            }
        };

        // 타이머 생성: 초기 지연 시간은 1000ms
        timer = new Timer(1000, actionListener);
    }
    public void updateGame() {
        isGameOver = gamePanel.getIsGameOver();
        scorePanel.updateScore(gamePanel.getScore());
        nextBlockPanel.updateBlock(gamePanel.getNextBlock());
    }

    public boolean getIsPause() {
        return isPaused;
    }

    public void toggleIsPause() {
        isPaused = !isPaused;
        pausePanel.setVisible(isPaused); // isPause 값에 따라 PausePanel 표시 또는 숨김
        if (isPaused) {
            timer.stop();
        }
        else {
            timer.start();
        }
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PlayFrame().setVisible(true);
            }
        });
    }
}
