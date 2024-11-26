package backend.academy.maze.search;

import backend.academy.maze.other.MazeSymbol;
import backend.academy.maze.other.Point;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BFS extends SearchPath {
    public BFS(
        char[][] maze
    ) {
        super(maze);
    }

    public List<Point> findPath(char[][] maze) {
        Point start = findPoint(MazeSymbol.START.symbol(), maze);
        Point finish = findPoint(MazeSymbol.FINISH.symbol(), maze);

        boolean[][] visited = new boolean[maze.length][maze[0].length];
        Map<Point, Point> prev = new HashMap<>();
        Queue<Point> queue = new LinkedList<>();

        queue.add(start);
        visited[start.x()][start.y()] = true;

        while (!queue.isEmpty()) {
            Point current = queue.poll();

            // Проверяем, достигли ли мы конечной точки
            if (current.equals(finish)) {
                return reconstructPath(prev, finish);
            }

            // Проверяем соседние клетки
            for (Point direction : directional()) {
                Point next = current.move(direction);

                if (isValidMove(next, visited, maze)) {
                    queue.add(next);
                    visited[next.x()][next.y()] = true;
                    prev.put(next, current);
                }
            }
        }

        return Collections.emptyList();
    }
}

