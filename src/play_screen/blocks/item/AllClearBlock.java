package play_screen.blocks.item;

import play_screen.blocks.Block;

public class AllClearBlock extends Block {
    public AllClearBlock() {
        this(1);
    }
    public AllClearBlock(int blockNum) {
        super(blockNum);
    }

    @Override
    protected void setShape() {
        shape = new int[][] {{blockNum}};
    }
}

