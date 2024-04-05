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
        endGame();

    }
    //게임오버 시 이름 입력 후 스코어보드 띄움
    private void endGame() {
        // ScoreInput 창을 띄우고 사용자로부터 이름을 입력받음
        String name = JOptionPane.showInputDialog(tetris, "이름을 입력하세요:");

        if (name != null && !name.isEmpty()) {
            // 게임의 난이도와 모드를 설정합니다.
            String difficulty = loadData.loadDifficulty();
            String mode = gameMode;

            // 테이블에 이름과 현재 점수, 난이도, 모드 추가
            new ScoreInput(name, tetris.gamePanel.getScore(), difficulty, mode).setVisible(true);
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
