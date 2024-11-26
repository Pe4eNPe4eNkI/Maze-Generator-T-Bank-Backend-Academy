package backend.academy.maze.search;

import backend.academy.maze.other.Point;
import java.util.List;
import java.util.Map;

public interface SearchPathInterface {

    List<Point> findPath(char[][] maze);

    List<Point> reconstructPath(Map<Point, Point> prev, Point finish);

    boolean isValidMove(Point p, boolean[][] visited, char[][] maze);
}
