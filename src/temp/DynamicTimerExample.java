package temp;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import playscreen.utils.TimerDelay; // 난이도에 따른 타이머 지연 시간 계산을 위해 추가
import settings.LoadData; // 설정 정보를 가져오기 위해 추가

public class DynamicTimerExample extends JFrame {
    private int score = 0;
    private JLabel label;
    private Timer timer;
    private LoadData loadData; // 설정 정보를 로드하기 위한 인스턴스

    public DynamicTimerExample() {
        loadData = new LoadData();
        String difficulty = loadData.loadDifficulty(); // 난이도 로드

        setTitle("Dynamic Timer Example");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        label = new JLabel("0", JLabel.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 40));
        add(label);

        createTimer(difficulty); // 난이도에 따라 타이머 생성
        timer.start();
    }

    private void createTimer(String difficulty) {
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score += 10; // 점수 증가
                label.setText(String.valueOf(score)); // 레이블 업데이트

                // 타이머의 지연 시간을 난이도와 점수에 따라 조정
                int delay = TimerDelay.calDelay(difficulty, score);
                timer.setDelay(delay);
            }
        };

        // 난이도와 초기 점수를 기반으로 초기 지연 시간 설정
        int initialDelay = TimerDelay.calDelay(difficulty, score);
        timer = new Timer(initialDelay, actionListener);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new DynamicTimerExample().setVisible(true));
    }
}
