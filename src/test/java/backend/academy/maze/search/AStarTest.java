package backend.academy.maze.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import backend.academy.maze.other.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AStarTest {

    @Test
    void currentPathTest() {
        char[][] mazeWithImpediment = {
            "────────────────────".toCharArray(),
            "S ^^^^^^^^^^^^^^^^^─".toCharArray(),
            "─     ─ ─  ─    ─  F".toCharArray(),
            "─── ─────────── ─  ─".toCharArray(),
            "─── ─   ─ ─   ─ ─  ─".toCharArray(),
            "─ ─                ─".toCharArray(),
            "──  ─       ─   ─ ──".toCharArray(),
            "───── ─── ─ ─ ─ ────".toCharArray(),
            "─     ─ ─ ─ ─ ─    ─".toCharArray(),
            "────────────────────".toCharArray()
        };

        List<Point> currentPath = new ArrayList<>(Arrays.asList(
            new Point(1, 0),
            new Point(1, 1),
            new Point(2, 1),
            new Point(2, 2),
            new Point(2, 3),
            new Point(3, 3),
            new Point(4, 3),
            new Point(5, 3),
            new Point(5, 4),
            new Point(5, 5),
            new Point(5, 6),
            new Point(5, 7),
            new Point(5, 8),
            new Point(5, 9),
            new Point(5, 10),
            new Point(5, 11),
            new Point(5, 12),
            new Point(5, 13),
            new Point(5, 14),
            new Point(5, 15),
            new Point(5, 16),
            new Point(5, 17),
            new Point(5, 18),
            new Point(4, 18),
            new Point(3, 18),
            new Point(2, 18),
            new Point(2, 19)
        ));

        SearchPath searchPath = new AStar(mazeWithImpediment);
        List<Point> path = searchPath.findPath(mazeWithImpediment);
        assertEquals(path, currentPath, "Найденный маршрут не оптимален!");
    }
}

