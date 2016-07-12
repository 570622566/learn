package test.com.netty.demo;

public class TimeServer {
	
	public static void main(String[] args) {
		int port = 8080;
		if(args!=null & args.length>0){
			try{
				port = Integer.valueOf(args[0]);
			}catch(Exception e){
				
			}
		}
		
		MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
		
		new Thread(timeServer,"Nio-MultiplexerTimerServer-001").start();
		
		
		
	}
	
	
}
