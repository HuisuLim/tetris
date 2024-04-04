package temp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class SettingsWindow extends JFrame implements KeyListener {
    private List<SettingItem> settingItems; // 설정 항목을 담는 리스트
    private int currentIndex = 0; // 현재 선택된 설정 항목의 인덱스
    private JLabel[] settingLabels; // 설정 항목을 표시하는 레이블 배열
    private JButton saveButton;
    private JButton cancelButton;
    private TetrisProperties tetrisProperties;

    public SettingsWindow() {
        tetrisProperties = new TetrisProperties();
        settingItems = new ArrayList<>(tetrisProperties.getAllSettings().values());
        initializeUI();
        addKeyListener(this);
        setFocusable(true);
        setVisible(true);
    }

    private void initializeUI() {
        setTitle("설정");
        setSize(500, 500);
        setLayout(new GridLayout(settingItems.size() + 1, 1)); // 설정 항목 + 버튼
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        settingLabels = new JLabel[settingItems.size()];
        for (int i = 0; i < settingItems.size(); i++) {
            settingLabels[i] = new JLabel(formatSettingLabel(i), SwingConstants.CENTER);
            add(settingLabels[i]);
        }

        JPanel buttonPanel = new JPanel();
        saveButton = new JButton("저장");
        cancelButton = new JButton("취소");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel);

        updateFocus();
    }

    private String formatSettingLabel(int index) {
        SettingItem item = settingItems.get(index);
        // 선택된 항목에는 화살표를 추가하여 표시
        String prefix = currentIndex == index ? "<- " : "";
        String suffix = currentIndex == index ? " ->" : "";
        return item.getName() + ": " + prefix + item.getCurrentValue() + suffix;
    }

    private void updateFocus() {
        for (int i = 0; i < settingItems.size(); i++) {
            settingLabels[i].setText(formatSettingLabel(i));
            settingLabels[i].setForeground(currentIndex == i ? Color.RED : Color.BLACK);
        }
        saveButton.setForeground(currentIndex == settingItems.size() ? Color.RED : Color.BLACK);
        cancelButton.setForeground(currentIndex == settingItems.size() + 1 ? Color.RED : Color.BLACK);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN) {
            // 위, 아래 키 처리 로직 유지
            handleUpDownKeys(keyCode);
        } else if ((keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) && currentIndex < settingItems.size()) {
            // 왼쪽, 오른쪽 키로 설정 값 변경
            adjustSettingValue(keyCode);
        } else if (keyCode == KeyEvent.VK_ENTER) {
            // Enter 키 처리 로직 유지
            if (currentIndex == settingItems.size()) {
                saveAndClose();
            } else if (currentIndex == settingItems.size() + 1) {
                closeWithoutSaving();
            }
        }
        updateFocus();
    }

    private void handleUpDownKeys(int keyCode) {
        if (keyCode == KeyEvent.VK_UP) {
            currentIndex = Math.max(0, currentIndex - 1);
        } else if (keyCode == KeyEvent.VK_DOWN) {
            currentIndex = Math.min(currentIndex + 1, settingItems.size() + 1); // +1 for save and cancel
        }
    }

    private void adjustSettingValue(int keyCode) {
        SettingItem currentItem = settingItems.get(currentIndex);
        List<String> options = currentItem.getOptions();
        int optionIndex = options.indexOf(currentItem.getCurrentValue());
        if (keyCode == KeyEvent.VK_LEFT) {
            optionIndex = (optionIndex - 1 + options.size()) % options.size();
        } else { // KeyEvent.VK_RIGHT
            optionIndex = (optionIndex + 1) % options.size();
        }
        currentItem.setCurrentValue(options.get(optionIndex));
        tetrisProperties.setProperty(currentItem.getName(), currentItem.getCurrentValue()); // 선택된 설정 값을 업데이트
    }

    private void saveAndClose() {
        tetrisProperties.saveProperties(); // 변경 사항을 저장하고
        dispose(); // 창을 닫음
    }

    private void closeWithoutSaving() {
        dispose(); // 변경 사항 없이 창을 닫음
    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SettingsWindow::new);
    }
}
