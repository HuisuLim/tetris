package settings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class settingMainTest {
    @Test
    void main() {
    }

    @Test
    void launchSettingScreen() {
        settingView view = settingMain.launchSettingScreen("ScreenSize");

        // settingName 확인
        assertEquals("ScreenSize", view.getSettingName());

    }
}