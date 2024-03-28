package tetrisPlay;

import javax.swing.*;
import java.awt.*;

public class SplitFrame extends JFrame {
    private int count = 0; // 숫자를 증가시키기 위한 변수
    private JLabel numberLabel; // 숫자를 표시할 레이블
    private Timer timer;
    private int delay = 1000; // 초기 딜레이는 1000ms (1초)

    public SplitFrame() {
        setTitle("Accelerating Timer Example");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2)); // 전체 프레임을 2개의 컬럼으로 분할

        // 왼쪽 빨간색 패널
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        add(redPanel);

        // 오른쪽 패널 (세로로 4등분)
        JPanel rightPanel = new JPanel(new GridLayout(4, 1));

        // 숫자가 증가하는 패널
        JPanel numberPanel = new JPanel(new BorderLayout());
        numberLabel = new JLabel("0", SwingConstants.CENTER);
        numberLabel.setFont(new Font("Serif", Font.BOLD, 30));
        numberPanel.add(numberLabel, BorderLayout.CENTER);
        rightPanel.add(numberPanel);

        add(rightPanel);

        // 타이머 설정
        timer = new Timer(delay, e -> updateCounter());
        timer.start();

        setVisible(true);
    }

    private void updateCounter() {
        count++;
        numberLabel.setText(String.valueOf(count));

        // 딜레이 감소 로직 (예: 현재 딜레이의 95%를 적용)
        delay = (int)(delay * 0.95);
        timer.setDelay(delay);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SplitFrame::new);
    }
}
