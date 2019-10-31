package com.angus.zookeeper.chapter03.m3;

import lombok.extern.slf4j.Slf4j;
import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Random;

/**
 * 从节点
 *
 * @author angus
 * @date 2019/10/31
 */
@Slf4j
public class Worker implements Watcher {

    private ZooKeeper zooKeeper;
    private String hostPort;
    private String serverId = Integer.toHexString(new Random().nextInt());

    public Worker(String hostPort) {
        this.hostPort = hostPort;
    }

    @Override
    public void process(WatchedEvent event) {
        log.info(event.toString() + ", " + hostPort);
    }

    private void startZK() throws IOException {
        zooKeeper = new ZooKeeper(hostPort, 15000, this);
    }

    private AsyncCallback.StringCallback createWorkerCallback = (rc, path, ctx, name) -> {
        switch (KeeperException.Code.get(rc)) {
            case OK:
                log.info("Registered successfully: " + serverId);
                break;
            case CONNECTIONLOSS:
                register();
                break;
            case NODEEXISTS:
                log.warn("Already registered: " + serverId);
                break;
            default:
                log.error("Error: " + KeeperException.create(KeeperException.Code.get(rc), path));
        }
    };

    private void register() {
        zooKeeper.create("/workers/worker" + serverId,
                "Idle".getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL,
                createWorkerCallback,
                null);
    }

    public static void main(String[] args) throws Exception {
        Worker w = new Worker("127.0.0.1:2181");
        w.startZK();
        w.register();
        Thread.sleep(6000);
    }
}
