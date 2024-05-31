package startscreen;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Font; // 폰트 설정을 위해 추가
import java.awt.Color;

import scoreboard.ScoreboardView;
import settingscreen.settingModel;
import playscreen.SinglePlayFrame;


public class StartMenu extends JFrame {
    private JFrame nextFrame; // 다음 화면에 해당하는 JFrame
    public static double screenRatio = 1.6; //화면 비율 조절
    public static boolean isColorblindness = false; //색맹모드
    public static String keySetting="ArrowKeys";//키설정

    public static String difficulty="normal";
    public static settingModel settingModel = new settingModel();
    public static void setScreenRatio(){
        screenRatio = settingModel.loadScreenSize();
    }
    public static void setColorBlindness(){
        isColorblindness = settingModel.loadColorBlindMode();
    }
    public static void setDifficutly(){
        difficulty = settingModel.loadDifficulty();
    }
    public static void setControlKey(){
        keySetting = settingModel.loadKeySettings();
    }
    public StartMenu() {
        setScreenRatio();//화면 비율 조절
        setColorBlindness();
        setControlKey();
        setDifficutly();

        setSize((int)(500*screenRatio), (int)(400*screenRatio));
        setTitle("테트리스 게임");
        setVisible(true);
        setResizable(false); // 창 크기 변경 불가능 설정 추가
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // 화면 중앙에 표시



        // 패널 생성
        JPanel panel = new JPanel(null); // 레이아웃 매니저를 null로 설정하여 수동으로 위치 및 크기 지정

        // 제목 라벨 생성
        JLabel titleLabel = new JLabel("SE Team9 Tetris", SwingConstants.CENTER); // 중앙 정렬
        titleLabel.setBounds((int)(150*screenRatio), (int)(50*screenRatio), (int)(200*screenRatio), (int)(25*screenRatio)); // 제목 라벨 위치 및 크기 지정
        titleLabel.setFont(new Font("Arial", Font.BOLD, (int)(12*screenRatio))); // 폰트 설정
        panel.add(titleLabel); // 패널에 제목 라벨 추가

        // 설정값 표현
        JLabel variableLabel = new JLabel();
        int fontSize = (int)(10*screenRatio);
        Font font = new Font("Arial", Font.PLAIN, fontSize);
        variableLabel.setFont(font);

        String showScreenRatio;
        if (screenRatio == 1){
            showScreenRatio = "small";
        }
        else if(screenRatio == 1.6){
            showScreenRatio = "medium";
        }
        else{
            showScreenRatio = "large";
        }

        String htmlText = "<html>" +
                "<tr><td><strong>화면 비율:</strong></td><td>" + showScreenRatio + "</td></tr>" +
                "<tr><td><strong>색맹 모드:</strong></td><td>" + isColorblindness + "</td></tr>" +
                "<tr><td><strong>난이도:</strong></td><td>" + difficulty + "</td></tr>" +
                "<tr><td><strong>키 설정:</strong></td><td>" + keySetting + "</td></tr>" +
                "</table>";
        if(keySetting.equals("ArrowKeys")) {
            variableLabel.setText(htmlText+"<table>\n"+
                    "  <tr>\n" +
                    "    <td>↑</td>\n" +
                    "    <td>90도 회전</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>←</td>\n" +
                    "    <td>좌로 이동</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>↓</td>\n" +
                    "    <td>아래로 이동</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>→</td>\n" +
                    "    <td>우로 이동</td>\n" +
                    "  </tr>\n" +
                    "</table></html>");
        } else {
            variableLabel.setText(htmlText+"<table>\n" +
                    "  <tr>\n" +
                    "    <td>W</td>\n" +
                    "    <td>90도 회전</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>A</td>\n" +
                    "    <td>좌로 이동</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>S</td>\n" +
                    "    <td>아래로 이동</td>\n" +
                    "  </tr>\n" +
                    "  <tr>\n" +
                    "    <td>D</td>\n" +
                    "    <td>우로 이동</td>\n" +
                    "  </tr>\n" +
                    "</table>\n</html>");
        }
        variableLabel.setBounds((int)(350*screenRatio), (int)(130*screenRatio), (int)(130*screenRatio), (int)(200*screenRatio));

        // 라벨에 테두리 추가
        variableLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // 내용을 가운데 정렬
        variableLabel.setHorizontalAlignment(SwingConstants.CENTER);
        variableLabel.setVerticalAlignment(SwingConstants.CENTER);
        panel.add(variableLabel);
        add(panel);

        // 버튼 생성
        JButton startButton = new JButton("일반모드");
        JButton startItemButton = new JButton("아이템모드");
        JButton battleButton = new JButton("대전모드");
        JButton settingsButton = new JButton("설정");
        JButton exitButton = new JButton("게임 종료");
        JButton scoreButton = new JButton("스코어보드");

        configureButton(startButton);
        configureButton(startItemButton);
        configureButton(battleButton);
        configureButton(settingsButton);
        configureButton(exitButton);
        configureButton(scoreButton);

        //버튼 위치 설정
        panel.add(startButton);
        startButton.setBounds((int) (200 * screenRatio), (int) (135 * screenRatio), (int) (100 * screenRatio), (int) (25 * screenRatio));

        panel.add(startItemButton);
        startItemButton.setBounds((int) (200 * screenRatio), (int) (170 * screenRatio), (int) (100 * screenRatio), (int) (25 * screenRatio));

        panel.add(battleButton);
        battleButton.setBounds((int) (200 * screenRatio), (int) (205 * screenRatio), (int) (100 * screenRatio), (int) (25 * screenRatio));

        panel.add(settingsButton);
        settingsButton.setBounds((int) (200 * screenRatio), (int) (240 * screenRatio), (int) (100 * screenRatio), (int) (25 * screenRatio));

        panel.add(exitButton);
        exitButton.setBounds((int) (200 * screenRatio), (int) (275 * screenRatio), (int) (100 * screenRatio), (int) (25 * screenRatio));

        panel.add(scoreButton);
        scoreButton.setBounds((int) (200 * screenRatio), (int) (310 * screenRatio), (int) (100 * screenRatio), (int) (25 * screenRatio));

        // 프레임에 패널 추가
        add(panel);
        // 게임 시작 버튼 이벤트 처리
        startButton.addActionListener(e -> {
            // 다음 화면으로 넘어가기 위해 새로운 JFrame 생성
            nextFrame = new SinglePlayFrame("normalMode");
            nextFrame.setVisible(true);
            setVisible(false); // 현재 화면 숨기기
        });

        startItemButton.addActionListener(e -> {
            // 다음 화면으로 넘어가기 위해 새로운 JFrame 생성
            nextFrame = new SinglePlayFrame("itemMode");
            nextFrame.setVisible(true);
            setVisible(false); // 현재 화면 숨기기
        });

        // 대전모드 버튼 이벤트 처리
        battleButton.addActionListener(e -> {
            nextFrame = new BattleMode(); // MultiPlayFrame 클래스의 인스턴스 생성
            nextFrame.setVisible(true);
            setVisible(false); // 현재 화면 숨기기
        });

        // 설정 버튼 이벤트 처리
        settingsButton.addActionListener(e -> {
            // 다음 화면으로 넘어가기 위해 새로운 JFrame 생성
            nextFrame = new Setting();
            nextFrame.setVisible(true);
            setVisible(false); // 현재 화면 숨기기
        });
        // 스코어보드 버튼 이벤트 처리
        scoreButton.addActionListener(e -> {

            nextFrame = new ScoreboardView();
            nextFrame.setVisible(true);
            // setVisible(false);

        });

        // 게임 종료 버튼 이벤트 처리
        exitButton.addActionListener(e -> {
            // 프로그램 종료
            System.exit(0);
        });



        // 방향키 및 엔터키 처리를 위한 설정
        setupDirectionalFocusTraversal(startButton, startItemButton, battleButton,settingsButton, exitButton, scoreButton);
    }
    private void configureButton(JButton button) {
        Color defaultColor = new Color(230, 230, 230); // 기본 배경색을 밝은 GRAY로 설정
        Color focusedColor = new Color(210, 210, 210); // 포커스가 있을 때의 배경색
        Color hoverColor = defaultColor.brighter(); // 마우스 오버 시 색상, 기본 색상보다 약간 밝게

        button.setBackground(defaultColor); // 버튼의 기본 배경색 설정
        button.setFocusPainted(false);
        button.setBorderPainted(false); // 버튼의 테두리를 그리지 않음

        // 마우스 리스너
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(hoverColor); // 마우스가 버튼 위에 있을 때
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!button.isFocusOwner()) {
                    button.setBackground(defaultColor); // 마우스가 떠나면 기본 색상으로 복원
                }
            }
        });

        // 포커스 리스너
        button.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                button.setBackground(focusedColor); // 포커스를 얻으면 색상을 변경
            }

            @Override
            public void focusLost(FocusEvent e) {
                button.setBackground(defaultColor); // 포커스를 잃으면 기본 색상으로 복원
            }
        });
    }

    /* switch문으로 키 적용
        private void setupDirectionalFocusTraversal(JButton... buttons) {
            for (int i = 0; i < buttons.length; i++) {
                final int index = i;
                buttons[i].addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent e) {
                        switch (e.getKeyCode()) {
                            case KeyEvent.VK_UP:
                            case KeyEvent.VK_LEFT:
                                // 위쪽 또는 왼쪽 방향키
                                int prevIndex = (index - 1 + buttons.length) % buttons.length;
                                buttons[prevIndex].requestFocus();
                                break;
                            case KeyEvent.VK_DOWN:
                            case KeyEvent.VK_RIGHT:
                                // 아래쪽 또는 오른쪽 방향키
                                int nextIndex = (index + 1) % buttons.length;
                                buttons[nextIndex].requestFocus();
                                break;
                            case KeyEvent.VK_ENTER:
                                // 엔터키
                                buttons[index].doClick();
                                break;
                        }
                    }
                });
            }
        }

     */
    // if문으로 키 적용
    public void setupDirectionalFocusTraversal(JButton... buttons) {
        for (int i = 0; i < buttons.length; i++) {
            final int index = i;
            settingModel key = new settingModel();
            buttons[i].addKeyListener(new KeyAdapter() {
                @Override
                public void keyPressed(KeyEvent e) {
                    int keyCode = e.getKeyCode();
                    if (keyCode == key.getUpKey() || keyCode == key.getLeftKey()) {
                        // 위쪽 방향키
                        int targetIndex = (index - 1 + buttons.length) % buttons.length;
                        buttons[targetIndex].requestFocus();
                    } else if (keyCode == key.getDownKey() || keyCode == key.getRightKey()) {
                        // 아래쪽 방향키
                        int targetIndex = (index + 1) % buttons.length;
                        buttons[targetIndex].requestFocus();
                    } else if (keyCode == KeyEvent.VK_ENTER){
                        buttons[index].doClick();
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
            StartMenu menu = new StartMenu();
            menu.setVisible(true);
        });
    }

    public void keyPressed(KeyEvent upKeyEvent) {
    }
}
