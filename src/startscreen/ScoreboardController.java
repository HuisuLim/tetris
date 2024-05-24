package startscreen;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class ScoreboardController extends JFrame {
    private DefaultTableModel model;
    private final JTable scoreboard;

    private String currentDifficulty;
    private String currentMode;

    public ScoreboardController(String name, int score, String difficulty, String mode) {
        setTitle("Scoreboard | 메뉴로 돌아가려면 esc를 눌러주세요");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        String[] columnNames = {"Rank", "Name", "Score", "Difficulty", "Mode"};
        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }

        scoreboard = new JTable(model) {
            @Override
            public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
                super.changeSelection(getSelectedRow(), columnIndex, toggle, extend);
            }
        };

        ScoreboardView.applyCenterAlignment(scoreboard);

        JScrollPane scrollPane = new JScrollPane(scoreboard);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

        ScoreboardModel.readScoreboard(model, "scoreboard.txt");

        ScoreboardModel.addDataDescending(name, score, difficulty, mode, model);
        ScoreboardModel.saveDataToFile(model, "scoreboard.txt");

        highlightNameAndScore(name, score);

        currentDifficulty = difficulty;
        currentMode = mode;

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    new StartMenu().setVisible(true);
                    dispose();
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this);
                }
                return false;
            }
        });
    }

    public void changeDifficulty(String newDifficulty) {
        currentDifficulty = newDifficulty;
        updateScoreboard();
    }

    public void changeMode(String newMode) {
        currentMode = newMode;
        updateScoreboard();
    }

    public void updateScoreboard() {

        scoreboard.clearSelection();
        for (int i = 0; i < model.getRowCount(); i++) {
            String difficulty = (String) model.getValueAt(i, 3);
            String mode = (String) model.getValueAt(i, 4);
            if (difficulty.equals(currentDifficulty) && mode.equals(currentMode)) {
                scoreboard.addRowSelectionInterval(i, i);
            }
        }
    }

    private void highlightNameAndScore(String name, int score) {
        Timer timer = new Timer(500, new ActionListener() {
            boolean flag = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag) {
                    scoreboard.setSelectionForeground(Color.BLUE);
                } else {
                    scoreboard.setSelectionForeground(scoreboard.getForeground());
                }
                flag = !flag;
            }
        });
        timer.setRepeats(true);
        timer.start();

        for (int i = 0; i < model.getRowCount(); i++) {
            if (model.getValueAt(i, 1).equals(name) && (int) model.getValueAt(i, 2) == score) {
                scoreboard.setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    public String getCurrentDifficulty() {
        return currentDifficulty;
    }

    public String getCurrentMode() {
        return currentMode;
    }

    public JTable getScoreboard() {
        return scoreboard;
    }

    public void setModel(DefaultTableModel model) {
        this.model = model;
    }

    public DefaultTableModel getModel() {
        return model;
    }
}


