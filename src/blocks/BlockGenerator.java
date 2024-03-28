package blocks;

import java.util.Random;

public class BlockGenerator {
    public Block getRandomBlock() {
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
}
