package playscreen.panels;

import playscreen.utils.ColorTable;
import settings.settingModel;

import javax.swing.*;
import java.awt.*;

public class ItemShowPanel extends JPanel {

        private final settingModel data;
        private final int SQUARE_SIZE;
        protected final int[] colorTable;
        public ItemShowPanel(settingModel data) {
            this.data = data;
            SQUARE_SIZE = (int)(10 * data.screenSize);
            colorTable = ColorTable.getTable(data.colorBlindMode);
            this.setPreferredSize(new java.awt.Dimension((int)(10 * 20 * data.screenSize), (int)(7 * 20 * data.screenSize)));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(new Font("Arial", Font.PLAIN, (int)(data.screenSize*6)));
            // 테스트를 위해 blockNum 값들을 다양하게 설정하여 drawSquare 메서드 호출
            drawSquare(g, 2, 1, 11);
            drawSquare(g, 3, 1, 11);
            drawSquare(g, 1, 2, 11);
            drawSquare(g, 2, 2, 11);
            drawSquare(g, 3, 2, 11);
            drawSquare(g, 4, 2, 11);
            drawSquare(g, 2, 4, 12);
            drawSquare(g, 2, 6, 13);
            drawSquare(g, 2, 8, 14);
            drawSquare(g, 2, 10, 15);
            drawSquare(g, 2, 12, 16);
            // 여기에 더 많은 drawSquare 호출을 추가할 수 있습니다.
        }

        protected void drawSquare(Graphics g, int x, int y, int blockNum) {
            if(blockNum == 11 && x ==2 && y ==1){
                g.drawString("destroy under weight block", x * SQUARE_SIZE + SQUARE_SIZE*4, y * SQUARE_SIZE + SQUARE_SIZE);
            }
            if (blockNum > 10) {
                int colorCode = colorTable[blockNum % 10];
                Color color = new Color(colorCode);
                g.setColor(color);
                final int y1 = y * SQUARE_SIZE + SQUARE_SIZE / 4 * 3;
                final int x1 = x * SQUARE_SIZE + SQUARE_SIZE * 3 / 4;
                final int y2 = y * SQUARE_SIZE + SQUARE_SIZE * 3 / 4;
                switch (blockNum) {
                    case 11 -> g.fillOval(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE); // 원 채우기
                    case 12 -> {
                        g.fillRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                        g.setColor(Color.black);
                        g.fillRect(x * SQUARE_SIZE + SQUARE_SIZE / 4, y * SQUARE_SIZE + SQUARE_SIZE / 4, SQUARE_SIZE / 2, SQUARE_SIZE / 2);
                        g.drawString("block shape destroy", x * SQUARE_SIZE + SQUARE_SIZE*4, y1);
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
                                x1,
                                x1
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
                        g.drawString("horizontal line destroy", x * SQUARE_SIZE + SQUARE_SIZE*4, y1);
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
                                y2,
                                y2
                        };

                        // 위쪽 화살표 머리를 그립니다.
                        g.fillPolygon(xPointsUp, yPointsUp, 3);

                        // 아래쪽 화살표 머리를 그립니다.
                        g.fillPolygon(xPointsDown, yPointsDown, 3);
                        g.drawString("vertical line destroy", x * SQUARE_SIZE + SQUARE_SIZE*4, y1);
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
                                y * SQUARE_SIZE + SQUARE_SIZE / 4,
                                y2
                        };

                        // 아래쪽 화살표 머리의 좌표 배열
                        int x2 = x * SQUARE_SIZE + SQUARE_SIZE * 7 / 8;
                        int[] xPointsRight = {
                                x * SQUARE_SIZE + SQUARE_SIZE ,
                                x2,
                                x2
                        };
                        int[] yPointsRight = {
                                y* SQUARE_SIZE + SQUARE_SIZE / 2,
                                y * SQUARE_SIZE + SQUARE_SIZE /4,
                                y2
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
                                x1
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
                                x1
                        };
                        int y3 = y * SQUARE_SIZE + SQUARE_SIZE * 7 / 8;
                        int[] yPointsDown = {
                                y * SQUARE_SIZE + SQUARE_SIZE ,
                                y3,
                                y3
                        };

                        // 위쪽 화살표 머리를 그립니다.
                        g.fillPolygon(xPointsUp, yPointsUp, 3);

                        // 아래쪽 화살표 머리를 그립니다.
                        g.fillPolygon(xPointsDown, yPointsDown, 3);

                        g.drawString("cross line destroy", x * SQUARE_SIZE + SQUARE_SIZE*4, y1);
                    }
                    case 16 -> {
                        g.setColor(Color.black);
                        g.fillRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                        g.setColor(color);
                        g.fillOval(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE); // 원 채우기
                        g.setColor(Color.black);
                        g.drawString("all block destroy", x * SQUARE_SIZE + SQUARE_SIZE*4, y1);
                    }
                }
            }
        }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Tetris Game Example");
            ItemShowPanel panel = new ItemShowPanel(new settingModel());

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(panel);
            frame.pack(); // 컴포넌트에 맞게 프레임 크기 조절
            frame.setLocationRelativeTo(null); // 화면 가운데에 프레임 위치
            frame.setVisible(true); // 프레임을 보이게 설정
        });
    }

}
