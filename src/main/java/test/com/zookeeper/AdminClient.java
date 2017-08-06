package test.com.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.Date;

public class AdminClient implements Watcher {


    ZooKeeper zk;
    String hostPort;

    AdminClient(String hostPort){
        this.hostPort = hostPort;
    }

    void start() throws IOException {
        zk = new ZooKeeper(hostPort,15000,this);
    }

    void listState() throws InterruptedException, KeeperException {
        Stat stat = new Stat();
        byte masterData[] = new byte[0];
        try {
            masterData = zk.getData("/master",false,stat);
            Date startDate = new Date(stat.getCtime());
            System.out.println("Master :"+new String(masterData)+" since "+startDate);
        } catch (KeeperException e) {
            e.printStackTrace();
            System.out.println("No Master....");
        }

        System.out.println("Workers:");
        for (String w:zk.getChildren("/workers",false)){
            byte data[] = zk.getData("/workers"+w,false,null);
            String state = new String(data);
            System.out.println("\t"+w+":"+state);
        }

        System.out.println("Tasks:");
        for (String t:zk.getChildren("/assign",false)){
            System.out.println("\t"+t);
        }


    }

    @Override
    public void process(WatchedEvent event) {

        System.out.println("WatchedWvent:"+event);
    }


    public static void main(String[] args) throws KeeperException, InterruptedException, IOException {
        AdminClient c = new AdminClient("127.0.0.1:2181");
        c.start();
        c.listState();
    }




}
