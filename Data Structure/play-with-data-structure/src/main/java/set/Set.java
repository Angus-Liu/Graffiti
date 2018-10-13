package set;

/**
 * @author Angus
 * @date 2018/10/10
 */
public interface Set<E> {
    void add(E e);
    void remove(E e);
    boolean contains(E e);
    int getSize();
    boolean isEmpty();
}
