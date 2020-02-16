package cn.net.syzc.analysis.kit;

import java.util.ArrayList;
import java.util.List;

public class CharmDataSource {
    List<Node> nodes = new ArrayList<>();
    List<Side> sides = new ArrayList<>();
    int[][] attri;
    int[][] classification;

    public int[][] getClassification() {
        return classification;
    }

    public void setClassification(int[][] classification) {
        this.classification = classification;
    }

    public int[][] getAttri() {
        return attri;
    }

    public void setAttri(int[][] attri) {
        this.attri = attri;
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public void setNodes(List<Node> nodes) {
        this.nodes = nodes;
    }

    public List<Side> getSides() {
        return sides;
    }

    public void setSides(List<Side> sides) {
        this.sides = sides;
    }
}
