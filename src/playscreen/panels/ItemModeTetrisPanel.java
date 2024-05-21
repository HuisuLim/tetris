package playscreen.panels;

import playscreen.utils.GameOverCallBack;
import settings.settingModel;

import java.awt.*;

public class ItemModeTetrisPanel extends TetrisPanel {
    protected int itemGenerateCount = -100;
    protected boolean weightBlockCanMove;

    public ItemModeTetrisPanel(GameOverCallBack gameOverCallBack, settingModel data) {
        super(gameOverCallBack, data);
    }
    public ItemModeTetrisPanel(GameOverCallBack gameOverCallBack, settingModel data, LineRemovePanel lineInputPanel, LineRemovePanel lineOutputPanel) {
        super(gameOverCallBack, data);
    }

    @Override
    public void createNewShape() {
        if(lineRemoveCount/10 > itemGenerateCount) {
            itemGenerateCount++;
            currBlock = nextBlock;
            nextBlock = generator.getRandomItemBlock();
            if (nextBlock.getBlockNum() == 11) weightBlockCanMove = true;
            int[] temp = currBlock.getStartPos();
            currentRow = temp[0];
            currentCol = temp[1];
            if(!canMoveTo(currentRow, currentCol, currBlock.getShape())){
                isGameOver = true;
                System.out.println("게임종료");
            }
        }
        else super.createNewShape();
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
    public void mergeShapeToBoard() {
        if (currBlock.getBlockNum() == 11) return;
        super.mergeShapeToBoard();
    }

    @Override
    public boolean checkLines() {
        if (currBlock.getBlockNum() > 11) {
            int[] itemIndex = findIndex(board, currBlock.getBlockNum());
            switch (currBlock.getBlockNum()) {
                case 12 -> {
                    for (int row = itemIndex[0] - 2; row <= itemIndex[0] + 2; row++) {
                        for (int col = itemIndex[1] - 2; col <= itemIndex[1] + 2; col++) {
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
            }
            score+= (int) (1000 * scoreMultiplier);
        }
        return super.checkLines();
    }

    @Override
    protected void drawSquare(Graphics g, int x, int y, int blockNum) {
        super.drawSquare(g, x, y, blockNum % 10);

        if (blockNum > 10) {
            int colorCode = colorTable[blockNum % 10];
            Color color = new Color(colorCode);
            g.setColor(color);
            switch (blockNum) {
                case 11 -> {
                    g.setColor(Color.white);
                    g.fillRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    g.setColor(color);
                    g.fillOval(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE); // 원 채우기
                }
                case 12 -> {

                    g.fillRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    g.setColor(Color.black);
                    g.fillRect(x * SQUARE_SIZE + SQUARE_SIZE / 4, y * SQUARE_SIZE + SQUARE_SIZE / 4, SQUARE_SIZE / 2, SQUARE_SIZE / 2);
                }
                case 13 -> {

                    g.fillRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    g.setColor(Color.black);
                    // 화살표의 몸통을 그립니다. (가운데 부분)
                    g.fillRect(x * SQUARE_SIZE + SQUARE_SIZE / 4, y * SQUARE_SIZE + SQUARE_SIZE / 4, SQUARE_SIZE / 2, SQUARE_SIZE / 2);

                    // 위쪽 화살표 머리의 좌표 배열
                    int[] xPointsLeft = {
                            x * SQUARE_SIZE,
                            x * SQUARE_SIZE + SQUARE_SIZE / 4,
                            x * SQUARE_SIZE + SQUARE_SIZE / 4
                    };
                    int[] yPointsLeft = {
                            y* SQUARE_SIZE + SQUARE_SIZE / 2,
                            y * SQUARE_SIZE,
                            y * SQUARE_SIZE + SQUARE_SIZE
                    };

                    // 아래쪽 화살표 머리의 좌표 배열
                    int[] xPointsRight = {
                            x * SQUARE_SIZE + SQUARE_SIZE ,
                            x * SQUARE_SIZE + SQUARE_SIZE*3/4,
                            x * SQUARE_SIZE + SQUARE_SIZE*3/4
                    };
                    int[] yPointsRight = {
                            y * SQUARE_SIZE + SQUARE_SIZE / 2,
                            y * SQUARE_SIZE,
                            y * SQUARE_SIZE + SQUARE_SIZE
                    };

                    // 위쪽 화살표 머리를 그립니다.
                    g.fillPolygon(xPointsLeft, yPointsLeft, 3);

                    // 아래쪽 화살표 머리를 그립니다.
                    g.fillPolygon(xPointsRight, yPointsRight, 3);
                }
                case 14 -> {
                    g.fillRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    g.setColor(Color.black);
                    // 화살표의 몸통을 그립니다. (가운데 부분)
                    g.fillRect(x * SQUARE_SIZE + SQUARE_SIZE / 4, y * SQUARE_SIZE + SQUARE_SIZE / 4, SQUARE_SIZE / 2, SQUARE_SIZE / 2);

                    // 위쪽 화살표 머리의 좌표 배열
                    int[] xPointsUp = {
                            x * SQUARE_SIZE + SQUARE_SIZE / 2,
                            x * SQUARE_SIZE,
                            x * SQUARE_SIZE + SQUARE_SIZE
                    };
                    int[] yPointsUp = {
                            y * SQUARE_SIZE,
                            y * SQUARE_SIZE + SQUARE_SIZE / 4,
                            y * SQUARE_SIZE + SQUARE_SIZE / 4
                    };

                    // 아래쪽 화살표 머리의 좌표 배열
                    int[] xPointsDown = {
                            x * SQUARE_SIZE + SQUARE_SIZE / 2,
                            x * SQUARE_SIZE,
                            x * SQUARE_SIZE + SQUARE_SIZE
                    };
                    int[] yPointsDown = {
                            y * SQUARE_SIZE + SQUARE_SIZE ,
                            y * SQUARE_SIZE + SQUARE_SIZE*3/4,
                            y * SQUARE_SIZE + SQUARE_SIZE*3/4
                    };

                    // 위쪽 화살표 머리를 그립니다.
                    g.fillPolygon(xPointsUp, yPointsUp, 3);

                    // 아래쪽 화살표 머리를 그립니다.
                    g.fillPolygon(xPointsDown, yPointsDown, 3);
                }
                case 15 -> {
                    g.fillRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    g.setColor(Color.black);
                    // 화살표의 몸통을 그립니다. (가운데 부분)
                    g.fillRect(x * SQUARE_SIZE + SQUARE_SIZE/8, y * SQUARE_SIZE + SQUARE_SIZE *7/ 16, SQUARE_SIZE * 3 / 4, SQUARE_SIZE / 8);

                    // 위쪽 화살표 머리의 좌표 배열
                    int[] xPointsLeft = {
                            x * SQUARE_SIZE,
                            x * SQUARE_SIZE + SQUARE_SIZE / 8,
                            x * SQUARE_SIZE + SQUARE_SIZE / 8
                    };
                    int[] yPointsLeft = {
                            y* SQUARE_SIZE + SQUARE_SIZE / 2,
                            y * SQUARE_SIZE + SQUARE_SIZE /4,
                            y * SQUARE_SIZE + SQUARE_SIZE *3/4
                    };

                    // 아래쪽 화살표 머리의 좌표 배열
                    int[] xPointsRight = {
                            x * SQUARE_SIZE + SQUARE_SIZE ,
                            x * SQUARE_SIZE + SQUARE_SIZE*7/8,
                            x * SQUARE_SIZE + SQUARE_SIZE*7/8
                    };
                    int[] yPointsRight = {
                            y* SQUARE_SIZE + SQUARE_SIZE / 2,
                            y * SQUARE_SIZE + SQUARE_SIZE /4,
                            y * SQUARE_SIZE + SQUARE_SIZE *3/4
                    };

                    // 위쪽 화살표 머리를 그립니다.
                    g.fillPolygon(xPointsLeft, yPointsLeft, 3);

                    // 아래쪽 화살표 머리를 그립니다.
                    g.fillPolygon(xPointsRight, yPointsRight, 3);

                    // 화살표의 몸통을 그립니다. (가운데 부분)
                    g.fillRect(x * SQUARE_SIZE + SQUARE_SIZE * 7 / 16, y * SQUARE_SIZE + SQUARE_SIZE / 8, SQUARE_SIZE / 8, SQUARE_SIZE * 3 / 4);

                    // 위쪽 화살표 머리의 좌표 배열
                    int[] xPointsUp = {
                            x * SQUARE_SIZE + SQUARE_SIZE / 2,
                            x * SQUARE_SIZE + SQUARE_SIZE /4,
                            x * SQUARE_SIZE + SQUARE_SIZE *3 /4
                    };
                    int[] yPointsUp = {
                            y * SQUARE_SIZE,
                            y * SQUARE_SIZE + SQUARE_SIZE / 8,
                            y * SQUARE_SIZE + SQUARE_SIZE / 8
                    };

                    // 아래쪽 화살표 머리의 좌표 배열
                    int[] xPointsDown = {
                            x * SQUARE_SIZE + SQUARE_SIZE / 2,
                            x * SQUARE_SIZE + SQUARE_SIZE /4,
                            x * SQUARE_SIZE + SQUARE_SIZE *3/4
                    };
                    int[] yPointsDown = {
                            y * SQUARE_SIZE + SQUARE_SIZE ,
                            y * SQUARE_SIZE + SQUARE_SIZE*7/8,
                            y * SQUARE_SIZE + SQUARE_SIZE*7/8
                    };

                    // 위쪽 화살표 머리를 그립니다.
                    g.fillPolygon(xPointsUp, yPointsUp, 3);

                    // 아래쪽 화살표 머리를 그립니다.
                    g.fillPolygon(xPointsDown, yPointsDown, 3);
                }
                case 16 -> {
                    g.setColor(Color.BLACK);
                    g.fillRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    g.setColor(color);
                    g.fillOval(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE); // 원 채우기
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
