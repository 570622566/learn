package test.com.mina2;

import java.io.IOException;
import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoConnector;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class MinaShortClient {
	private static final int PORT = 3001;
	static IoSession session = null;

	public static void main(String[] args) throws IOException, InterruptedException {
		IoConnector connector = new NioSocketConnector();
		connector.getSessionConfig().setReadBufferSize(2048);

		connector.getFilterChain().addLast("logger", new LoggingFilter());
		// connector.getFilterChain().addLast("codec", new
		// ProtocolCodecFilter(new
		// TextLineCodecFactory(Charset.forName("GBK"))));
		connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new MsgCodecFactory()));

		connector.setHandler(new MinaShortClientHandler());

		ConnectFuture future = connector.connect(new InetSocketAddress("127.0.0.1", PORT));
		future.awaitUninterruptibly();
		session = future.getSession();
		session.write(
				"020310&777D2E360CCF6222848BE2909E6DC8C5&CN=信息通信处测试 370202020202020202, OU=00, OU=00, O=10, L=00, L=02, S=37, C=CN&10.49.138.1750302");

		run();
		for (int i = 1; i <= 1; i++) {
			Thread.sleep(500);
			run();
			session.write(
					"020310&777D2E360CCF6222848BE2909E6DC8C5&CN=信息通信处测试 370202020202020202, OU=00, OU=00, O=10, L=00, L=02, S=37, C=CN&10.49.138.1750302");
			session.getCloseFuture().awaitUninterruptibly();
			System.out.println(session.read());
			System.out.println("result =" + session.getAttribute("result"));
			Thread.sleep(500);
		}

	}

	public static void run() {
		while (true) {
			try {

				session.write(
						"020310&777D2E360CCF6222848BE2909E6DC8C5&CN=信息通信处测试 370202020202020202, OU=00, OU=00, O=10, L=00, L=02, S=37, C=CN&10.49.138.1750302");
				// session.write("020301&test&123456&MAC&IP0302");
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
