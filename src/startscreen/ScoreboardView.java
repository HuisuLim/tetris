package startscreen;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class ScoreboardView extends JFrame {
    // 스코어보드를 보여주는 프레임 생성자
    public ScoreboardView() {
        setTitle("Scoreboard");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel();
        JTable scoreboardTable = new JTable(model);
        scoreboardTable.setDefaultEditor(Object.class, null);

        String[] columnNames = {"Rank", "Name", "Score", "Difficulty", "Mode"};
        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }

        ScoreboardModel.readScoreboard(model, "scoreboard.txt");
        applyCenterAlignment(scoreboardTable);

        JScrollPane scrollPane = new JScrollPane(scoreboardTable);
        add(scrollPane);

        addKeyListenerToHideScoreboard(this);

        setVisible(true);
    }

    // 테이블 내용을 가운데 정렬하는 메서드
    public static void applyCenterAlignment(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    // ESC 키를 눌렀을 때 프레임을 숨기는 이벤트를 추가하는 메서드
    public static void addKeyListenerToHideScoreboard(JFrame frame) {
        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    frame.setVisible(false);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
    }
}
