package trie;

import org.junit.Test;
import util.FileOperation;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Angus
 * @date 2018/10/20
 */
public class TrieTest {

    @Test
    public void testTrie() {
        List<String> words = new ArrayList<>();
        System.out.println("A Tale of Two Cities");
        String file = this.getClass()
                .getClassLoader()
                .getResource("pride-and-prejudice.txt")
                .getFile();
        FileOperation.readFile(file, words);
        System.out.println("Total words: " + words.size());

        Trie trie = new Trie();
        for (String word : words) {
            trie.add(word);
        }
        System.out.println("Total different size: " + trie.getSize());
    }

}