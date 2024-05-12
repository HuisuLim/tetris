package playscreen;

import playscreen.panels.*;
import settings.settingModel;

import javax.swing.*;

public class BattlePlayFrame extends JFrame {

    //data load
    private final settingModel data = new settingModel();
    private final boolean colorMode = data.loadColorBlindMode();
    private final String difficulty = data.loadDifficulty(); // 난이도 로드
    private final double screenSize = data.loadScreenSize();

    //panel load
    public TetrisPanel[] gamePanels;
    public ScorePanel[] scorePanels;
    public NextBlockPanel[] nextBlockPanels;
    public PausePanel[] pausePanels;
    public ItemShowPanel[] itemShowPanels;
    public NameInputPanel[] nameInputPanels;

    public Timer[] timers;

    //상태변수들
    private boolean[] isGameOver = new boolean[] {false, false};
    private boolean isPaused = false;
    private boolean[] isCleaningTime;
}
