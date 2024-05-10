package settings;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class settingController implements ActionListener {
    private settingModel model;
    private settingView view;

    public settingController(settingModel model, settingView view) {
        this.model = model;
        this.view = view;
        this.view.Button1.addActionListener(this);
        this.view.Button2.addActionListener(this);
        this.view.Button3.addActionListener(this);
        this.view.checkButton.addActionListener(this);

        setInitialSelection();
        setupKeyBindings();
    }

    private void setInitialSelection() {
        String settingName = view.getSettingName();
        switch (settingName) {
            case "screenSize":
                double screenSize = model.loadScreenSize();
                if (screenSize == 1) {
                    view.Button1.setSelected(true);
                } else if (screenSize == 1.6) {
                    view.Button2.setSelected(true);
                } else if (screenSize == 2.4) {
                    view.Button3.setSelected(true);
                } else {
                    view.Button2.setSelected(true);
                }
                break;

            case "colorBlindness":
                boolean isColorBlind = model.loadColorBlindMode();
                if (isColorBlind) {
                    view.Button2.setSelected(true);
                } else {
                    view.Button1.setSelected(true);
                }
                break;

            case "Key":
                String keySetting = model.loadKeySettings();
                switch (keySetting) {
                    case "ArrowKeys":
                        view.Button1.setSelected(true);
                        break;
                    case "WASD":
                        view.Button2.setSelected(true);
                        break;
                    default:
                        // Default to medium size if no valid setting found
                        view.Button1.setSelected(true);
                        break;
                }
                break;

            case "Difficulty":
                String difficulty = model.loadDifficulty();
                switch (difficulty) {
                    case "easy":
                        view.Button1.setSelected(true);
                        break;
                    case "normal":
                        view.Button2.setSelected(true);
                        break;
                    case "hard":
                        view.Button3.setSelected(true);
                        break;
                    default:
                        // Default to normal difficulty if no valid setting found
                        view.Button2.setSelected(true);
                        break;
                }
                break;

            default:
                // Handle any other unrecognized settings, potentially with a default or error
                // This case could log an error or provide feedback to the user
                System.out.println("Unrecognized setting: " + settingName);
                break;
        }
    }

    private void setupKeyBindings() {
        // Set up key bindings for the panel
        view.panel.setFocusable(true);
        view.panel.requestFocusInWindow();
        view.panel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();

                if (keyCode == model.getLeftKey() || keyCode == model.getRightKey()) {
                    switchRadioButton(keyCode, model, view.checkButton, view.Button1, view.Button2, view.Button3);
                }
            }
        });

        // Binding for the "check" button
        view.checkButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "checkPressed");
        view.checkButton.getActionMap().put("checkPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.checkButton.doClick();
            }
        });

        // Binding for "ESC" key to close the window
        view.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "escapePressed");
        view.getRootPane().getActionMap().put("escapePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }


    private void switchRadioButton(int keyCode, settingModel model, JButton checkButton, JRadioButton... radioButtons) {
        int index = 0;
        if (view.Button1.isSelected()) {
            index = 0;
        } else if (view.Button2.isSelected()) {
            index = 1;
        } else {
            index = 2;
        }
        if (keyCode == model.getLeftKey()) {
            // 왼쪽방향키
            int targetIndex = (index - 1 + radioButtons.length) % radioButtons.length;
            radioButtons[targetIndex].setSelected(true);
        } else if (keyCode == model.getRightKey()) {
            // 오른쪽방향키
            int targetIndex = (index + 1) % radioButtons.length;
            radioButtons[targetIndex].setSelected(true);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String settingName = view.getSettingName();
        switch (settingName) {
            case "screenSize":
                double screenRatio = 1.6; // Default screen ratio
                if (e.getSource() == view.checkButton) {
                    if (view.Button1.isSelected()) {
                        screenRatio = 1;
                    } else if (view.Button2.isSelected()) {
                        screenRatio = 1.6;
                    } else if (view.Button3.isSelected()) {
                        screenRatio = 2.4;
                    }
                }
                model.saveSetting("ScreenSize", String.valueOf(screenRatio));
                view.dispose(); // Close the settings view
                break;

            case "colorBlindness":
                if (e.getSource() == view.checkButton) {
                    boolean isColorBlindMode = view.Button2.isSelected();
                    model.saveSetting("ColorMode", String.valueOf(isColorBlindMode));
                    view.dispose(); // Close the settings view
                }
                break;

            case "Key":
                if (e.getSource() == view.checkButton) {
                    String movement = view.Button1.isSelected() ? "ArrowKeys" : "WASD";
                    model.saveSetting("MOVEMENT", movement);
                    view.dispose(); // Close the settings view
                }
                break;

            case "Difficulty":
                if (e.getSource() == view.checkButton) {
                    String difficulty = "normal"; // Default difficulty
                    if (view.Button1.isSelected()) {
                        difficulty = "easy";
                    } else if (view.Button2.isSelected()) {
                        difficulty = "normal";
                    } else if (view.Button3.isSelected()) {
                        difficulty = "hard";
                    }
                    model.saveSetting("Difficulty", difficulty);
                    view.dispose(); // Close the settings view
                }
                break;
            case "Reset":
                if (e.getSource() == view.checkButton) {
                    double screenRatioDefault = 1.6;
                    boolean isColorBlindMode = false;
                    String keySetting = "ArrowKeys";
                    String difficulty = "normal";
                    model.saveSetting("ScreenSize", String.valueOf(screenRatioDefault));
                    model.saveSetting("ColorMode", String.valueOf(isColorBlindMode));
                    model.saveSetting("MoveMent", keySetting);
                    model.saveSetting("Difficulty", difficulty);
                    view.dispose(); // 설정 화면 종료
                }

            default:
                // Optionally handle unrecognized setting names
                System.out.println("Unrecognized setting: " + settingName);
                break;
        }
    }
}
