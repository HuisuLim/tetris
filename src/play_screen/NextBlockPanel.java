package play_screen;

import settings.LoadData;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class NextBlockPanel extends JPanel {
    private int[][] shape;
    int[] colorTable;
    private int screenSize;
    private boolean colorMode;

    public void setProps() {
        LoadData data = new LoadData();
        this.screenSize = data.loadScreenSize();
        this.colorMode = data.loadColorBlindMode();
        this.colorTable = ColorTable.getTable(this.colorMode);
    }

    // Constructor
    public NextBlockPanel(int[][] shape) {
        setProps();
        this.shape = shape;
        this.setPreferredSize(new java.awt.Dimension(10 * 20 * this.screenSize, 5 * 20 * this.screenSize));
    }

    // arr 업데이트 메서드
    public void updateBlock(int[][] shape) {
        this.shape = shape;
        repaint(); // 패널 다시 그리기
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int startX = 4 * 20 * screenSize;
        int startY = 1 * 20 * screenSize;

        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[i].length; j++) {
                if (shape[i][j] != 0) {
                    int color = colorTable[shape[i][j]];
                    g.setColor(new Color(color)); // 속이 빨간색인 사각형
                    g.fillRect(startX + j * 20 * screenSize, startY + i * 20 * screenSize, 20 * screenSize, 20 * screenSize);
                    g.setColor(Color.BLACK); // 테두리는 검은색
                    g.drawRect(startX + j * 20 * screenSize, startY + i * 20 * screenSize, 20 * screenSize, 20 * screenSize);
                }
            }
        }
    }
}
