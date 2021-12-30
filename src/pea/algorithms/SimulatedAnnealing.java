package pea.algorithms;

import pea.graph.Graph;
import pea.menu.Menu;
import java.util.ArrayList;
import java.util.List;

public class SimulatedAnnealing {
    private final int[][] graph = Graph.getInstance().getGraph();
    private List<Integer> currentPath;
    private List<Integer> bestPath;
    int timeInSeconds = Menu.timeInSeconds;
    double coolingParameter = Menu.coolingParameter;
    double temperature;
    int bestCost;

    public void run() {
        currentPath = NearestNeighbor.getPath();
        bestPath = NearestNeighbor.getPath();
        bestCost = getCost(currentPath);
        temperature = getCost(currentPath) * 2;

        long finish = System.currentTimeMillis() + timeInSeconds * 1000L;
        do {
            List<Integer> changedPath = swapCurrentPath();

            int currentPathCost = getCost(currentPath);
            int changedPathCost = getCost(changedPath);

            if (changedPathCost - currentPathCost  <= 0) {
                currentPath = new ArrayList<>(changedPath);

                if(changedPathCost < bestCost) {
                    bestCost = changedPathCost;
                    bestPath = new ArrayList<>(currentPath);
                    System.out.println("FOUND");
                }

            } else {
                if ((Math.exp((-1 * (changedPathCost - currentPathCost)) / temperature) > Math.random())) {
                    currentPath = new ArrayList<>(changedPath);
                    System.out.println("ACCEPT WORSE");
                }
            }

            System.out.println(temperature);
            //System.out.println(Math.exp((-1 * (changedPathCost - currentPathCost)) / temperature));
            temperature = temperature * coolingParameter;

        } while (finish - System.currentTimeMillis() > 0 && temperature > 0.0001);


        System.out.println("Best path: "+ bestPath);
        System.out.println("Cost: "+ bestCost);
    }

    private Integer getCost(List<Integer> path) {
        int cost = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            cost += graph[path.get(i)][path.get(i+1)];
        }
        return cost;
    }

    private List<Integer> swapCurrentPath() {
        int max = currentPath.size() - 3;
        int min = 1;
        int a = (int)(Math.random() * ((max - min) + 1)) + min;
        int b = a + 1;
        List<Integer> changed = new ArrayList<>(currentPath);

        int temp = changed.get(a);
        changed.set(a, changed.get(b));
        changed.set(b, temp);

        return changed;
    }
}
