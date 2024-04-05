package temp;

import javax.swing.*;
import java.awt.*;
import playscreen.utils.TimerDelay;
import settings.LoadData; // 설정 정보를 가져오기 위해 추가

public class SplitFrame extends JFrame {
    private int count = 0; // 숫자를 증가시키기 위한 변수
    private JLabel numberLabel; // 숫자를 표시할 레이블
    private Timer timer;
    private LoadData loadData; // 설정 정보를 로드하기 위한 인스턴스
    private String difficulty; // 난이도를 저장하기 위한 변수

    public SplitFrame() {
        loadData = new LoadData();
        String difficulty = loadData.loadDifficulty(); // 난이도 로드

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

        // 타이머 설정 및 시작 부분 수정
        int initialDelay = TimerDelay.calDelay(difficulty, count); // 초기 지연 시간 설정
        timer = new Timer(initialDelay, e -> updateCounter());
        timer.start();

        setVisible(true);
    }

    private void updateCounter() {
        count++;
        numberLabel.setText(String.valueOf(count));

        // 딜레이 재계산 및 적용
        int newDelay = TimerDelay.calDelay(difficulty, count); // 난이도와 현재 점수를 기반으로 새 딜레이 계산
        timer.setDelay(newDelay);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(SplitFrame::new);
    }
}
