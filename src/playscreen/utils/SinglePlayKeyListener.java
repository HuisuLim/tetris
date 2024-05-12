package playscreen.utils;

import playscreen.PlayFrame;
import playscreen.panels.TetrisPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SinglePlayKeyListener  implements KeyListener {
    private final PlayFrame playFrame;
    private final TetrisPanel tetrisPanel;
    private final int upKey;
    private final int rightKey;
    private final int downKey;
    private final int leftKey;
    private final int downToEndKey;

    public SinglePlayKeyListener(PlayFrame playFrame, int[] keys){
        this.playFrame = playFrame;
        this.tetrisPanel = playFrame.playPanel.tetrisPanel;
        upKey = keys[0];
        rightKey = keys[1];
        downKey = keys[2];
        leftKey = keys[3];
        downToEndKey = keys[4];

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if(playFrame.playPanel.getIsPause()) {
            handlePauseState(keyCode);
        }
        else {
            handleGameState(keyCode);
        }

    }

    private void handleGameState(int keyCode) { //게임중일때 키입력 처리
        if (keyCode == KeyEvent.VK_ESCAPE) {
            playFrame.playPanel.toggleIsPause();
        } else if (keyCode == leftKey) {
            tetrisPanel.goLeft();
        } else if (keyCode == rightKey) {
            tetrisPanel.goRight();
        } else if (keyCode == upKey) {
            tetrisPanel.rotate90();
        } else if (keyCode == downToEndKey) {
            tetrisPanel.goDownToEnd();
        } else if (keyCode == downKey) {
            tetrisPanel.goDown();
        }
    }

    private void handlePauseState(int keyCode) {
//
//        //옵션이 위에서부터 인덱스가 0 1 2기때문에 반대.
//        if (keyCode == upKey) {
//            tetris.pausePanel.upPoint();
//        }
//        else if (keyCode == downKey) {
//            tetris.pausePanel.downPoint();
//        }
//
//        else if (keyCode == KeyEvent.VK_ENTER) {
//            switch (tetris.pausePanel.getCurrPoint()) {
//                case 0: // RESUME
//                    tetris.toggleIsPause();
//                    break;
//                case 1: // Go to StartMenu
//                    playFrame.dispose();
//                    break;
//                case 2: // EXIT
//                    System.exit(0); // 프로그램 종료
//                    break;
//            }
//        } else if (keyCode == KeyEvent.VK_ESCAPE) {
//            tetris.toggleIsPause();
//        }
    }

    //필요할 시 구현.


    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
