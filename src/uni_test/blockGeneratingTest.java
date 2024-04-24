package uni_test;

import playscreen.blocks.Block;
import playscreen.blocks.BlockGenerator;
import settings.LoadData;
import java.io.*;
import java.util.HashMap;
import java.util.Properties;
import java.util.Map;

public class blockGeneratingTest {
    private static final String SETTINGS_FILE = "settings.properties";
    private static final String DIFFICULTY_KEY = "Difficulty";

    public static void main(String[] args) {
        saveSettings(DIFFICULTY_KEY, "hard"); //여기서 난이도 변경 후 테스트
        LoadData load = new LoadData();
        String difficulty = load.loadDifficulty();
        System.out.println(difficulty + " mode");
        // 사용자가 원하는 횟수, 예를 들어 10000번을 입력하면 됩니다.
        int numberOfBlocksToGenerate = 100000;
        testBlockGeneration(numberOfBlocksToGenerate);
    }

    public static void testBlockGeneration(int numberOfBlocks) {
        BlockGenerator generator = new BlockGenerator();
        Map<String, Integer> blockCounts = new HashMap<>();

        // 모든 블록 유형에 대해 카운트를 0으로 초기화
        blockCounts.put("IBlock", 0);
        blockCounts.put("JBlock", 0);
        blockCounts.put("LBlock", 0);
        blockCounts.put("OBlock", 0);
        blockCounts.put("SBlock", 0);
        blockCounts.put("TBlock", 0);
        blockCounts.put("ZBlock", 0);

        for (int i = 0; i < numberOfBlocks; i++) {
            Block block = generator.getRandomStandardBlock();
            String className = block.getClass().getSimpleName();
            blockCounts.put(className, blockCounts.getOrDefault(className, 0) + 1);
        }

        // 결과 출력
        int totalBlocks = numberOfBlocks;
        for (Map.Entry<String, Integer> entry : blockCounts.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // IBlock 개수 비율 계산 및 기대값 대비 증가 또는 감소 비율 출력
        LoadData load = new LoadData();
        String difficulty = load.loadDifficulty();
        double expectedIBlockCount = totalBlocks / 7;
        if(difficulty.equals("hard")){
            expectedIBlockCount = totalBlocks / 7 * 0.8;
        }
        if(difficulty.equals("normal")){
            expectedIBlockCount = totalBlocks / 7;
        }
        if(difficulty.equals("easy")){
            expectedIBlockCount = totalBlocks / 7 * 1.2;
        }
        double actualIBlockCount = blockCounts.get("IBlock");
        double percentageDifference = (actualIBlockCount - expectedIBlockCount) / expectedIBlockCount * 100;
        System.out.println(String.format("IBlock count is %.2f%% %s than expectation",
                Math.abs(percentageDifference),
                percentageDifference >= 0 ? "higher" : "lower"));
    }

    private static void saveSettings(String key, String value) {
        Properties properties = new Properties();
        try (InputStream inputStream = new FileInputStream(SETTINGS_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        properties.setProperty(key, value);
        try (OutputStream outputStream = new FileOutputStream(SETTINGS_FILE)) {
            properties.store(outputStream, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
