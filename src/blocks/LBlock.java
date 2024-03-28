package blocks;

public class LBlock extends Block{

    public LBlock() {
        this(1);
    }
    public LBlock(int colorNum) {
        super(colorNum);
    }
    protected void setShape() {
        int c = colorNum;
        shape = new int[][][] {
                {
                        {0,0,c},
                        {c,c,c},
                        {0,0,0}
                },
                {
                        {0,c,0},
                        {0,c,0},
                        {0,c,c}
                },
                {
                        {0,0,0},
                        {c,c,c},
                        {c,0,0}
                },
                {
                        {c,c,0},
                        {0,c,0},
                        {0,c,0}
                },
        };
    }

}
