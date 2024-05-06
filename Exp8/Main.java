import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    // Mapper function
    public static List<Pair<String, Integer>> map(String input) {
        return Arrays.stream(input.split("\\s+"))
        .map(word -> new Pair<>(word, 1))
        .collect(Collectors.toList());
    }
    // Reducer function
    public static Map<String, Integer> reduce(List<Pair<String, Integer>>
    pairs) {
        Map<String, Integer> counts = new HashMap<>();
        for (Pair<String, Integer> pair : pairs) {
        String word = pair.getKey();
        int count = counts.getOrDefault(word, 0);
        counts.put(word, count + pair.getValue());
        }
        return counts;
    }
    // Pair class to hold key-value pairs
    static class Pair<K, V> {
        private K key;
        private V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        public K getKey() {
            return key;
        }
        public V getValue() {
            return value;
        }
    }
    public static void main(String[] args) {
        // Input data
        String[] data = {
        "hello world",
        "world",
        "hello",
        "world world"
        };
        // Map phase
        List<Pair<String, Integer>> mapped = Arrays.stream(data)
        .flatMap(input -> map(input).stream())
        .collect(Collectors.toList());
        // Reduce phase
        Map<String, Integer> reduced = reduce(mapped);
        // Output result
        reduced.forEach((word, count) -> System.out.println(word + ": " +
        count));
    }
}