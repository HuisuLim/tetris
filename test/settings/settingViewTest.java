package settings;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;

class settingViewTest {

    private settingView view;

    @BeforeEach
    void setUp() {
        // You might want to set up a specific setting view before each test
        // view = new settingView("screenSize");
    }

    @Test
    void testTitleForReset() {
        settingView view;
        view = new settingView("Reset");
        assertEquals("Setting Reset", view.getTitle(), "Title should be 'Setting Reset'");
    }

    @Test
    void testTitleForScreenSize() {
        view = new settingView("screenSize");
        assertEquals("Screen Setting", view.getTitle(), "Title should be 'Screen Setting'");
    }
    @Test
    void testTitleForColorBlindness() {
        view = new settingView("colorBlindness");
        assertEquals("ColorBlindness Setting", view.getTitle(), "Title should be 'ColorBlindness Setting'");
    }

    @Test
    void testTitleForKey() {
        view = new settingView("Key");
        assertEquals("Key Setting", view.getTitle(), "Label should display 'Key Setting'");
    }

    @Test
    void testTitleForDifficulty() {
        view = new settingView("Difficulty");
        assertEquals("Difficulty Setting", view.getTitle(), "Label should display 'Difficulty Setting'");
    }

    @Test
    void testDefaultCaseForUnrecognizedSettingName() {
        // Given an unrecognized setting name
        String unrecognizedSetting = "unknown";
        settingView view = new settingView(unrecognizedSetting);
        // Then verify the title has been set to "Invalid Setting"
        assertEquals("Invalid Setting", view.getTitle(), "The title should reflect an invalid setting");
    }
    @Test
    void testGetSettingName() {
        // Given a settingView instance with a specific setting name
        String expectedSettingName = "screenSize";
        settingView view = new settingView(expectedSettingName);
        // When retrieving the setting name
        String actualSettingName = view.getSettingName();
        // Then it should match the expected setting name
        assertEquals(expectedSettingName, actualSettingName, "The getSettingName method should return the correct setting name.");
    }
}
