package startscreen;

import javax.swing.table.DefaultTableModel;
import java.io.*;

public class ScoreboardModel {

    // 파일에서 스코어보드 데이터를 읽어와 테이블 모델에 채우는 메서드
    public static void readScoreboard(DefaultTableModel model, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 각 줄을 쉼표로 나누어 개별 데이터를 추출
                String[] parts = line.split(",");
                // 이름, 점수, 난이도, 모드를 추출하여 테이블 모델에 추가
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
    // 데이터를 테이블 모델에 추가하는 메서드
    public static void addData(DefaultTableModel model, String name, int score, String difficulty, String mode) {
        int rank = 1;
        // 새 항목의 순위를 기존 항목을 기반으로 계산
        if (model.getRowCount() > 0) {
            rank = (int) model.getValueAt(model.getRowCount() - 1, 0) + 1;
        }
        // 데이터 배열을 생성 후 테이블 모델에 추가
        Object[] rowData = {rank, name, score, difficulty, mode};
        model.addRow(rowData);
    }

    // 새로운 데이터를 삽입할 위치를 찾고, 내림차순으로 유지하면서 테이블에 추가하는 메서드

    public static void addDataDescending(String name, int score, String difficulty, String mode, DefaultTableModel model) {
        int rank = 1; // Initialize rank to 1

        // Check if the model already contains data
        if (model.getRowCount() > 0) {
            // Loop through the existing data to find the correct rank for the new data
            for (int i = 0; i < model.getRowCount(); i++) {
                int rowScore = (int) model.getValueAt(i, 2); // Get the score from the current row
                if (score > rowScore) {
                    rank = i + 1; // Update the rank if the new score is higher than the current row's score
                    break; // Exit the loop since we found the correct rank
                } else if (score == rowScore) {
                    // If scores are equal, compare names alphabetically to determine rank
                    String rowName = (String) model.getValueAt(i, 1); // Get the name from the current row
                    if (name.compareToIgnoreCase(rowName) <= 0) {
                        rank = i + 1; // Update the rank if the new name comes before or is equal to the current row's name
                        break; // Exit the loop since we found the correct rank
                    }
                }
                rank = i + 2; // Increment rank if the new score is not higher or equal to the current row's score
            }
        }

        // Add the new data to the model at the determined rank
        model.insertRow(rank - 1, new Object[]{rank, name, score, difficulty, mode});
    }

    // 테이블 모델의 데이터를 파일로 저장하는 메서드
    public static void saveDataToFile(DefaultTableModel model, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                String name = (String) model.getValueAt(i, 1);
                int score = (int) model.getValueAt(i, 2);
                String difficulty = (String) model.getValueAt(i, 3);
                String mode = (String) model.getValueAt(i, 4);
                writer.write(name + "," + score + "," + difficulty + "," + mode + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}