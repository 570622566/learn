package test.com.iteratorfile;

import java.util.List;

public class UDirectoryInfo implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9078158964108618574L;
	
	/**
	 * 文件名称
	 */
	private String name;
	/**
	 * 包含的文件夹列表
	 */
	private List<UDirectoryInfo> directories;
	/**
	 * 包含的文件列表
	 */
	private List<UFileInfo> files;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<UDirectoryInfo> getDirectories() {
		return directories;
	}
	public void setDirectories(List<UDirectoryInfo> directories) {
		this.directories = directories;
	}
	public List<UFileInfo> getFiles() {
		return files;
	}
	public void setFiles(List<UFileInfo> files) {
		this.files = files;
	}
	
	

}
