package ch08.se05;

import java.util.Set;

/**
 * 表示"搬箱子"之类谜题的抽象类
 */
public interface Puzzle<P, M> {
    P initialPosition();

    boolean isGoal(P position);

    Set<M> legalMoves(P positions);

    P move(P position, M move);
}
