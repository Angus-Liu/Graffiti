package ch08.se05;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 串行谜题解答器
 */
public class SequentialPuzzleSolver {
    private final Puzzle<P, M> puzzle;
    private final Set<P> seen = new HashSet<>();

    public SequentialPuzzleSolver(Puzzle<P, M> puzzle) {
        this.puzzle = puzzle;
    }

    public List<M> solve() {
        P pos = puzzle.initialPosition();
        return search(new Node<>(pos, null, null));
    }

    /**
     * 在谜题空间中执行一个深度优先搜索，当找到解决方案（不一定是最短的解决方案）后结束搜索
     */
    private List<M> search(Node<P, M> node) {
        if (!seen.contains(node.pos)) {
            seen.add(node.pos);
            if (puzzle.isGoal(node.pos))
                return node.asMoveList();
            for (M move : puzzle.legalMoves(node.pos)) {
                P pos = puzzle.move(node.pos, move);
                Node<P, M> child = new Node<>(pos, move, node);
                List<M> result = search(child);
                if (result != null)
                    return result;
            }
        }
        return null;
    }
}
