package test.com.handle;

import java.io.Serializable;

public class DownloadSoftware implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7256238420163821786L;

	private Integer id;

    private String softName;

    private String desc;

    private String url;

    private String processName;

    private String regName;

    private String forceNext;

    private String fileName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSoftName() {
        return softName;
    }

    public void setSoftName(String softName) {
        this.softName = softName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getForceNext() {
        return forceNext;
    }

    public void setForceNext(String forceNext) {
        this.forceNext = forceNext;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}