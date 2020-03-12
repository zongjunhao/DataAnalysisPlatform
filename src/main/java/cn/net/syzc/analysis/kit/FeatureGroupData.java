package cn.net.syzc.analysis.kit;

public class FeatureGroupData {
    double[][] attri;
    int[][] classification;

    public int[][] getClassification() {
        return classification;
    }

    public void setClassification(int[][] classification) {
        this.classification = classification;
    }

    public double[][] getAttri() {
        return attri;
    }

    public void setAttri(double[][] attri) {
        this.attri = attri;
    }
}
