package cn.net.syzc.analysis.kit;

/**
 * Save the result of file
 */
public class FileResult{
    private double[][] value;
    private int max;
    private int min;

    FileResult(double[][] value, int max, int min){
        this.value = value;
        this.max = max;
        this.min = min;
    }

    public double[][] getValue() {
        return value;
    }

    public void setValue(double[][] value) {
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
