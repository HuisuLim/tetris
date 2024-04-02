package play_screen.blocks;

import play_screen.blocks.item.*;
import play_screen.blocks.standard.*;

import java.util.Random;

public class BlockGenerator {
    public Block getRandomStandardBlock() {
        Random random = new Random();
        int blockType = random.nextInt(7) + 1; // 1부터 7까지의 랜덤 수 생성
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

    public Block getRandomItemBlock() {
        Random random = new Random();
        int blockType = random.nextInt(6) + 11; // 11부터 16까지의 랜덤 수 생성
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
        Block b = generator.getRandomStandardBlock();
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
