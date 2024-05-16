package playscreen.utils;

import playscreen.MultiPlayFrame;
import playscreen.panels.PlayPanel;
import startscreen.StartMenu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Arrays;

public class MultiPlayKeyListener  implements KeyListener {
    private final MultiPlayFrame playFrame;

    private final PlayPanel player1PlayPanel;
    private final PlayPanel player2PlayPanel;

    private final int[] player1Keys;
    private final int[] player2Keys;

    public MultiPlayKeyListener(MultiPlayFrame playFrame, int[] player1Keys, int[] player2Keys){
        this.playFrame = playFrame;
        this.player1PlayPanel = playFrame.player1PlayPanel;
        this.player2PlayPanel = playFrame.player2PlayPanel;

        this.player1Keys = player1Keys;
        this.player2Keys = player2Keys;

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
        else if(contains(player1Keys, keyCode)) {
            handle1PKeys(keyCode);
        }
        else if(contains(player2Keys, keyCode)) {
            handle2PKeys(keyCode);
        }
        playFrame.repaint();
        player1PlayPanel.repaint();
        player2PlayPanel.repaint();
    }

    private void handle1PKeys(int keyCode) {
        for(int i = 0; i < 5; i++) {
            if (keyCode == player1Keys[i]) {
                player1PlayPanel.gameControl(i);
                break;
            }
        }
    }
    private void handle2PKeys(int keyCode) {
        for(int i = 0; i < 5; i++) {
            if (keyCode == player2Keys[i]) {
                player2PlayPanel.gameControl(i);
                break;
            }
        }
    }


    private void handlePauseState(int keyCode) {

        //옵션이 위에서부터 인덱스가 0 1 2기때문에 반대.
        if (keyCode == player1Keys[0] || keyCode == player2Keys[0]) {
            playFrame.pausePanel.upPoint();
        }
        else if (keyCode == player1Keys[2] || keyCode == player2Keys[2]) {
            playFrame.pausePanel.downPoint();
        }

        else if (keyCode ==player1Keys[4] || keyCode == player2Keys[4]) {
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
        }
    }

    public static boolean contains(int[] arr, int target) { //target이 arr에 포함되있나 체크
        return Arrays.stream(arr).anyMatch(num -> num == target);
    }

    //필요할 시 구현.
    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
