package avl;

import binarysearchtree.BinarySearchTree;
import map.BSTMap;
import org.junit.Test;
import set.BSTSet;
import util.FileOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Angus
 * @date 2018/10/21
 */
public class AVLTreeTest {

    @Test
    public void wordCount() {
        List<String> words = new ArrayList<>();
        System.out.println("Pride and Prejudice");
        String file = this.getClass()
                .getClassLoader()
                .getResource("pride-and-prejudice.txt")
                .getFile();
        FileOperation.readFile(file, words);
        System.out.println("Total words: " + words.size());

        // 排好序的 words，会使普通的 BST 退化成链表，而 AVL 有自平衡机制，则不会
        Collections.sort(words);

        long startTime = System.nanoTime();
        AVLTree<String, Integer> tree = new AVLTree<>();
        for (String word : words) {
            if (tree.contains(word)) {
                tree.set(word, tree.get(word) + 1);
            } else {
                tree.add(word, 1);
            }
        }
        long endTime = System.nanoTime();
        System.out.println("AVL: " + (endTime - startTime) / 1000000000.0 + " s");
        System.out.println("Total different size: " + tree.getSize());
        System.out.println("Frequency of PRIDE: " + tree.get("pride"));
        System.out.println("Frequency of PREJUDICE: " + tree.get("prejudice"));
        System.out.println("Is BST: " + tree.isBST());
        System.out.println("Is Balanced: " + tree.isBalanced());
        System.out.println("// ==================================================== //");
        startTime = System.nanoTime();
        BSTMap<String, Integer> map = new BSTMap<>();
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word, map.get(word) + 1);
            } else {
                map.add(word, 1);
            }
        }
        endTime = System.nanoTime();
        System.out.println("BSTMap: " + (endTime - startTime) / 1000000000.0 + " s");
        System.out.println("Total different size: " + map.getSize());
        System.out.println("Frequency of PRIDE: " + map.get("pride"));
        System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
    }

    @Test
    public void remove() {
        List<String> words = new ArrayList<>();
        System.out.println("Pride and Prejudice");
        String file = this.getClass()
                .getClassLoader()
                .getResource("pride-and-prejudice.txt")
                .getFile();
        FileOperation.readFile(file, words);
        System.out.println("Total words: " + words.size());

        AVLTree<String, Integer> tree = new AVLTree<>();
        for (String word : words) {
            if (tree.contains(word)) {
                tree.set(word, tree.get(word) + 1);
            } else {
                tree.add(word, 1);
            }
        }
        System.out.println("Total different size: " + tree.getSize());
        System.out.println("Frequency of PRIDE: " + tree.get("pride"));
        System.out.println("Frequency of PREJUDICE: " + tree.get("prejudice"));
        System.out.println("Is BST: " + tree.isBST());
        System.out.println("Is Balanced: " + tree.isBalanced());
        for (String word : words) {
            tree.remove(word);
           if (!tree.isBST() || !tree.isBalanced()) {
               throw new IllegalStateException("Is not AVLTree!");
           }
        }
    }
}