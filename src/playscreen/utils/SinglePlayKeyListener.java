package playscreen.utils;

import playscreen.PlayFrame;
import playscreen.panels.PlayPanel;
import startscreen.StartMenu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SinglePlayKeyListener  implements KeyListener {
    private final PlayFrame playFrame;
    private final PlayPanel playPanel;
    private final int[] keys;
    //keys 0 : upkey, 1 : rightkey, 2 : downkey, 3 : leftkey, 4 : space/enter
    //위부터 시계방향순서.

    public SinglePlayKeyListener(PlayFrame playFrame, int[] keys){
        this.playFrame = playFrame;
        this.playPanel = playFrame.playPanel;
        this.keys = keys;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(keyCode == KeyEvent.VK_ESCAPE) {
            playFrame.toggleIsPause();
        }
        else if(playFrame.getIsPause()) {
            handlePauseState(keyCode);
        }
        else {
            handleGameState(keyCode);
        }
        playFrame.repaint();
        playPanel.repaint();
    }

    private void handleGameState(int keyCode) { //게임중일때 키입력 처리
        for(int i = 0; i < 5; i++) {
            if (keyCode == keys[i]) {
                playPanel.gameControl(i);
                break;
            }
        }
    }

    private void handlePauseState(int keyCode) {

        //옵션이 위에서부터 인덱스가 0 1 2기때문에 반대.
        if (keyCode == keys[0]) {
            playFrame.pausePanel.upPoint();
        }
        else if (keyCode == keys[2]) {
            playFrame.pausePanel.downPoint();
        }

        else if (keyCode == KeyEvent.VK_ENTER) {
            switch (playFrame.pausePanel.getCurrPoint()) {
                case 0: // RESUME
                    playFrame.toggleIsPause();
                    break;
                case 1: // Go to StartMenu
                    playFrame.dispose();
                    StartMenu menu = new StartMenu();
                    menu.setVisible(true);
                    break;
                case 2: // EXIT
                    System.exit(0); // 프로그램 종료
                    break;
            }
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            playFrame.toggleIsPause();
        }
    }

    //필요할 시 구현.


    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
