package play_screen;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayFrame extends JFrame {
    private int screenRatio;
    private TetrisPlay gamePanel;
    private ScorePanel scorePanel;
    private NextBlockPanel nextBlockPanel;
    private Timer timer;
    private boolean isGameOver = false;
    private boolean pause = false;

    public PlayFrame(int screenRatio) {
        //JFrame 설정.
        this.screenRatio = screenRatio;
        setTitle("Play Frame");
        setSize(screenRatio * 20 * 20, screenRatio * 20 * 20);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2)); // 프레임을 가로로 2등분

        initUI();
        createTimer();
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                // switch 문을 사용해 키 코드에 따라 분기 처리
                if(!gamePanel.getIsGameOver())
                {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP: // 위쪽 화살표 키
                            gamePanel.rotate90();
                            break;
                        case KeyEvent.VK_DOWN: // 아래쪽 화살표 키
                            gamePanel.goDown();
                            updateGame();
                            break;
                        case KeyEvent.VK_LEFT: // 왼쪽 화살표 키
                            gamePanel.goLeft();
                            break;
                        case KeyEvent.VK_RIGHT: // 오른쪽 화살표 키
                            gamePanel.goRight();
                            break;
                    }
                }
            }
        });

        pack();
        setLocationRelativeTo(null); // 화면 가운데에 위치
        setVisible(true);
    }

    private void initUI() {
        // 왼쪽 패널 : 테트리스 패널
        gamePanel = new TetrisPlay(screenRatio, false);
        add(gamePanel);

        // 오른쪽 패널 (세로로 4등분)
        JPanel rightPanel = new JPanel(new GridLayout(4, 1));

        // ScorePanel 추가
        scorePanel = new ScorePanel(screenRatio, 0);
        rightPanel.add(scorePanel);

        // NextBlockPanel 추가
        nextBlockPanel = new NextBlockPanel(screenRatio, gamePanel.getNextBlock());
        rightPanel.add(nextBlockPanel);

        // 나머지 두 개의 패널은 비워둡니다.
        rightPanel.add(new JPanel());
        rightPanel.add(new JPanel());

        add(rightPanel);
    }

    private void createTimer() {
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
    private void updateGame() {
        isGameOver = gamePanel.getIsGameOver();
        scorePanel.updateScore(gamePanel.getScore());
        nextBlockPanel.updateBlock(gamePanel.getNextBlock());
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PlayFrame(2).setVisible(true);
            }
        });
    }
}
