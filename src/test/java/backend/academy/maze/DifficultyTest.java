package backend.academy.maze;

import backend.academy.maze.difficulty.Difficulty;
import backend.academy.maze.difficulty.DifficultyLevel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class DifficultyTest {

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

    @Test
    void getCurrentDifficultyTest() {
        DifficultyLevel easy = Difficulty.getDifficultyLevel("Легкий");
        DifficultyLevel medium = Difficulty.getDifficultyLevel("Средний");
        DifficultyLevel hard = Difficulty.getDifficultyLevel("Высокий");

        assertEquals(DifficultyLevel.EASY, easy, "Неверный уровень сложности!");
        assertEquals(DifficultyLevel.MEDIUM, medium, "Неверный уровень сложности!");
        assertEquals(DifficultyLevel.HARD, hard, "Неверный уровень сложности!");
    }

    @Test
    void setCurrentHardDifficultyTest() {
        int emptyCount = countEmptySpaces(maze);
        char[][] hardMaze = Difficulty.setDifficulty(maze, DifficultyLevel.HARD);
        int emptyCountHard = countEmptySpaces(hardMaze);

        assertEquals(emptyCount, emptyCountHard + maze.length, "Неверное кол-во пустых клеток!");
    }

    @Test
    void setCurrentEasyDifficultyTest() {
        int emptyCount = countEmptySpaces(maze);
        char[][] easyMaze = Difficulty.setDifficulty(maze, DifficultyLevel.EASY);
        int emptyCountEasy = countEmptySpaces(easyMaze);

        assertEquals(emptyCount, emptyCountEasy - 2 * maze.length, "Неверное кол-во пустых клеток!");
    }

    public static int countEmptySpaces(char[][] maze) {
        int count = 0;
        for (char[] chars : maze) {
            for (char aChar : chars) {
                if (aChar == ' ') {
                    count++;
                }
            }
        }
        return count;
    }
}
