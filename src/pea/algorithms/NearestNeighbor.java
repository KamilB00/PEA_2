package pea.algorithms;

import java.util.*;
import java.util.stream.Collectors;

public class NearestNeighbor {

    private List<List<Integer>> allPairs;
    private List<Integer> distances;
    private boolean[] visited;

    public void run(int[][] graph) {

        allPairs = new ArrayList<>();
        distances = new ArrayList<>();
        visited = new boolean[graph.length];
        int currentVertex = 0;
        int destinationVertex = 0;
        int min;

        Arrays.fill(visited, false);

        for (int i = 0; i < graph.length - 1; i++) {
            visited[currentVertex] = true;
            min = Integer.MAX_VALUE;
            for (int j = 0; j < graph.length; j++) {
                if (i != j && !visited[j] && graph[currentVertex][j] < min) {
                    min = graph[currentVertex][j];
                    destinationVertex = j;
                }
            }
            allPairs.add(List.of(currentVertex, destinationVertex));
            distances.add(min);
            currentVertex = destinationVertex;
        }

        int lastIndex = allPairs.get(allPairs.size() - 1).get(1);
        distances.add(graph[lastIndex][0]);
    }

    public List<Integer> getPath() {
        List<Integer> path = allPairs.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList())
                .stream()
                .distinct()
                .collect(Collectors.toList());
        path.add(0);
        return path;
    }

    public Integer getCost() {
        return distances.stream()
                .reduce(Integer::sum)
                .orElseThrow();
    }
}

