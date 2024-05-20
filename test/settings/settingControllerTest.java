package settings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class settingControllerTest {
    private settingController controller;
    private settingModel model;
    private settingView view;

    private void buttonCheck(int x, int buttonNum, settingView viewTest){
        if(buttonNum == 4) {
            switch (x) {
                case 1:
                    assertTrue(viewTest.Button1.isSelected());
                    break;
                case 2:
                    assertTrue(viewTest.Button2.isSelected());
                    break;
                case 3:
                    assertTrue(viewTest.Button3.isSelected());
                    break;
                default:
                    assertTrue(viewTest.Button2.isSelected());
            }
        }
        else if(buttonNum == 3) {
            switch (x) {
                case 1:
                    assertTrue(view.Button1.isSelected());
                    break;
                case 2:
                    assertTrue(view.Button2.isSelected());
                    break;
                default:
                    assertTrue(view.Button1.isSelected());
            }
        }
    }



    /**
     * 제네릭 배열의 모든 요소를 출력하는 메서드
     * @param settingArray 제네릭 배열
     * @param <T> 배열의 타입 파라미터
     */
    public <T> void check(T[] settingArray, String settingName) {
        view = new settingView(settingName);
        int length = settingArray.length;
        for(int i = 0; i < length; i++){
            model.saveSetting(settingName, String.valueOf(settingArray[i]));
            controller = new settingController(model, view);
            buttonCheck(i, length, view);
        }
    }

    @BeforeEach
    void setUp() {
        model = new settingModel();
        model.setSettingsFile("test/settings/settings.properties");
        model.saveSetting("MOVEMENT","ArrowKeys");
        model.saveSetting("ScreenSize", "1.6");
        model.saveSetting("ColorMode", "false");
        model.saveSetting("Difficulty", "normal");
        view = new settingView("ScreenSize");
    }
    @Test
    void testKeyBindingsSetup() throws Exception {
        controller = new settingController(model, view);
        controller.setupKeyBindings();

        // Robot 클래스를 사용하여 실제 키 이벤트를 시뮬레이션
        Robot robot = new Robot();

        // Simulate pressing the key associated with 'model.getLeftKey()'
        robot.keyPress(model.getLeftKey());
        robot.keyRelease(model.getLeftKey());

        // Simulate pressing the key associated with 'model.getRightKey()'
        robot.keyPress(model.getRightKey());
        robot.keyRelease(model.getRightKey());

        // Manually trigger the actions to test their effects
        Action enterAction = view.checkButton.getActionMap().get("checkPressed");
        assertNotNull(enterAction);
        enterAction.actionPerformed(null);  // Assuming this should trigger `doClick`

        Action escapeAction = view.getRootPane().getActionMap().get("escapePressed");
        assertNotNull(escapeAction);
        escapeAction.actionPerformed(null);  // Assuming this should dispose the view

        // Verify the effects of actions
        // Note: Checking view.dispose() effects would need mock or spying unless it changes state checked here
        // For this, we might prefer using a tool like Mockito to spy on `view` methods.
    }
/*
    @Test
    void testActionPerformedWithScreenSize() {
        view = new settingView("ScreenSize");
        controller = new settingController(model, view);
        view.Button3.setSelected(true);  // Selecting screen ratio 2.4

        ActionEvent e = new ActionEvent(view.checkButton, ActionEvent.ACTION_PERFORMED, null);
        controller.actionPerformed(e);

        assertEquals(2.4, model.loadScreenSize());
        // Assuming a method exists to check if the view was disposed or not
    }

 */

    @Test
    void testSetInitialSelection() {
        view = new settingView("Reset");
        controller = new settingController(model, view);
        view = new settingView("ScoreBoardReset");
        controller = new settingController(model, view);
        String[] ScreenSize = {"100" ,"1", "1.6", "2.4"};
        String[] ColorMode = {"default", "false", "true"};
        String[] MOVEMENT = {"default", "ArrowKeys", "WASD"};
        String[] Difficulty = {"default", "easy", "normal", "hard"};

        check(ScreenSize, "ScreenSize");
        check(ColorMode, "ColorMode");
        check(MOVEMENT, "MOVEMENT");
        check(Difficulty, "Difficulty");
    }
    @Test
    void testSettings() {
        // Create a map of setting names and their expected values after actionPerformed
        double[] ScreenSize = {1.0, 1.6, 2.4};
        Map<String, String[]> testCases = new HashMap<>();
        //testCases.put("ScreenSize", new String[]{"1.0", "1.6", "2.4"});
        testCases.put("ColorMode", new String[]{"false", "true"});
        testCases.put("MOVEMENT", new String[]{"ArrowKeys", "WASD"});
        testCases.put("Difficulty", new String[]{"easy", "normal", "hard"});

        // Test ScreenSize setting
        view = new settingView("ScreenSize");
        controller = new settingController(model, view);
        AbstractButton[] screenSizeButtons = {view.Button1, view.Button2, view.Button3};
        for (int i = 0; i < ScreenSize.length; i++) {
            screenSizeButtons[i].setSelected(true);
            controller.actionPerformed(new ActionEvent(view.checkButton, ActionEvent.ACTION_PERFORMED, null));
            assertEquals(ScreenSize[i], model.loadScreenSize());
        }

        // Test ColorMode setting
        view = new settingView("ColorMode");
        controller = new settingController(model, view);
        AbstractButton[] colorModeButtons = {view.Button1, view.Button2};
        for (int i = 0; i < testCases.get("ColorMode").length; i++) {
            colorModeButtons[i].setSelected(true);
            controller.actionPerformed(new ActionEvent(view.checkButton, ActionEvent.ACTION_PERFORMED, null));
            assertEquals(Boolean.parseBoolean(testCases.get("ColorMode")[i]), model.loadColorBlindMode());
        }

        // Test MOVEMENT setting
        view = new settingView("MOVEMENT");
        controller = new settingController(model, view);
        AbstractButton[] movementButtons = {view.Button1, view.Button2};
        for (int i = 0; i < testCases.get("MOVEMENT").length; i++) {
            movementButtons[i].setSelected(true);
            controller.actionPerformed(new ActionEvent(view.checkButton, ActionEvent.ACTION_PERFORMED, null));
            assertEquals(testCases.get("MOVEMENT")[i], model.loadKeySettings());
        }

        // Test Difficulty setting
        view = new settingView("Difficulty");
        controller = new settingController(model, view);
        AbstractButton[] difficultyButtons = {view.Button1, view.Button2, view.Button3};
        for (int i = 0; i < testCases.get("Difficulty").length; i++) {
            difficultyButtons[i].setSelected(true);
            controller.actionPerformed(new ActionEvent(view.checkButton, ActionEvent.ACTION_PERFORMED, null));
            assertEquals(testCases.get("Difficulty")[i], model.loadDifficulty());
        }

        // Test Reset setting
        view = new settingView("Reset");
        controller = new settingController(model, view);
        controller.actionPerformed(new ActionEvent(view.checkButton, ActionEvent.ACTION_PERFORMED, null));
        assertEquals(1.6, model.loadScreenSize());
        assertEquals(false, model.loadColorBlindMode());
        assertEquals("ArrowKeys", model.loadKeySettings());
        assertEquals("normal", model.loadDifficulty());

        // Test ScoreBoardReset setting
        view = new settingView("ScoreBoardReset");
        controller = new settingController(model, view);
        model.setScoreboardFile("test/settings/scoreBoard.txt");
        writeToSettingsFile("test");
        controller.actionPerformed(new ActionEvent(view.checkButton, ActionEvent.ACTION_PERFORMED, null));
        assertEquals(0, new File(settingModel.SCOREBOARD_FILE).length(), "Scoreboard file should be empty after clearing.");
    }
    public void writeToSettingsFile(String content) {
        File file = new File(settingModel.SCOREBOARD_FILE);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    ActionEvent event = new ActionEvent(view.checkButton, ActionEvent.ACTION_PERFORMED, "ScreenSize");
        controller.actionPerformed(event);

        assertEquals(1.0, model.loadScreenSize());
     */


}