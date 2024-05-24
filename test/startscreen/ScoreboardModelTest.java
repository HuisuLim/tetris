package startscreen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class ScoreboardModelTest {

    private DefaultTableModel model;

    @Before
    public void setUp() throws Exception {
        // 테스트 전에 새로운 DefaultTableModel 인스턴스를 생성
        model = new DefaultTableModel();
        // 모델에 컬럼 추가
        model.addColumn("Rank");
        model.addColumn("Name");
        model.addColumn("Score");
        model.addColumn("Difficulty");
        model.addColumn("Mode");
    }

    @After
    public void tearDown() {
        // 테스트 후에 필요한 정리 작업
        model = null;
    }

    @Test
    public void testAddData() {
        // ScoreboardModel.addData() 호출
        ScoreboardModel.addData(model, "NewPlayer", 150, "Hard", "normalMode");

        // 모델에 행이 추가되었는지 확인
        assertEquals(1, model.getRowCount());
        // 추가된 행의 데이터가 예상대로 추가되었는지 확인
        assertEquals("NewPlayer", model.getValueAt(0, 1));
        assertEquals(150, model.getValueAt(0, 2));
        assertEquals("Hard", model.getValueAt(0, 3));
        assertEquals("normalMode", model.getValueAt(0, 4));
    }

    @Test
    public void testAddDataDescending() {
        // 테스트를 위한 더미 데이터 추가
        model.addRow(new Object[]{1, "Player1", 100, "Easy", "itemMode"});
        model.addRow(new Object[]{2, "Player2", 150, "Hard", "normalMode"});

        // ScoreboardModel.addDataDescending() 호출
        ScoreboardModel.addDataDescending("NewPlayer", 175, "Easy", "normalMode", model);

        // 모델에 행이 추가되었는지 확인
        assertEquals(3, model.getRowCount());
        // 추가된 행의 데이터가 예상대로 추가되었는지 확인
        assertEquals(1, model.getValueAt(0, 0)); // rank가 예상대로 업데이트되었는지 확인
        assertEquals("NewPlayer", model.getValueAt(0, 1));
        assertEquals(175, model.getValueAt(0, 2));
        assertEquals("Easy", model.getValueAt(0, 3));
        assertEquals("normalMode", model.getValueAt(0, 4));
    }

    @Test
    public void testReadScoreboard() {
        // 테스트할 파일 경로
        String filePath = "scoreboard.txt";

        // ScoreboardModel.readScoreboard() 호출
        ScoreboardModel.readScoreboard(model, filePath);

        // 모델에 기대되는 행이 추가되었는지 확인
        assertEquals(2, model.getRowCount());
        // 추가된 행의 데이터가 예상대로 추가되었는지 확인
        assertEquals("Player1", model.getValueAt(0, 1));
        assertEquals(100, model.getValueAt(0, 2));
        assertEquals("Easy", model.getValueAt(0, 3));
        assertEquals("itemMode", model.getValueAt(0, 4));
    }

    @Test
    public void testSaveDataToFile() {
        // 임시 파일 경로
        String filePath = "scoreboard.txt";

        // 테스트를 위한 더미 데이터 추가
        model.addRow(new Object[]{1, "Player1", 100, "Easy", "itemMode"});
        model.addRow(new Object[]{2, "Player2", 150, "Hard", "normalMode"});

        // ScoreboardModel.saveDataToFile() 호출
        ScoreboardModel.saveDataToFile(model, filePath);

        // 파일이 생성되었는지 확인
        File file = new File(filePath);
        assertTrue(file.exists());

        // 파일의 내용이 모델의 데이터와 일치하는지 확인
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                assertEquals(model.getValueAt(row, 1), parts[0].trim());
                assertEquals(model.getValueAt(row, 2), Integer.parseInt(parts[1].trim()));
                assertEquals(model.getValueAt(row, 3), parts[2].trim());
                assertEquals(model.getValueAt(row, 4), parts[3].trim());
                row++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
