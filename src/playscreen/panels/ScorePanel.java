package playscreen.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;

public class ScorePanel extends JPanel {
    private int score;
    private final JLabel scoreLabel;

    // Constructor
    public ScorePanel(double screenSize, int score) {
        this.score = score;
        this.setLayout(new BorderLayout());
        
        scoreLabel = new JLabel(String.valueOf(score), SwingConstants.CENTER);
        scoreLabel.setFont(new Font("Serif", Font.BOLD, (int)(20 * screenSize)));
        this.add(scoreLabel, BorderLayout.CENTER);

        this.setPreferredSize(new java.awt.Dimension((int)(10 * 20 * screenSize), (int)(5 * 20 * screenSize)));
    }

    // 점수 업데이트 메서드
    public void updateScore(int newScore) {
        this.score = newScore;
        scoreLabel.setText(String.valueOf(newScore));
    }
}
