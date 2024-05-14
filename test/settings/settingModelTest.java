package settings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.event.KeyEvent;
import java.io.IOException;

class settingModelTest {

    private settingModel model;
    @BeforeEach
    void setUp(){
        model = new settingModel();
        model.setSettingsFile("test/settings/settings.properties");
        model.saveSetting("MOVEMENT","ArrowKeys");
        model.saveSetting("DIFFICULTY", "hard");
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

}
