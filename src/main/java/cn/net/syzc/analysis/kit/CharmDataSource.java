package cn.net.syzc.analysis.kit;

import cn.net.syzc.analysis.model.Task;

import java.util.ArrayList;
import java.util.List;

public class CharmDataSource {
    Task task = new Task();
    List<Node> nodes = new ArrayList<>();
    List<Side> sides = new ArrayList<>();
    int[][] attri;
    int[][] classification;

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

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
