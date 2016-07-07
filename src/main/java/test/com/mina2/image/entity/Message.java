package test.com.mina2.image.entity;

import java.io.Serializable;

public class Message implements Serializable   {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7045457935826130149L;
	private int alonght;
	private int imagenamelongth;
	private long imagelongth;
	private String imagename;
	private byte[] image;
	
	public int getAlonght() {
		return alonght;
	}
	public void setAlonght(int alonght) {
		this.alonght = alonght;
	}
	public int getImagenamelongth() {
		return imagenamelongth;
	}
	public void setImagenamelongth(int imagenamelongth) {
		this.imagenamelongth = imagenamelongth;
	}
	public long getImagelongth() {
		return imagelongth;
	}
	public void setImagelongth(long imagelongth) {
		this.imagelongth = imagelongth;
	}
	public String getImagename() {
		return imagename;
	}
	public void setImagename(String imagename) {
		this.imagename = imagename;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}

}
