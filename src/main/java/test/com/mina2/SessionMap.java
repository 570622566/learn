package test.com.mina2;

import java.util.HashMap;
import java.util.Map;

import org.apache.mina.core.session.IoSession;

public class SessionMap  {

	private Map<String, IoSession> map = new HashMap<String, IoSession>();
	private static SessionMap sessionMap = null;

	// 构造私有化 单例
	private SessionMap() {
	}

	public static SessionMap newInstance() {
		if (sessionMap == null) {
			sessionMap = new SessionMap();
		}
		return sessionMap;
	}

	public void addSession(String key, IoSession session) {
		this.map.put(key, session);
	}

	public IoSession getSession(String key) {
		return map.get(key);
	}

	public void sendMessage(Object message) {
		Object s[] = map.keySet().toArray();
		for (int i = 0; i < map.size(); i++) {
			if (map.get(s[i]) != null) {
				IoSession session = getSession(String.valueOf(map.get(s[i])
						.getId()));
				System.out.println("反向发送消息到客户端Session---key=" + session.getId()
						+ "----------消息=" + message + " " + i);
				if (session != null) {
					// session.resumeWrite();
					session.write(message);
				}
			}
		}
	}
}
