
package startscreen;

import settings.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SettingTest extends JFrame {
    private double screenRatio = StartMenu.screenRatio; //화면 비율 조절
    private JButton backButton = new JButton();

    private int settingIndex = 0;

    public SettingTest() {
        setTitle("게임 설정");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize((int)(500 * screenRatio), (int)(400 * screenRatio));
        setLocationRelativeTo(null); // 화면 중앙에 표시
        setLayout(null); // Layout Manager를 사용하지 않음
        setResizable(false); // 창 크기 변경 불가능 설정 추가

        //버튼 너비,높이,간격 설정
        int buttonWidth = (int)(150 * screenRatio);
        int buttonHeight = (int)(30 * screenRatio);
        int frameWidth = getWidth();
        int frameHeight = getHeight();
        int verticalSpacing = (int)(20 * screenRatio); // 버튼 간의 세로 간격을 40px의 비율로 조정

        // 뒤로가기 버튼 생성 및 설정
        backButton = new JButton("뒤로가기");
        backButton.setBounds((int)(5 * screenRatio), (int)(5 * screenRatio), buttonWidth, buttonHeight); // 화면 상단 왼쪽에 배치
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // 현재 창 숨기기
                dispose(); // 현재 창 자원 해제
                new StartMenu().setVisible(true); // StartMenu 인스턴스 생성 및 보이기
            }
        });
        add(backButton); // 뒤로가기 버튼을 프레임에 추가

        // 버튼 생성 및 설정
        JButton button1 = new JButton("조작키 설정");
        button1.setBounds((frameWidth - buttonWidth) / 2, (int)(25 * screenRatio) + 0 * verticalSpacing, buttonWidth, buttonHeight); // 화면 가운데를 기준으로 행 정렬
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                settingView view = settingMain.launchSettingScreen("Key");
            }
        });

        JButton button2 = new JButton("색맹 모드");
        button2.setBounds((frameWidth - buttonWidth) / 2, (int)(60 * screenRatio)+ 1 * verticalSpacing, buttonWidth, buttonHeight);
        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                settingView view = settingMain.launchSettingScreen("colorBlindness");
            }
        });

        JButton button3 = new JButton("설정 초기화");
        button3.setBounds((frameWidth - buttonWidth) / 2, (int)(95 * screenRatio)+ 2 * verticalSpacing, buttonWidth, buttonHeight);
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                settingView view = settingMain.launchSettingScreen("Reset");
                view.addWindowListener(new WindowAdapter() {
                    public void windowClosed(WindowEvent e) {
                        StartMenu.setScreenRatio();
                        // SettingScreen이 닫힐 때 설정된 새로운 비율을 가져와 적용
                        // 새로운 비율로 설정 창 새로고침
                        dispose();
                        new SettingTest().setVisible(true);
                    }
                });
            }
        });

        JButton button4 = new JButton("화면 크기 조절");
        button4.setBounds((frameWidth - buttonWidth) / 2, (int)(130 * screenRatio)+ 3 * verticalSpacing, buttonWidth, buttonHeight);
        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                settingView view = settingMain.launchSettingScreen("screenSize");
                view.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        StartMenu.setScreenRatio();
                        // SettingScreen이 닫힐 때 설정된 새로운 비율을 가져와 적용
                        // 새로운 비율로 설정 창 새로고침
                        dispose();
                        new SettingTest().setVisible(true);
                    }
                });
            }
        });

        JButton button5 = new JButton("스코어보드 초기화");
        button5.setBounds((frameWidth - buttonWidth) / 2, (int)(165 * screenRatio)+ 4 * verticalSpacing, buttonWidth, buttonHeight);
        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // 새로운 ScoreboardReset 화면을 여는 로직
                new ScoreboardReset().setVisible(true);
            }
        });

        // 난이도 설정 버튼 생성 및 설정
        JButton difficultyButton = new JButton("난이도 설정");
        difficultyButton.setBounds((frameWidth - buttonWidth) / 2, (int)(200 * screenRatio)+ 5 * verticalSpacing, buttonWidth, buttonHeight);

        // 난이도 설정 버튼에 액션 리스너
        difficultyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                settingView view = settingMain.launchSettingScreen("Difficulty");
            }
        });

        // 버튼을 프레임에 추가
        add(button1);
        add(button2);
        add(button3);
        add(button4);
        add(button5);
        add(difficultyButton);

        // 포커스 이동 가능하도록 설정
        difficultyButton.setFocusable(true);

        // 방향키에 의한 포커스 이동 설정에 새 버튼 추가
        setupDirectionalFocusTraversal(backButton, button1, button2, button3, button4, button5, difficultyButton);

        // 버튼의 배경색과 인터랙션에 따른 색상 변경 설정
        configureButton(difficultyButton);

        // 포커스 이동 가능하도록 설정
        backButton.setFocusable(true);
        button1.setFocusable(true);
        button2.setFocusable(true);
        button3.setFocusable(true);
        button4.setFocusable(true);
        button5.setFocusable(true);

        // 컴포넌트(버튼)에 대한 ActionMap과 InputMap 설정
        setupKeyBindings(backButton);
        setupKeyBindings(button1);
        setupKeyBindings(button2);
        setupKeyBindings(button3);
        setupKeyBindings(button4);
        setupKeyBindings(button5);
        setupKeyBindings(difficultyButton); // 난이도 버튼에 대한 키 바인딩 설정

        // 방향키에 의한 포커스 이동 설정
        setupDirectionalFocusTraversal(backButton, button1, button2, button3, button4, button5, difficultyButton);
        // 버튼의 배경색과 인터랙션에 따른 색상 변경 설정
        configureButton(backButton);
        configureButton(button1);
        configureButton(button2);
        configureButton(button3);
        configureButton(button4);
        configureButton(button5);
        configureButton(difficultyButton);

    }
    private void configureButton(JButton button) {
        Color defaultColor = Color.LIGHT_GRAY; // 기본 배경색 설정
        Color focusColor = defaultColor.darker(); // 포커스나 마우스 오버 시 사용할 색상
        button.setBackground(defaultColor); // 버튼의 배경색 설정
        button.setFocusPainted(false);
        button.setBorderPainted(true); // 버튼의 테두리를 보이도록 설정

        // 마우스 리스너
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(focusColor); // 마우스가 올라갔을 때 색상을 진하게
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!button.isFocusOwner()) { // 마우스가 내려갔을 때, 버튼이 포커스를 가지고 있지 않다면 원래 색상으로 복원
                    button.setBackground(defaultColor);
                }
            }
        });

        // 포커스 리스너
        button.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                button.setBackground(focusColor); // 포커스를 얻으면 색상을 진하게 변경
            }

            @Override
            public void focusLost(FocusEvent e) {
                button.setBackground(defaultColor); // 포커스를 잃으면 원래 색상으로 복원
            }
        });
    }

    private void setupKeyBindings(JButton button) {
        // InputMap과 ActionMap을 가져옵니다.
        InputMap inputMap = button.getInputMap(JComponent.WHEN_FOCUSED);
        ActionMap actionMap = button.getActionMap();

        // 엔터 키를 눌렀을 때의 Action 정의
        Action pressAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                button.doClick(); // 버튼 클릭 효과
            }
        };

        // 엔터 키에 대한 바인딩
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "pressAction");
        actionMap.put("pressAction", pressAction);
    }

    private void setupDirectionalFocusTraversal(JButton... buttons) {
        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            LoadData key = new LoadData();
            buttons[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    if (keyCode == key.getUpKey()) {
                        // 위쪽 방향키
                        int targetIndex = (index - 1 + buttons.length) % buttons.length;
                        buttons[targetIndex].requestFocus();
                    } else if (keyCode == key.getDownKey()) {
                        // 아래쪽 방향키
                        int targetIndex = (index + 1) % buttons.length;
                        buttons[targetIndex].requestFocus();
                    } else if(keyCode == key.getLeftKey()) {
                        backButton.requestFocus();
                    } else if(keyCode == key.getRightKey()) {
                        buttons[1].requestFocus();
                    }
                }
            });
        }
    }
    public static void main(String[] args) {
        // 시스템 기본 모양과 느낌으로 설정
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            SettingTest menu = new SettingTest();
            menu.setVisible(true);
        });
    }


}
