package com.angus.zookeeper.chapter03.m3;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.util.Date;

/**
 * 用于展示系统的运行状态
 *
 * @author angus
 * @date 2019/10/31
 */
public class AdminClient implements Watcher {

    private ZooKeeper zooKeeper;
    private String hostPort;

    public AdminClient(String hostPort) {
        this.hostPort = hostPort;
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
    }

    private void start() throws Exception {
        zooKeeper = new ZooKeeper(hostPort, 15000, this);
    }

    private void listStat() throws KeeperException, InterruptedException {
        try {
            Stat stat = new Stat();
            byte[] masterData = zooKeeper.getData("/master", false, stat);
            Date startDate = new Date(stat.getCtime());
            System.out.println("Master: " + new String(masterData) + " since " + startDate);
        } catch (KeeperException.NoNodeException e) {
            System.out.println("No master");
        }
        System.out.println("Workers:");
        for (String worker : zooKeeper.getChildren("/workers", false)) {
            byte[] data = zooKeeper.getData("/workers/" + worker, false, null);
            String state = new String(data);
            System.out.println("\t" + worker + ": " + state);
        }
        System.out.println("Tasks:");
        for (String task : zooKeeper.getChildren("/assign", false)) {
            System.out.println("\t" + task);
        }
    }

    public static void main(String[] args) throws Exception {
        AdminClient adminClient = new AdminClient("127.0.0.1:2181");
        adminClient.start();
        adminClient.listStat();
    }
}
