package startscreen;

import javax.swing.table.DefaultTableModel;
import java.io.*;

public class ScoreboardManager {

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
        boolean playerFound = false;
        int row = 0;

        // 기존 데이터와 새로운 데이터를 비교하여 적절한 위치를 찾습니다.
        while (row < model.getRowCount() && score <= (int) model.getValueAt(row, 2)) {
            if (name.equals((String) model.getValueAt(row, 1))) {
                // 같은 이름을 가진 플레이어의 기록을 찾았을 경우
                playerFound = true;
                break;
            }
            row++;
        }

        // 같은 이름을 가진 플레이어의 기록이 없는 경우 새로운 행을 추가합니다.
        if (!playerFound) {
            // 새로운 데이터의 랭킹을 설정합니다.
            while (row < model.getRowCount() && score <= (int) model.getValueAt(row, 2)) {
                row++;
            }
            // 테이블에 새로운 행을 추가합니다.
            model.insertRow(row, new Object[]{row + 1, name, score, difficulty, mode});

            // 새로운 데이터의 랭킹을 설정합니다.
            for (int i = row; i < model.getRowCount(); i++) {
                model.setValueAt(i + 1, i, 0); // 랭킹 열 업데이트
            }
        } else {
            // 같은 이름을 가진 플레이어의 기록이 있는 경우, 새로운 행으로 추가합니다.
            model.addRow(new Object[]{row + 1, name, score, difficulty, mode});

            // 테이블의 마지막 행으로 추가되므로 해당 행의 랭킹을 업데이트합니다.
            model.setValueAt(model.getRowCount(), model.getRowCount() - 1, 0);
        }
    }
    // 테이블 모델의 데이터를 파일로 저장하는 메서드
    public static void saveDataToFile(DefaultTableModel model, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                String name = (String) model.getValueAt(i, 1);
                int score = (int) model.getValueAt(i, 2);
                String difficulty = (String) model.getValueAt(i, 3);
                String mode = (String) model.getValueAt(i, 4);
                writer.write(name + "," + score + "," + difficulty + "," + mode);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}