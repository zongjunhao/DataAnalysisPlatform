package cn.net.syzc.analysis.kit;

/**
 * Save the result of file
 */
public class OtherResult{
    private int[][] value;
    private int max;
    private int min;

    OtherResult(int[][] value, int max, int min){
        this.value = value;
        this.max = max;
        this.min = min;
    }

    public int[][] getValue() {
        return value;
    }

    public void setValue(int[][] value) {
        this.value = value;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }
}
