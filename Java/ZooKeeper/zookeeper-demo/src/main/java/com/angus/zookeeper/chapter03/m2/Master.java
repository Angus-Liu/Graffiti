package com.angus.zookeeper.chapter03.m2;

import org.apache.zookeeper.*;

import java.util.List;
import java.util.Random;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 * 异步方式实现
 *
 * @author angus
 * @date 2019/10/26
 */
public class Master implements Watcher {

    private static ZooKeeper zooKeeper;
    private static String hostPort;

    /**
     * Master Znode
     */
    private static String masterZnode = "/master";

    /**
     * 用作该服务器的唯一 ID
     */
    private static String serverId = Integer.toHexString(new Random().nextInt());

    /**
     * 是否为 Leader
     */
    private boolean isLeader = false;

    private AsyncCallback.StringCallback masterCreateCallback = (rc, path, ctx, name) -> {
        // 从 rc 中获取 create 请求的结果
        switch (KeeperException.Code.get(rc)) {
            case OK:
                this.isLeader = true;
                break;
            case CONNECTIONLOSS:
                // 连接丢失导致 create 请求失败，需检查系统当前的状态，并进行恢复
                checkMaster();
                break;
            case NONODE:
                runForMaster();
                break;
            default:
                this.isLeader = false;
        }
        if (this.isLeader) {
            System.out.println("成为 Master 节点");
        } else {
            System.out.println("存在 Master 节点");
        }
    };

    private AsyncCallback.DataCallback masterCheckCallback = (rc, path, ctx, data, stat) -> {
        switch (KeeperException.Code.get(rc)) {
            case CONNECTIONLOSS:
                checkMaster();
                break;
            case NONODE:
                runForMaster();
                break;
        }
    };

    private Master(String hostPort) {
        Master.hostPort = hostPort;
    }

    private void runForMaster() {
        zooKeeper.create(masterZnode, serverId.getBytes(), OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL, masterCreateCallback, null);
    }

    private void checkMaster() {
        zooKeeper.getData(masterZnode, false, masterCheckCallback, null);
    }

    private void startZK() throws Exception {
        zooKeeper = new ZooKeeper(hostPort, 15000, this);
        List<String> children = zooKeeper.getChildren("/", true);
        System.out.println(children);
    }

    private void stopZK() throws InterruptedException {
        zooKeeper.close();
    }

    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent);
        watchedEvent.getType();
    }

    public static void main(String[] args) throws Exception {
        Master m = new Master("127.0.0.1:2181");
        m.startZK();
        m.runForMaster();
        Thread.sleep(6000);
        m.stopZK();
    }
}
