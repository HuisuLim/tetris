package playscreen.blocks.item;

import playscreen.blocks.Block;

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

