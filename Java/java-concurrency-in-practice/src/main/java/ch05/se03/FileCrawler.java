package ch05.se03;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;

/**
 * 文件搜寻
 */
public class FileCrawler implements Runnable {

    private final BlockingQueue<File> fileQueue;
    private final FileFilter fileFilter;
    private final File root;
    private final Callback success;

    public FileCrawler(BlockingQueue<File> fileQueue, FileFilter fileFilter, File root, Callback success) {
        this.fileQueue = fileQueue;
        this.fileFilter = fileFilter;
        this.root = root;
        this.success = success;
    }

    private boolean alreadyIndexed(File f) {
        return FileIndexService.containsIndex(f);
    }

    private void crawl(File root) throws InterruptedException {
        System.out.println( "[" + Thread.currentThread().getName() + "]" + " crawl " + root.getAbsolutePath() + "...");
        File[] files = root.listFiles(fileFilter);
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory())
                    crawl(f);
                else if (!alreadyIndexed(f))
                    fileQueue.put(f);
            }
        }
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            success.run();
        }
    }
}
