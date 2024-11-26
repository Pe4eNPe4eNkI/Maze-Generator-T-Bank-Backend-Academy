package backend.academy.maze.generate;

import backend.academy.maze.difficulty.Difficulty;
import backend.academy.maze.difficulty.DifficultyLevel;
import backend.academy.maze.other.MazeSymbol;
import backend.academy.maze.other.Point;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class Backtracker extends MazeGenerator {

    public Backtracker(
        int height,
        int width,
        DifficultyLevel difficultyLevel
    ) {
        super(height, width, difficultyLevel);
    }

    public Backtracker(
        int height,
        int width,
        DifficultyLevel difficultyLevel,
        int start,
        int finish
    ) {
        super(height, width, difficultyLevel, start, finish);
    }

    @Override
    public char[][] generateMaze() {
        char[][] maze = new char[height()][width()];
        init(maze);
        Random random = new Random();
        int startX = random.nextInt(width() / 2) * 2 + 1; // Начинаем с нечетных координат (чтобы были пути)
        int startY = random.nextInt(height() / 2) * 2 + 1;
        maze[startY][startX] = MazeSymbol.EMPTY.symbol(); // Пустая клетка для старта

        Stack<Point> stack = new Stack<>();
        stack.push(new Point(startX, startY));

        Set<Point> visited = new HashSet<>();
        visited.add(new Point(startX, startY));

        while (!stack.isEmpty()) {
            Point current = stack.peek();
            Point next = getRandomUnvisitedNeighbor(current, maze, visited);

            if (next != null) {
                // Открываем проход между текущей и следующей клеткой
                int wallX = (current.x() + next.x()) / 2;
                int wallY = (current.y() + next.y()) / 2;
                maze[wallY][wallX] = MazeSymbol.EMPTY.symbol(); // Убираем стену
                maze[next.y()][next.x()] = MazeSymbol.EMPTY.symbol(); // Очищаем следующую клетку
                stack.push(next);
                visited.add(next);
            } else {
                stack.pop();
            }
        }

        maze = Difficulty.setDifficulty(maze, difficultyLevel());

        for (int i = 0; i < maze.length; i++) {
            setBoundaryWalls(maze, i);
        }

        return maze;
    }

    private Point getRandomUnvisitedNeighbor(Point current, char[][] maze, Set<Point> visited) {
        Random random = new Random();
        //CHECKSTYLE:OFF
        int curCoordinate = -2;
        //CHECKSTYLE:ON
        Point[] directions = {
            new Point(0, curCoordinate), // вверх
            new Point(0, -curCoordinate),  // вниз
            new Point(curCoordinate, 0), // влево
            new Point(-curCoordinate, 0)   // вправо
        };

        for (int i = 0; i < directions.length; i++) {
            int j = random.nextInt(directions.length);
            Point temp = directions[i];
            directions[i] = directions[j];
            directions[j] = temp;
        }

        for (Point dir : directions) {
            int newX = current.x() + dir.x();
            int newY = current.y() + dir.y();

            if (newX >= 0 && newX < maze[0].length && newY >= 0 && newY < maze.length) {
                Point neighbor = new Point(newX, newY);
                if (maze[newY][newX] == MazeSymbol.WALL.symbol() && !visited.contains(neighbor)) {
                    return neighbor;
                }
            }
        }
        return null;
    }
}
