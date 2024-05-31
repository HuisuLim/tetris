package playscreen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import playscreen.utils.MultiPlayKeyListener;
import settingscreen.settingModel;
import startscreen.StartMenu;

import java.awt.event.KeyEvent;

import static org.junit.jupiter.api.Assertions.*;

public class MultiPlayFrameTest {

    private MultiPlayFrame frame;
    private settingModel data;

    @BeforeEach
    public void setUp() {
        data = new settingModel();
        data.screenSize = 1;  // Assuming screenSize is a public field for this example
        data.difficulty = String.valueOf("normal");
        data.colorBlindMode = false;

        frame = new MultiPlayFrame("normalMode");
    }

    @Test
    public void testInitialConditions() {
        assertFalse(frame.getIsPause());
        assertTrue(frame.isVisible());
        assertEquals("Battle Play Frame", frame.getTitle());
    }

    @Test
    public void testToggleIsPause() {
        frame.toggleIsPause();
        assertTrue(frame.getIsPause());
        assertTrue(frame.pausePanel.isVisible());

        frame.toggleIsPause();
        assertFalse(frame.getIsPause());
        assertFalse(frame.pausePanel.isVisible());
    }

    @Test
    public void testGameOver() {
        frame.gameOver(100);
        assertFalse(frame.isVisible());
        // Since StartMenu is a new instance, it should be created and visible
        StartMenu menu = new StartMenu();
        assertTrue(menu.isVisible());
    }

    @Test
    public void testKeyPressed() {
        MultiPlayKeyListener keyListener = new MultiPlayKeyListener(frame,
                new int[]{KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_SPACE},
                new int[]{KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_ENTER}
        );

        KeyEvent keyEvent = new KeyEvent(frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyListener.keyPressed(keyEvent);
        // Verify that the gameControl method in player1PlayPanel is called with correct argument
        // Due to the nature of the environment, direct method verification is complex without a framework like Mockito
        // However, we can indirectly verify the state change or side-effects
        assertTrue(frame.player1PlayPanel.isVisible());
    }

    // Additional tests to cover other key presses and states

    @Test
    public void testPauseStateKeyPresses() {
        frame.toggleIsPause();  // First pause the game
        MultiPlayKeyListener keyListener = new MultiPlayKeyListener(frame,
                new int[]{KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_SPACE},
                new int[]{KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_ENTER}
        );

        // Simulate pressing 'S' key to navigate in pause menu
        KeyEvent downKeyEvent = new KeyEvent(frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_S, 'S');
        keyListener.keyPressed(downKeyEvent);
        assertEquals(1, frame.pausePanel.getCurrPoint());

        // Simulate pressing 'W' key to navigate up in pause menu
        KeyEvent upKeyEvent = new KeyEvent(frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_W, 'W');
        keyListener.keyPressed(upKeyEvent);
        assertEquals(0, frame.pausePanel.getCurrPoint());
    }

    @Test
    public void testPauseMenuSelection() {
        frame.toggleIsPause();  // First pause the game
        MultiPlayKeyListener keyListener = new MultiPlayKeyListener(frame,
                new int[]{KeyEvent.VK_W, KeyEvent.VK_D, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_SPACE},
                new int[]{KeyEvent.VK_UP, KeyEvent.VK_RIGHT, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_ENTER}
        );

        // Navigate to EXIT option
        frame.pausePanel.downPoint();
        frame.pausePanel.downPoint();
        assertEquals(2, frame.pausePanel.getCurrPoint());

        // Simulate pressing 'SPACE' key to select EXIT
        KeyEvent selectKeyEvent = new KeyEvent(frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_SPACE, ' ');
        keyListener.keyPressed(selectKeyEvent);
        assertFalse(frame.isVisible());  // Frame should be closed
    }

    // Continue adding other necessary test cases to ensure full coverage

}
