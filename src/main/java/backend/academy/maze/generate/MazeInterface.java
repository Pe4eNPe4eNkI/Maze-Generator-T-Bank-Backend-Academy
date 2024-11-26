package backend.academy.maze.generate;

public interface MazeInterface {
    char[][] generateMaze();

    void init(char[][] maze);

    char[][] setImpediment(char[][] maze);

    void setBoundaryWalls(char[][] maze, int i);

    void fillRowWithWall(char[][] maze, int row);
}
