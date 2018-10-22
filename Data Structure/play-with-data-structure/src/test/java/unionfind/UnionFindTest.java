package unionfind;


import org.junit.Test;

import java.util.Random;

/**
 * @author Angus
 * @date 2018/10/20
 */
public class UnionFindTest {

    private double testUnionFind(UnionFind unionFind, int m) {

        int size = unionFind.geiSize();
        Random random = new Random();
        long startTime = System.nanoTime();
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            unionFind.unionElements(a, b);
        }
        for (int i = 0; i < m; i++) {
            int a = random.nextInt(size);
            int b = random.nextInt(size);
            unionFind.isConnected(a, b);
        }
        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;

    }

    @Test
    public void compare() {
        int size = 10000000;
        int m = 10000000;
        UnionFind unionFind;
//        unionFind = new QuickFind(size);
//        System.out.println("Quick Find: " + testUnionFind(unionFind, m) + " s");
//        unionFind = new QuickUnion(size);
//        System.out.println("Quick Union: " + testUnionFind(unionFind, m) + " s");
        unionFind = new QuickUnion2(size);
        System.out.println("Quick Union2: " + testUnionFind(unionFind, m) + " s");
        unionFind = new QuickUnion3(size);
        System.out.println("Quick Union3: " + testUnionFind(unionFind, m) + " s");
        unionFind = new QuickUnion4(size);
        System.out.println("Quick Union4: " + testUnionFind(unionFind, m) + " s");
        unionFind = new QuickUnion5(size);
        System.out.println("Quick Union5: " + testUnionFind(unionFind, m) + " s");
    }

}