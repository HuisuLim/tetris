package play_screen;

import startscreen.StartMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PausePanel extends JPanel {
    private int screenRatio;
    private int currPoint = 0; // 현재 포인터 위치 (0: RESUME, 1: EXIT)
    private JLabel resumeLabel;
    private JLabel exitLabel;
    private JFrame parentFrame; // 현재 창의 참조

    public PausePanel(JFrame parentFrame, int screenRatio) {
        this.parentFrame = parentFrame;
        this.screenRatio = screenRatio;
        setFocusable(true);
        setPreferredSize(new Dimension(150 * screenRatio, 100 * screenRatio));
        setBackground(Color.BLACK);
        setLayout(new GridLayout(3, 1));


        requestFocusInWindow();

        // 키 바인딩 설정
        setupKeyBindings();

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
        // "EXIT" 라벨에 대한 키 이벤트 리스너 추가 (가정)
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER && currPoint == 1) { // "EXIT" 선택
                    exitToMainMenu();
                }
            }
        });
    }

    private void setupKeyBindings() {
        // RESUME 선택
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "RESUME");
        this.getActionMap().put("RESUME", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currPoint = 0;
                updateLabels();
            }
        });
        // EXIT 선택
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "EXIT");
        this.getActionMap().put("EXIT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currPoint = 1;
                updateLabels();
            }
        });
        // 엔터키로 선택 실행
        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "SELECT");
        this.getActionMap().put("SELECT", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currPoint == 1) { // EXIT가 선택된 경우
                    exitToMainMenu();
                }
            }
        });
    }
    private void exitToMainMenu2() {
        EventQueue.invokeLater(() -> {
            parentFrame.dispose(); // 현재 창 닫기
            new StartMenu().setVisible(true); // 메인 메뉴 창 열기
        });
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

    private void exitToMainMenu() {
        EventQueue.invokeLater(() -> {
            parentFrame.dispose(); // 현재 창 닫기
            new StartMenu().setVisible(true); // 메인 메뉴 창 열기
        });
    }

}
