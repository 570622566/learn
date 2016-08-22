package test.com.socket;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.snmp4j.CommandResponder;
import org.snmp4j.CommandResponderEvent;
import org.snmp4j.MessageDispatcherImpl;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.mp.MPv1;
import org.snmp4j.mp.MPv2c;
import org.snmp4j.mp.MPv3;
import org.snmp4j.security.SecurityModels;
import org.snmp4j.security.SecurityProtocols;
import org.snmp4j.security.USM;
import org.snmp4j.smi.Address;
import org.snmp4j.smi.GenericAddress;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.TcpAddress;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultTcpTransportMapping;
import org.snmp4j.transport.DefaultUdpTransportMapping;
import org.snmp4j.util.MultiThreadedMessageDispatcher;
import org.snmp4j.util.ThreadPool;


/**
 * 利用SNMP4J实现Snmp Trap完整例子
 * 
 * 下面是两个用于发送和接收Trap报文信息的类:
 * 
 */
public class MultiThreadedTrapReceiver implements CommandResponder {
	
	private static final Logger logger = LoggerFactory.getLogger(MultiThreadedTrapReceiver.class);
	private MultiThreadedMessageDispatcher dispatcher;
	private Snmp snmp = null;
	private Address listenAddress;
	private ThreadPool threadPool;
	
	public void run() {
		try {
			init();
			snmp.addCommandResponder(this);
			logger.info("start listen trap event...");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void init() throws UnknownHostException, IOException {
		threadPool = ThreadPool.create("Trap", 10);
		dispatcher = new MultiThreadedMessageDispatcher(threadPool,new MessageDispatcherImpl());
		String udp = "0.0.0.0/162"; //不认识的地址一律去这里
		listenAddress = GenericAddress.parse(System.getProperty("snmp4j.listenAddress", udp)); // 本地IP与监听端口
		TransportMapping transport;
		// 对TCP与UDP协议进行处理
		if (listenAddress instanceof UdpAddress) {
			transport = new DefaultUdpTransportMapping((UdpAddress) listenAddress);
		} else {
			transport = new DefaultTcpTransportMapping((TcpAddress) listenAddress);
		}
		snmp = new Snmp(dispatcher, transport);
		snmp.getMessageDispatcher().addMessageProcessingModel(new MPv1());
		snmp.getMessageDispatcher().addMessageProcessingModel(new MPv2c());
		snmp.getMessageDispatcher().addMessageProcessingModel(new MPv3());
		USM usm = new USM(SecurityProtocols.getInstance(), new OctetString(MPv3.createLocalEngineID()), 0);
		SecurityModels.getInstance().addSecurityModel(usm);
		snmp.listen();
	}

	/**
	 * 实现CommandResponder的processPdu方法, 用于处理传入的请求、PDU等信息
	 * 当接收到trap时，会自动进入这个方法
	 */
	@SuppressWarnings("unchecked")
	public void processPdu(CommandResponderEvent respEvnt) {
		// 解析Response
        if (respEvnt != null && respEvnt.getPDU() != null) {
        	Vector<VariableBinding> recVBs = (Vector<VariableBinding>) respEvnt.getPDU().getVariableBindings();
           	for (int i = 0; i < recVBs.size(); i++) {
            	VariableBinding recVB = recVBs.elementAt(i);
            	HashMap<String, Object> map = new HashMap<String, Object>();
        		String oid = recVB.getOid().toString();
        		String company = oid.substring(0, oid.indexOf(".",oid.indexOf(".")+11 ));
        		if(company.equals("1.3.6.1.4.1.4881")){
        			String ip = respEvnt.getPeerAddress().toString();
        			map.put("ip", ip.substring(0,ip.indexOf("/")));
        			map.put("oid", recVB.getOid());
        			map.put("oidValue", recVB.getVariable());
        			map.put("company", "锐捷");
        			map.put("date", new Date());
        		String s = 	recVB.getVariable().toString();
        		if(s.substring(0, 2).equals("01")){
        			map.put("mac", s.substring(9, 26));
        			map.put("port", s.substring(30, 32));
        			map.put("status", 0);
        		}if(s.substring(0, 2).equals("02")){
        			map.put("status", 1);
        		}
                	
        		}if(company.equals("1.3.6.1.4.1.9")){
        			map.put("company", "思科");
        		}if(company.equals("1.3.6.1.4.1.2001")){
        			map.put("company", "华为");
        		}if(company.equals("1.3.6.1.4.1.25506")){
        			map.put("company", "华三");
        		}if(company.equals("1.3.6.1.4.1.2")){
        			map.put("company", "IBM");
        		}
      		}
		}
	}
	
	public static void main(String[] args) {
		
	    MultiThreadedTrapReceiver multithreadedtrapreceiver = new MultiThreadedTrapReceiver();  
        multithreadedtrapreceiver.run();  
		
	}
}

