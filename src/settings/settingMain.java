package settings;

public class settingMain {
    //ScreenSize ScoreBoardReset
    public static settingView launchSettingScreen(String settingName) {
        settingModel model = new settingModel();
        settingView view = new settingView(settingName);
        settingController controller = new settingController(model, view);
        return view; // settingView 인스턴스 반환
    }
}
