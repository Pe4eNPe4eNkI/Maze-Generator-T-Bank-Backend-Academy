package backend.academy.maze.search;

import backend.academy.maze.other.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.Getter;

@Getter
public abstract class SearchPath implements SearchPathInterface {

    // Направления движения: вверх, вниз, влево, вправо
    private final Point[] directional = {
        new Point(-1, 0), // вверх
        new Point(1, 0),  // вниз
        new Point(0, -1), // влево
        new Point(0, 1)   // вправо
    };

    SearchPath(char[][] maze) {
    }

    public Point findPoint(char symbol, char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == symbol) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    public List<Point> reconstructPath(Map<Point, Point> prev, Point finish) {
        List<Point> path = new ArrayList<>();
        for (Point at = finish; at != null; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path); // Переворачиваем путь для правильного порядка
        return path;
    }

    public boolean isValidMove(Point p, boolean[][] visited, char[][] maze) {
        // Проверяем, что клетка в пределах лабиринта, не является стеной
        return p.x() >= 0 && p.x() < maze.length && p.y() >= 0 && p.y() < maze[0].length
            && (maze[p.x()][p.y()] == ' ' || maze[p.x()][p.y()] == 'F' || maze[p.x()][p.y()] == '^')
            && !visited[p.x()][p.y()];
    }
}
