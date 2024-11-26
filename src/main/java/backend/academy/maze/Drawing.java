package backend.academy.maze;

import backend.academy.maze.other.MazeSymbol;
import backend.academy.maze.other.Point;
import java.util.List;
import java.util.Objects;

public class Drawing {

    public void printMaze(char[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                char cell = maze[i][j];
                if (cell == MazeSymbol.WALL.symbol()) {
                    //CHECKSTYLE:OFF
                    System.out.print(getWallCharacter(maze, i, j));
                    //CHECKSTYLE:ON
                } else {
                    //CHECKSTYLE:OFF
                    System.out.print(cell);
                    //CHECKSTYLE:ON
                }
            }
            //CHECKSTYLE:OFF
            System.out.println();
            //CHECKSTYLE:ON
        }
    }

    public void markPathWithArrows(char[][] maze, List<Point> path) {
        for (int i = 0; i < path.size() - 1; i++) {
            Point current = path.get(i);
            Point next = path.get(i + 1);
            if (maze[current.x()][current.y()] != 'S' && maze[current.x()][current.y()] != 'F') {
                if (next.x() < current.x()) {
                    maze[current.x()][current.y()] = '↑'; // вверх
                } else if (next.x() > current.x()) {
                    maze[current.x()][current.y()] = '↓'; // вниз
                } else if (next.y() < current.y()) {
                    maze[current.x()][current.y()] = '←'; // влево
                } else if (next.y() > current.y()) {
                    maze[current.x()][current.y()] = '→'; // вправо
                }
            }
        }
        Drawing drawing = new Drawing();
        drawing.printMaze(maze);
    }

    public String getWallCharacter(char[][] maze, int x, int y) {
        boolean isTopWall = isWall(maze, x - 1, y);
        boolean isBottomWall = isWall(maze, x + 1, y);
        boolean isLeftWall = isWall(maze, x, y - 1);
        boolean isRightWall = isWall(maze, x, y + 1);

        String wallCharacter = "";

        if (isCorner(isTopWall, isBottomWall, isLeftWall, isRightWall)) {
            wallCharacter = getCornerSymbol(isTopWall, isBottomWall, isLeftWall, isRightWall);
        } else if (isTShape(isTopWall, isBottomWall, isLeftWall, isRightWall)) {
            wallCharacter = getTShapeSymbol(isTopWall, isBottomWall, isLeftWall, isRightWall);
        } else if (isCross(isTopWall, isBottomWall, isLeftWall, isRightWall)) {
            wallCharacter = String.valueOf(MazeSymbol.CROSS.symbol());
        } else if (isVertical(isTopWall, isBottomWall, isLeftWall, isRightWall)) {
            wallCharacter = String.valueOf(MazeSymbol.VERTICAL.symbol());
        } else if (isHorizontal(isLeftWall, isRightWall, isTopWall, isBottomWall)) {
            wallCharacter = String.valueOf(MazeSymbol.HORIZONTAL.symbol());
        }

        return Objects.equals(wallCharacter, "") ? String.valueOf(MazeSymbol.WALL.symbol()) : wallCharacter;
    }

    private boolean isWall(char[][] maze, int x, int y) {
        return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == MazeSymbol.WALL.symbol();
    }

    private boolean isCorner(boolean isTop, boolean isBottom, boolean isLeft, boolean isRight) {
        return (isTop && isLeft && !isBottom && !isRight) || (isTop && isRight && !isBottom && !isLeft)
            || (isBottom && isLeft && !isTop && !isRight) || (isBottom && isRight && !isTop && !isLeft);
    }

    private String getCornerSymbol(boolean isTop, boolean isBottom, boolean isLeft, boolean isRight) {
        String cornerSymbol = "";
        if (isTop && isLeft) {
            cornerSymbol = String.valueOf(MazeSymbol.BOTTOM_RIGHT_CORNER.symbol());
        } else if (isTop && isRight) {
            cornerSymbol = String.valueOf(MazeSymbol.BOTTOM_LEFT_CORNER.symbol());
        } else if (isBottom && isLeft) {
            cornerSymbol = String.valueOf(MazeSymbol.TOP_RIGHT_CORNER.symbol());
        } else if (isBottom && isRight) {
            cornerSymbol = String.valueOf(MazeSymbol.TOP_LEFT_CORNER.symbol());
        }
        return cornerSymbol;
    }

    private boolean isTShape(boolean isTop, boolean isBottom, boolean isLeft, boolean isRight) {
        return (isTop && isBottom && (isLeft || isRight)) || (isLeft && isRight && (isTop || isBottom));
    }

    private String getTShapeSymbol(boolean isTop, boolean isBottom, boolean isLeft, boolean isRight) {
        String tShapeSymbol = "";
        if (isTop && isBottom && isLeft) {
            tShapeSymbol = String.valueOf(MazeSymbol.RIGHT_T.symbol());
        } else if (isTop && isBottom && isRight) {
            tShapeSymbol = String.valueOf(MazeSymbol.LEFT_T.symbol());
        } else if (isLeft && isRight && isTop) {
            tShapeSymbol = String.valueOf(MazeSymbol.UP_T.symbol());
        } else if (isLeft && isRight && isBottom) {
            tShapeSymbol = String.valueOf(MazeSymbol.DOWN_T.symbol());
        }
        return tShapeSymbol;
    }

    private boolean isCross(boolean isTop, boolean isBottom, boolean isLeft, boolean isRight) {
        return isTop && isBottom && isLeft && isRight;
    }

    private boolean isVertical(boolean isTop, boolean isBottom, boolean isLeft, boolean isRight) {
        return (isTop && isBottom) || ((isTop || isBottom) && !isLeft && !isRight);
    }

    private boolean isHorizontal(boolean isLeft, boolean isRight, boolean isTop, boolean isBottom) {
        return (isLeft && isRight) || ((isLeft || isRight) && !isTop && !isBottom);
    }
}
