package play_screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PausePanel extends JPanel {
    private int screenRatio;
    private int currPoint = 0; // 현재 포인터 위치 (0: RESUME, 1: EXIT)
    private JLabel resumeLabel;
    private JLabel exitLabel;

    public PausePanel(int screenRatio) {
        this.screenRatio = screenRatio;
        setSize(150 * screenRatio, 100 * screenRatio);
        setBackground(Color.BLACK);
        setLayout(new GridLayout(3, 1));

        // PAUSE 라벨 추가
        JLabel pauseLabel = new JLabel("PAUSE", SwingConstants.CENTER);
        pauseLabel.setForeground(Color.WHITE);
        pauseLabel.setFont(new Font("Serif", Font.BOLD, 12 * screenRatio));
        add(pauseLabel);

        // 파란색 박스 패널 추가
        JPanel blueBoxPanel = new JPanel();
        blueBoxPanel.setLayout(new GridLayout(2, 1));
        blueBoxPanel.setBackground(Color.BLUE);

        // RESUME 라벨 추가
        resumeLabel = new JLabel("RESUME", SwingConstants.CENTER);
        resumeLabel.setForeground(Color.WHITE);
        resumeLabel.setFont(new Font("Serif", Font.BOLD, 10 * screenRatio));
        blueBoxPanel.add(resumeLabel);

        // EXIT 라벨 추가
        exitLabel = new JLabel("EXIT", SwingConstants.CENTER);
        exitLabel.setForeground(Color.WHITE);
        exitLabel.setFont(new Font("Serif", Font.BOLD, 10 * screenRatio));
        blueBoxPanel.add(exitLabel);

        add(blueBoxPanel);

        updateLabels(); // 레이블 업데이트 메소드 호출
    }

    private void updateLabels() {
        if (currPoint == 0) {
            resumeLabel.setText("RESUME←");
            resumeLabel.setForeground(Color.RED);
            exitLabel.setText("EXIT");
            exitLabel.setForeground(Color.WHITE);
        } else {
            resumeLabel.setText("RESUME");
            resumeLabel.setForeground(Color.WHITE);
            exitLabel.setText("EXIT←");
            exitLabel.setForeground(Color.RED);
        }
    }

    public void changePoint() {
        currPoint = (currPoint == 0) ? 1 : 0; // currPoint 값 토글
        updateLabels(); // 레이블 상태 업데이트
    }

    public int getCurrPoint() {
        return currPoint;
    }

}
