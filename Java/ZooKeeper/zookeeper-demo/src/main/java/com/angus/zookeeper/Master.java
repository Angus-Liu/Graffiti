package com.angus.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * @author angus
 * @date 2019/10/26
 */
public class Master implements Watcher {

    private ZooKeeper zooKeeper;
    private String hostPort;

    public Master(String hostPort) {
        this.hostPort = hostPort;
    }

    public void startZK() throws Exception {
        zooKeeper = new ZooKeeper(hostPort, 15000, this);
        List<String> children = zooKeeper.getChildren("/", true);
        System.out.println(children);
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
       watchedEvent.getType();
    }

    public static void main(String[] args) throws Exception {
        Master m = new Master("localhost:2181");
        m.startZK();
        for(;;){}
    }
}
