//package playscreen.utils;
//
//import playscreen.PlayFrame;
//import startscreen.StartMenu;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//public class TetrisKeyListener implements KeyListener {
//    private final PlayFrame tetris;
//    private final int upKey;
//    private final int rightKey;
//    private final int downKey;
//    private final int leftKey;
//    private final int downToEndKey;
//
//    public TetrisKeyListener(PlayFrame tetris, int[] keys) {
//        this.tetris = tetris;
//        upKey = keys[0];
//        rightKey = keys[1];
//        downKey = keys[2];
//        leftKey = keys[3];
//        downToEndKey = keys[4];
//    }
//
//
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        int keyCode = e.getKeyCode();
//
//        //상태에 따른 키입력 처리
//        if (tetris.getIsPause()) { //pause상태일때
//            handlePauseState(keyCode);
//        } else if (tetris.getIsCleaningTime()) { //블럭이 merge될때 잠시 대기.
//        } else if (tetris.getIsGameOver()) { //게임오버됐을때
//            handleGameOverState(keyCode);
//        } else { //게임중일때
//            handleGameState(keyCode);
//        }
//    }
//
//    private void handleGameState(int keyCode) { //게임중일때 키입력 처리
//        if (keyCode == KeyEvent.VK_ESCAPE) {
//            tetris.toggleIsPause();
//        } else if (keyCode == leftKey) {
//            tetris.gamePanel.goLeft();
//        } else if (keyCode == rightKey) {
//            tetris.gamePanel.goRight();
//        } else if (keyCode == upKey) {
//            tetris.gamePanel.rotate90();
//        } else if (keyCode == downToEndKey) {
//            while (tetris.gamePanel.goDown()) { // 블록을 가장 아래로 이동
//                if(tetris.gamePanel.getIsGameOver()) break;
//            }
//            tetris.updateGame(true);
//        } else if (keyCode == downKey) {
//            tetris.updateGame(tetris.gamePanel.goDown());
//            tetris.timer.stop();
//            tetris.timer.start();
//        }
//    }
//
//    private void handlePauseState(int keyCode) {
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
//                    tetris.dispose();
//                    new StartMenu().setVisible(true);
//                    break;
//                case 2: // EXIT
//                    System.exit(0); // 프로그램 종료
//                    break;
//            }
//        } else if (keyCode == KeyEvent.VK_ESCAPE) {
//            tetris.toggleIsPause();
//        }
//    }
//
//    private void handleGameOverState(int keyCode) {
//        if (keyCode == KeyEvent.VK_ESCAPE) {
//            // 게임 오버 상태에서 ESC를 누르면 StartMenu로 돌아감
//            tetris.dispose();
//            new StartMenu().setVisible(true);
//        }
//    }
//
//
//    //KeyListner implements할때 필수구성.
//    @Override
//    public void keyTyped(KeyEvent e) {
//        //필요할시 구현
//    }
//    @Override
//    public void keyReleased(KeyEvent e) {
//        //필요할시 구현
//    }
//}
