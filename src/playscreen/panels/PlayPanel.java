package playscreen.panels;

import playscreen.utils.GameOverCallBack;
import playscreen.utils.TimerDelay;
import settingscreen.settingModel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class PlayPanel extends JPanel {

    //data load
    public final settingModel data;
    private int limitTime = -1;

    //panel load
    public TetrisPanel tetrisPanel;
    private ScorePanel scorePanel;
    private NextBlockPanel nextBlockPanel;
    private LineRemovePanel lineInputPanel;
    private LineRemovePanel lineOutputPanel;

    public Timer timer;

    //상태 변수들.
    private boolean isPaused = false;
    private boolean isMultiPlay = false;
    private GameOverCallBack gameOverCallBack;

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

    public PlayPanel(GameOverCallBack gameOverCallBack,settingModel data, String gameMode, LineRemovePanel lineInputPanel, LineRemovePanel lineOutputPanel) {
        this.data = data;
        this.gameMode = gameMode;
        setSize((int)(data.screenSize * 20 * 20),(int)(data.screenSize * 20 * 20));
        isMultiPlay = true;
        if(gameMode.equals("timeLimit")) {
            this.limitTime = 300;
            this.gameOverCallBack = gameOverCallBack;
            Timer limitTimer = new Timer(1000, e-> {
                limitTime--;
                if(limitTime<=0) {
                    gameOverCallBack.onGameOver(0);
                }
            });
            limitTimer.setRepeats(true);
            limitTimer.start();
        }
        this.lineInputPanel = lineInputPanel;
        this.lineOutputPanel = lineOutputPanel;
        initUI(gameOverCallBack);
        createTimer();
        timer.start();
        setVisible(true); //창 보이게.

    }

    private void initUI(GameOverCallBack gameOverCallBack) {
        setLayout(new GridLayout(1, 2)); // 프레임을 가로로 2등분
        // 왼쪽 패널 : 테트리스 패널
        if (isMultiPlay){
            if (gameMode.equals("itemMode")) {
                tetrisPanel = new ItemModeTetrisPanel(gameOverCallBack, data, lineInputPanel, lineOutputPanel);
            }
            else{
                tetrisPanel = new TetrisPanel(gameOverCallBack, data, lineInputPanel, lineOutputPanel);
            }
        } else{
            if (gameMode.equals("itemMode")) {
                tetrisPanel = new ItemModeTetrisPanel(gameOverCallBack, data);
            } else {
                tetrisPanel = new TetrisPanel(gameOverCallBack, data);
            }
        }
        add(tetrisPanel);

        // 오른쪽 패널 (세로로 3등분)
        JPanel rightPanel = new JPanel(new GridLayout(3, 1));
        rightPanel.setPreferredSize(new Dimension((int)(10 * 20 * data.screenSize), (int)(20 * 20 * data.screenSize)));

        // ScorePanel 추가
        scorePanel = new ScorePanel(data.screenSize, 0);
        rightPanel.add(scorePanel);

        // NextBlockPanel 추가
        nextBlockPanel = new NextBlockPanel(data.screenSize, data.colorBlindMode);
        nextBlockPanel.updateBlock(tetrisPanel.getNextBlock());
        rightPanel.add(nextBlockPanel);

        // 나머지 1 개의 패널은 비워둡니다.
        if (isMultiPlay) {
            //--------------------------공격 화면 테스트용------------------------
            rightPanel.add(lineOutputPanel);
            tetrisPanel.setLineRemovePanel(lineInputPanel,lineOutputPanel);
            //-----------------------------------------------------------------
            //rightPanel.add(new JPanel());
        }
        else if(gameMode.equals("itemMode")){
            ItemShowPanel itemShowPanel = new ItemShowPanel(data);
            itemShowPanel.setPreferredSize(new Dimension((int)(10 * 20 * data.screenSize), (int)(6 * 20 * data.screenSize)));
            rightPanel.add(itemShowPanel);
        }
        else{
            rightPanel.add(new JPanel());
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
        tetrisPanel.goDown();
        if(limitTime > 0) {
            scorePanel.updateScore(tetrisPanel.getScore(), limitTime);
        } else if (limitTime == -1) {
            scorePanel.updateScore(tetrisPanel.getScore());
        }
        nextBlockPanel.updateBlock(tetrisPanel.getNextBlock());
        if(isMultiPlay) {
            lineInputPanel.repaint();
        }
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
            //타이머 재시작 안해주면 아래키누르는데 timer 까지 적용되서 2칸씩 내려가는거방지
            timer.stop();
            timer.start();
            tetrisPanel.goDown();
        }
        else if (input == 3) tetrisPanel.goLeft();
        else if (input == 4) tetrisPanel.goDownToEnd();
        if(limitTime > 0) {
            scorePanel.updateScore(tetrisPanel.getScore(), limitTime);
        } else if (limitTime == -1) {
            scorePanel.updateScore(tetrisPanel.getScore());
        }
        nextBlockPanel.updateBlock(tetrisPanel.getNextBlock());
    }

    public int getScore() {
        return tetrisPanel.getScore();
    }

    public boolean getIsGameOver() {
        return tetrisPanel.getIsGameOver();
    }
}
