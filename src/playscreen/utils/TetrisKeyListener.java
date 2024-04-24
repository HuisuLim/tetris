package playscreen.utils;

import playscreen.PlayFrame;
import settings.LoadData;
import startscreen.StartMenu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class TetrisKeyListener implements KeyListener {
    private PlayFrame tetris;
    private LoadData key = new LoadData();
    private final int leftKey = key.getLeftKey();
    private final int rightKey = key.getRightKey();
    private final int upKey = key.getUpKey();
    private final int downKey = key.getDownKey();

    public TetrisKeyListener(PlayFrame tetris) {
        this.tetris = tetris;
    }



    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        //상태에 따른 키입력 처리
        if (tetris.getIsPause()) {
            handlePauseState(keyCode);
        } else if (tetris.getIsCleaningTime()) {
            // 청소 시간 동안 키 입력을 무시
        } else if (tetris.getIsGameOver()) {
            handleGameOverState(keyCode);
        } else {
            handleGameState(keyCode);
        }
    }

    private void handleGameState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            tetris.toggleIsPause();
        } else if (keyCode == leftKey) {
            tetris.gamePanel.goLeft();
        } else if (keyCode == rightKey) {
            tetris.gamePanel.goRight();
        } else if (keyCode == upKey) {
            tetris.gamePanel.rotate90();
        } else if (keyCode == downKey) {
            while (tetris.gamePanel.goDown()) { // 블록을 가장 아래로 이동
                // 이동이 완료되면 반복 중지
            }
            tetris.updateGame(true);
        }
    }

    private void handlePauseState(int keyCode) {
        if (keyCode == upKey || keyCode == downKey) {
            tetris.pausePanel.changePoint();
        } else if (keyCode == KeyEvent.VK_ENTER) {
            switch (tetris.pausePanel.getCurrPoint()) {
                case 0: // RESUME
                    tetris.toggleIsPause();
                    break;
                case 1: // Go to StartMenu
                    tetris.dispose();
                    new StartMenu().setVisible(true);
                    break;
                case 2: // EXIT
                    System.exit(0); // 프로그램 종료
                    break;
            }
        } else if (keyCode == KeyEvent.VK_ESCAPE) {
            tetris.toggleIsPause();
        }
    }

    private void handleGameOverState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            // 게임 오버 상태에서 ESC를 누르면 StartMenu로 돌아감
            tetris.dispose();
            new StartMenu().setVisible(true);
        }
    }


    //KeyListner implements할때 필수구성.
    @Override
    public void keyTyped(KeyEvent e) {
        //필요할시 구현
    }
    @Override
    public void keyReleased(KeyEvent e) {
        //필요할시 구현
    }
}
