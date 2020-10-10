package ch03.se04;

import java.math.BigInteger;
import java.util.Arrays;

/**
 * 对数值及其因数分解结果进行缓存的不可变容器类
 */
public class OneValueCache {

    private final BigInteger lastNumber;

    private final BigInteger[] lastFactories;

    public OneValueCache(BigInteger lastNumber, BigInteger[] lastFactories) {
        this.lastNumber = lastNumber;
        this.lastFactories = lastFactories.clone();
    }

    public BigInteger[] getFactories(BigInteger i) {
        if (lastNumber != null && lastNumber.equals(i))
            return lastFactories.clone();
        return null;
    }

    public static void main(String[] args) {
        BigInteger[] lastFactories = new BigInteger[10];
        Arrays.fill(lastFactories, new BigInteger("1"));
        BigInteger lastNumber = new BigInteger("1");
        OneValueCache o = new OneValueCache(lastNumber, lastFactories);

        BigInteger[] factories = o.getFactories(lastNumber);
        System.out.println("factories = " + Arrays.toString(factories));

        factories[0] = new BigInteger("0");
        System.out.println("factories = " + Arrays.toString(factories));
        System.out.println("lastFactories = " + Arrays.toString(lastFactories));
    }
}
