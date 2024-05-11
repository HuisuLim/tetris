package startscreen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.table.DefaultTableModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ScoreboardControllerTest {

    private ScoreboardController scoreboardController;
    private DefaultTableModel model;

    @BeforeEach
    public void setUp() {
        // 테스트를 위해 ScoreboardController와 DefaultTableModel을 초기화합니다.
        model = new DefaultTableModel();
        scoreboardController = new ScoreboardController("TestPlayer", 100, "Easy", "itemMode");
        scoreboardController.setModel(model);
    }

    @Test
    public void testChangeDifficulty() {
        // 난이도를 변경하고 올바르게 업데이트되었는지 확인합니다.
        scoreboardController.changeDifficulty("Hard");
        assertEquals("Hard", scoreboardController.getCurrentDifficulty());
    }

    @Test
    public void testChangeMode() {
        // 모드를 변경하고 올바르게 업데이트되었는지 확인합니다.
        scoreboardController.changeMode("normalMode");
        assertEquals("normalMode", scoreboardController.getCurrentMode());
    }

    @Test
    public void testUpdateScoreboard() {
        // 스코어보드를 업데이트하고 올바른 행이 선택되었는지 확인합니다.
        scoreboardController.changeDifficulty("Easy");
        scoreboardController.changeMode("itemMode");
        scoreboardController.updateScoreboard();
    }



}
