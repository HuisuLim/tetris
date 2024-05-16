package playscreen;


import playscreen.panels.PausePanel;
import playscreen.panels.PlayPanel;
import playscreen.utils.MultiPlayKeyListener;
import settings.settingModel;
import startscreen.StartMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MultiPlayFrame extends JFrame {

    public PlayPanel player1PlayPanel;
    public PlayPanel player2PlayPanel;
    public PausePanel pausePanel;
    private boolean isPaused = false;


    public MultiPlayFrame(String gameMode) {
        setTitle("Battle Play Frame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        settingModel data = new settingModel();
        setSize((int)(data.screenSize * 20 * 20 * 2),(int)(data.screenSize * 20 * 20));
        setResizable(false); // 창 크기 변경 불가능 설정 추가

        setLayout(new GridLayout(1, 2)); // 프레임을 가로로 2등분

        //playPanel 추가
        player1PlayPanel = new PlayPanel(this::gameOver, data, gameMode);
        player2PlayPanel = new PlayPanel(this::gameOver, data, gameMode);
        add(player1PlayPanel);
        add(player2PlayPanel);

        //pausePanel 추가
        pausePanel = new PausePanel((int)(data.screenSize * 1.5)); // PausePanel 인스턴스 생성
        pausePanel.setLocation((getWidth() - pausePanel.getWidth()) / 2, (getHeight() - pausePanel.getHeight()) / 2); // 위치 중앙으로 설정
        pausePanel.setVisible(false); // 초기에는 보이지 않게 설정
        getLayeredPane().add(pausePanel, JLayeredPane.POPUP_LAYER); // JLayeredPane에 PausePanel 추가

        //KeyListener 추가
        int[] player1Keys = {KeyEvent.VK_W,KeyEvent.VK_D,KeyEvent.VK_S,KeyEvent.VK_A,KeyEvent.VK_SPACE};
        int[] player2Keys = {KeyEvent.VK_UP,KeyEvent.VK_RIGHT,KeyEvent.VK_DOWN,KeyEvent.VK_LEFT,KeyEvent.VK_ENTER};
        MultiPlayKeyListener multiPlayKeyListener = new MultiPlayKeyListener(this, player1Keys, player2Keys);
        addKeyListener(multiPlayKeyListener);

        pack();//화면자동설정.
        setLocationRelativeTo(null); //화면 중앙에 위치.
        setVisible(true); //창 보이게.
    }


    public void toggleIsPause() {
        isPaused = !isPaused;
        player1PlayPanel.gameControl((-1));
        player2PlayPanel.gameControl((-1));
        pausePanel.setVisible(isPaused);
    }
    public boolean getIsPause() {
        return isPaused;
    }

    public void gameOver(int score) {

        dispose();
        StartMenu menu = new StartMenu();
        menu.setVisible(true);
    }



}
