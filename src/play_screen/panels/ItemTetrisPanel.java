package play_screen.panels;

import java.awt.*;

public class ItemTetrisPanel extends TetrisPanel{
    protected int itemGenerateCount = -1000;
    protected boolean weightBlockCanMove;
    @Override
    protected void createNewShape() {
        score +=100;
        if(lineRemoveCount > itemGenerateCount) {
            itemGenerateCount++;
            currBlock = generator.getRandomItemBlock();
            if (currBlock.getBlockNum() == 11) weightBlockCanMove = true;
        }
        else {
            currBlock = nextBlock;
            nextBlock = generator.getRandomStandardBlock();
        }
        int[] temp = currBlock.getStartPos();
        currentRow = temp[0];
        currentCol = temp[1];
        if(!canMoveTo(currentRow, currentCol, currBlock.getShape())){
            isGameOver = true;
            System.out.println("게임종료");
        }
    }

    @Override
    protected boolean canMoveTo(int targetRow, int targetCol, int[][] shape) {
        if (currBlock.getBlockNum() != 11) {
            return super.canMoveTo(targetRow, targetCol, shape);
        }
        if (!weightBlockCanMove && targetCol != currentCol) return false;
        for(int row = 0; row < shape.length; row++) {
            for(int col = 0; col < shape.length; col++) {
                if (shape[row][col] == 0) continue;
                if (board[targetRow + row][targetCol + col] != 0) {
                    board[targetRow + row][targetCol+ col] = 0;
                    continue;
                }
                if (targetRow + row < 0 || targetRow + row >=BOARD_HEIGHT) return false;
                if (targetCol + col < 0 || targetCol + col >=BOARD_WIDTH) return false;

            }
        }
        return true;
    }

    @Override
    protected void mergeShapeToBoard() {
        if (currBlock.getBlockNum() == 11) return;
        super.mergeShapeToBoard();
    }

    @Override
    protected void checkAndClearLines() {
        if (currBlock.getBlockNum() > 11) {
            return;
        }
        super.checkAndClearLines();
    }

    @Override
    protected void drawSquare(Graphics g, int x, int y, int blockNum) {
        if (blockNum < 10) {
            super.drawSquare(g, x, y, blockNum);
            return;
        }
        int colorCode = colorTable[blockNum - 10];
        Color color = new Color(colorCode);
        g.setColor(color);
        g.fillOval(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE); // 사각형 채우기

        g.setColor(Color.BLACK); // 테두리 색상 설정
        g.drawRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE); // 사각형 테두리 그리기

    }

}
