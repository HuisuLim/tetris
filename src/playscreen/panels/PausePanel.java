package playscreen.panels;

import javax.swing.*;
import java.awt.*;

public class PausePanel extends JPanel {
    private int currPoint = 0; // 현재 포인터 위치 (0: RESUME, 1: Go to StartMenu, 2: EXIT)
    private JLabel resumeLabel;
    private JLabel startMenuLabel;
    private JLabel exitLabel;
    private JFrame parentFrame; // 현재 창의 참조

    public PausePanel(JFrame parentFrame, int screenSize) {
        this.parentFrame = parentFrame;
        setFocusable(true);
        setBackground(Color.BLACK);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // BoxLayout으로 변경

        // "PAUSE" 라벨 추가
        JLabel pauseLabel = new JLabel("PAUSE", SwingConstants.CENTER);
        pauseLabel.setForeground(Color.WHITE);
        pauseLabel.setFont(new Font("Serif", Font.BOLD, 18 * screenSize)); // 폰트 크기 유지
        pauseLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // 가운데 정렬
        add(pauseLabel);

        // 옵션 패널 설정
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS)); // 옵션 패널도 BoxLayout으로 변경
        optionsPanel.setBackground(Color.BLUE);
        optionsPanel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200 * screenSize)); // 옵션 패널의 최대 높이 설정

        // "RESUME" 라벨 추가
        resumeLabel = new JLabel("RESUME", SwingConstants.CENTER);
        resumeLabel.setForeground(Color.WHITE);
        resumeLabel.setFont(new Font("Serif", Font.BOLD, 15 * screenSize)); // 폰트 크기 유지
        optionsPanel.add(resumeLabel);

        // "Go to StartMenu" 라벨 추가
        startMenuLabel = new JLabel("Go to StartMenu", SwingConstants.CENTER);
        startMenuLabel.setForeground(Color.WHITE);
        startMenuLabel.setFont(new Font("Serif", Font.BOLD, 15 * screenSize)); // 폰트 크기 유지
        optionsPanel.add(startMenuLabel);

        // "EXIT" 라벨 추가
        exitLabel = new JLabel("EXIT", SwingConstants.CENTER);
        exitLabel.setForeground(Color.WHITE);
        exitLabel.setFont(new Font("Serif", Font.BOLD, 15 * screenSize)); // 폰트 크기 유지
        optionsPanel.add(exitLabel);

        add(optionsPanel);
        updateLabels();
    }

    private void updateLabels() {
        resumeLabel.setText(currPoint == 0 ? "RESUME←" : "RESUME");
        resumeLabel.setForeground(currPoint == 0 ? Color.RED : Color.WHITE);

        startMenuLabel.setText(currPoint == 1 ? "Go to StartMenu←" : "Go to StartMenu");
        startMenuLabel.setForeground(currPoint == 1 ? Color.RED : Color.WHITE);

        exitLabel.setText(currPoint == 2 ? "EXIT←" : "EXIT");
        exitLabel.setForeground(currPoint == 2 ? Color.RED : Color.WHITE);
    }

    public void changePoint() {
        currPoint = (currPoint + 1) % 3; // currPoint 값 순환
        updateLabels(); // 레이블 상태 업데이트
    }

    public int getCurrPoint() {
        return currPoint;
    }
}
