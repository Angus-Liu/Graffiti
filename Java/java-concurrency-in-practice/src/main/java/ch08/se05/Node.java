package ch08.se05;

import jdk.nashorn.internal.ir.annotations.Immutable;

import java.util.LinkedList;
import java.util.List;

/**
 * 用于谜题解决框架的链表节点
 */
@Immutable
public class Node<P, M> {
    final P pos;
    final M move;
    final Node<P, M> prev;

    public Node(P pos, M move, Node<P, M> prev) {
        this.pos = pos;
        this.move = move;
        this.prev = prev;
    }

    public List<M> asMoveList() {
        List<M> solution = new LinkedList<>();
        for (Node<P, M> n = this; n.move != null; n = n.prev)
            solution.add(0, n.move);
        return solution;
    }
}
