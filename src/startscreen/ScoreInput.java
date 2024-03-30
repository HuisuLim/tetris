package startscreen;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class ScoreInput extends JFrame {
    private DefaultTableModel model;
    private JTable scoreboard;

    public ScoreInput() {
        setTitle("Scoreboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        // JTable에 사용할 모델 생성
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        //랭킹, 이름, 점수 컬럼 추가

        String[] columnNames = {"Rank", "Name", "Score"};
        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }

        // JTable 생성 및 각 컬럼 너비 설정
        scoreboard = new JTable(model);
        scoreboard.getColumnModel().getColumn(0).setPreferredWidth(10); // 랭킹 열의 너비를 설정
        scoreboard.getColumnModel().getColumn(1).setPreferredWidth(150); // 이름 열의 너비를 설정
        scoreboard.getColumnModel().getColumn(2).setPreferredWidth(50); // Score 열의 너비를 설정

        // 가운데 정렬을 위한 셀 렌더러 설정
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // 셀 내용을 가운데 정렬로 설정
        for (int i = 0; i < model.getColumnCount(); i++) {
            scoreboard.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        //스코어보드를 패널에 넣고, 프레임 가운데 배치
        JScrollPane scrollPane = new JScrollPane(scoreboard);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        // 텍스트 파일로부터 데이터를 불러와 스코어보드에 추가
        addDataFromFile("scoreboard.txt");

        // 이름을 입력하는 다이얼로그 띄우기
        String name = JOptionPane.showInputDialog(this, "이름을 입력하세요:");

        if (name != null && !name.isEmpty()) {
            // 테이블에 이름과 초기 점수 0 추가
            addData(name, 0);
            // 방금 입력한 이름과 점수를 파란색으로 강조 표시
            highlightNameAndScore(name, 0);
            // 이름과 점수를 파일에 저장
            saveNameToFile(name, 0, "scoreboard.txt");
        } else {
            System.exit(0); // 이름이 없으면 프로그램 종료
        }
    }

    // 데이터를 테이블에 추가하는 메서드
    private void addData(String name, int score) {
        int rank = 1; //테이블 데이터 없을 때는 1등으로 들어감
        // 테이블의 마지막 행의 순위를 가져와서 그보다 1 높은 순위를 계산
        if (model.getRowCount() > 0) {
            rank = (int) model.getValueAt(model.getRowCount() - 1, 0) + 1;
        }
        // 테이블에 새로운 행 추가
        model.addRow(new Object[]{rank, name, score});
    }

    // 텍스트 파일로부터 데이터를 불러와 스코어보드에 추가하는 메서드
    private void addDataFromFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            // 데이터를 저장할 배열 리스트 생성
            Object[][] data = new Object[1000][3]; // 임의의 크기로 설정, 데이터가 많으면 크기를 조정할 수 있습니다.
            int index = 0;

            // 파일에서 데이터를 읽어와 배열 리스트에 추가
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // 예시: 이름과 점수가 쉼표로 구분되어 있는 경우
                if (parts.length >= 2) { // 배열 길이가 2 이상이어야 합니다.
                    String name = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    data[index++] = new Object[]{name, score};

                }
            }

            // 배열 리스트에 저장된 데이터를 점수가 높은 순서대로 정렬하여 테이블에 추가
            Arrays.sort(data, 0, index, Comparator.comparingInt(o -> (int) o[1]));
            for (int i = index - 1; i >= 0; i--) {
                addData((String) data[i][0], (int) data[i][1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 이름과 점수를 파일에 저장하는 메서드
    private void saveNameToFile(String name, int score, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(name + "," + score);
            writer.newLine(); // 다음 데이터를 위해 개행 추가
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 이름과 점수를 파란색으로 강조하는 메서드
    private void highlightNameAndScore(String name, int score) {
        Timer timer = new Timer(500, new ActionListener() {
            boolean flag = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag) {
                    scoreboard.setSelectionForeground(Color.BLUE);
                } else {
                    scoreboard.setSelectionForeground(scoreboard.getForeground());
                }
                flag = !flag;
            }
        });
        timer.setRepeats(true);
        timer.start();

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1).equals(name) && (int) model.getValueAt(i, 2) == score) {
                scoreboard.setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ScoreInput scoreboard = new ScoreInput();
            scoreboard.setVisible(true);
        });
    }
}