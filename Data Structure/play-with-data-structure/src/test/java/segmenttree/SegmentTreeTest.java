package segmenttree;


import org.junit.Test;

/**
 * @author Angus
 * @date 2018/10/13
 */
public class SegmentTreeTest {

    @Test
    public void sum() {
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, (a, b) -> a + b);
        System.out.println(segmentTree);
        System.out.println(segmentTree.query(0, 2));
        System.out.println(segmentTree.query(2, 5));
        segmentTree.set(2, 2);
        System.out.println(segmentTree.query(0, 2));
        System.out.println(segmentTree.query(2, 5));
    }

}