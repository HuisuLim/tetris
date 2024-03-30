package play_screen.blocks;

public abstract class Block {

    protected int colorNum;
    protected int[][][] shape;
    protected  int maxRotate;
    protected int currRotate;
    protected int len;

    public Block(int colorNum) {
        this.colorNum = colorNum;
        setShape();
        maxRotate = shape.length;
        currRotate = 0;
        len = shape[currRotate].length;
    }
    public Block() {
        this(1);
    }

    protected abstract void setShape();

    public void rotate90(){
        currRotate = (currRotate + 1) % maxRotate;
    }

    public int[][] getCurrShape() {
        return copy2DArr(shape[currRotate]);
    }

    public int[][] getRotateShape() {
        return copy2DArr((shape[(currRotate+1)%maxRotate]));
    }


    public int getLen() {
        return len;
    }

    public int getCurrRotate() {
        return currRotate;
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
