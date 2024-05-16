package playscreen.utils;

import playscreen.MultiPlayFrame;
import playscreen.panels.PlayPanel;
import startscreen.StartMenu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class MultiPlayKeyListener  implements KeyListener {
    private final MultiPlayFrame playFrame;
    private final PlayPanel playPanel;
    private final int upKey;
    private final int rightKey;
    private final int downKey;
    private final int leftKey;
    private final int downToEndKey;

    public MultiPlayKeyListener(MultiPlayFrame playFrame, int[] keys){
        this.playFrame = playFrame;
        this.playPanel = playFrame.player1PlayPanel;
        upKey = keys[0];
        rightKey = keys[1];
        downKey = keys[2];
        leftKey = keys[3];
        downToEndKey = keys[4];

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(playFrame.getIsPause()) {
            handlePauseState(keyCode);
        }
        else {
            handleGameState(keyCode);
        }
        playFrame.repaint();
        playPanel.repaint();
    }

    private void handleGameState(int keyCode) { //게임중일때 키입력 처리
        if (keyCode == KeyEvent.VK_ESCAPE) {
            playFrame.toggleIsPause();
            System.out.println(playFrame.getIsPause());
        } else if (keyCode == upKey) {
            playPanel.gameControl(0);
        } else if (keyCode == rightKey) {
            playPanel.gameControl(1);
        } else if (keyCode == downKey) {
            playPanel.gameControl(2);
        } else if (keyCode == leftKey) {
            playPanel.gameControl(3);
        } else if (keyCode == downToEndKey) {
            playPanel.gameControl(4);
        }
    }

    private void handlePauseState(int keyCode) {
        if(keyCode == KeyEvent.VK_ESCAPE) {
            playFrame.toggleIsPause();
            System.out.println(playFrame.getIsPause());
        }

        //옵션이 위에서부터 인덱스가 0 1 2기때문에 반대.
        if (keyCode == upKey) {
            playFrame.pausePanel.upPoint();
        }
        else if (keyCode == downKey) {
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
