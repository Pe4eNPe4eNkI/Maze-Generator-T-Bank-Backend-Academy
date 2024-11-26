package backend.academy.maze.search;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SearchTest {

    @Test
    void testGetCurrentSearchType() {
        SearchType bfs = Search.getSearchType("BFS");
        SearchType aStar = Search.getSearchType("A-Star");

        assertEquals(SearchType.BFS, bfs, "Неверный тип алгоритма поиска!");
        assertEquals(SearchType.A_STAR, aStar, "Неверный тип алгоритма поиска!");
    }
}
