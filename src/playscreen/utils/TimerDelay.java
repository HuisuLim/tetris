package playscreen.utils;

public class TimerDelay {
    private static final int BASE_DELAY = 1000; // "normal" 난이도 기준 지연 시간
    private static final double HARD_MULTIPLIER = 0.8; // "hard" 난이도에서 속도를 20% 증가
    private static final double EASY_MULTIPLIER = 1.2; // "easy" 난이도에서 속도를 20% 감소
    private static final int MIN_DELAY = 100; // 최소 지연 시간
    private static final double K = 2.1000e-5;    // 최적화된 K 값

    public static int calDelay(String difficulty, int score) {
        double delay = BASE_DELAY * Math.exp(-K * score);
        delay = Math.max(MIN_DELAY, delay); // 계산된 딜레이가 최소 딜레이보다 작지 않도록 보장

        if ("easy".equalsIgnoreCase(difficulty)) delay *= EASY_MULTIPLIER;
        else if ("hard".equalsIgnoreCase(difficulty)) delay *= HARD_MULTIPLIER;

        return (int)delay;
    }

    public static double calScoreMultiplier(int score) {
        return 1.0 + (1000.0 - (double)calDelay("normal", score)) / 200.0;
    }

    public static void main(String[] args) {
        System.out.println(calScoreMultiplier(1000));
    }
}

