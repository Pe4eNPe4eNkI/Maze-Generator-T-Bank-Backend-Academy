package backend.academy.maze.genarate;

import backend.academy.maze.difficulty.DifficultyLevel;
import backend.academy.maze.generate.GenerateEntranceAndExit;
import backend.academy.maze.generate.MazeGenerator;
import backend.academy.maze.generate.Prim;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PrimTest {

    int height = 10;
    int width = 20;
    int entrance = 5;
    int exit = 7;
    MazeGenerator mazeGenerator = new Prim(height, width, DifficultyLevel.EASY);

    @Test
    void isCurrentSizeTest() {
        char[][] maze = mazeGenerator.generateMaze();

        assertEquals(height, maze.length, "Некорректная длина лабиринта!");
        assertEquals(width, maze[0].length, "Некорректная ширина лабиринта!");
    }

    @Test
    void isSingleEntranceTest() {
        char[][] maze = mazeGenerator.generateMaze();
        GenerateEntranceAndExit generateEntranceAndExit = new GenerateEntranceAndExit();
        maze = generateEntranceAndExit.getCurrentMaze(maze, entrance, exit);
        int entranceCount = 0;

        for (char[] row : maze) {
            for (char cell : row) {
                if (cell == 'S') {
                    entranceCount++;
                    assertTrue(entranceCount <= 1, "Найдено больше одного входа!");
                }
            }
        }
        assertTrue(entranceCount > 0, "Не найдено ни одного входа!");
    }

    @Test
    void isSingleExitTest() {
        char[][] maze = mazeGenerator.generateMaze();
        GenerateEntranceAndExit generateEntranceAndExit = new GenerateEntranceAndExit();
        maze = generateEntranceAndExit.getCurrentMaze(maze, entrance, exit);
        int exitCount = 0;

        for (char[] row : maze) {
            for (char cell : row) {
                if (cell == 'F') {
                    exitCount++;
                    assertTrue(exitCount <= 1, "Найдено больше одного выхода!");
                }
            }
        }
        assertTrue(exitCount > 0, "Не найдено ни одного выхода!");
    }

    @Test
    void isCurrentEntranceTest() {
        char[][] maze = mazeGenerator.generateMaze();
        GenerateEntranceAndExit generateEntranceAndExit = new GenerateEntranceAndExit();
        maze = generateEntranceAndExit.getCurrentMaze(maze, entrance, exit);
        assertEquals('S', maze[entrance - 1][0], "Неверная точка входа!");
    }

    @Test
    void isCurrentExitTest() {
        char[][] maze = mazeGenerator.generateMaze();
        GenerateEntranceAndExit generateEntranceAndExit = new GenerateEntranceAndExit();
        maze = generateEntranceAndExit.getCurrentMaze(maze, entrance, exit);
        assertEquals('F', maze[exit - 1][maze[0].length - 1], "Неверная точка выхода!");
    }
}
