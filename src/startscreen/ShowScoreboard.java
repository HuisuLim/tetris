package startscreen;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class ShowScoreboard extends JFrame {
    public ShowScoreboard() {
        setTitle("Scoreboard");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel();
        JTable scoreboardTable = new JTable(model);

        String[] columnNames = {"Rank", "Name", "Score", "Difficulty", "Mode"};
        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }

        readScoreboard(model, "scoreboard.txt");
        applyCenterAlignment(scoreboardTable);

        JScrollPane scrollPane = new JScrollPane(scoreboardTable);
        add(scrollPane);

        addKeyListenerToHideScoreboard(this);

        setVisible(true);
    }

    public static void readScoreboard(DefaultTableModel model, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    String name = parts[0].trim();
                    int score = Integer.parseInt(parts[1].trim());
                    String difficulty = parts[2].trim();
                    String mode = parts[3].trim();
                    addData(model, name, score, difficulty, mode);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void addData(DefaultTableModel model, String name, int score, String difficulty, String mode) {
        int rank = 1;
        if (model.getRowCount() > 0) {
            rank = (int) model.getValueAt(model.getRowCount() - 1, 0) + 1;
        }

        Object[] rowData = {rank, name, score, difficulty, mode};
        model.addRow(rowData);
    }

    public static void applyCenterAlignment(JTable table) {
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

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

    public static class ClearScoreboard {
        public static void clear(String filePath) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write("");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
