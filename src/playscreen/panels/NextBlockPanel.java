package playscreen.panels;

import playscreen.utils.ColorTable;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class NextBlockPanel extends JPanel {
    private int[][] shape;
    private final int[] colorTable;
    private final double screenSize;

    // Constructor
    public NextBlockPanel(double screenSize, boolean colorMode) {
        this.screenSize = screenSize;
        this.colorTable = ColorTable.getTable(colorMode);
        this.shape = new int[][] {{0}};
        this.setPreferredSize(new java.awt.Dimension((int)(10 * 20 * this.screenSize), (int)(5 * 20 * this.screenSize)));
    }

    // arr 업데이트 메서드
    public void updateBlock(int[][] shape) {
        this.shape = shape;
        repaint(); // 패널 다시 그리기
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int startX = (int)(4 * 20 * screenSize);
        int startY = (int)(1 * 20 * screenSize);
        for (int[] ints : shape) {
            for (int anInt : ints) {
                if (anInt == 1) {
                    startX = (int) (2 * 20 * screenSize);
                    startY = 0;
                    break;
                }
            }
        }
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    if (shape[i][j] < 10) {
                        int color = colorTable[shape[i][j] % 10];
                        g.setColor(new Color(color)); // 속이 빨간색인 사각형
                        g.fillRect((int) (startX + j * 20 * screenSize), (int) (startY + i * 20 * screenSize), (int) (20 * screenSize), (int) (20 * screenSize));
                        g.setColor(Color.BLACK); // 테두리는 검은색
                        g.drawRect((int) (startX + j * 20 * screenSize), (int) (startY + i * 20 * screenSize), (int) (20 * screenSize), (int) (20 * screenSize));
                        g.setColor(new Color(color));
                    } else {
                        int SQUARE_SIZE = (int) (screenSize * 20);
                        int colorCode = colorTable[shape[i][j] % 10];
                        Color color = new Color(colorCode);
                        g.setColor(color);
                        final int x = startX + j * SQUARE_SIZE + SQUARE_SIZE / 4;
                        final int x1 = startX + j * SQUARE_SIZE + SQUARE_SIZE * 3 / 4;
                        final int y = startY + i * SQUARE_SIZE + SQUARE_SIZE / 4;
                        final int y1 = startY + i * SQUARE_SIZE + SQUARE_SIZE * 3 / 4;
                        switch (shape[i][j]) {
                            case 11 -> {
                                startX = (int)(3 * 20 * screenSize);
                                startY = (int)((-1.5)*10*screenSize);
                                g.setColor(Color.white);
                                g.fillRect(startX + j  * SQUARE_SIZE, startY + i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                                g.setColor(color);
                                g.fillOval(startX + j * SQUARE_SIZE, startY + i  * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE); // 원 채우기
                            }
                            case 12 -> {
                                g.fillRect(startX + j * SQUARE_SIZE, startY + i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                                g.setColor(Color.black);
                                g.fillRect(x, y, SQUARE_SIZE / 2, SQUARE_SIZE / 2);
                            }
                            case 13 -> {
                                g.fillRect(startX + j * SQUARE_SIZE, startY + i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                                g.setColor(Color.black);
                                // 화살표의 몸통을 그립니다. (가운데 부분)
                                g.fillRect(x, y, SQUARE_SIZE / 2, SQUARE_SIZE / 2);
                                // 위쪽 화살표 머리의 좌표 배열
                                int[] xPointsLeft = {
                                        startX + j  * SQUARE_SIZE,
                                        x,
                                        x
                                };
                                int[] yPointsLeft = {
                                        startY + i  * SQUARE_SIZE + SQUARE_SIZE / 2,
                                        startY + i  * SQUARE_SIZE,
                                        startY + i  * SQUARE_SIZE + SQUARE_SIZE
                                };

                                // 아래쪽 화살표 머리의 좌표 배열
                                int[] xPointsRight = {
                                        startX + j * SQUARE_SIZE + SQUARE_SIZE,
                                        x1,
                                        x1
                                };
                                int[] yPointsRight = {
                                        startY + i * SQUARE_SIZE + SQUARE_SIZE / 2,
                                        startY + i * SQUARE_SIZE,
                                        startY + i * SQUARE_SIZE + SQUARE_SIZE
                                };

                                // 위쪽 화살표 머리를 그립니다.
                                g.fillPolygon(xPointsLeft, yPointsLeft, 3);

                                // 아래쪽 화살표 머리를 그립니다.
                                g.fillPolygon(xPointsRight, yPointsRight, 3);
                            }
                            case 14 -> {
                                g.fillRect(startX+j * SQUARE_SIZE, startY + i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                                g.setColor(Color.black);
                                // 화살표의 몸통을 그립니다. (가운데 부분)
                                g.fillRect(x, y, SQUARE_SIZE / 2, SQUARE_SIZE / 2);

                                // 위쪽 화살표 머리의 좌표 배열
                                int[] xPointsUp = {
                                        startX + j * SQUARE_SIZE + SQUARE_SIZE / 2,
                                        startX + j * SQUARE_SIZE,
                                        startX + j * SQUARE_SIZE + SQUARE_SIZE
                                };
                                int[] yPointsUp = {
                                        startY + i * SQUARE_SIZE,
                                        y,
                                        y
                                };

                                // 아래쪽 화살표 머리의 좌표 배열
                                int[] xPointsDown = {
                                        startX + j * SQUARE_SIZE + SQUARE_SIZE / 2,
                                        startX + j * SQUARE_SIZE,
                                        startX + j * SQUARE_SIZE + SQUARE_SIZE
                                };
                                int[] yPointsDown = {
                                        startY + i * SQUARE_SIZE + SQUARE_SIZE,
                                        y1,
                                        y1
                                };

                                // 위쪽 화살표 머리를 그립니다.
                                g.fillPolygon(xPointsUp, yPointsUp, 3);

                                // 아래쪽 화살표 머리를 그립니다.
                                g.fillPolygon(xPointsDown, yPointsDown, 3);
                            }
                            case 15 -> {
                                g.fillRect(startX + j * SQUARE_SIZE, startY + i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                                g.setColor(Color.black);
                                // 화살표의 몸통을 그립니다. (가운데 부분)
                                int x2 = startX + j * SQUARE_SIZE + SQUARE_SIZE / 8;
                                g.fillRect(x2, startY + i * SQUARE_SIZE + SQUARE_SIZE * 7 / 16, SQUARE_SIZE * 3 / 4, SQUARE_SIZE / 8);

                                // 위쪽 화살표 머리의 좌표 배열
                                int[] xPointsLeft = {
                                        startX + j * SQUARE_SIZE,
                                        x2,
                                        x2
                                };
                                int[] yPointsLeft = {
                                        startY + i * SQUARE_SIZE + SQUARE_SIZE / 2,
                                        y,
                                        y1
                                };

                                // 아래쪽 화살표 머리의 좌표 배열
                                int x3 = startX + j * SQUARE_SIZE + SQUARE_SIZE * 7 / 8;
                                int[] xPointsRight = {
                                        startX + j * SQUARE_SIZE + SQUARE_SIZE,
                                        x3,
                                        x3
                                };
                                int[] yPointsRight = {
                                        startY + i * SQUARE_SIZE + SQUARE_SIZE / 2,
                                        y,
                                        y1
                                };

                                // 위쪽 화살표 머리를 그립니다.
                                g.fillPolygon(xPointsLeft, yPointsLeft, 3);

                                // 아래쪽 화살표 머리를 그립니다.
                                g.fillPolygon(xPointsRight, yPointsRight, 3);

                                // 화살표의 몸통을 그립니다. (가운데 부분)
                                int y3 = startY + i * SQUARE_SIZE + SQUARE_SIZE / 8;
                                g.fillRect(startX + j * SQUARE_SIZE + SQUARE_SIZE * 7 / 16, y3, SQUARE_SIZE / 8, SQUARE_SIZE * 3 / 4);
                                // 위쪽 화살표 머리의 좌표 배열
                                int[] xPointsUp = {
                                        startX + j * SQUARE_SIZE + SQUARE_SIZE / 2,
                                        x,
                                        x1
                                };
                                int[] yPointsUp = {
                                        startY + i * SQUARE_SIZE,
                                        y3,
                                        y3
                                };

                                // 아래쪽 화살표 머리의 좌표 배열
                                int[] xPointsDown = {
                                        startX + j * SQUARE_SIZE + SQUARE_SIZE / 2,
                                        x,
                                        x1
                                };
                                int y4 = startY + i * SQUARE_SIZE + SQUARE_SIZE * 7 / 8;
                                int[] yPointsDown = {
                                        startY + i * SQUARE_SIZE + SQUARE_SIZE,
                                        y4,
                                        y4
                                };

                                // 위쪽 화살표 머리를 그립니다.
                                g.fillPolygon(xPointsUp, yPointsUp, 3);

                                // 아래쪽 화살표 머리를 그립니다.
                                g.fillPolygon(xPointsDown, yPointsDown, 3);
                            }
                            case 16 -> {
                                startX = (int)(4.5 * 20 * screenSize);
                                startY = (int)((2)*10*screenSize);
                                g.setColor(Color.BLACK);
                                g.fillRect(startX + j * SQUARE_SIZE, startY + i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                                g.setColor(color);
                                g.fillOval(startX + j * SQUARE_SIZE, startY + i * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE); // 원 채우기
                            }
                        }
                    }
                }

            }
        }
    }
}