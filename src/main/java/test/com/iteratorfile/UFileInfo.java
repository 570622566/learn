package test.com.iteratorfile;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class UFileInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8319594415148460240L;
	
	/**
	 * 文件名称
	 */
	@JSONField(serialize=false)  
	private String fileName;
	/**
	 * 文件说明
	 */
	private String fileDis;
	/**
	 * 版本号
	 */
	private String version;
	/**
	 * 最后修改时间
	 */
	@JSONField(serialize=false)  
	private String lastModify;
	/**
	 * md5
	 */
	private String md5;
	
	
	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	public String getFileDis() {
		return fileDis;
	}


	public void setFileDis(String fileDis) {
		this.fileDis = fileDis;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getLastModify() {
		return lastModify;
	}


	public void setLastModify(String lastModify) {
		this.lastModify = lastModify;
	}


	public String getMd5() {
		return md5;
	}


	public void setMd5(String md5) {
		this.md5 = md5;
	}


	public  String getPath(String directory){
		return directory +"\\" + fileName;
	}
	
	
}
