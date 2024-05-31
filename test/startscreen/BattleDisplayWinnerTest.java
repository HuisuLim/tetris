package startscreen;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import playscreen.panels.BattleDisplayWinner;

import javax.swing.*;

import static org.junit.Assert.*;

public class BattleDisplayWinnerTest {

    private JFrame frame;
    private JLabel winnerLabel;

    @Before
    public void setUp() throws Exception {
        // 초기화 작업이 필요한 경우 여기에 작성합니다.
        // 각 테스트 메소드가 실행되기 전에 호출됩니다.
    }

    @After
    public void tearDown() {
        // 각 테스트 메소드가 실행된 후 호출됩니다.
        if (frame != null) {
            frame.dispose();
        }
    }

    @Test
    public void testPlayer1Wins() {
        frame = BattleDisplayWinner.displayWinner(10, 5);
        winnerLabel = (JLabel) frame.getContentPane().getComponent(0);
        assertEquals("Player 1 win!", winnerLabel.getText());
    }

    @Test
    public void testPlayer2Wins() {
        frame = BattleDisplayWinner.displayWinner(5, 10);
        winnerLabel = (JLabel) frame.getContentPane().getComponent(0);
        assertEquals("Player 2 win!", winnerLabel.getText());
    }

    @Test
    public void testTie() {
        frame = BattleDisplayWinner.displayWinner(10, 10);
        winnerLabel = (JLabel) frame.getContentPane().getComponent(0);
        assertEquals("It's a tie!", winnerLabel.getText());
    }
}
