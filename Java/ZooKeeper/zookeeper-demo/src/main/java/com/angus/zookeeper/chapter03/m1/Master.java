package com.angus.zookeeper.chapter03.m1;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.apache.zookeeper.ZooDefs.Ids.OPEN_ACL_UNSAFE;

/**
 * 同步方式实现
 *
 * @author angus
 * @date 2019/10/26
 */
public class Master implements Watcher {

    private ZooKeeper zooKeeper;
    private String hostPort;

    /**
     * Master Znode
     */
    private String masterZnode = "/master";

    /**
     * 用作该服务器的唯一 ID
     */
    private String serverId = Integer.toHexString(new Random().nextInt());

    /**
     * 是否为 Leader
     */
    private boolean isLeader = false;

    private Master(String hostPort) {
        this.hostPort = hostPort;
    }

    private void runForMaster() throws InterruptedException {
        while (true) {
            try {
                zooKeeper.create(
                        /* 创建一个临时的 master 节点，如果改 znode 节点存在，创建就会失败 */
                        masterZnode,
                        /* 数据字段用于保存该服务器的唯一ID */
                        serverId.getBytes(),
                        /* 使用开放的 ACL 策略*/
                        OPEN_ACL_UNSAFE,
                        /* 临时节点 */
                        CreateMode.EPHEMERAL);
                isLeader = true;
                break;
            } catch (KeeperException.NodeExistsException e) {
                e.printStackTrace();
                isLeader = false;
                break;
            } catch (KeeperException e) {
                e.printStackTrace();
            }
            // 检查活动主节点是否存在，不存在就进行创建
            if (checkMaster()) break;
        }
    }

    private boolean checkMaster() throws InterruptedException {
        while (true) {
            try {
                Stat stat = new Stat();
                // 获取 master 节点数据，检查活动主节点
                byte[] data = zooKeeper.getData(masterZnode, false, stat);
                isLeader = Arrays.toString(data).equals(serverId);
                return true;
            } catch (KeeperException.NoNodeException e) {
                e.printStackTrace();
                return false;
            } catch (KeeperException e) {
                e.printStackTrace();
            }
        }
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
        if (m.isLeader) {
            System.out.println("成为 Master 节点");
            Thread.sleep(6000);
        } else {
            System.out.println("存在 Master 节点");
        }
        m.stopZK();
    }
}
