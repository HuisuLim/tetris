package settings;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class settingControllerTest {
    private settingController controller;
    private settingModel model;
    private settingView view;
    private JButton mockButton;
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

    @Test
    void testInitialSelectionWithDefaultSettings() {
        // Assuming default is 1.6 for screen size
        String[] ScreenSize = {"100" ,"1", "1.6", "2.4"};
        String[] ColorMode = {"default", "false", "true"};
        String[] MOVEMENT = {"default", "ArrowKeys", "WASD"};
        String[] Difficulty = {"default", "easy", "normal", "hard"};
        check(ScreenSize, "ScreenSize");
        check(ColorMode, "ColorMode");
        check(MOVEMENT, "MOVEMENT");
        check(Difficulty, "Difficulty");
    }



}