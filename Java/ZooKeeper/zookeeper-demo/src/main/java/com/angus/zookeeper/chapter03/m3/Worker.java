package com.angus.zookeeper.chapter03.m3;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Random;

/**
 * 从节点 - 用以主节点发号施令
 *
 * @author angus
 * @date 2019/10/31
 */
public class Worker implements Watcher {

    private ZooKeeper zooKeeper;
    private String hostPort;
    private String serverId = Integer.toHexString(new Random().nextInt());
    private String status = "Idle";
    private String workerPath = "/workers/worker-" + serverId;

    public Worker(String hostPort) {
        this.hostPort = hostPort;
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event.toString() + ", " + hostPort);
    }

    private void startZK() throws IOException {
        zooKeeper = new ZooKeeper(hostPort, 15000, this);
    }

    private AsyncCallback.StringCallback createWorkerCallback = (rc, path, ctx, name) -> {
        switch (KeeperException.Code.get(rc)) {
            case OK:
                System.out.println("Registered successfully: " + serverId);
                break;
            case CONNECTIONLOSS:
                register();
                break;
            case NODEEXISTS:
                System.out.println("Already registered: " + serverId);
                break;
            default:
                System.out.println("Error: " + KeeperException.create(KeeperException.Code.get(rc), path));
        }
    };

    private void register() {
        zooKeeper.create(workerPath,
                status.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                createWorkerCallback,
                null);
    }

    private AsyncCallback.StatCallback statCallback = (rc, path, ctx, stat) -> {
        switch (KeeperException.Code.get(rc)) {
            case CONNECTIONLOSS:
                updateStatus((String) ctx);
                break;
            default:
        }
    };

    synchronized private void updateStatus(String status) {
        // 查看待更新 status 是不是当前最新的 status，因重试时可能导致 status 不是最新的
        if (status.equals(this.status)) {
            zooKeeper.setData(workerPath, status.getBytes(), -1, statCallback, status);
        }
    }

    private void setStatus(String status) {
        this.status = status;
        updateStatus(status);
    }

    public static void main(String[] args) throws Exception {
        Worker w = new Worker("127.0.0.1:2181");
        w.startZK();
        w.register();
        w.setStatus("test");
        while (true) {
        }
    }
}
