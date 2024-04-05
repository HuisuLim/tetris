package startscreen;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.io.*;

public class ShowScoreboard {
    public static void readScoreboard(DefaultTableModel model, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String name = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    String difficulty = parts[2].trim();
                    String mode = parts[3].trim();
                    addData(model, name, score, difficulty, mode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addData(DefaultTableModel model, String name, int score, String difficulty, String mode) {
        int rank = 1;
        if (model.getRowCount() > 0) {
            rank = (int) model.getValueAt(model.getRowCount() - 1, 0) + 1;
        }

        // 데이터 추가
        Object[] rowData = {rank, name, score, difficulty, mode};
        model.addRow(rowData);
    }

    public static void applyCenterAlignment(JTable table) {
        // 가운데 정렬을 위한 셀 렌더러 설정
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // 셀 내용을 가운데 정렬로 설정

        // 각 열에 셀 렌더러 설정하여 가운데 정렬 적용
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    /*
    public static void main(String[] args) {
        DefaultTableModel model = new DefaultTableModel();
        JTable table = new JTable(model);
        applyCenterAlignment(table);

        // 데이터 파일 경로
        String filePath = "scoreboard.txt";

        // 스코어보드 파일 읽기
        readScoreboard(model, filePath);

        // JFrame 생성 및 테이블 추가
        JFrame frame = new JFrame("Scoreboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane);
        frame.pack();
        frame.setVisible(true);
    }

     */

    public static class ClearScoreboard {
        public static void clear(String filePath) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                // 파일 내용을 모두 지움
                writer.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
