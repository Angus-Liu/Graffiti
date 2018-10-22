package trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 字典树/前缀树的简单实现
 *
 * @author Angus
 * @date 2018/10/20
 */
public class Trie {
    /**
     * 字典树节点类
     */
    private class Node {
        /**
         * 当前节点是否是一个单词的结尾
         */
        public boolean isWord;

        /**
         * 该节点到下一个节点的映射，数量是不定的，故而用 map 存储
         */
        public Map<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            next = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }

    /**
     * 字典树整体根节点
     */
    private Node root;

    private int size;

    public Trie() {
        root = new Node();
        size = 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * 向 Trie 中添加一个新的单词
     *
     * @param word
     */
    public void add(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c =  word.charAt(i);
            // 判断该节点是否包含该字符对应节点的映射
            if (cur.next.get(c) == null) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        // 确保该单词没有被添加过，防止 size 重复计算
        if (!cur.isWord) {
            // 标注最后一个节点
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 查询该单词是否在 Trie 中
     * 
     * @param word
     * @return
     */
    public boolean contains(String word) {
        Node cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        // 返回 isWord，确保 word 是独立存在的，而不仅是某个单词的中间值（例如 pan & panda）
        return cur.isWord;
    }

    /**
     * 查询是否在 Trie 中有单词以 prefix 为前缀
     *
     * @return
     */
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if (cur.next.get(c) == null) {
                return false;
            }
            cur = cur.next.get(c);
        }
        return true;
    }
}
