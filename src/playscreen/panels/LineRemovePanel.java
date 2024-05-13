package playscreen.panels;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class LineRemovePanel extends JPanel {
    private static final int ATTACK_BOARD_SIZE = 10; // 10x10 board for the attack panel
    private final TetrisPanel tetrisPanel; // Reference to the Tetris game panel
    private int[][] attackBoard; // Board for the attack panel
    private int lineRemove = 0;

    protected double screenSize;
    private int SQUARE_SIZE = 20;


    public LineRemovePanel(double screenSize, TetrisPanel tetrispanel) {
        this.setLayout(new BorderLayout());
        this.screenSize = screenSize;
        this.tetrisPanel = tetrispanel;
        this.SQUARE_SIZE = (int)(10 * screenSize);
        this.attackBoard = new int[ATTACK_BOARD_SIZE][ATTACK_BOARD_SIZE]; // Initialize the attack board
        setSize(ATTACK_BOARD_SIZE * SQUARE_SIZE, ATTACK_BOARD_SIZE * SQUARE_SIZE);
    }

    // Method to update the attack board based on lines cleared from the Tetris panel
    public void updateAttackBoard(int lineRemoveCount, int currentRow, int currentCol, int[][] shape, int[][] tetrisBoardCopy) {
        int linesCleared = lineRemoveCount - lineRemove;
        int freeRows = 0;
        for (int row = 0; row < ATTACK_BOARD_SIZE; row++) {
            boolean isRowEmpty = true;
            for (int col = 0; col < ATTACK_BOARD_SIZE; col++) {
                if (attackBoard[row][col] != 0) {
                    isRowEmpty = false;
                    break;
                }
            }
            if (isRowEmpty) {
                freeRows++;
            } else {
                break; // Stop counting if a filled row is encountered
            }
        }

        if (linesCleared > freeRows) {
            linesCleared = freeRows;
        }

        if (linesCleared >= 2) {
            for (int row = 0; row < ATTACK_BOARD_SIZE - linesCleared; row++) {
                for (int col = 0; col < ATTACK_BOARD_SIZE; col++) {
                    attackBoard[row][col] = attackBoard[row + linesCleared][col];
                }
            }

            int index = 10-linesCleared;
            for (int row = 0; row < shape.length; row++) {
                for (int col = 0; col < shape.length; col++) {
                    if(shape[row][col]!=0){
                        tetrisBoardCopy[currentRow + row][currentCol + col] = 0;
                    }
                    //System.out.print(shape[row][col]+" ");
                }
                //System.out.println(" ");
            }
            for (int row = 0; row < tetrisBoardCopy.length; row++) {
                for (int col = 0; col < tetrisBoardCopy[row].length; col++) {
                    if(tetrisBoardCopy[row][col] == 8){
                        System.arraycopy(tetrisBoardCopy[row], 0, attackBoard[index], 0, tetrisBoardCopy[row].length);
                        index++;
                        break;
                    }
                }
            }
        }
        this.lineRemove = lineRemoveCount;
        /*

        //-----------확인용-------------------------ß-------
        System.out.println("currnetRow: "+ currentRow);
        System.out.println("currentCol: "+currentCol);
        System.out.println("lineRemoveCount: "+lineRemoveCount);
        System.out.println("블록 shape");
        for (int row = 0; row < shape.length; row++) {
            for (int col = 0; col < shape.length; col++) {
                System.out.print(shape[row][col]+" ");
            }
            System.out.println(" ");
        }
        //------------------------------------------------
        // Debug print the copied array
        System.out.println("attackBoard: ");
        for (int row = 0; row < attackBoard.length; row++) {
            for (int col = 0; col < attackBoard[row].length; col++) {
                System.out.print(attackBoard[row][col] + " ");
            }
            System.out.println();
        }

         */
    }
    //---------------------나중에 가져다 쓸때 사용할거-------------------------------------------------------
    private void clearAttackBoard() {
        for (int row = 0; row < ATTACK_BOARD_SIZE; row++) {
            boolean isRowEmpty = true;
            for (int col = 0; col < ATTACK_BOARD_SIZE; col++) {
                attackBoard[row][col] = 0;
            }
        }
    }
    public int[][] copyAttackBoard(){
        int[][] attackBoardCopy = new int[attackBoard.length][];
        for (int i = 0; i < attackBoard.length; i++) {
            attackBoardCopy[i] = new int[attackBoard[i].length]; // Initialize the inner array
            System.arraycopy(attackBoard[i], 0, attackBoardCopy[i], 0, attackBoard[i].length); // Copy each inner array
        }
        return attackBoardCopy;
    }

    //--------------------------------------------------------------------------------------------------

    @Override
    public void paint(Graphics g) {
        // 오프스크린 이미지 생성
        BufferedImage offScreenImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics offScreenGraphics = offScreenImage.getGraphics();

        // 기본 배경을 그립니다 (선택적)
        offScreenGraphics.setColor(Color.black);
        offScreenGraphics.fillRect(0, 0, getWidth(), getHeight());

        super.paint(offScreenGraphics); // JFrame의 기본 페인트 메커니즘을 사용하여 구성요소를 그림

        // 게임 보드 그리는 부분
        for (int row = 0; row < ATTACK_BOARD_SIZE; row++) {
            for (int col = 0; col < ATTACK_BOARD_SIZE; col++) {
                // 직접 색상 계산 로직을 넣지 않고 색상 인덱스를 전달
                drawSquare(offScreenGraphics, col, row, 0);
            }
        }
        for (int row = 0; row < ATTACK_BOARD_SIZE; row++) {
            for (int col = 0; col < ATTACK_BOARD_SIZE; col++) {
                if (attackBoard[row][col] == 8) {
                    drawSquare(offScreenGraphics, col, row,1);
                }
            }
        }
        g.drawImage(offScreenImage, 0, 0, this);

        // 자원 정리
        offScreenGraphics.dispose();
    }

    protected void drawSquare(Graphics g, int x, int y, int color) {
        // 색상 테이블에서 색상 코드를 가져옴
        if(color == 1){
            g.setColor(Color.gray);
        }
        else{
            g.setColor(Color.black);
        }
        int defaultX = SQUARE_SIZE*5;
        g.fillRect(defaultX+x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE); // 사각형 채우기

        g.setColor(Color.BLACK); // 테두리 색상 설정
        g.drawRect(defaultX+x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE); // 사각형 테두리 그리기
    }


}
