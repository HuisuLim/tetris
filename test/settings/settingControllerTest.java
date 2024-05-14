package settings;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        for(int i = 0; i<length; i++){
            model.saveSetting(settingName, String.valueOf(settingArray[i]));
            System.out.println(settingArray[i]);
            controller = new settingController(model, view);
            buttonCheck(i,length, view);
        }
    }

    @BeforeEach
    void setUp() {
        model = new settingModel();
        model.setSettingsFile("test/settings/settings.properties");
        model.saveSetting("MOVEMENT","ArrowKeys");
        model.saveSetting("DIFFICULTY", "hard");
        model.saveSetting("ScreenSize", "1.6");
        model.saveSetting("ColorMode", "false");
        model.saveSetting("Difficulty", "normal");
    }
    @Test
    void testInitialSelectionWithDefaultSettings() {
        // Assuming default is 1.6 for screen size
        String[] ScreenSize = {"100" ,"1", "1.6", "2.4"};
        String[] colorBlindness = {"default", "true", "false"};
        check(ScreenSize, "ScreenSize");
        view = new settingView("colorBlindness");
        check(colorBlindness, "colorBlindness");
    }
    @Test
    void actionPerformed() {
    }
}