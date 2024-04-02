package play_screen.panels;

import play_screen.ColorTable;
import play_screen.blocks.Block;
import play_screen.blocks.BlockGenerator;
import settings.LoadData;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TetrisPanel extends JPanel{

    private int SQUARE_SIZE;
    private static final int BOARD_WIDTH = 10; // 게임 보드의 가로 칸 수
    private static final int BOARD_HEIGHT = 20; // 게임 보드의 세로 칸 수
    private final int[][] board = new int[BOARD_HEIGHT][BOARD_WIDTH]; // 게임 보드를 표현하는 2차원 배열
    private final BlockGenerator generator = new BlockGenerator();
    //게임보드의 가장 왼쪽 위가 board[0][0]
    private Block currBlock;
    private Block[] nextBlocks;
    private int currentRow;
    private int currentCol;
    int[] colorTable;
    private int score = 0;
    private int screenSize;
    private boolean colorBlindMode;
    private String gameMode;
    private String difficulty;
    private boolean isGameOver = false;

    // 화면 크기 조절을 위해 SquareSize의 조절
    public void setProps() {
        LoadData data = new LoadData();
        this.screenSize = data.loadScreenSize();
        SQUARE_SIZE = 20 * screenSize;
        this.colorBlindMode = data.loadColorBlindMode();
        this.colorTable = ColorTable.getTable(this.colorBlindMode);
        this.gameMode = data.loadGameMode();
    }

    public TetrisPanel() {
        setProps();
        setSize(BOARD_WIDTH * SQUARE_SIZE, BOARD_HEIGHT * SQUARE_SIZE); // 창 크기 설정
        currBlock = null;
        nextBlocks = new Block[5];
        for(int i = 0; i < 5; i++) {
            nextBlocks[i] = generator.getRandomStandardBlock();
        }
        createNewShape(); // 새 도형 생성
        repaint();
    }

    public boolean getIsGameOver() {
        return isGameOver;
    }

    private void createNewShape() {
        score +=100;
        currBlock = nextBlocks[0];
        for(int i = 1; i < 5; i++) {
            nextBlocks[i-1] = nextBlocks[i];
        }
        nextBlocks[4] = generator.getRandomStandardBlock();

        int[] temp = currBlock.getStartPos();
        currentRow = temp[0];
        currentCol = temp[1];
        if(!canMoveTo(currentRow, currentCol, currBlock.getShape())){
            isGameOver = true;
            System.out.println("게임종료");
        }
    }

    private boolean canMoveTo(int targetRow, int targetCol, int[][] shape) {
        for(int row = 0; row < shape.length; row++) {
            for(int col = 0; col < shape.length; col++) {
                if (shape[row][col] == 0) continue;
                if (targetRow + row < 0 || targetRow + row >=BOARD_HEIGHT) return false;
                if (targetCol + col < 0 || targetCol + col >=BOARD_WIDTH) return false;
                if (board[targetRow + row][targetCol+ col] != 0) return false;
            }
        }
        return true;
    }

    private boolean canRotate() {
        return canMoveTo(currentRow, currentCol, currBlock.getRotatedShape());
    }

    private void mergeShapeToBoard() {
        int[][] shape = currBlock.getShape();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape.length; col++) {
                if (shape[row][col] != 0) {
                    board[currentRow + row][currentCol + col] = shape[row][col];
                }
            }
        }
    }

    private void checkAndClearLines() {
        for (int row = BOARD_HEIGHT - 1; row >= 0; ) {
            boolean isLineComplete = true;
            for (int col = 0; col < BOARD_WIDTH; col++) {
                if (board[row][col] == 0) {
                    isLineComplete = false;
                    break;
                }
            }

            // 완성된 라인이 있으면 제거하고 위쪽 라인들을 아래로 이동
            if (isLineComplete) {
                score+= 1000;
                for (int r = row; r > 0; r--) {
                    for (int col = 0; col < BOARD_WIDTH; col++) {
                        board[r][col] = board[r - 1][col];
                    }
                }
            } else {
                row--; // 완성된 라인이 없으면 다음 라인 확인
            }
        }
    }

    public int getScore() {
        return score;
    }

    public void goLeft() {
        if(canMoveTo(currentRow, currentCol-1, currBlock.getShape())){
            currentCol--;
            repaint();
        }
    }
    public void goRight() {
        if(canMoveTo(currentRow, currentCol+1, currBlock.getShape())){
            currentCol++;
            repaint();
        }
    }
    public void goDown() {
        if(canMoveTo(currentRow+1, currentCol, currBlock.getShape())){
            currentRow++;
        }
        else {
            mergeShapeToBoard();
            checkAndClearLines();
            createNewShape();
        }
        repaint();
    }
    public void rotate90() {
        if(canRotate()){
            currBlock.rotate90();
            repaint();
        }
    }

    public int[][] getNextBlock(){
        return nextBlocks[0].getShape();
    }

    @Override
    public void paint(Graphics g) {
        // 오프스크린 이미지 생성
        BufferedImage offScreenImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics offScreenGraphics = offScreenImage.getGraphics();

        // 기본 배경을 그립니다 (선택적)
        offScreenGraphics.setColor(getBackground());
        offScreenGraphics.fillRect(0, 0, getWidth(), getHeight());

        super.paint(offScreenGraphics); // JFrame의 기본 페인트 메커니즘을 사용하여 구성요소를 그림

        // 게임 보드 및 현재 도형을 오프스크린 그래픽 객체에 그립니다
        for (int row = 0; row < BOARD_HEIGHT; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                int color = colorTable[board[row][col]];
                offScreenGraphics.setColor(new Color(color));
                offScreenGraphics.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                offScreenGraphics.setColor(Color.BLACK); // 테두리는 검은색
                offScreenGraphics.drawRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }

        // 현재 도형 그리기
        int[][] shape = currBlock.getShape();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape.length; col++) {
                if (shape[row][col] != 0) {
                    int color = colorTable[shape[row][col]];
                    offScreenGraphics.setColor(new Color(color));
                    offScreenGraphics.fillRect((currentCol + col) * SQUARE_SIZE, (currentRow + row) * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    offScreenGraphics.setColor(Color.BLACK); // 테두리는 검은색
                    offScreenGraphics.drawRect((currentCol + col) * SQUARE_SIZE, (currentRow + row) * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }

        // 화면에 오프스크린 이미지를 복사
        g.drawImage(offScreenImage, 0, 0, this);

        // 자원 정리
        offScreenGraphics.dispose();
    }



}
