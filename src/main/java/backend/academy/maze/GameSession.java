package backend.academy.maze;

import backend.academy.maze.difficulty.Difficulty;
import backend.academy.maze.difficulty.DifficultyLevel;
import backend.academy.maze.generate.Backtracker;
import backend.academy.maze.generate.Generate;
import backend.academy.maze.generate.GenerateEntranceAndExit;
import backend.academy.maze.generate.GenerateType;
import backend.academy.maze.generate.MazeGenerator;
import backend.academy.maze.generate.Prim;
import backend.academy.maze.other.Point;
import backend.academy.maze.search.AStar;
import backend.academy.maze.search.BFS;
import backend.academy.maze.search.Search;
import backend.academy.maze.search.SearchPath;
import backend.academy.maze.search.SearchType;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import lombok.Setter;

public class GameSession {
    private int height;
    private int width;
    private DifficultyLevel difficultyLevel;
    private GenerateType generateType;
    private SearchType searchType;
    private int start;
    private int finish;
    @Setter
    private Scanner inInt = new Scanner(System.in);
    @Setter
    private Scanner in = new Scanner(System.in);

    public GameSession() {
        initialization();
    }

    private void initialization() {
        //CHECKSTYLE:OFF
        System.out.println("Введите желаемый размер лабиринта:");
        height = selectHeight();
        width = selectWidth();
        System.out.println("Размер лабиринта: " + height + " " + width);

        System.out.println("Введите желаемый уровень сложности лабиринта");
        difficultyLevel = selectDifficulty();
        generateType = selectGenerateType();
        searchType = selectedSearchType();
        //CHECKSTYLE:ON
    }

    public char[][] currentMazeGeneration(char[][] maze) {
        //CHECKSTYLE:OFF
        System.out.println("Введите на какой высоте будет находиться точка входа:");
        start = selectStart();
        finish = selectFinish();
        System.out.println("вход и выход лабиринта находится на высоте: " + start + " " + finish);
        //CHECKSTYLE:ON

        GenerateEntranceAndExit generateEntranceAndExit = new GenerateEntranceAndExit();

        return generateEntranceAndExit.getCurrentMaze(maze, start, finish);
    }

    public char[][] generate() {
        MazeGenerator mazeGenerator = switch (generateType) {
            case PRIM -> new Prim(height, width, difficultyLevel);
            case BACKTRACKER -> new Backtracker(height, width, difficultyLevel);
        };

        char[][] maze = mazeGenerator.generateMaze();
        if (searchType == SearchType.A_STAR) {
            maze = mazeGenerator.setImpediment(maze);
        }
        return maze;
    }

    public void search(char[][] maze) {
        SearchPath searchPath = switch (searchType) {
            case BFS -> new BFS(maze);
            case A_STAR -> new AStar(maze);

        };
        Drawing drawing = new Drawing();
        List<Point> path = searchPath.findPath(maze);

        //CHECKSTYLE:OFF
        if (path != null) {
            System.out.println("Путь найден:");
            drawing.markPathWithArrows(maze, path);
        } else {
            System.out.println("Путь не найден.");
        }
        //CHECKSTYLE:ON
    }

    public int selectHeight() {
        int curHeight;
        //CHECKSTYLE:OFF
        while (true) {
            try {
                System.out.println("Введите длину не меньше 10 и не более 60:");
                curHeight = inInt.nextInt();

                if (curHeight >= 10 && curHeight <= 60) {
                    break;
                } else {
                    System.out.println("Введите корректную высоту!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число!");
                inInt.next();
            }
        }
        //CHECKSTYLE:ON
        return curHeight;
    }

    public int selectWidth() {
        int curWidth;
        //CHECKSTYLE:OFF
        while (true) {
            try {
                System.out.println("Введите ширину не меньше 10 и не более 60:");
                curWidth = inInt.nextInt();

                if (curWidth >= 10 && curWidth <= 60) {
                    break;
                } else {
                    System.out.println("Введите корректную ширину!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число!");
                inInt.next();
            }
        }
        //CHECKSTYLE:ON
        return curWidth;
    }

    public int selectStart() {
        int curStart;
        //CHECKSTYLE:OFF
        while (true) {
            try {
                System.out.println("Введите высоту входа:");
                curStart = inInt.nextInt();

                if (curStart > 1 && curStart < height) {
                    break;
                } else {
                    System.out.println("Введите корректную высоту входа!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число!");
                inInt.next();
            }
        }
        //CHECKSTYLE:ON
        return curStart;
    }

    public int selectFinish() {
        int curFinish;
        //CHECKSTYLE:OFF
        while (true) {
            try {
                System.out.println("Введите высоту выхода:");
                curFinish = inInt.nextInt();

                if (curFinish > 1 && curFinish < height) {
                    break;
                } else {
                    System.out.println("Введите корректную высоту выхода!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка: введите целое число!");
                inInt.next();
            }
        }
        //CHECKSTYLE:ON
        return curFinish;
    }

    public DifficultyLevel selectDifficulty() {
        //CHECKSTYLE:OFF
        System.out.println("Выберете уровень сложности:");
        for (String difficulty : Difficulty.difficultyLevelMap().values()) {
            System.out.println(difficulty);
            //CHECKSTYLE:ON
        }
        String selectDifficulty;
        try {
            selectDifficulty = in.nextLine();

        } catch (Exception e) {
            selectDifficulty = "";
        }
        DifficultyLevel difficultyLvl = Difficulty.getDifficultyLevel(selectDifficulty);
        //CHECKSTYLE:OFF
        System.out.println("Сложность: " + Difficulty.difficultyLevelMap().get(difficultyLvl));
        //CHECKSTYLE:ON
        return difficultyLvl;
    }

    public GenerateType selectGenerateType() {
        //CHECKSTYLE:OFF
        System.out.println("Выберете алгоритм генерации лабиринта:");
        for (String type : Generate.generateTypeMap().values()) {
            System.out.println(type);
            //CHECKSTYLE:ON
        }
        String selectedGenerateType;
        try {
            selectedGenerateType = in.nextLine();

        } catch (Exception e) {
            selectedGenerateType = "";
        }
        GenerateType curGenerateType = Generate.getGenerateType(selectedGenerateType);
        //CHECKSTYLE:OFF
        System.out.println("Алгоритм генерации: " + Generate.generateTypeMap().get(curGenerateType));
        //CHECKSTYLE:ON
        return curGenerateType;
    }

    public SearchType selectedSearchType() {
        //CHECKSTYLE:OFF
        System.out.println("Выберете алгоритм поиска пути:");
        System.out.println("Если вы выберете A-Star, то будут генерироваться дополнительные препятствия");

        for (String type : Search.searchTypeMap().values()) {
            System.out.println(type);
            //CHECKSTYLE:ON
        }
        String selectedSearchType = in.nextLine();
        SearchType curSearchType = Search.getSearchType(selectedSearchType);
        //CHECKSTYLE:OFF
        System.out.println("Алгоритм поиска: " + Search.searchTypeMap().get(curSearchType));
        //CHECKSTYLE:ON
        return curSearchType;
    }
}
