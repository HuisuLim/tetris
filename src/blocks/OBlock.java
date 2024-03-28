package blocks;

public class OBlock extends Block{

    public OBlock() {
        this(1);
    }
    public OBlock(int colorNum) {
        super(colorNum);
    }
    protected void setShape() {
        int c = colorNum;
        shape = new int[][][] {
                {
                        {c,c},
                        {c,c}
                }
        };
    }

}
