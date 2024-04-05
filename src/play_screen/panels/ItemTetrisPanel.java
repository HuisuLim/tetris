package play_screen.panels;

import java.awt.*;

public class ItemTetrisPanel extends TetrisPanel{
    protected int itemGenerateCount = -1000; // 디버깅용
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
        if (!weightBlockCanMove && targetCol != currentCol) {
            return false;
        }
        for(int row = 0; row < shape.length; row++) {
            for(int col = 0; col < shape.length; col++) {
                if (shape[row][col] == 0) continue;
                if (targetRow + row < 0 || targetRow + row >=BOARD_HEIGHT) return false;
                if (targetCol + col < 0 || targetCol + col >=BOARD_WIDTH) return false;
                if (board[targetRow + row][targetCol + col] != 0) {
                    board[targetRow + row][targetCol+ col] = 0;
                    weightBlockCanMove = false;
                }


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
            int[] itemIndex = findIndex(board, currBlock.getBlockNum());
            switch (currBlock.getBlockNum()) {
                case 12 -> {
                    for (int row = itemIndex[0] - 2; row < itemIndex[0] + 2; row++) {
                        for (int col = itemIndex[1] - 2; col < itemIndex[1] + 2; col++) {
                            if (row < 0 || row >= BOARD_HEIGHT) continue;
                            if (col < 0 || col >= BOARD_WIDTH) continue;
                            board[row][col] = 0;
                        }
                    }
                }
                case 13 -> {
                    for (int col = 0; col < BOARD_WIDTH; col++) {
                        board[itemIndex[0]][col] = 0;
                    }
                }
                case 14 -> {
                    for (int row = 0; row < BOARD_HEIGHT; row++) {
                        board[row][itemIndex[1]] = 0;
                    }
                }
                case 15 -> {
                    for (int col = 0; col < BOARD_WIDTH; col++) {
                        board[itemIndex[0]][col] = 0;
                    }
                    for (int row = 0; row < BOARD_HEIGHT; row++) {
                        board[row][itemIndex[1]] = 0;
                    }
                }
                case 16 -> {
                    for (int row = 0; row < BOARD_HEIGHT; row++) {
                        for (int col = 0; col < BOARD_WIDTH; col++) {
                            board[row][col] = 0;
                        }
                    }
                }
            };
        }
        super.checkAndClearLines();
    }

    @Override
    protected void drawSquare(Graphics g, int x, int y, int blockNum) {
        super.drawSquare(g, x, y, blockNum % 10);
        if (blockNum > 10) {
            g.setColor(Color.WHITE);
            g.fillOval(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE); // 원 채우기
            g.setColor(Color.BLACK);
            switch (blockNum) {
                case 12 -> {
                    g.fillRect(x * SQUARE_SIZE + SQUARE_SIZE / 4, y * SQUARE_SIZE + SQUARE_SIZE / 4, SQUARE_SIZE / 2, SQUARE_SIZE / 2);
                }
                case 13 -> {
                    g.fillRect(x * SQUARE_SIZE, y * SQUARE_SIZE + SQUARE_SIZE / 4, SQUARE_SIZE, SQUARE_SIZE / 2);
                }
                case 14 -> {
                    g.fillRect(x * SQUARE_SIZE + SQUARE_SIZE / 4, y * SQUARE_SIZE, SQUARE_SIZE / 2, SQUARE_SIZE);
                }
                case 15 -> {
                    g.fillRect(x * SQUARE_SIZE, y * SQUARE_SIZE + SQUARE_SIZE / 4, SQUARE_SIZE, SQUARE_SIZE / 2);
                    g.fillRect(x * SQUARE_SIZE + SQUARE_SIZE / 4, y * SQUARE_SIZE, SQUARE_SIZE / 2, SQUARE_SIZE);
                }
                case 16 -> {

                }
            }
        }
        


    }

    public static int[] findIndex(int[][] array, int target) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == target) {
                    return new int[]{i, j}; // 값을 찾았으면 위치 반환
                }
            }
        }
        return new int[]{-1, -1}; // 값을 찾지 못했으면 -1, -1 반환
    }
}
