package play_screen;

import settings.LoadData;
import startscreen.ScoreInput;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import static startscreen.StartMenu.*;

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
        int keykode = e.getKeyCode();

        //상태에 따른 키입력 처리
        if (tetris.getIsPause()) {
            handlePauseState(keykode);
        }
        else if (tetris.getIsGameOver()) {
            handleGameOverState(keykode);
        }
        else {
            handleGameState(keykode);
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
            tetris.gamePanel.goDown();
            tetris.updateGame();
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

            }
        }
        else if (keyCode == KeyEvent.VK_ESCAPE) {
            tetris.toggleIsPause();
        }
    }

    private void handleGameOverState(int keyCode) {

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
