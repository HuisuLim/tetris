package play_screen.blocks;

import play_screen.blocks.item.*;
import play_screen.blocks.standard.*;
import settings.LoadData;
import java.util.Random;

public class BlockGenerator {
    private int[] weights; // 각 블록 유형의 가중치를 저장할 배열

    private int[] itemWeights; // 각 블록 유형의 가중치를 저장할 배열
    private final Random random = new Random();
    LoadData load = new LoadData();
    String difficulty = load.loadDifficulty();
    private int maxWeight; // 최대 가중치
    private int maxItemWeight; // 최대 가중치

    public BlockGenerator() {
        setDifficulty();
        setItemPercent();
        maxItemWeight = calculateItemMaxWeight();
        // 최대 가중치 계산
        maxWeight = calculateMaxWeight();
    }

    private void setDifficulty() {
        // 기본 가중치 설정 (I, J, L, O, S, T, Z 블록)
        this.weights = new int[]{10, 10, 10, 10, 10, 10, 10};
        // 난이도에 따라 I형 블록의 가중치 조정
        if (difficulty.equals("easy")) {
            this.weights[0] = 12; // 쉬운 난이도에서는 I형 블록이 더 자주 등장
        } else if (difficulty.equals("normal")) {
            this.weights[0] = 10; // 중간 난이도
        } else if (difficulty.equals("hard")){
            this.weights[0] = 8; // 어려운 난이도에서는 덜 자주 등장
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
        this.itemWeights = new int[]{10, 10, 10, 10, 10, 10};
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
