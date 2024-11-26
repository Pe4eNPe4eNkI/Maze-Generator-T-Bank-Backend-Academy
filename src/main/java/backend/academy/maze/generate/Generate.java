package backend.academy.maze.generate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import lombok.Getter;

public class Generate {
    private Generate() {
    }

    @Getter
    private static Map<GenerateType, String> generateTypeMap = Map.of(
        GenerateType.PRIM, "Прима",
        GenerateType.BACKTRACKER, "Backtracker"
    );

    public static GenerateType getGenerateType(String selectedGenerateType) {
        String selectedGenerateTypeCopy;
        HashMap<String, GenerateType> generateTypeMapRevers = new HashMap<>();
        Random random = new Random();
        String[] values = generateTypeMap.values().toArray(new String[0]);

        for (HashMap.Entry<GenerateType, String> entry : generateTypeMap.entrySet()) {
            generateTypeMapRevers.put(entry.getValue(), entry.getKey());
        }

        selectedGenerateTypeCopy = !Objects.equals(selectedGenerateType, "")
            ? selectedGenerateType.substring(0, 1).toUpperCase() + selectedGenerateType.substring(1).toLowerCase()
            : "random";
        if (generateTypeMap.containsValue(selectedGenerateTypeCopy)) {
            return generateTypeMapRevers.get(selectedGenerateTypeCopy);
        }

        int randomIndex = random.nextInt(generateTypeMap.size());
        return generateTypeMapRevers.get(values[randomIndex]);
    }
}
