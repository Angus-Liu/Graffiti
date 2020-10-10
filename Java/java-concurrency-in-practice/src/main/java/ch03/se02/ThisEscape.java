package ch03.se02;

import java.util.HashSet;
import java.util.Set;

/**
 * 在构造函数中使 this 引用逸出
 */
public class ThisEscape {
    public ThisEscape(EventSource source) {
        source.registerListener(e -> {
            // this 引用逃逸，发布了一个尚未构造完成的对象
            doSomeThing(e);
        });
    }

    private void doSomeThing(Event e) {
        // TODO: angus on 2020/10/10
    }
}

class Event {
}

@FunctionalInterface
interface EventListener {
    void onEvent(Event e);
}

class EventSource {
    private final Set<EventListener> listenerSet = new HashSet<>();

    public void registerListener(EventListener listener) {
        listenerSet.add(listener);
    }
}