package backend.academy.maze.other;

import lombok.Getter;

@Getter
public enum MazeSymbol {
    WALL('─'),
    EMPTY(' '),
    START('S'),
    FINISH('F'),
    IMPEDIMENT('^'),
    TOP_LEFT_CORNER('┌'),
    TOP_RIGHT_CORNER('┐'),
    BOTTOM_LEFT_CORNER('└'),
    BOTTOM_RIGHT_CORNER('┘'),
    VERTICAL('│'),
    HORIZONTAL('─'),
    CROSS('┼'),
    RIGHT_T('┤'),
    LEFT_T('├'),
    UP_T('┴'),
    DOWN_T('┬');

    private final char symbol;

    MazeSymbol(char symbol) {
        this.symbol = symbol;
    }

}
