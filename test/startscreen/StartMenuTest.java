package startscreen;

import javax.swing.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import settings.settingModel;

import java.awt.*;
import java.io.File;

public class StartMenuTest {

    private StartMenu startMenu;
    private static final String TEST_SETTINGS_FILE = "test/settings/settings.properties";
    private settingModel model;


    @BeforeEach
    public void setUp() {
        File file = new File(TEST_SETTINGS_FILE);
        if (file.exists()) {
            file.delete();
        }
        model = new settingModel();
        model.setSettingsFile(TEST_SETTINGS_FILE);
        try {
            SwingUtilities.invokeAndWait(() -> startMenu = new StartMenu());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testDefaultSettings() {
        assertEquals(1.6, StartMenu.screenRatio, 0.01);
        assertFalse(StartMenu.isColorblindness);
        assertEquals("ArrowKeys", StartMenu.keySetting);
        assertEquals("normal", StartMenu.difficulty);
    }

    @Test
    public void testComponentsInitialized() {
        assertNotNull(startMenu.getContentPane());
        Component[] components = startMenu.getContentPane().getComponents();
        assertTrue(components.length > 0);
    }

    @AfterEach
    public void tearDown() {
        startMenu.dispose();
        File file = new File(TEST_SETTINGS_FILE);
        if (file.exists()) {
            file.delete();
        }
    }
}