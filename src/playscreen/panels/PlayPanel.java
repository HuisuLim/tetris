package playscreen.panels;

import playscreen.utils.GameOverCallBack;
import playscreen.utils.TimerDelay;
import settings.settingModel;
import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class PlayPanel extends JPanel {

    //data load
    private final settingModel data;

    //panel load
    public TetrisPanel tetrisPanel;
    private ScorePanel scorePanel;
    private NextBlockPanel nextBlockPanel;
    private LineRemovePanel lineRemovePanel;

    public Timer timer;

    //상태 변수들.
    private boolean isPaused = false;

    private final String gameMode;

    public PlayPanel(GameOverCallBack gameOverCallBack,settingModel data, String gameMode) {
        this.data = data;
        this.gameMode = gameMode;
        setSize((int)(data.screenSize * 20 * 20),(int)(data.screenSize * 20 * 20));
        initUI(gameOverCallBack);
        createTimer();
        timer.start();
        setVisible(true); //창 보이게.

    }

    private void initUI(GameOverCallBack gameOverCallBack) {
        setLayout(new GridLayout(1, 2)); // 프레임을 가로로 2등분
        // 왼쪽 패널 : 테트리스 패널
        if (gameMode.equals("itemMode")) {
            tetrisPanel = new ItemModeTetrisPanel(gameOverCallBack, data.screenSize, data.colorBlindMode);
        }
        else {
            tetrisPanel = new TetrisPanel(gameOverCallBack, data.screenSize, data.colorBlindMode);
        }
        add(tetrisPanel);

        // 오른쪽 패널 (세로로 3등분)
        JPanel rightPanel = new JPanel(new GridLayout(3, 1));
        rightPanel.setPreferredSize(new java.awt.Dimension((int)(10 * 20 * data.screenSize), (int)(20 * 20 * data.screenSize)));

        // ScorePanel 추가
        scorePanel = new ScorePanel(data.screenSize, 0);
        rightPanel.add(scorePanel);

        // NextBlockPanel 추가
        nextBlockPanel = new NextBlockPanel(data.screenSize, data.colorBlindMode);
        nextBlockPanel.updateBlock(tetrisPanel.getNextBlock());
        rightPanel.add(nextBlockPanel);

        // 나머지 1 개의 패널은 비워둡니다.
        if (gameMode.equals("itemMode")) {
            ItemShowPanel itemShowPanel = new ItemShowPanel(data);
            itemShowPanel.setPreferredSize(new java.awt.Dimension((int)(10 * 20 * data.screenSize), (int)(6 * 20 * data.screenSize)));
            rightPanel.add(itemShowPanel);
        }
        else{
            //--------------------------공격 화면 테스트용------------------------
            lineRemovePanel = new LineRemovePanel(data.screenSize, tetrisPanel);
            rightPanel.add(lineRemovePanel);
            tetrisPanel.setLineRemovePanel(lineRemovePanel);
            //-----------------------------------------------------------------
            //rightPanel.add(new JPanel());
        }
        add(rightPanel);
    }

    private void createTimer() {
        ActionListener actionListener = e -> {
            updateGame();
                // 난이도와 점수에 따른 타이머 지연 시간을 동적으로 조정
                int delay = TimerDelay.calDelay(data.difficulty, tetrisPanel.getScore());
                timer.setDelay(delay);

        };
        // 타이머 생성: 초기 지연 시간은 난이도에 따라 조정됨
        int initialDelay = TimerDelay.calDelay(data.difficulty, tetrisPanel.getScore());
        timer = new Timer(initialDelay, actionListener);
    }


    public void updateGame() {
        if (tetrisPanel.getIsGameOver()) {
            System.out.println("게임오버");
            timer.stop();
        }
        tetrisPanel.goDown();
        scorePanel.updateScore(tetrisPanel.getScore());
        nextBlockPanel.updateBlock(tetrisPanel.getNextBlock());
        lineRemovePanel.repaint();
        repaint();
    }

    public void toggleIsPause() {
        isPaused = !isPaused;
        if (isPaused) {
            timer.stop();
        }
        else {
            timer.start();
        }
    }

    public void gameControl(int input) {
        
        if (input == -1) toggleIsPause();
        else if (isPaused) return;
        else if (input == 0) tetrisPanel.rotate90();
        else if (input == 1) tetrisPanel.goRight();
        else if (input == 2){
            //타이머 재시작 안해주면 아래키누르는데 timer까지 적용되서 2칸씩 내려가는거방지
            timer.stop();
            timer.start();
            tetrisPanel.goDown();
        }
        else if (input == 3) tetrisPanel.goLeft();
        else if (input == 4) tetrisPanel.goDownToEnd();
        scorePanel.updateScore(tetrisPanel.getScore());
        nextBlockPanel.updateBlock(tetrisPanel.getNextBlock());
    }
}