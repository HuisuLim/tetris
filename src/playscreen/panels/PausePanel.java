package playscreen.panels;

import javax.swing.*;
import java.awt.*;


public class PausePanel extends JPanel {
    private int currPoint = 0; // 현재 포인터 위치 (0: RESUME, 1: EXIT)
    private final JLabel resumeLabel;
    private final JLabel menuLabel;
    private final JLabel exitLabel;

    public PausePanel(int screenSize) {
        setFocusable(true);
        setSize(150 * screenSize, 100 * screenSize);
        setBackground(Color.BLACK);
        setLayout(new GridLayout(3, 1));


        // PAUSE 라벨 추가
        JLabel pauseLabel = new JLabel("PAUSE", SwingConstants.CENTER);
        pauseLabel.setForeground(Color.WHITE);
        pauseLabel.setFont(new Font("Serif", Font.BOLD, 12 * screenSize));
        add(pauseLabel);
        // 파란색 박스 패널 추가
        JPanel blueBoxPanel = new JPanel();
        blueBoxPanel.setLayout(new GridLayout(3, 1));
        blueBoxPanel.setBackground(Color.BLUE);
        // RESUME 라벨 추가
        resumeLabel = new JLabel("RESUME", SwingConstants.CENTER);
        resumeLabel.setForeground(Color.WHITE);
        resumeLabel.setFont(new Font("Serif", Font.BOLD, 10 * screenSize));
        blueBoxPanel.add(resumeLabel);
        // MENU 라벨 추가
        menuLabel = new JLabel("MENU", SwingConstants.CENTER);
        menuLabel.setForeground(Color.WHITE);
        menuLabel.setFont(new Font("Serif", Font.BOLD, 10 * screenSize));
        blueBoxPanel.add(menuLabel);
        // EXIT 라벨 추가
        exitLabel = new JLabel("EXIT", SwingConstants.CENTER);
        exitLabel.setForeground(Color.WHITE);
        exitLabel.setFont(new Font("Serif", Font.BOLD, 10 * screenSize));
        blueBoxPanel.add(exitLabel);
        add(blueBoxPanel);

        updateLabels(); // 레이블 업데이트 메소드 호출
    }

    private void updateLabels() {
        resumeLabel.setText("RESUME←");
        resumeLabel.setForeground(Color.WHITE);
        menuLabel.setText("MENU");
        menuLabel.setForeground(Color.WHITE);
        exitLabel.setText("EXIT");
        exitLabel.setForeground(Color.WHITE);

        switch (currPoint) {
            case 0 :
                resumeLabel.setText("RESUME←");
                resumeLabel.setForeground(Color.RED);
                break;
            case 1 :
                menuLabel.setText("MENU←");
                menuLabel.setForeground(Color.RED);
                break;
            case 2 :
                exitLabel.setText("EXIT←");
                exitLabel.setForeground(Color.RED);
                break;
        }


    }


    public void downPoint() {
        currPoint = (currPoint+1) % 3;
        updateLabels();
    }

    public void upPoint() {
        currPoint = (currPoint+2) % 3;
        updateLabels();
    }

    public int getCurrPoint() {
        return currPoint;
    }
}