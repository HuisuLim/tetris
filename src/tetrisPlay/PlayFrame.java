package tetrisPlay;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class PlayFrame extends JFrame {
    private int screenRatio;
    private TetrisPlay gamePanel;
    private ScorePanel scorePanel;
    private NextBlockPanel nextBlockPanel;
    private Timer timer;
    private int score;

    public PlayFrame(int screenRatio) {
        //JFrame 설정.
        this.screenRatio = screenRatio;
        setTitle("Play Frame");
        setSize(screenRatio * 20 * 20, screenRatio * 20 * 20);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2)); // 프레임을 가로로 2등분

        initUI();
        createTimer();
        timer.start();

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    // 스페이스바를 누를 때 점수 업데이트
                    score +=10;
                    updateScore(score);
                }
            }
        });

        pack();
        setLocationRelativeTo(null); // 화면 가운데에 위치
        setVisible(true);
    }

    private void initUI() {
        // 왼쪽 패널 (빨간색 배경)
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        add(redPanel);

        // 오른쪽 패널 (세로로 4등분)
        JPanel rightPanel = new JPanel(new GridLayout(4, 1));
        // ScorePanel 추가
        scorePanel = new ScorePanel(screenRatio, 0);
        rightPanel.add(scorePanel);

        // NextBlockPanel 추가
        nextBlockPanel = new NextBlockPanel(screenRatio, new int[][]{{1, 1}, {1, 1}}); // 임시 배열
        rightPanel.add(nextBlockPanel);

        // 나머지 두 개의 패널은 비워둡니다.
        rightPanel.add(new JPanel());
        rightPanel.add(new JPanel());

        add(rightPanel);
    }

    private void updateScore(int newScore) {
        scorePanel.updateScore(newScore);
    }
    private void createTimer() {
        // ActionListener 정의: 점수를 업데이트하고 타이머의 지연 시간 조정
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                score +=1;
                updateScore(score);
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
                new PlayFrame(2).setVisible(true);
            }
        });
    }
}
