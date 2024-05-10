package startscreen;

import settings.settingModel;
import javax.swing.*;
import java.awt.*;

public class BattlePlayFrame extends JFrame {
    private final settingModel data = new settingModel();
    private final double screenSize = data.loadScreenSize(); // screenSize 값 로드

    public BattlePlayFrame() {
        setTitle("Battle Play Frame");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize((int)(screenSize * 40 * 20), (int)(screenSize * 22 * 20));  // 가로 길이를 더 늘림
        setLocationRelativeTo(null);

        initUI();
        setVisible(true);
    }

    private void initUI() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;

        // 패널 구성 설정
        String[] panelTitles = {"1P 게임 창", "1P 점수 창", "아이템 설명 창", "2P 게임 창", "2P 점수 창"};
        double[] panelWeights = {0.4, 0.15, 0.1, 0.4, 0.15}; // 가중치 설정

        for (int i = 0; i < panelTitles.length; i++) {
            gbc.weightx = panelWeights[i];
            gbc.gridx = i;
            JPanel panel = createPanel(panelTitles[i]);
            mainPanel.add(panel, gbc);
        }

        add(mainPanel);
    }

    private JPanel createPanel(String title) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.setBackground(new Color(240, 240, 240));

        // 레이블 추가
        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 20));
        panel.add(label, BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {
        new BattlePlayFrame();
    }
}
