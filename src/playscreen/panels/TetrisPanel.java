package playscreen.panels;

import playscreen.blocks.BlankBlock;
import playscreen.utils.ColorTable;
import playscreen.blocks.Block;
import playscreen.blocks.BlockGenerator;
import playscreen.utils.GameOverCallBack;
import playscreen.utils.TimerDelay;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class TetrisPanel extends JPanel{
    private final GameOverCallBack gameOverCallBack;
    protected double screenSize;
    protected int[] colorTable;

    //보드 초기설정    게임보드의 가장 왼쪽 위가 board[0][0]
    protected int SQUARE_SIZE;
    protected static final int BOARD_WIDTH = 10; // 게임 보드의 가로 칸 수
    protected static final int BOARD_HEIGHT = 20; // 게임 보드의 세로 칸 수
    protected final int[][] board = new int[BOARD_HEIGHT][BOARD_WIDTH]; // 게임 보드를 표현하는 2차원 배열

    //블럭 정보
    protected final BlockGenerator generator = new BlockGenerator();
    protected Block currBlock;
    protected Block nextBlock;
    protected int currentRow;
    protected int currentCol;

    //게임정보
    protected int score = 0;
    protected double scoreMultiplier = 1; //점수획득 계수
    protected int lineRemoveCount = 0;

    //상태 변수들
    protected boolean isCleaningTime = false;
    protected boolean isGameOver = false;

    //상태변수들 getter, setter
    public boolean getIsGameOver() {
        return isGameOver;
    }

    public int getScore() {
        return score;
    }
    public int[][] getNextBlock(){
        return nextBlock.getShape();
    }
    //--------대전모드용------------------------------------------------
    private LineRemovePanel lineRemovePanel;
    public void setLineRemovePanel(LineRemovePanel lineRemovePanel) {
        this.lineRemovePanel = lineRemovePanel;
    }
    public int[][] getBoardCopy(){
        int[][] tetrisBoardCopy = new int[board.length][];
        for (int i = 0; i < board.length; i++) {
            tetrisBoardCopy[i] = new int[board[i].length]; // Initialize the inner array
            System.arraycopy(board[i], 0, tetrisBoardCopy[i], 0, board[i].length); // Copy each inner array
        }
        return tetrisBoardCopy;
    }
    public int getlineRemoveCount() {
        return lineRemoveCount;
    }
    // 현재 블록의 행 위치를 반환하는 메서드
    public int getCurrentRow() {
        return currentRow;
    }

    // 현재 블록의 열 위치를 반환하는 메서드
    public int getCurrentCol() {
        return currentCol;
    }

    public int[][] getCurrBlock(){
        return currBlock.getShape();
    }

    //---------------------------------------------------------------

    public TetrisPanel(GameOverCallBack gameOverCallBack, double screenSize, boolean colorMode) {
        this.gameOverCallBack = gameOverCallBack;
        this.screenSize = screenSize;
        this.SQUARE_SIZE = (int)(20 * screenSize);
        this.colorTable = ColorTable.getTable(colorMode);
        lineRemovePanel = new LineRemovePanel(screenSize, this);

        setSize(BOARD_WIDTH * SQUARE_SIZE, BOARD_HEIGHT * SQUARE_SIZE); // 창 크기 설정
        currBlock = null;
        nextBlock = generator.getRandomStandardBlock();
        createNewShape(); // 새 도형 생성
        repaint();
    }


    public void createNewShape() {
        //다음블럭을 현재블럭으로 옮기고 다음블럭을 생성.
        currBlock = nextBlock;
        nextBlock = generator.getRandomStandardBlock();

        //현재블럭의 시작위치를 잡아줌.
        int[] temp = currBlock.getStartPos();
        currentRow = temp[0];
        currentCol = temp[1];

        //블럭을 생성할 수 없을때 게임종료.
        if(!canMoveTo(currentRow, currentCol, currBlock.getShape())){
            currBlock = new BlankBlock();
            isGameOver = true;
            gameOverCallBack.onGameOver(score);
        }
    }

    protected boolean canMoveTo(int targetRow, int targetCol, int[][] shape) { //블럭이 원하는 위치에 원하는 형태로 존재할 수 있는지 체크.
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

    protected boolean canRotate() { //블럭이 회전한 형태가 현재 위치에 존재할 수 이는지 체크.
        return canMoveTo(currentRow, currentCol, currBlock.getRotatedShape());
    }

    protected void mergeShapeToBoard() { // 블럭을 보드에 병합하는 메서드.
        int[][] shape = currBlock.getShape();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape.length; col++) {
                if (shape[row][col] != 0) {
                    board[currentRow + row][currentCol + col] = shape[row][col];
                }
            }
        }
        scoreMultiplier = TimerDelay.calScoreMultiplier(score);
        score += (int) (100 * scoreMultiplier);
    }

    protected boolean checkLines() { //지워질 수 있는 라인이 있는지 체크하고 지워질 수 있으면 그 라인을 전부 8로설정.
        boolean doClear = false;
        for (int row = BOARD_HEIGHT - 1; row >= 0; row--) {
            boolean isLineComplete = true;
            for (int col = 0; col < BOARD_WIDTH; col++) {
                if (board[row][col] == 0) {
                    isLineComplete = false;
                    break;
                }
            }

            // 완성된 라인이 있으면 제거할 곳에 8을 넣음. ColorTable에서 검은색을 담당.
            if (isLineComplete) {
                doClear = true;
                lineRemoveCount++;
                score+= (int) (1000 * scoreMultiplier);
                for (int col = 0; col < BOARD_WIDTH; col++) {
                    board[row][col] = 8;
                }
            }
        }
        return doClear;
    }

    protected void clearLines() { //8로 구성되어있는 라인을 제거.
        for (int row = BOARD_HEIGHT - 1; row >=0;) {
            if (board[row][0] != 8) {
                row--;
                continue;
            }
            for (int r = row; r > 0; r--) {
                System.arraycopy(board[r - 1], 0, board[r], 0, BOARD_WIDTH);
            }
        }
    }


    //테트리스 동작관련 메서드들
    public void goLeft() {
        if(isCleaningTime || isGameOver) return;
        if(canMoveTo(currentRow, currentCol-1, currBlock.getShape())){
            currentCol--;
            repaint();
        }
    }
    public void goRight() {
        if(isCleaningTime || isGameOver) return;
        if(canMoveTo(currentRow, currentCol+1, currBlock.getShape())){
            currentCol++;
            repaint();
        }
    }
    public void goDown() {
        if(isCleaningTime || isGameOver) return;
        if(canMoveTo(currentRow+1, currentCol, currBlock.getShape())){
            currentRow++;
            scoreMultiplier = TimerDelay.calScoreMultiplier(score);
            score += (int) (5*scoreMultiplier);
            repaint();
        }
        else {
            mergeShapeToBoard();
            if(checkLines()) {
                if (lineRemovePanel != null) {
                    lineRemovePanel.updateAttackBoard(getlineRemoveCount(), getCurrentRow(), getCurrentCol(), getCurrBlock(), getBoardCopy());
                }
                //lineRemovePanel.updateAttackBoard(getlineRemoveCount(), getCurrentRow(), getCurrentCol(), getCurrBlock(), getBoardCopy());
                isCleaningTime = true;
                repaint();
                Timer cleaningTimer = new Timer(700, e-> {
                    clearLines();
                    repaint();
                    isCleaningTime = false;

                });
                cleaningTimer.setRepeats(false);
                cleaningTimer.start();
            }
            createNewShape();
        }
    }
    public void goDownToEnd() {
        if(isCleaningTime || isGameOver) return;
        while(true) {
            if(canMoveTo(currentRow+1, currentCol, currBlock.getShape())) {
                currentRow++;
                scoreMultiplier = TimerDelay.calScoreMultiplier(score);
                score += (int) (5*scoreMultiplier);
            }
            else break;
        }
        repaint();
    }
    public void rotate90() {
        if(isCleaningTime || isGameOver) return;
        if(canRotate()){
            currBlock.rotate90();
            repaint();
        }
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

        // 게임 보드 그리는 부분
        for (int row = 0; row < BOARD_HEIGHT; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                // 직접 색상 계산 로직을 넣지 않고 색상 인덱스를 전달
                drawSquare(offScreenGraphics, col, row, board[row][col]);
            }
        }

        // 현재 도형 그리기 부분
        int[][] shape = currBlock.getShape();
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape.length; col++) {
                if (shape[row][col] != 0) {
                    // 직접 색상 계산 로직을 넣지 않고 색상 인덱스를 전달
                    drawSquare(offScreenGraphics, currentCol + col, currentRow + row, shape[row][col]);
                }
            }
        }

        // 화면에 오프스크린 이미지를 복사
        g.drawImage(offScreenImage, 0, 0, this);

        // 자원 정리
        offScreenGraphics.dispose();
    }
    protected void drawSquare(Graphics g, int x, int y, int blockNum) {
        // 색상 테이블에서 색상 코드를 가져옴
        int colorCode = colorTable[blockNum];
        Color color = new Color(colorCode);
        g.setColor(color);
        g.fillRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE); // 사각형 채우기

        g.setColor(Color.BLACK); // 테두리 색상 설정
        g.drawRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE); // 사각형 테두리 그리기
    }


}
