package backend.academy.maze.search;

import backend.academy.maze.other.MazeSymbol;
import backend.academy.maze.other.Node;
import backend.academy.maze.other.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class AStar extends SearchPath {
    private static final int NORMAL_COST = 1;
    private static final int IMPEDIMENT_COST = 3;

    public AStar(char[][] maze) {
        super(maze);
    }

    public List<Point> findPath(char[][] maze) {
        Point start = findPoint(MazeSymbol.START.symbol(), maze);
        Point finish = findPoint(MazeSymbol.FINISH.symbol(), maze);

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Map<Point, Integer> gScore = new HashMap<>();
        Map<Point, Point> prev = new HashMap<>();
        boolean[][] visited = new boolean[maze.length][maze[0].length];

        openSet.add(new Node(start, 0, heuristic(start, finish), 0, null));
        gScore.put(start, 0);

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();
            if (currentNode.point().equals(finish)) {
                return reconstructPath(prev, finish);
            }

            visited[currentNode.point().x()][currentNode.point().y()] = true;

            // Проверяем соседние клетки
            for (Point direction : directional()) {
                Point next = currentNode.point().move(direction);
                if (!isValidMove(next, visited, maze)) {
                    continue;
                }

                int moveCost =
                    maze[next.x()][next.y()] == MazeSymbol.IMPEDIMENT.symbol() ? IMPEDIMENT_COST
                        : NORMAL_COST; // Препятствие увеличивает стоимость
                int tentativeGScore = currentNode.g() + moveCost;

                if (tentativeGScore < gScore.getOrDefault(next, Integer.MAX_VALUE)) {
                    prev.put(next, currentNode.point());
                    gScore.put(next, tentativeGScore);
                    int hCost = heuristic(next, finish);
                    int fCost = tentativeGScore + hCost;

                    openSet.add(new Node(next, tentativeGScore, hCost, fCost, currentNode));
                }
            }
        }

        return Collections.emptyList();
    }

    private int heuristic(Point a, Point b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }
}
