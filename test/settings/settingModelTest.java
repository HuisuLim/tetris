package settings;

import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


class settingModelTest {

    private settingModel model;

    public void writeToSettingsFile(String content) {
        File file = new File(settingModel.SCOREBOARD_FILE);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @BeforeEach
    void setUp() throws IOException {
        model = new settingModel();
        model.setSettingsFile("test/settings/settings.properties");
        model.setScoreboardFile("test/settings/scoreBoard.txt");
        model.saveSetting("MOVEMENT", "ArrowKeys");
        model.saveSetting("ScreenSize", "1.6");
        model.saveSetting("ColorMode", "false");
        model.saveSetting("Difficulty", "normal");
    }

    @Test
    void testKey() {
        assertEquals("ArrowKeys", model.loadKeySettings());

        assertEquals(KeyEvent.VK_LEFT, model.getLeftKey());
        assertEquals(KeyEvent.VK_RIGHT, model.getRightKey());
        assertEquals(KeyEvent.VK_UP, model.getUpKey());
        assertEquals(KeyEvent.VK_DOWN, model.getDownKey());

        model.saveSetting("MOVEMENT", "WASD");
        assertEquals(KeyEvent.VK_A, model.getLeftKey());
        assertEquals(KeyEvent.VK_D, model.getRightKey());
        assertEquals(KeyEvent.VK_W, model.getUpKey());
        assertEquals(KeyEvent.VK_S, model.getDownKey());

        model.saveSetting("MOVEMENT", "INVALID VALUE");
        assertEquals(KeyEvent.VK_LEFT, model.getLeftKey());
        assertEquals(KeyEvent.VK_RIGHT, model.getRightKey());
        assertEquals(KeyEvent.VK_UP, model.getUpKey());
        assertEquals(KeyEvent.VK_DOWN, model.getDownKey());
    }

    @Test
    void testLoadKeys() {
        model.saveSetting("MOVEMENT", "WASD");
        // Define the expected array based on the mocked key inputs
        int[] expectedKeys = {KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_SPACE};

        // Call loadKeys and assert that the results match the expected output
        assertArrayEquals(expectedKeys, model.loadKeys(), "The key array should match the expected keys.");
    }

    @Test
    void testLoadScreenSize() {
        assertEquals(1.6, model.loadScreenSize());
    }

    @Test
    void testLoadColorBlindMode() {
        assertFalse(model.loadColorBlindMode());
    }

    @Test
    void testLoadDifficulty() {
        assertEquals("normal", model.loadDifficulty());
    }

    @Test
    void testSaveSetting() {
        assertEquals("normal", model.loadDifficulty());
        model.saveSetting("Difficulty", "hard");
        assertEquals("hard", model.loadDifficulty());
    }

    @Test
    public void testClearScoreboard() {
        // 스코어보드 초기화
        String content = "This is a test content.";
        writeToSettingsFile(content);
        // 파일 내용이 비었는지 확인
        model.clearScoreboard();
        File file = new File("test/settings/scoreBoard.txt");
        assertEquals(0, file.length(), "Scoreboard file should be empty after clearing.");
    }
}


