package startscreen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import playscreen.MultiPlayFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static org.junit.Assert.*;

public class BattleModeTest {

    private BattleMode battleMode;

    @Before
    public void setUp() throws Exception {
        // BattleMode 인스턴스를 생성합니다.
        SwingUtilities.invokeAndWait(() -> battleMode = new BattleMode());
    }

    @After
    public void tearDown() throws Exception {
        // 테스트가 끝난 후 리소스를 정리합니다.
        SwingUtilities.invokeAndWait(() -> battleMode.dispose());
    }

    @Test
    public void testBackButton() throws Exception {
        // 뒤로가기 버튼을 가져옵니다.
        JButton backButton = battleMode.getBackButton();

        // 뒤로가기 버튼의 액션 이벤트를 만듭니다.
        ActionEvent event = new ActionEvent(backButton, ActionEvent.ACTION_PERFORMED, "뒤로가기");

        // 액션 리스너를 호출합니다.
        for (ActionListener listener : backButton.getActionListeners()) {
            listener.actionPerformed(event);
        }

        // BattleMode 창이 닫혔는지 확인합니다.
        SwingUtilities.invokeAndWait(() -> assertFalse(battleMode.isVisible()));
    }

    @Test
    public void testNormalModeButton() throws Exception {
        // 일반모드 버튼을 가져옵니다.
        JButton normalModeButton = battleMode.getNormalModeButton();

        // 일반모드 버튼의 액션 이벤트를 만듭니다.
        ActionEvent event = new ActionEvent(normalModeButton, ActionEvent.ACTION_PERFORMED, "일반모드");

        // 액션 리스너를 호출합니다.
        for (ActionListener listener : normalModeButton.getActionListeners()) {
            listener.actionPerformed(event);
        }

    }

    @Test
    public void testItemModeButton() throws Exception {
        // 아이템 모드 버튼을 가져옵니다.
        JButton itemModeButton = battleMode.getItemModeButton();

        // 아이템 모드 버튼의 액션 이벤트를 만듭니다.
        ActionEvent event = new ActionEvent(itemModeButton, ActionEvent.ACTION_PERFORMED, "아이템 모드");

        // 액션 리스너를 호출합니다.
        for (ActionListener listener : itemModeButton.getActionListeners()) {
            listener.actionPerformed(event);
        }


    }

    @Test
    public void testTimeLimitModeButton() throws Exception {
        // 시간제한 모드 버튼을 가져옵니다.
        JButton timeLimitModeButton = battleMode.getTimeLimitModeButton();

        // 시간제한 모드 버튼의 액션 이벤트를 만듭니다.
        ActionEvent event = new ActionEvent(timeLimitModeButton, ActionEvent.ACTION_PERFORMED, "시간제한 모드");

        // 액션 리스너를 호출합니다.
        for (ActionListener listener : timeLimitModeButton.getActionListeners()) {
            listener.actionPerformed(event);
        }


    }
}
