package map;

import org.junit.Test;
import util.FileOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Angus
 * @date 2018/10/10
 */
public class MapTest {

    @Test
    public void wordFrequencyStatistics() {
        List<String> words = new ArrayList<>();
        System.out.println("A Tale of Two Cities");
        String file = this.getClass()
                .getClassLoader()
                .getResource("a-tale-of-two-cities.txt")
                .getFile();
        FileOperation.readFile(file, words);
        System.out.println("Total words: " + words.size());

//        Map<String, Integer> map = new LinkedListMap<>();
        Map<String, Integer> map = new BSTMap<>();
        for (String word : words) {
            Integer count = map.get(word);
            map.add(word, count == null ? 1 : ++count);
        }
        System.out.println("Frequency of cities: " + map.get("cities"));
    }
}