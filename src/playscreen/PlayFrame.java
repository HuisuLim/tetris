package playscreen;

import playscreen.panels.*;
import playscreen.utils.TetrisKeyListener;
import playscreen.utils.TimerDelay;
import settings.LoadData;
import startscreen.ScoreInput;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayFrame extends JFrame {
    private LoadData data = new LoadData();
    private String gameMode = data.loadGameMode();
    private String difficulty = data.loadDifficulty(); // 난이도 로드
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
        setTitle("Play Frame");
        setSize(screenSize * 20 * 20, screenSize * 20 * 20);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.addKeyListener(listener);
        initUI();
        createTimer();
        timer.start();

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initUI() {
        setLayout(new GridLayout(1, 2)); // 프레임을 가로로 2등분
        // 왼쪽 패널 : 테트리스 패널
        if (gameMode.equals("itemMode")) {
            gamePanel = new ItemTetrisPanel();
        }
        else {
            gamePanel = new TetrisPanel();
        }
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

        pausePanel = new PausePanel(this, screenSize); // PausePanel 인스턴스 생성
        pausePanel.setSize(200, 100); // 적당한 크기 설정
        pausePanel.setLocation((getWidth() - pausePanel.getWidth()) / 2, (getHeight() - pausePanel.getHeight()) / 2); // 위치 중앙으로 설정
        pausePanel.setVisible(false); // 초기에는 보이지 않게 설정

        getLayeredPane().add(pausePanel, JLayeredPane.POPUP_LAYER); // JLayeredPane에 PausePanel 추가
    }

    private void createTimer() {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isGameOver) {
                    timer.stop();
                } else {
                    gamePanel.goDown();
                    updateGame();

                    // 난이도와 점수에 따른 타이머 지연 시간을 동적으로 조정
                    int delay = TimerDelay.calDelay(difficulty, gamePanel.getScore());
                    timer.setDelay(delay);
                }
            }
        };

        // 타이머 생성: 초기 지연 시간은 난이도에 따라 조정됨
        int initialDelay = TimerDelay.calDelay(difficulty, gamePanel.getScore());
        timer = new Timer(initialDelay, actionListener);
    }
    public void updateGame() {
        isGameOver = gamePanel.getIsGameOver();
        if (isGameOver) {
            scorePanel.updateScore(gamePanel.getScore());
            String name = JOptionPane.showInputDialog(this, "이름을 입력하세요:");

            if (name != null && !name.isEmpty()) {
                // 게임의 난이도와 모드를 설정합니다.

                // 테이블에 이름과 현재 점수, 난이도, 모드 추가
                new ScoreInput(name, gamePanel.getScore(), difficulty, gameMode).setVisible(true);
            }
            return;
        }
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
