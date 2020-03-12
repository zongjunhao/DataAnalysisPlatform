package cn.net.syzc.analysis.kit;

import cn.net.syzc.analysis.model.Task;

import java.util.ArrayList;
import java.util.List;

public class CharmData {
    Task task = new Task();
    List<Integer> nodes = new ArrayList<>();
    int[][] sides;


    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public List<Integer> getNodes() {
        return nodes;
    }

    public void setNodes(List<Integer> nodes) {
        this.nodes = nodes;
    }

    public int[][] getSides() {
        return sides;
    }

    public void setSides(int[][] sides) {
        this.sides = sides;
    }
}
