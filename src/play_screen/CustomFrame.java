package play_screen;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class CustomFrame extends JFrame {
    private PausePanel pausePanel;

    public CustomFrame() {
        setTitle("Custom Frame");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 800));
        add(layeredPane, BorderLayout.CENTER);

        // 왼쪽 빨간색 패널
        JPanel redPanel = new JPanel();
        redPanel.setBackground(Color.RED);
        redPanel.setBounds(0, 0, 400, 800); // 왼쪽 절반

        // 오른쪽 패널 (파란색, 노란색 패널 포함)
        JPanel rightPanel = new JPanel(new GridLayout(2, 1));
        rightPanel.setBounds(400, 0, 400, 800); // 오른쪽 절반

        JPanel bluePanel = new JPanel();
        bluePanel.setBackground(Color.BLUE);
        rightPanel.add(bluePanel);

        JPanel yellowPanel = new JPanel();
        yellowPanel.setBackground(Color.YELLOW);
        rightPanel.add(yellowPanel);

        layeredPane.add(redPanel, Integer.valueOf(1));
        layeredPane.add(rightPanel, Integer.valueOf(1));

        // PausePanel 설정
        pausePanel = new PausePanel();
        pausePanel.setBounds(300, 350, 200, 100); // 중앙에 위치
        pausePanel.setVisible(false);
        layeredPane.add(pausePanel, Integer.valueOf(2)); // 더 높은 레이어에 추가

        // KeyBindings 설정
        setKeyBindings();
    }

    private void setKeyBindings() {
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"), "togglePause");
        getRootPane().getActionMap().put("togglePause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                togglePausePanel();
            }
        });
    }

    private void togglePausePanel() {
        pausePanel.setVisible(!pausePanel.isVisible());
        pausePanel.requestFocus();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CustomFrame().setVisible(true));
    }
}
