package backend.academy.maze;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Main {

    public static void main(String[] args) {
        GameSession gameSession = new GameSession();
        Drawing drawing = new Drawing();
        char[][] maze = gameSession.generate();
        drawing.printMaze(maze);

        maze = gameSession.currentMazeGeneration(maze);
        drawing.printMaze(maze);

        gameSession.search(maze);
    }
}
