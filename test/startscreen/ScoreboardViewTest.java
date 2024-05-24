package startscreen;

import org.junit.jupiter.api.Test;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;


import static org.junit.jupiter.api.Assertions.*;

public class ScoreboardViewTest {
    @Test
    void testScoreboardViewConstructor() {
        // ScoreboardView 생성자 호출
        ScoreboardView scoreboardView = new ScoreboardView();

        // ScoreboardView가 null이 아닌지 확인
        assertNotNull(scoreboardView);
        // ScoreboardView의 visibility 확인
        assertTrue(scoreboardView.isVisible());

        // ScoreboardView의 모든 컴포넌트 확인
        Component[] components = scoreboardView.getContentPane().getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JScrollPane);
        JScrollPane scrollPane = (JScrollPane) components[0];
        assertTrue(scrollPane.getViewport().getView() instanceof JTable);

        // ScoreboardView의 모든 컬럼 확인
        JTable table = (JTable) scrollPane.getViewport().getView();
        assertEquals(5, table.getColumnCount());
        assertEquals("Rank", table.getColumnName(0));
        assertEquals("Name", table.getColumnName(1));
        assertEquals("Score", table.getColumnName(2));
        assertEquals("Difficulty", table.getColumnName(3));
        assertEquals("Mode", table.getColumnName(4));
    }

    @Test
    void testApplyCenterAlignment() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Column1");
        model.addColumn("Column2");
        JTable table = new JTable(model);

        // 가운데 정렬 적용
        ScoreboardView.applyCenterAlignment(table);

        // 가운데 정렬 후 확인
        DefaultTableCellRenderer renderer1 = (DefaultTableCellRenderer) table.getColumn("Column1").getCellRenderer();
        assertEquals(SwingConstants.CENTER, renderer1.getHorizontalAlignment());

        DefaultTableCellRenderer renderer2 = (DefaultTableCellRenderer) table.getColumn("Column2").getCellRenderer();
        assertEquals(SwingConstants.CENTER, renderer2.getHorizontalAlignment());
    }


    @Test
    void testAddKeyListenerToHideScoreboard() {
        // JFrame 생성
        JFrame frame = new JFrame();

        // ESC 키를 눌렀을 때 프레임이 숨겨지는지 확인
        ScoreboardView.addKeyListenerToHideScoreboard(frame);
        KeyEvent escapeEvent = new KeyEvent(frame, KeyEvent.KEY_PRESSED, System.currentTimeMillis(), 0, KeyEvent.VK_ESCAPE, KeyEvent.CHAR_UNDEFINED);
        assertFalse(frame.isVisible());
    }
}
