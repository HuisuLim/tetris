package playscreen.blocks;

public abstract class Block {

    protected int blockNum;
    protected int[][] shape;
    protected int len;
    protected int[] startPos =new int[] {0, 4};

    public Block(int blockNum) {
        this.blockNum = blockNum;
        this.setShape();
        len = shape.length;
    }
    public Block() {
        this(1);
    }

    protected abstract void setShape();

    public int[][] getShape() {
        return copy2DArr(shape);
    }

    public int[][] getRotatedShape() {
        if (shape == null || shape.length == 0 || shape[0].length == 0) {
            // shape 배열이 비었거나 정의되지 않은 경우, 빈 배열을 반환
            return new int[0][0];
        }

        int row = shape.length;
        int col = shape[0].length;
        int[][] rotatedShape = new int[col][row]; // 회전된 배열의 크기 조정

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                rotatedShape[j][row - 1 - i] = shape[i][j];
            }
        }

        return rotatedShape;
    }

    public void rotate90() {
        shape = getRotatedShape();
    }


    public int getLen() {
        return len;
    }

    public int[] getStartPos() {
        return startPos;
    }

    public int getBlockNum() {
        return blockNum;
    }

    public int[][] copy2DArr(int[][] arr) {
        if (arr == null) return null; // 입력 배열이 null인 경우, null 반환

        int[][] copy = new int[arr.length][]; // 복사본 배열 초기화
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null) { // 각 행이 null이 아닌 경우에만 복사 수행
                copy[i] = new int[arr[i].length]; // 해당 행에 대한 새 배열 생성
                System.arraycopy(arr[i], 0, copy[i], 0, arr[i].length); // 각 행 복사
            }
        }
        return copy; // 복사본 배열 반환
    }


}
