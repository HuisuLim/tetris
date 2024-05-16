package playscreen.blocks;

import playscreen.blocks.item.*;
import playscreen.blocks.standard.*;
import settings.settingModel;
import java.util.Random;

public class BlockGenerator {
    private int[] weights; // 각 블록 유형의 가중치를 저장할 배열

    private int[] itemWeights; // 각 블록 유형의 가중치를 저장할 배열
    private final Random random = new Random();
    settingModel load = new settingModel();
    String difficulty = load.loadDifficulty();
    private final int maxWeight; // 최대 가중치
    private final int maxItemWeight; // 최대 가중치

    public BlockGenerator() {
        setDifficulty();
        setItemPercent();
        maxItemWeight = calculateItemMaxWeight();
        // 최대 가중치 계산
        maxWeight = calculateMaxWeight();
    }

    private void setDifficulty() {
        // 기본 가중치 설정 (I, J, L, O, S, T, Z 블록)
        this.weights = new int[]{10, 0, 0, 0, 0, 0, 0};
        // 난이도에 따라 I형 블록의 가중치 조정
        switch (difficulty) {
            case "easy" -> this.weights[0] = 12; // 쉬운 난이도에서는 I형 블록이 더 자주 등장
            case "normal" -> this.weights[0] = 10; // 중간 난이도
            case "hard" -> this.weights[0] = 8; // 어려운 난이도에서는 덜 자주 등장
        }
    }

    private int calculateMaxWeight() {
        int max = weights[0];
        for (int weight : weights) {
            if (weight > max) max = weight;
        }
        return max;
    }


    public Block getRandomStandardBlock() {
        while (true) {
            int index = random.nextInt(weights.length);
            int selectedWeight = weights[index];
            if (random.nextDouble() < (double) selectedWeight / maxWeight) {
                return createBlock(index+1);
            }
        }
    }
    private Block createBlock(int blockType) {
        return switch (blockType) {
            case 1 -> new IBlock(blockType);
            case 2 -> new JBlock(blockType);
            case 3 -> new LBlock(blockType);
            case 4 -> new OBlock(blockType);
            case 5 -> new SBlock(blockType);
            case 6 -> new TBlock(blockType);
            case 7 -> new ZBlock(blockType);
            default -> null;
        };
    }


    //---------------여기부터 item

    private void setItemPercent() {
        // 가중치 설정
        this.itemWeights = new int[]{10, 10, 10, 10, 10, 5};
    }

    private int calculateItemMaxWeight() {
        int max = itemWeights[0];
        for (int weight : itemWeights) {
            if (weight > max) max = weight;
        }
        return max;
    }


    public Block getRandomItemBlock() {
        while (true) {
            int index = random.nextInt(itemWeights.length);
            int selectedWeight = itemWeights[index];
            if (random.nextDouble() < (double) selectedWeight / maxItemWeight) {
                return createItemBlock(index+11);
            }
        }
    }

    private Block createItemBlock(int blockType) {
        return switch (blockType) {
            case 11 -> new WeightBlock(blockType);
            case 12 -> new BoxClearBlock(blockType);
            case 13 -> new RowClearBlock(blockType);
            case 14 -> new ColClearBlock(blockType);
            case 15 -> new CrossClearBlock(blockType);
            case 16 -> new AllClearBlock(blockType);
            default -> null;
        };
    }



    public static void main(String[] args) {
        BlockGenerator generator = new BlockGenerator();
        Block b = generator.getRandomItemBlock();
        int len = b.getLen();
        int[][] shape = b.getShape();
        for(int i = 0; i< len; i++){
            for(int j = 0; j < len; j++) {
                System.out.print(shape[i][j]);
            }
            System.out.println();
        }
    }
}
