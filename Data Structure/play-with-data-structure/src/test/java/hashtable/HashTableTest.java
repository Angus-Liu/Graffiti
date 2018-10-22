package hashtable;

import binarysearchtree.BinarySearchTree;
import map.AVLMap;
import map.BSTMap;
import org.junit.Test;
import redblacktree.RedBlackTree;
import util.FileOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Angus
 * @date 2018/10/21
 */
public class HashTableTest {

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
        long startTime = System.nanoTime();
        HashTable<String, Integer> table = new HashTable<>();
        for (String word : words) {
            Integer count = table.get(word);
            table.add(word, count == null ? 1 : ++count);
        }
        long endTime = System.nanoTime();
        System.out.println("Frequency of cities: " + table.get("cities"));
        System.out.println("HashTable: " + (endTime - startTime) / 1000000000.0 + "s");
        System.out.println("---------");
        startTime = System.nanoTime();
        BSTMap<String, Integer> map = new BSTMap<>();
        for (String word : words) {
            Integer count = map.get(word);
            map.add(word, count == null ? 1 : ++count);
        }
        endTime = System.nanoTime();
        System.out.println("Frequency of cities: " + map.get("cities"));
        System.out.println("BSTMap: " + (endTime - startTime) / 1000000000.0 + "s");
        System.out.println("---------");
        startTime = System.nanoTime();
        AVLMap<String, Integer> avlMap = new AVLMap<>();
        for (String word : words) {
            Integer count = avlMap.get(word);
            avlMap.add(word, count == null ? 1 : ++count);
        }
        endTime = System.nanoTime();
        System.out.println("Frequency of cities: " + avlMap.get("cities"));
        System.out.println("AVLMap: " + (endTime - startTime) / 1000000000.0 + "s");
        System.out.println("---------");
        startTime = System.nanoTime();
        RedBlackTree<String, Integer> redBlackTree = new RedBlackTree<>();
        for (String word : words) {
            Integer count = redBlackTree.get(word);
            redBlackTree.add(word, count == null ? 1 : ++count);
        }
        endTime = System.nanoTime();
        System.out.println("Frequency of cities: " + redBlackTree.get("cities"));
        System.out.println("RedBlackTree: " + (endTime - startTime) / 1000000000.0 + "s");
    }
}