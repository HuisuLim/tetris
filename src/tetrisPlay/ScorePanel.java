package tetrisPlay;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;

public class ScorePanel extends JPanel {
    private int screenRatio;
    private int score;
    private JLabel scoreLabel;

    // Constructor
    public ScorePanel(int screenRatio, int score) {
        this.screenRatio = screenRatio;
        this.score = score;

        this.setLayout(new BorderLayout());
        scoreLabel = new JLabel(String.valueOf(score), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 20 * screenRatio));
        this.add(scoreLabel, BorderLayout.CENTER);

        this.setPreferredSize(new java.awt.Dimension(10 * 20 * screenRatio, 5 * 20 * screenRatio));
    }

    // 점수 업데이트 메서드
    public void updateScore(int newScore) {
        this.score = newScore;
        scoreLabel.setText(String.valueOf(newScore));
    }

    // 메인 함수
    public static void main(String[] args) {
        JFrame frame = new JFrame("Score Panel Test");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ScorePanel 인스턴스 생성
        ScorePanel scorePanel = new ScorePanel(2, 100); // 초기 점수는 100
        frame.add(scorePanel);
        frame.pack();
        frame.setVisible(true);

        // 5초 후 점수 업데이트
        try {
            Thread.sleep(5000); // 5초 대기
            scorePanel.updateScore(200); // 점수를 200으로 업데이트
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
