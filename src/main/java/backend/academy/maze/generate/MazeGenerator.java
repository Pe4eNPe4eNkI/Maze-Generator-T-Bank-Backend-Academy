package backend.academy.maze.generate;

import backend.academy.maze.difficulty.DifficultyLevel;
import backend.academy.maze.other.MazeSymbol;
import java.util.Arrays;
import java.util.Random;
import lombok.Getter;

@Getter
public abstract class MazeGenerator implements MazeInterface {
    private final int height;
    private final int width;
    private final DifficultyLevel difficultyLevel;

    private int start;
    private int finish;

    MazeGenerator(
        int height,
        int width,
        DifficultyLevel difficultyLevel,
        int start,
        int finish
    ) {
        this.height = height;
        this.width = width;
        this.difficultyLevel = difficultyLevel;
        this.start = start;
        this.finish = finish;
    }

    MazeGenerator(int height, int width, DifficultyLevel difficultyLevel) {
        this.height = height;
        this.width = width;
        this.difficultyLevel = difficultyLevel;
    }

    public void init(char[][] maze) {
        for (int y = 0; y < height(); y++) {
            for (int x = 0; x < width(); x++) {
                maze[y][x] = MazeSymbol.WALL.symbol();
            }
        }
    }

    public char[][] setImpediment(char[][] maze) {
        Random rand = new Random();
        int rows = maze.length;
        int cols = maze[0].length;
        //CHECKSTYLE:OFF
        double obstacleDensity = 0.25;
        int emptyCells = 0;
        //CHECKSTYLE:ON
        for (char[] chars : maze) {
            for (int j = 0; j < cols; j++) {
                if (chars[j] == MazeSymbol.EMPTY.symbol()) {
                    emptyCells++;
                }
            }
        }

        int obstaclesToAdd = (int) (emptyCells * obstacleDensity);

        while (obstaclesToAdd > 0) {
            int randRow = rand.nextInt(rows);
            int randCol = rand.nextInt(cols);

            if (maze[randRow][randCol] == MazeSymbol.EMPTY.symbol()
                && maze[randRow][randCol] != MazeSymbol.START.symbol()
                && maze[randRow][randCol] != MazeSymbol.FINISH.symbol()) {
                maze[randRow][randCol] = MazeSymbol.IMPEDIMENT.symbol();
                obstaclesToAdd--;
            }
        }

        return maze;
    }

    public void setBoundaryWalls(char[][] maze, int i) {
        if (i == 0 || i == maze.length - 1) {
            fillRowWithWall(maze, i);
        } else {
            maze[i][0] = MazeSymbol.WALL.symbol();
            maze[i][maze[0].length - 1] = MazeSymbol.WALL.symbol();
        }

    }

    public void fillRowWithWall(char[][] maze, int row) {
        Arrays.fill(maze[row], MazeSymbol.WALL.symbol());
    }
}
