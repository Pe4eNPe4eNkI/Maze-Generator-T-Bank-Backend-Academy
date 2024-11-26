package backend.academy.maze.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import backend.academy.maze.other.Point;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class BFSTest {

    @Test
    void currentPathTest() {
        char[][] maze = {
            "────────────────────".toCharArray(),
            "S ───── ─── ─── ─ ──".toCharArray(),
            "─                  F".toCharArray(),
            "─── ─────────── ─ ──".toCharArray(),
            "─── ─   ─ ─   ─ ─ ──".toCharArray(),
            "─ ─── ─── ─── ─── ──".toCharArray(),
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
            new Point(2, 4),
            new Point(2, 5),
            new Point(2, 6),
            new Point(2, 7),
            new Point(2, 8),
            new Point(2, 9),
            new Point(2, 10),
            new Point(2, 11),
            new Point(2, 12),
            new Point(2, 13),
            new Point(2, 14),
            new Point(2, 15),
            new Point(2, 16),
            new Point(2, 17),
            new Point(2, 18),
            new Point(2, 19)
        ));

        SearchPath searchPath = new BFS(maze);
        List<Point> path = searchPath.findPath(maze);

        assertEquals(path, currentPath, "Некорректная работа алгоритма!");
    }
}
