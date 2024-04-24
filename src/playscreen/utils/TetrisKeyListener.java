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
    private String difficulty;

    public  TetrisKeyListener(PlayFrame tetris) {
        this.tetris = tetris;
    }




    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        //상태에 따른 키입력 처리
        if (tetris.getIsPause()) {
            handlePauseState(keyCode);
        }
        else if (tetris.getIsCleaningTime()) {
            System.out.println("cantmove");
        }
        else if (tetris.getIsGameOver()) {
            handleGameOverState(keyCode);
        }
        else {
            handleGameState(keyCode);
        }
    }

    private void handleGameState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            tetris.toggleIsPause();
        }
        else if (keyCode == leftKey) {
            tetris.gamePanel.goLeft();
        }
        else if (keyCode == rightKey) {
            tetris.gamePanel.goRight();
        }
        else if (keyCode == upKey) {
            tetris.gamePanel.rotate90();
        }
        else if (keyCode == downKey) {

            tetris.updateGame(tetris.gamePanel.goDown());
        }
    }

    private void handlePauseState(int keyCode) {
        if (keyCode == upKey || keyCode == downKey) {
            tetris.pausePanel.changePoint();
        }
        else if (keyCode == KeyEvent.VK_ENTER) {
            if (tetris.pausePanel.getCurrPoint() == 0) {
                tetris.toggleIsPause();
            }
            else {
                tetris.dispose();
                new StartMenu().setVisible(true);
            }
        }
        else if (keyCode == KeyEvent.VK_ESCAPE) {
            tetris.toggleIsPause();
        }
    }

    private void handleGameOverState(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            // 현재 창을 닫고 StartMenu 창을 엽니다.
            tetris.dispose(); // 현재 창을 닫음
            new StartMenu().setVisible(true); // StartMenu 창을 엶

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
