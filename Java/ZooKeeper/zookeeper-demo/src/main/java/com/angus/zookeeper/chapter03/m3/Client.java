package com.angus.zookeeper.chapter03.m3;

import org.apache.zookeeper.*;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 * Client 应用程序队列，用于从节点执行其中的任务
 *
 * @author angus
 * @date 2019/10/31
 */
public class Client implements Watcher {

    private ZooKeeper zooKeeper;
    private String hostPort;

    public Client(String hostPort) {
        this.hostPort = hostPort;
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
    }

    private void startZK() throws Exception {
        zooKeeper = new ZooKeeper(hostPort, 15000, this);
    }

    private String queueCommand(String command) throws Exception {
        while (true) {
            String name = "";
            try {
                Thread.sleep(1000);
                name = zooKeeper.create("/tasks/task-",
                        command.getBytes(),
                        OPEN_ACL_UNSAFE,
                        CreateMode.PERSISTENT_SEQUENTIAL);
            } catch (KeeperException.NodeExistsException e) {
                throw new Exception(name + " already appears to be running");
            } catch (KeeperException.ConnectionLossException e) {
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Client client = new Client("127.0.0.1:2181");
        client.startZK();
        String name = client.queueCommand("get a service");
        System.out.println("Created " + name);
    }
}
