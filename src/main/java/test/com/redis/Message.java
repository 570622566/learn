package test.com.redis;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1023575404124814837L;
	
	private int id;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Message(int id, String content) {
		this.id = id;
		this.content = content;
	}
	public Message() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Message [id=" + id + ", content=" + content + "]";
	}
	
	
	
}
