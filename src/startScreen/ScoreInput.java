package startScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ScoreInput extends JFrame {
    private DefaultTableModel model;
    private JTable scoreboard;

    public ScoreInput() {
        setTitle("Scoreboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // JTable에 사용할 모델 생성
        model = new DefaultTableModel();
        model.addColumn("Name");
        model.addColumn("Score");

        // JTable 생성 및 모델 설정
        scoreboard = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(scoreboard);


        // 레이아웃 설정
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    // 데이터를 테이블에 추가하는 메서드
    private void addData(String name, int score) {
        model.addRow(new Object[]{name, score});
    }

    private void addDataFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // 예시: 이름과 점수가 쉼표로 구분되어 있는 경우
                String name = parts[0].trim();
                int score = Integer.parseInt(parts[1].trim());
                addData(name, score);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ScoreInput scoreboard = new ScoreInput();
            scoreboard.setVisible(true);
            scoreboard.addDataFromFile("scoreboard.txt");
        });
    }
}