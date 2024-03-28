package startScreen;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.Arrays;

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
                if (parts.length >= 2) { // 배열 길이가 2 이상이어야 합니다.
                    String name = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    addData(name, score);
                } else {
                    System.out.println("Invalid data format: " + Arrays.toString(parts));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ScoreInput scoreboard = new ScoreInput();

        // 이름을 입력하는 다이얼로그 띄우기
        String name = JOptionPane.showInputDialog(scoreboard, "Enter your name:");
        if (name != null && !name.isEmpty()) {
            scoreboard.addDataToFile("scoreboard.txt", name);
        } else {
            System.exit(0); // 이름이 없으면 프로그램 종료
        }

        // 텍스트 파일로부터 데이터를 불러오기
        scoreboard.addDataFromFile("scoreboard.txt");
        scoreboard.setVisible(true);
    }

    // 데이터를 파일에 추가하는 메서드
    private void addDataToFile(String filePath, String name) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(name + ", 0\n"); // 새 이름과 초기 점수 0을 파일에 추가
            writer.flush();
            addData(name, 0); // 테이블에도 추가
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}