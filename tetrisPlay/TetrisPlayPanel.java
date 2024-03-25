package tetrisPlay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class TetrisPlayPanel extends JPanel implements KeyListener {

    private int SQUARE_SIZE = 15;
    private static final int BOARD_WIDTH = 10; // 게임 보드의 가로 칸 수
    private static final int BOARD_HEIGHT = 20; // 게임 보드의 세로 칸 수
    private int[][] board = new int[BOARD_HEIGHT][BOARD_WIDTH]; // 게임 보드를 표현하는 2차원 배열
    private Blocks blocks = new Blocks();
    private int[][][] currentShape;
    private int currentRotate;
    private int currentRow;
    private int currentCol;
    private int shapeLen;
    int[][] colorHex = {
            {0xffffff, 0x00ffff, 0x0000ff, 0xffa500, 0xffff00, 0x00ff00, 0x800080, 0xff0000},//TTC World Standard 색상표 (참고 : https://ko.wikipedia.org/wiki/%ED%85%8C%ED%8A%B8%EB%A6%AC%EC%8A%A4)
            {0xffffff, 0xe1a102, 0x56b4e8, 0x009f73, 0xf0e442, 0x0072b1, 0xd45d00, 0xcc79a6} //색약용 색상표 (참고 : https://nuli.navercorp.com/community/article/1132656)
    };
    int isColorBlindness = 0;

    public void setSquareSize(int screenRatio) {
        SQUARE_SIZE = 15 * screenRatio;
    }

    public void setColorBlindnessMode(boolean checkColorBlindness) {
        isColorBlindness = checkColorBlindness ? 1 : 0;
    }

    public TetrisPlayPanel(int screenRatio, boolean ColorBlindness) {
        setSquareSize(screenRatio);
        setColorBlindnessMode(ColorBlindness);
        setPreferredSize(new Dimension(BOARD_WIDTH * SQUARE_SIZE, BOARD_HEIGHT * SQUARE_SIZE));
        addKeyListener(this);
        setFocusable(true);
        createNewShape();
    }

    private void createNewShape() {
        currentShape = blocks.getRandomBlock();
        currentRotate = 0;
        int[] temp = (currentShape[0].length == 4) ? new int[]{-1, 3} : new int[]{0, 4};
        currentRow = temp[0];
        currentCol = temp[1];
        shapeLen = currentShape[currentRotate].length;
        if (!canMoveTo(currentRow, currentCol)) {
            System.out.println("게임종료");
        }
    }

    private boolean canMoveTo(int targetRow, int targetCol) {
        for (int row = 0; row < shapeLen; row++) {
            for (int col = 0; col < shapeLen; col++) {
                if (currentShape[currentRotate][row][col] == 0) continue;
                if (targetRow + row < 0 || targetRow + row >= BOARD_HEIGHT) return false;
                if (targetCol + col < 0 || targetCol + col >= BOARD_WIDTH) return false;
                if (board[targetRow + row][targetCol + col] != 0) return false;
            }
        }
        return true;
    }

    private boolean canRotate() {
        for (int row = 0; row < shapeLen; row++) {
            for (int col = 0; col < shapeLen; col++) {
                if (currentShape[(currentRotate + 1) % currentShape.length][row][col] == 0) continue;
                if (currentRow + row < 0 || currentRow + row >= BOARD_HEIGHT) return false;
                if (currentCol + col < 0 || currentCol + col >= BOARD_WIDTH) return false;
                if (board[currentRow + row][currentCol + col] != 0) return false;
            }
        }
        return true;
    }

    private void mergeShapeToBoard() {
        for (int row = 0; row < shapeLen; row++) {
            for (int col = 0; col < shapeLen; col++) {
                if (currentShape[currentRotate][row][col] != 0) {
                    board[currentRow + row][currentCol + col] = currentShape[currentRotate][row][col];
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
                int color = colorHex[isColorBlindness][board[row][col]];
                offScreenGraphics.setColor(new Color(color));
                offScreenGraphics.fillRect(col * SQUARE_SIZE, row * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }

        // 현재 도형 그리기
        for (int row = 0; row < shapeLen; row++) {
            for (int col = 0; col < shapeLen; col++) {
                if (currentShape[currentRotate][row][col] != 0) {
                    int color = colorHex[isColorBlindness][currentShape[currentRotate][row][col]];
                    offScreenGraphics.setColor(new Color(color));
                    offScreenGraphics.fillRect((currentCol + col) * SQUARE_SIZE, (currentRow + row) * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }

        // 화면에 오프스크린 이미지를 복사
        g.drawImage(offScreenImage, 0, 0, this);

        // 자원 정리
        offScreenGraphics.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        ((JComponent) e.getSource()).requestFocus();
        if (keyCode == KeyEvent.VK_LEFT) {// 왼쪽 화살표 키 처리
            if (canMoveTo(currentRow, currentCol - 1)) {
                currentCol--;
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {// 오른쪽 화살표 키 처리
            if (canMoveTo(currentRow, currentCol + 1)) {
                currentCol++;
            }
        } else if (keyCode == KeyEvent.VK_UP) {
            if (canRotate()) {
                currentRotate = (currentRotate + 1) % currentShape.length;
            }
        } else if (keyCode == KeyEvent.VK_DOWN) {// 아래 화살표 키 처리
            if (canMoveTo(currentRow + 1, currentCol)) {
                currentRow++;
            } else {
                mergeShapeToBoard();
                checkAndClearLines();
                createNewShape();
            }
        }

        repaint(); // 화면 갱신
    }

    //아래 메서드는 구현되지 않았지만 KeyListener 인터페이스를 구현하기 위해 필요함
    @Override
    public void keyReleased(KeyEvent e) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("테트리스 게임");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 800);
            frame.setLocationRelativeTo(null);

            TetrisPlayPanel tetris = new TetrisPlayPanel(1, true);
            frame.add(tetris);

            frame.setVisible(true);
        });
    }
}


