package play_screen;

public class TimerDelay {
    private static final int BASE_DELAY = 1000; // "normal" 난이도 기준 지연 시간
    private static final double HARD_MULTIPLIER = 0.8; // "hard" 난이도에서 속도를 20% 증가
    private static final double EASY_MULTIPLIER = 1.2; // "easy" 난이도에서 속도를 20% 감소
    private static final int MIN_DELAY = 100; // 최소 지연 시간
    private static final double SCORE_DECREASE_FACTOR = 0.9; // 점수가 증가할 때마다 지연 시간을 조정하는 비율
    private static final int SCORE_INTERVAL = 600; // 점수 증가 구간

    public static int calDelay(String difficulty, int score) {
        double delay = BASE_DELAY;
        if ("hard".equalsIgnoreCase(difficulty)) {
            // "hard" 난이도에서는 기본 지연 시간의 80%를 적용
            delay *= HARD_MULTIPLIER;
        } else if ("easy".equalsIgnoreCase(difficulty)) {
            // "easy" 난이도에서는 기본 지연 시간의 120%를 적용
            delay *= EASY_MULTIPLIER;
        }

        // 점수에 따라 지연 시간을 점진적으로 감소
        delay *= Math.pow(SCORE_DECREASE_FACTOR, score / SCORE_INTERVAL);

        return (int) Math.max(delay, MIN_DELAY);
    }
}

