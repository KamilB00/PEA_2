package pea.algorithms;

import pea.graph.Graph;
import pea.menu.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SimulatedAnnealing {

    int[][] graph = Graph.getInstance().getGraph();
    private List<Integer> currentPath;
    private List<Integer> changedPath;
    int timeInSeconds = Menu.timeInSeconds;

    public void run(){
        currentPath = NearestNeighbor.getPath();

        long finish =  System.currentTimeMillis() + timeInSeconds* 1000L;
        do{

        }while(finish - System.currentTimeMillis() > 0);
    }

    private Integer getCost(List<Integer> path) {
        int cost = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            cost += graph[i][i + 1];
        }
        return cost;
    }

    private List<Integer> swapCurrentPath() {

        int a = new Random().nextInt((currentPath.size() - 4) + 1);
        int b = a + 3;

        List<Integer> changed = new ArrayList<>(currentPath);
        int temp = changed.get(a);
        changed.set(a, changed.get(b));
        changed.set(b, temp);
        return changed;
    }

    public List<Integer> getCurrentPath() {
        return currentPath;
    }
}
