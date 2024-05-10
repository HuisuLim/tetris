package playscreen;

import playscreen.panels.*;
import playscreen.utils.TetrisKeyListener;
import playscreen.utils.TimerDelay;
import settings.LoadData;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class PlayFrame extends JFrame {

    //data load
    private final LoadData data = new LoadData();
    private final String gameMode = data.loadGameMode();
    private final boolean colorMode = data.loadColorBlindMode();
    private final String difficulty = data.loadDifficulty(); // 난이도 로드
    private final double screenSize = data.loadScreenSize();

    //panel load
    public TetrisPanel gamePanel;
    public ScorePanel scorePanel;
    public NextBlockPanel nextBlockPanel;
    public PausePanel pausePanel;
    public ItemShowPanel itemShowPanel;
    public NameInputPanel nameInputPanel;

    public Timer timer;

    //상태 변수들.
    private boolean isGameOver = false;
    private boolean isPaused = false;
    private boolean isCleaningTime = false;




    public PlayFrame() {
        setTitle("Play Frame");
        setSize((int)(screenSize * 20 * 20),(int)(screenSize * 20 * 20));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false); // 창 크기 변경 불가능 설정 추가

        int[] keys = data.loadKeys();
        this.addKeyListener(new TetrisKeyListener(this, keys));
        initUI();
        createTimer();
        timer.start();

        pack();//화면자동설정.
        setLocationRelativeTo(null); //화면 중앙에 위치.
        setVisible(true); //창 보이게.

    }

    private void initUI() {
        setLayout(new GridLayout(1, 2)); // 프레임을 가로로 2등분
        // 왼쪽 패널 : 테트리스 패널
        if (gameMode.equals("itemMode")) {
            gamePanel = new ItemTetrisPanel(screenSize, colorMode);
        }
        else {
            gamePanel = new TetrisPanel(screenSize, colorMode);
        }
        add(gamePanel);

        // 오른쪽 패널 (세로로 3등분)
        JPanel rightPanel = new JPanel(new GridLayout(3, 1));
        rightPanel.setPreferredSize(new java.awt.Dimension((int)(10 * 20 * screenSize), (int)(20 * 20 * screenSize)));

        // ScorePanel 추가
        scorePanel = new ScorePanel(screenSize, 0);
        rightPanel.add(scorePanel);

        // NextBlockPanel 추가
        nextBlockPanel = new NextBlockPanel(screenSize, colorMode);
        rightPanel.add(nextBlockPanel);

        // 나머지 1 개의 패널은 비워둡니다.
        if (gameMode.equals("itemMode")) {
            itemShowPanel = new ItemShowPanel();
            itemShowPanel.setPreferredSize(new java.awt.Dimension((int)(10 * 20 * screenSize), (int)(6 * 20 * screenSize)));
            rightPanel.add(itemShowPanel);
        }
        else{
            rightPanel.add(new JPanel());
        }
        //rightPanel.add(new JPanel());
        add(rightPanel);
        pausePanel = new PausePanel((int)(screenSize)); // PausePanel 인스턴스 생성
        pausePanel.setLocation((getWidth() - pausePanel.getWidth()) / 2, (getHeight() - pausePanel.getHeight()) / 2); // 위치 중앙으로 설정
        pausePanel.setVisible(false); // 초기에는 보이지 않게 설정

        getLayeredPane().add(pausePanel, JLayeredPane.POPUP_LAYER); // JLayeredPane에 PausePanel 추가
    }

    private void createTimer() {
        ActionListener actionListener = e -> {
            if (isGameOver) {
                timer.stop();
            } else {
                updateGame(gamePanel.goDown());
                repaint();

                // 난이도와 점수에 따른 타이머 지연 시간을 동적으로 조정
                int delay = TimerDelay.calDelay(difficulty, gamePanel.getScore());
                timer.setDelay(delay);
            }
        };


        // 타이머 생성: 초기 지연 시간은 난이도에 따라 조정됨
        int initialDelay = TimerDelay.calDelay(difficulty, gamePanel.getScore());
        timer = new Timer(initialDelay, actionListener);
    }


    public void updateGame(boolean doDown) {
        gamePanel.setScoreMultiplier(TimerDelay.calScoreMultiplier(gamePanel.getScore()));
        isGameOver = gamePanel.getIsGameOver();
        if (isGameOver) {
            nameInputPanel = new NameInputPanel(this, screenSize, gamePanel.getScore(), gameMode, difficulty);
            nameInputPanel.setLocation((getWidth() - nameInputPanel.getWidth()) / 2, (getHeight() - nameInputPanel.getHeight()) / 2); // 위치 중앙으로 설정
            nameInputPanel.setVisible(true); // 초기에는 보이지 않게 설정
            getLayeredPane().add(nameInputPanel, JLayeredPane.POPUP_LAYER);
            nameInputPanel.input.requestFocus();
            return;
        }
        //테트리스 goDown했을때 움직여지지 않는다면
        if (!doDown) {
            gamePanel.mergeShapeToBoard();
            if(gamePanel.checkLines()){
                isCleaningTime = true;
                timer.stop();
                gamePanel.repaint();
                Timer clearTimer = new Timer(700, e -> {
                    gamePanel.clearLines();
                    gamePanel.repaint();
                    isCleaningTime = false;
                    timer.start();
                });
                clearTimer.setRepeats(false); // 타이머가 한 번만 실행되도록 설정
                clearTimer.start(); // 타이머 시작
            }
            gamePanel.createNewShape();
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

    public boolean getIsCleaningTime() {
        return isCleaningTime;
    }


    public static void main(String[] args) {
    }
}
