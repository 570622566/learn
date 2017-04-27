package test.com.handle;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Created by Administrator on 2017/4/27.
 */
public class ZookeeperCRUD {

    public static void main(String[] args) {
        String hostUrl = "localhost:2181";
        int sessionTimeout = 3000;//ms单位

        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(hostUrl, sessionTimeout, new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    String path = watchedEvent.getPath();
                    System.out.println("watch:"+watchedEvent.getType()+",path:"+path);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }


        String data = "hello world!";

        System.out.println("create data:"+data);
        String path = "/test/app1";
        try {
            String result = zk.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println(result);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }

        String result = null;
        try {
            byte[] bytes = zk.getData(path, null, null);
            result = new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("get data:"+result);


        String data2 = "hello ricky!";

        System.out.println("set data:"+data2);
        try {
            zk.setData(path, data2.getBytes(), -1);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }


        try {
            zk.delete(path, -1);
        } catch (InterruptedException | KeeperException e) {
            e.printStackTrace();
        }

        try {
            zk.close();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
