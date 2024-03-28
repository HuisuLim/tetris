package tetrisPlay;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DynamicTimerExample extends JFrame {
    private int score = 0;
    private JLabel label;
    private Timer timer;

    public DynamicTimerExample() {
        // JFrame 설정
        setTitle("Dynamic Timer Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // 점수를 표시할 JLabel 설정
        label = new JLabel("0", JLabel.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 40));
        add(label);

        // 타이머 생성 및 시작
        createTimer();
        timer.start();
    }

    private void createTimer() {
        // ActionListener 정의: 점수를 업데이트하고 타이머의 지연 시간 조정
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score += 10; // 점수 10 증가
                label.setText(String.valueOf(score)); // 레이블 업데이트

                // 타이머의 지연 시간 조정
                timer.setDelay(1000 - score);
            }
        };

        // 타이머 생성: 초기 지연 시간은 1000ms
        timer = new Timer(1000, actionListener);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DynamicTimerExample().setVisible(true);
            }
        });
    }
}
