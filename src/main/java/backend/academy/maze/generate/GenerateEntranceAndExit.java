package backend.academy.maze.generate;

import backend.academy.maze.other.MazeSymbol;

public class GenerateEntranceAndExit {

    public void setEntranceAndExit(char[][] maze, int entranceHeight, int exitHeight) {
        maze[entranceHeight - 1][0] = MazeSymbol.START.symbol();
        maze[exitHeight - 1][maze[0].length - 1] = MazeSymbol.FINISH.symbol();
    }

    public char[][] getCurrentMaze(char[][] maze, int start, int finish) {
        setEntranceAndExit(maze, start, finish);

        for (int i = 0; i < maze.length; i++) {
            setBoundaryWalls(maze, i);
        }

        maze[start - 1][1] = MazeSymbol.EMPTY.symbol();
        maze[finish - 1][maze[0].length - 2] = MazeSymbol.EMPTY.symbol();

        return maze;
    }

    private void setBoundaryWalls(char[][] maze, int i) {

        if (maze[i][0] != MazeSymbol.START.symbol()) {
            maze[i][0] = MazeSymbol.WALL.symbol();
        }
        if (maze[i][maze[0].length - 1] != MazeSymbol.FINISH.symbol()) {
            maze[i][maze[0].length - 1] = MazeSymbol.WALL.symbol();
        }

    }

}
