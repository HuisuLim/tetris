package playscreen.panels;

import playscreen.utils.ColorTable;
import settings.LoadData;

import javax.swing.*;
import java.awt.*;

public class ItemShowPanel extends JPanel {

        protected final LoadData data = new LoadData();
        private double screenSize = data.loadScreenSize();
        protected final int SQUARE_SIZE = (int)(10 * screenSize);
        protected int[] colorTable = ColorTable.getTable(data.loadColorBlindMode());
        public ItemShowPanel() {
            this.setPreferredSize(new java.awt.Dimension((int)(10 * 20 * screenSize), (int)(6 * 20 * screenSize)));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setFont(new Font("Arial", Font.PLAIN, (int)(screenSize*6)));
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
            // 여기에 더 많은 drawSquare 호출을 추가할 수 있습니다.
        }

        protected void drawSquare(Graphics g, int x, int y, int blockNum) {
            if(blockNum == 11 && x ==2 && y ==1){
                g.drawString("무게추에 닿은 아래 블럭 전부 파괴", x * SQUARE_SIZE + SQUARE_SIZE*4, y * SQUARE_SIZE + SQUARE_SIZE);
            }
            if (blockNum > 10) {
                int colorCode = colorTable[blockNum % 10];
                Color color = new Color(colorCode);
                g.setColor(color);
                g.fillRect(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                g.setColor(Color.white);
                g.fillOval(x * SQUARE_SIZE, y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE); // 원 채우기
                g.setColor(Color.black);
                switch (blockNum) {
                    case 12 -> {
                        g.fillRect(x * SQUARE_SIZE + SQUARE_SIZE / 4, y * SQUARE_SIZE + SQUARE_SIZE / 4, SQUARE_SIZE / 2, SQUARE_SIZE / 2);
                        g.drawString("파괴", x * SQUARE_SIZE + SQUARE_SIZE*4, y * SQUARE_SIZE + SQUARE_SIZE / 4 * 3);
                    }
                    case 13 -> {
                        g.fillRect(x * SQUARE_SIZE, y * SQUARE_SIZE + SQUARE_SIZE / 4, SQUARE_SIZE, SQUARE_SIZE / 2);
                        g.drawString("파괴", x * SQUARE_SIZE + SQUARE_SIZE*4, y * SQUARE_SIZE + SQUARE_SIZE / 4 * 3);
                    }
                    case 14 -> {
                        g.fillRect(x * SQUARE_SIZE + SQUARE_SIZE / 4, y * SQUARE_SIZE, SQUARE_SIZE / 2, SQUARE_SIZE);
                        g.drawString("파괴", x * SQUARE_SIZE + SQUARE_SIZE*4, y * SQUARE_SIZE + SQUARE_SIZE / 4 * 3);
                    }
                    case 15 -> {
                        g.fillRect(x * SQUARE_SIZE, y * SQUARE_SIZE + SQUARE_SIZE / 4, SQUARE_SIZE, SQUARE_SIZE / 2);
                        g.fillRect(x * SQUARE_SIZE + SQUARE_SIZE / 4, y * SQUARE_SIZE, SQUARE_SIZE / 2, SQUARE_SIZE);
                        g.drawString("파괴", x * SQUARE_SIZE + SQUARE_SIZE*4, y * SQUARE_SIZE + SQUARE_SIZE / 4 * 3);
                    }
                    case 16 -> {
                        // 16의 경우는 추가적인 그래픽 요소가 없음
                        g.drawString("파괴", x * SQUARE_SIZE + SQUARE_SIZE, y * SQUARE_SIZE + SQUARE_SIZE / 2);
                    }
                }
            }
        }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            public void run() {
                JFrame frame = new JFrame("Tetris Game Example");
                ItemShowPanel panel = new ItemShowPanel();

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.add(panel);
                frame.pack(); // 컴포넌트에 맞게 프레임 크기 조절
                frame.setLocationRelativeTo(null); // 화면 가운데에 프레임 위치
                frame.setVisible(true); // 프레임을 보이게 설정
            }
        });
    }

}
