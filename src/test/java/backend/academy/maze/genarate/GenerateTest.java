package backend.academy.maze.genarate;

import backend.academy.maze.generate.Generate;
import backend.academy.maze.generate.GenerateType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GenerateTest {

    @Test
    void testGetCurrentGenerateType() {
        GenerateType prim = Generate.getGenerateType("Прима");
        GenerateType backtracker = Generate.getGenerateType("Backtracker");

        assertEquals(GenerateType.PRIM, prim, "Неверный тип алгоритма генерации!");
        assertEquals(GenerateType.BACKTRACKER, backtracker, "Неверный тип алгоритма генерации!");

    }
}
