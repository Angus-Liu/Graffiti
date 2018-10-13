package binarysearchtree;

import org.junit.Test;

/**
 * @author Angus
 * @date 2018/10/9
 */
public class BinarySearchTreeTest {

    @Test
    public void add() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] nums = {2, 5, 6, 1, 23, 33, 17, 4, 12, 2, 3, 100, 34, 122, -1};
        for (int num : nums) {
            bst.addNr(num);
        }
        System.out.println(bst);
    }

    @Test
    public void contains() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] nums = {2, 5, 6, 1, 23, 33, 17, 4, 12, 2, 3, 100, 34, 122, -1};
        for (int num : nums) {
            bst.addNr(num);
        }
        System.out.println(bst.contains(1));
        System.out.println(bst.contains(-1));
        System.out.println(bst.contains(1111));
    }

    @Test
    public void traverse() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] nums = {5, 3, 2, 4, 6, 8};
        for (int num : nums) {
            bst.add(num);
        }
        bst.preOrder();
        System.out.println("#===#");
        bst.preOrderNr();
        System.out.println("#===#");
        bst.inOrder();
        System.out.println("#===#");
        bst.postOrder();
        System.out.println("#===#");
        bst.levelOrder();
    }

    @Test
    public void remove() {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        int[] nums = {5, 3, 2, 4, 6, 8};
        for (int num : nums) {
            bst.add(num);
        }
        bst.inOrder();
        System.out.println("====");
        bst.remove(6);
        bst.inOrder();
        System.out.println("size:" + bst.size());
        System.out.println("====");
        bst.remove(2);
        bst.inOrder();
        System.out.println("size:" + bst.size());
    }
}