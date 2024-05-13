package startscreen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import startscreen.Setting;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JButton;
import java.awt.*;
import java.util.Arrays;

class SettingTest {
    private Setting setting;

    @BeforeEach
    void setUp() {
        setting = new Setting();
        setting.setVisible(true); // 창을 보이게 하여 컴포넌트들이 초기화되도록 함
    }

    @Test
    void testBackButtonProperties() {
        JButton backButton = setting.getBackButton(); // getBackButton 메서드가 public으로 선언되어 있어야 합니다.
        assertNotNull(backButton, "Back button should not be null");
        assertEquals("뒤로가기", backButton.getText(), "Back button text mismatch");
        assertFalse(backButton.isFocusPainted(), "Focus should not be painted");
        assertTrue(backButton.isBorderPainted(), "Border should be painted");
    }

    @Test
    void testButtonsCount() {
        Component[] components = setting.getContentPane().getComponents();
        long buttonsCount = Arrays.stream(components)
                .filter(c -> c instanceof JButton)
                .count();
        assertEquals(7, buttonsCount, "There should be 7 buttons initialized in the setting");
    }

    @Test
    void testButtonActionListeners() {
        JButton backButton = setting.getBackButton(); // getBackButton 메서드가 public으로 선언되어 있어야 합니다.
        assertTrue(backButton.getActionListeners().length > 0, "Back button should have at least one ActionListener");
    }

    @Test
    void testButtonSizeAndPosition() {
        JButton backButton = setting.getBackButton(); // getBackButton 메서드가 public으로 선언되어 있어야 합니다.
        assertEquals(150 * setting.getScreenRatio(), backButton.getWidth(), "Button width does not match expected size");
        assertEquals(30 * setting.getScreenRatio(), backButton.getHeight(), "Button height does not match expected size");
    }
}