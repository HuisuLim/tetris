package startscreen;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class ScoreInput extends JFrame {
    private DefaultTableModel model;
    private JTable scoreboard;

    private String currentDifficulty;
    private String currentMode;

    public ScoreInput(String name, int score, String difficulty, String mode) {
        setTitle("Scoreboard | 메뉴로 돌아가려면 esc를 눌러주세요");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        // JTable에 사용할 모델 생성
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // 랭킹, 이름, 점수, 난이도, 모드 컬럼 추가
        String[] columnNames = {"Rank", "Name", "Score", "Difficulty", "Mode"};
        for (String columnName : columnNames) {
            model.addColumn(columnName);
        }

        // 가운데 정렬을 위한 셀 렌더러 설정
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER); // 셀 내용을 가운데 정렬로 설정

        // JTable 생성 및 각 컬럼 너비 설정
        scoreboard = new JTable(model) {
            @Override
            public void changeSelection(int rowIndex, int columnIndex, boolean toggle, boolean extend) {
                // 행 선택을 비활성화하여 위아래로 이동할 수 없게 만듭니다.
                super.changeSelection(getSelectedRow(), columnIndex, toggle, extend);
            }
        };

        int[] columnWidths = {10, 150, 50, 80, 80};
        for (int i = 0; i < columnWidths.length; i++) {
            scoreboard.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
            scoreboard.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // 스코어보드를 패널에 넣고, 프레임 가운데 배치
        JScrollPane scrollPane = new JScrollPane(scoreboard);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);

// 파일에서 스코어보드 데이터 읽기
        ScoreboardManager.readScoreboard(model, "scoreboard.txt");

        // 새 데이터를 스코어보드에 추가하고 파일에 저장
        ScoreboardManager.addDataDescending(name, score, difficulty, mode, model);
        ScoreboardManager.saveDataToFile(model, "scoreboard.txt");

        // 이름과 점수를 파란색으로 강조
        highlightNameAndScore(name, score);

        // 현재 게임 난이도와 모드 설정
        currentDifficulty = difficulty;
        currentMode = mode;

        // ESC 키를 눌렀을 때 창을 종료하는 이벤트 추가
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    new StartMenu().setVisible(true);
                    dispose(); // 현재 창을 닫음
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().removeKeyEventDispatcher(this);
                }
                return false;
            }
        });
    }

    // 게임 난이도 변경 메서드
    public void changeDifficulty(String newDifficulty) {
        currentDifficulty = newDifficulty;
        // 변경된 난이도에 따라 스코어보드를 업데이트하는 로직 추가
        updateScoreboard();
    }

    // 게임 모드 변경 메서드
    public void changeMode(String newMode) {
        currentMode = newMode;
        // 변경된 모드에 따라 스코어보드를 업데이트하는 로직 추가
        updateScoreboard();
    }

    // 스코어보드 업데이트 메서드
    private void updateScoreboard() {
        // 모든 행을 선택 해제
        scoreboard.clearSelection();
        // 현재 난이도와 모드에 맞는 행만 선택
        for (int i = 0; i < model.getRowCount(); i++) {
            String difficulty = (String) model.getValueAt(i, 3);
            String mode = (String) model.getValueAt(i, 4);
            if (difficulty.equals(currentDifficulty) && mode.equals(currentMode)) {
                scoreboard.addRowSelectionInterval(i, i);
            }
        }
    }


    // 이름과 점수를 파란색으로 강조하는 메서드
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

}
