package ch05.se03;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 文件索引
 */
public class FileIndexService {

    private static final Map<File, String> indexMap;

    static {
        indexMap = new ConcurrentHashMap<>();
    }

    public static void addIndex(File f, String i) {
        indexMap.put(f, i);
    }

    public static boolean containsIndex(File f) {
        return indexMap.containsKey(f);
    }

    public static Map<File, String> getAllIndex() {
        return Collections.unmodifiableMap(indexMap);
    }

    public static void startIndexing(List<File> roots, int consumerSize) throws InterruptedException {
        CountDownLatch producerLatch = new CountDownLatch(roots.size());
        CountDownLatch consumerLatch = new CountDownLatch(consumerSize);
        BlockingQueue<File> fileQueue = new LinkedBlockingQueue<>(1000);
        for (File root : roots) {
            new Thread(
                    new FileCrawler(
                            fileQueue,
                            pathname -> pathname.isDirectory() || pathname.getName().endsWith(".jpg"),
                            root,
                            () -> {
                                producerLatch.countDown();
                                System.out.println("[" + Thread.currentThread().getName() + "]" + " stop...");
                            })
            ).start();
        }
        for (int i = 0; i < consumerSize; i++) {
            new Thread(
                    new Indexer(
                            fileQueue,
                            () -> {
                               if (producerLatch.getCount() == 0 && fileQueue.isEmpty())
                                   consumerLatch.await();
                                   Thread.currentThread().interrupt();
                            })
            ).start();
        }
        producerLatch.await();
        consumerLatch.await();
    }

    public static void main(String[] args) throws InterruptedException {
        List<File> roots = new ArrayList<>();
        roots.add(new File("/Users/angus/Documents/Notes"));
        roots.add(new File("/Users/angus/Documents/Notes"));

        System.out.println("start index...");
        startIndexing(roots, 2);

        getAllIndex().forEach((f, s) ->
                System.out.println(f.getName() + " : " + s)
        );

        System.out.println("end...");
    }
}
