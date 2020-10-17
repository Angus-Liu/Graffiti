package ch05.se05;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier 应用
 */
public class CellularAutomata {
    private final Board mainBoard;
    private final CyclicBarrier barrier;
    private final Worker[] workers;

    public CellularAutomata(Board mainBoard) {
        this.mainBoard = mainBoard;
        int count = Runtime.getRuntime().availableProcessors();
        this.barrier = new CyclicBarrier(count, mainBoard::commitNewValues);
        this.workers = new Worker[count];
        for (int i = 0; i < count; i++) {
            workers[i] = new Worker(mainBoard.getSubBoard(count, i));
        }
    }

    class Worker implements Runnable {
        private final Board board;

        public Worker(Board board) {
            this.board = board;
        }


        private int computeValue(int x, int y) {
            return 0;
        }

        @Override
        public void run() {
            while (!board.hasConverged()) {
                for (int x = 0; x < board.getMaxX(); x++)
                    for (int y = 0; y < board.getMaxY(); y++)
                        board.setNewValue(x, y, computeValue(x, y));
                try {
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    return;
                }
            }
        }

        public void start() {
            for (Worker worker : workers) {
                new Thread(worker).start();
                mainBoard.waitForConvergence();
            }
        }
    }

}

class Board {
    public void commitNewValues() {
    }

    public boolean hasConverged() {
        return true;
    }

    public int getMaxX() {
        return 0;
    }

    public int getMaxY() {
        return 0;
    }

    public void setNewValue(int x, int y, int value) {

    }

    public void waitForConvergence() {
    }

    public Board getSubBoard(int count, int i) {
       return new Board();
    }
}

