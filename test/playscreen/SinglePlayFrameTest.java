package playscreen;

import org.junit.Before;
import org.junit.Test;
import playscreen.SinglePlayFrame;
import playscreen.panels.NameInputPanel;
import playscreen.panels.PausePanel;
import playscreen.panels.PlayPanel;

import javax.swing.*;

import java.awt.event.KeyEvent;

import static org.junit.Assert.*;

public class SinglePlayFrameTest {

    private SinglePlayFrame itemModeFrame;
    private SinglePlayFrame normalModeFrame;

    @Before
    public void setUp() {
        itemModeFrame = new SinglePlayFrame("itemMode");
        normalModeFrame = new SinglePlayFrame("normalMode");
    }

    @Test
    public void testInitialSetupItemMode() {
        verifyInitialSetup(itemModeFrame, "itemMode");
    }

    @Test
    public void testInitialSetupNormalMode() {
        verifyInitialSetup(normalModeFrame, "normalMode");
    }

    private void verifyInitialSetup(SinglePlayFrame frame, String gameMode) {
        assertEquals("Play Frame", frame.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertFalse(frame.isResizable());
        assertTrue(frame.isVisible());

        assertNotNull(frame.playPanel);
        assertTrue(frame.playPanel.isVisible());

        assertNotNull(frame.pausePanel);
        assertFalse(frame.pausePanel.isVisible());
    }

    @Test
    public void testTogglePauseItemMode() {
        verifyTogglePause(itemModeFrame);
    }

    @Test
    public void testTogglePauseNormalMode() {
        verifyTogglePause(normalModeFrame);
    }

    private void verifyTogglePause(SinglePlayFrame frame) {
        frame.toggleIsPause();
        assertTrue(frame.getIsPause());
        assertTrue(frame.pausePanel.isVisible());

        frame.toggleIsPause();
        assertFalse(frame.getIsPause());
        assertFalse(frame.pausePanel.isVisible());
    }

    @Test
    public void testGameOverItemMode() {
        verifyGameOver(itemModeFrame);
    }

    @Test
    public void testGameOverNormalMode() {
        verifyGameOver(normalModeFrame);
    }

    private void verifyGameOver(SinglePlayFrame frame) {
        int score = 100;
        frame.gameOver(score);
        assertNotNull(frame.nameInputPanel);
        assertTrue(frame.nameInputPanel.isVisible());
        // Assume NameInputPanel has a method getScore to verify the score
        // assertEquals(score, frame.nameInputPanel.getScore());
    }


    private void verifyPanelPositions(SinglePlayFrame frame) {
        frame.toggleIsPause();
        PausePanel pausePanel = frame.pausePanel;
        int expectedPauseX = (frame.getWidth() - pausePanel.getWidth()) / 2;
        int expectedPauseY = (frame.getHeight() - pausePanel.getHeight()) / 2;
        assertEquals(expectedPauseX, pausePanel.getX());
        assertEquals(expectedPauseY, pausePanel.getY());

        frame.gameOver(100);
        NameInputPanel nameInputPanel = frame.nameInputPanel;
        int expectedNameInputX = (frame.getWidth() - nameInputPanel.getWidth()) / 2;
        int expectedNameInputY = (frame.getHeight() - nameInputPanel.getHeight()) / 2;
        assertEquals(expectedNameInputX, nameInputPanel.getX());
        assertEquals(expectedNameInputY, nameInputPanel.getY());
    }



    private void verifyKeyListener(SinglePlayFrame frame) {
        KeyEvent pauseKeyEvent = new KeyEvent(frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_P, 'P');
        frame.dispatchEvent(pauseKeyEvent);
        assertTrue(frame.getIsPause());

        frame.dispatchEvent(pauseKeyEvent);
        assertFalse(frame.getIsPause());
    }
}
