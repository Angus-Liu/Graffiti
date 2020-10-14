package ch05.se03;

import java.io.File;
import java.util.concurrent.BlockingQueue;

/**
 * 索引服务
 */
public class Indexer implements Runnable {

    private final BlockingQueue<File> fileQueue;
    private final Callback check;


    public Indexer(BlockingQueue<File> fileQueue, Callback check) {
        this.fileQueue = fileQueue;
        this.check = check;
    }

    private void indexFile(File f) {
        FileIndexService.addIndex(f, f.getPath());
    }

    @Override
    public void run() {
        try {
            while (true) {
                check.run();
                indexFile(fileQueue.take());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
