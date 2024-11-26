package backend.academy.maze.difficulty;

import backend.academy.maze.other.MazeSymbol;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import lombok.Getter;

public class Difficulty {
    private Difficulty() {
    }

    @Getter
    private static Map<DifficultyLevel, String> difficultyLevelMap = Map.of(
        DifficultyLevel.EASY, "Легкий",
        DifficultyLevel.MEDIUM, "Средний",
        DifficultyLevel.HARD, "Высокий"
    );

    public static DifficultyLevel getDifficultyLevel(String selectedDifficulty) {
        String selectedDifficultyCopy;
        HashMap<String, DifficultyLevel> difficultyLevelMapRevers = new HashMap<>();
        Random random = new Random();
        String[] values = difficultyLevelMap.values().toArray(new String[0]);

        for (HashMap.Entry<DifficultyLevel, String> entry : difficultyLevelMap.entrySet()) {
            difficultyLevelMapRevers.put(entry.getValue(), entry.getKey());
        }

        selectedDifficultyCopy = !Objects.equals(selectedDifficulty, "")
            ? selectedDifficulty.substring(0, 1).toUpperCase() + selectedDifficulty.substring(1).toLowerCase()
            : "random";
        if (difficultyLevelMap().containsValue(selectedDifficultyCopy)) {
            return difficultyLevelMapRevers.get(selectedDifficultyCopy);
        }

        int randomIndex = random.nextInt(difficultyLevelMap().size());
        return difficultyLevelMapRevers.get(values[randomIndex]);
    }

    static char[][] setMaze(char[][] maze, int elementsToRemove, char targetSymbol, char replacementSymbol) {
        Random random = new Random();
        int removedCount = 0;

        int rows = maze.length;
        int cols = maze[0].length;

        while (removedCount < elementsToRemove) {
            int randomX = random.nextInt(rows - 2) + 1;
            int randomY = random.nextInt(cols - 2) + 1;

            if (maze[randomX][randomY] == targetSymbol) {
                maze[randomX][randomY] = replacementSymbol;
                removedCount++;
            }
        }
        return maze;
    }

    static char[][] setEasyMaze(char[][] maze) {
        return setMaze(maze, 2 * maze.length, MazeSymbol.WALL.symbol(), MazeSymbol.EMPTY.symbol());
    }

    static char[][] setHardMaze(char[][] maze) {
        return setMaze(maze, maze.length, MazeSymbol.EMPTY.symbol(), MazeSymbol.WALL.symbol());
    }

    public static char[][] setDifficulty(char[][] maze, DifficultyLevel difficultyLevel) {
        return switch (difficultyLevel) {
            case EASY -> setEasyMaze(maze);
            case MEDIUM -> maze;
            case HARD -> setHardMaze(maze);
        };
    }

}
