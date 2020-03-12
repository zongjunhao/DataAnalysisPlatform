package cn.net.syzc.analysis.kit;

import cn.net.syzc.analysis.model.Task;
import jnr.ffi.annotations.In;

import java.util.ArrayList;
import java.util.List;

public class CharmDataSource {
    Task task = new Task();
    List<Integer> nodes = new ArrayList<>();
//    List<Side> sides = new ArrayList<>();
    int[][] sides;
    double[][] attri;
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

    public double[][] getAttri() {
        return attri;
    }

    public void setAttri(double[][] attri) {
        this.attri = attri;
    }

//    public List<Node> getNodes() {
//        return nodes;
//    }
//
//    public void setNodes(List<Node> nodes) {
//        this.nodes = nodes;
//    }

    public List<Integer> getNodes() {
        return nodes;
    }

    public void setNodes(List<Integer> nodes) {
        this.nodes = nodes;
    }

//    public List<Side> getSides() {
//        return sides;
//    }
//
//    public void setSides(List<Side> sides) {
//        this.sides = sides;
//    }

    public int[][] getSides() {
        return sides;
    }

    public void setSides(int[][] sides) {
        this.sides = sides;
    }
}
