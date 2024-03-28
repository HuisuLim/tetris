package play_screen;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

public class NextBlockPanel extends JPanel {
    private int screenRatio;
    private int[][] arr;
    int[][] colorHex = {
            {0xffffff,0x00ffff,0x0000ff,0xffa500,0xffff00,0x00ff00,0x800080,0xff0000},//TTC World Standard 색상표 (참고 : https://ko.wikipedia.org/wiki/%ED%85%8C%ED%8A%B8%EB%A6%AC%EC%8A%A4)
            {0xffffff,0xe1a102,0x56b4e8,0x009f73,0xf0e442,0x0072b1,0xd45d00,0xcc79a6} //색약용 색상표 (참고 : https://nuli.navercorp.com/community/article/1132656)
    };//색상들을 16진수로 저장. 후에 new Color(colorHex[isColorBlindness][i]) 형식으로 이용
    private int isColorBlindness = 0;

    // Constructor
    public NextBlockPanel(int screenRatio, int[][] arr) {
        this.screenRatio = screenRatio;
        this.arr = arr;
        this.setPreferredSize(new java.awt.Dimension(10 * 20 * screenRatio, 5 * 20 * screenRatio));
    }

    // arr 업데이트 메서드
    public void updateBlock(int[][] newArr) {
        this.arr = newArr;
        repaint(); // 패널 다시 그리기
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int startX = 4 * 20 * screenRatio;
        int startY = 1 * 20 * screenRatio;

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    int color = colorHex[isColorBlindness][arr[i][j]];
                    g.setColor(new Color(color)); // 속이 빨간색인 사각형
                    g.fillRect(startX + j * 20 * screenRatio, startY + i * 20 * screenRatio, 20 * screenRatio, 20 * screenRatio);
                    g.setColor(Color.BLACK); // 테두리는 검은색
                    g.drawRect(startX + j * 20 * screenRatio, startY + i * 20 * screenRatio, 20 * screenRatio, 20 * screenRatio);
                }
            }
        }
    }

    // 메인 함수
    public static void main(String[] args) {
        javax.swing.JFrame frame = new javax.swing.JFrame("Next Block Test");
        frame.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);

        // 테스트 배열
        int[][] testArr = {
                {1, 1},
                {1, 1}
        };

        NextBlockPanel panel = new NextBlockPanel(2, testArr);
        frame.add(panel);
        frame.pack(); // 패널의 선호 사이즈에 맞게 프레임 크기 조정
        frame.setVisible(true); // 프레임 보이게 설정
    }
}
