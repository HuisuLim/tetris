package startscreen;

import javax.swing.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.*;

public class StartMenuTest {

    private StartMenu startMenu;

    @BeforeEach
    public void setUp() {
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
    }
}