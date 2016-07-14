package test.com.netty.demo.aio;

public class TimeServer {
	
	public static void main(String[] args) {
		
		int port = 8080;
		if(args!=null && args.length >0 ){
			port = Integer.valueOf(args[0]);
		}
		
		AsyncTimeSeverHandler timerServer = new AsyncTimeSeverHandler(port);
		
		new Thread(timerServer,"AIO-AsyncTimeSeverHandler-001").start();
	}
}
