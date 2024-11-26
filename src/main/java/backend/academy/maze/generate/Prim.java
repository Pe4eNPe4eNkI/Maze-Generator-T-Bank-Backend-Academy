package backend.academy.maze.generate;

import backend.academy.maze.difficulty.Difficulty;
import backend.academy.maze.difficulty.DifficultyLevel;
import backend.academy.maze.other.Edge;
import backend.academy.maze.other.MazeSymbol;
import backend.academy.maze.other.Point;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Prim extends MazeGenerator {

    public Prim(
        int height,
        int width,
        DifficultyLevel difficultyLevel
    ) {
        super(height, width, difficultyLevel);
    }

    public char[][] generateMaze() {

        char[][] maze = new char[height()][width()];
        init(maze);
        Random random = new Random();
        int startX = random.nextInt(width());
        int startY = random.nextInt(height());

        // Множество посещенных клеток
        Set<Point> visited = new HashSet<>();
        visited.add(new Point(startX, startY));

        // Множество возможных стен для удаления (фронт)
        List<Edge> frontier = new ArrayList<>();
        addEdgesToFrontier(startX, startY, maze, frontier, visited);

        while (!frontier.isEmpty()) {
            // Выбираем случайное ребро
            Edge edge = frontier.remove(random.nextInt(frontier.size()));

            Point from = edge.from();
            Point to = edge.to();

            if (!visited.contains(to)) {
                // Открываем проход
                maze[(from.y() + to.y()) / 2][(from.x() + to.x()) / 2] = MazeSymbol.EMPTY.symbol();
                maze[to.y()][to.x()] = MazeSymbol.EMPTY.symbol();
                visited.add(to);
                addEdgesToFrontier(to.x(), to.y(), maze, frontier, visited);
            }
        }
        maze = Difficulty.setDifficulty(maze, difficultyLevel());

        for (int i = 0; i < maze.length; i++) {
            setBoundaryWalls(maze, i);
        }

        return maze;

    }

    private void addEdgesToFrontier(int x, int y, char[][] maze, List<Edge> frontier, Set<Point> visited) {
        if (x > 1 && maze[y][x - 2] == MazeSymbol.WALL.symbol() && !visited.contains(new Point(x - 2, y))) {
            frontier.add(new Edge(new Point(x, y), new Point(x - 2, y)));
        }
        if (x < maze[0].length - 2 && maze[y][x + 2] == MazeSymbol.WALL.symbol()
            && !visited.contains(new Point(x + 2, y))) {
            frontier.add(new Edge(new Point(x, y), new Point(x + 2, y)));
        }
        if (y > 1 && maze[y - 2][x] == MazeSymbol.WALL.symbol() && !visited.contains(new Point(x, y - 2))) {
            frontier.add(new Edge(new Point(x, y), new Point(x, y - 2)));
        }
        if (y < maze.length - 2 && maze[y + 2][x] == MazeSymbol.WALL.symbol()
            && !visited.contains(new Point(x, y + 2))) {
            frontier.add(new Edge(new Point(x, y), new Point(x, y + 2)));
        }
    }

}
