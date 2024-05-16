package playscreen;


import playscreen.panels.NameInputPanel;
import playscreen.panels.PausePanel;
import playscreen.panels.PlayPanel;
import playscreen.utils.SinglePlayKeyListener;
import settings.settingModel;

import javax.swing.*;

public class PlayFrame extends JFrame {

    private settingModel data = new settingModel();
    private double screenSize = data.loadScreenSize();
    private String difficulty = data.loadDifficulty();
    private String gameMode;
    public PlayPanel playPanel;
    public PausePanel pausePanel;
    public NameInputPanel nameInputPanel;
    private boolean isPaused = false;
    private final SinglePlayKeyListener singlePlayKeyListener;


    public PlayFrame(String gameMode) {
        this.gameMode = gameMode;
        setTitle("Play Frame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize((int)(screenSize * 20 * 20),(int)(screenSize * 20 * 20));
        setResizable(false); // 창 크기 변경 불가능 설정 추가

        //playPanel 추가
        playPanel = new PlayPanel(this::gameOver, gameMode);
        add(playPanel);
        
        //pausePanel 추가
        pausePanel = new PausePanel((int)(screenSize)); // PausePanel 인스턴스 생성
        pausePanel.setLocation((getWidth() - pausePanel.getWidth()) / 2, (getHeight() - pausePanel.getHeight()) / 2); // 위치 중앙으로 설정
        pausePanel.setVisible(false); // 초기에는 보이지 않게 설정
        getLayeredPane().add(pausePanel, JLayeredPane.POPUP_LAYER); // JLayeredPane에 PausePanel 추가
        
        //KeyListener 추가
        singlePlayKeyListener = new SinglePlayKeyListener(this, data.loadKeys());
        addKeyListener(singlePlayKeyListener);

        pack();//화면자동설정.
        setLocationRelativeTo(null); //화면 중앙에 위치.
        setVisible(true); //창 보이게.
    }


    public void toggleIsPause() {
        isPaused = !isPaused;
        playPanel.gameControl((-1));
        pausePanel.setVisible(isPaused);
    }
    public boolean getIsPause() {
        return isPaused;
    }

    public void gameOver(int score) {
            nameInputPanel = new NameInputPanel(this, screenSize, score, gameMode, difficulty);
            nameInputPanel.setLocation((getWidth() - nameInputPanel.getWidth()) / 2, (getHeight() - nameInputPanel.getHeight()) / 2); // 위치 중앙으로 설정
            nameInputPanel.setVisible(true); // 초기에는 보이지 않게 설정
            getLayeredPane().add(nameInputPanel, JLayeredPane.POPUP_LAYER);
            nameInputPanel.input.requestFocus();
            return;
    }



}
