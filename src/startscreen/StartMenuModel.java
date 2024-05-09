package startscreen;

import settings.LoadData;

public class StartMenuModel {

    public static double screenRatio = 1.6; // 화면 비율
    public static boolean isColorblindness = false; // 색맹 모드 여부
    public static String keySetting = "ArrowKeys"; // 키 설정
    public static String difficulty = "normal"; // 게임 난이도
    public static LoadData loadData = new LoadData();

    public static void setScreenRatio(){
        screenRatio = loadData.loadScreenSize();
    }
    public static void setColorBlindness(){
        isColorblindness = loadData.loadColorBlindMode();
    }
    public static void setDifficutly(){
        difficulty = loadData.loadDifficulty();
    }
    public static void setControlKey(){
        keySetting = loadData.loadKeySettings();
    }
}
