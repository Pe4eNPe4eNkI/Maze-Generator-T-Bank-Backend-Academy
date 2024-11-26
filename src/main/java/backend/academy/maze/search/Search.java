package backend.academy.maze.search;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lombok.Getter;

public class Search {

    private Search() {
    }

    @Getter
    private static Map<SearchType, String> searchTypeMap = Map.of(
        SearchType.BFS, "BFS",
        SearchType.A_STAR, "A-STAR"
    );

    public static SearchType getSearchType(String selectedSearchType) {
        String selectedSearchTypeCopy;
        HashMap<String, SearchType> searchTypeMapRevers = new HashMap<>();
        Random random = new Random();
        String[] values = searchTypeMap.values().toArray(new String[0]);

        for (HashMap.Entry<SearchType, String> entry : searchTypeMap.entrySet()) {
            searchTypeMapRevers.put(entry.getValue(), entry.getKey());
        }

        selectedSearchTypeCopy = selectedSearchType.toUpperCase();
        if (searchTypeMap().containsValue(selectedSearchTypeCopy)) {
            return searchTypeMapRevers.get(selectedSearchTypeCopy);
        }

        int randomIndex = random.nextInt(searchTypeMap().size());
        return searchTypeMapRevers.get(values[randomIndex]);
    }
}
