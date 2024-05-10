package settings;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.Properties;

class SettingModelTest {

    private settingModel model;
    private final Properties properties = new Properties();


    @BeforeEach
    void setUp() throws IOException {
        model = new settingModel();
        model.setSettingsFile("test/settings/settings.properties");

    }

    @Test
    void testLoadKeySettings() throws Exception {
        model.saveSetting("MOVEMENT", "WASD");
        String result = model.loadKeySettings();
        assertEquals("WASD", result, "Should load 'WASD' as the control setting");
        String result2 = model.loadKeySettings();
        assertEquals("ArrowKeys", result2, "Should load 'ArrowKeys' as the control setting");


    }

    @Test
    void testSaveSetting() throws Exception {
        properties.setProperty("MOVEMENT", "ArrowKeys");
        model.saveSetting("MOVEMENT", "ArrowKeys");
        String result = model.loadKeySettings();
        assertEquals("WASD", result, "Should load saved 'WASD' as the control setting");

        // Since we cannot directly check what is written to FileOutputStream, this would ideally be verified by reading back the file,
        // but since we are mocking, this step is a bit contrived and focuses on process rather than actual data verification.
    }
}
