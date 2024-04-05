package temp;

import javax.swing.*;
import java.awt.*;

public class MyPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 부모 클래스의 paintComponent 메소드 호출

        // 정사각형의 위치와 크기
        int squareX = 10;
        int squareY = 10;
        int squareWidth = 80;
        int squareHeight = 80;

        // 정사각형 그리기
        g.drawRect(squareX, squareY, squareWidth, squareHeight);

        // 문자열 설정
        String text = "I";

        // 폰트 메트릭스를 이용해 문자열의 너비와 높이를 얻음
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getAscent();

        // 문자열을 그릴 위치 계산 (정사각형 중앙에 위치시킴)
        int textX = squareX + (squareWidth - textWidth) / 2;
        int textY = squareY + (squareHeight - textHeight) / 2 + fm.getAscent();

        // 문자열 그리기
        g.drawString(text, textX, textY);
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.add(new MyPanel());
        frame.setSize(200, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}




