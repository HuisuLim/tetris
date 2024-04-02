package play_screen.panels;

public class ItemTetrisPanel extends TetrisPanel{
    protected int itemGenerateCount = 0;
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
            //아이템블럭효과
        }
        super.checkAndClearLines();
    }

}
