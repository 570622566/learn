package test.com.enums;

import java.util.HashMap;
import java.util.Map;

public enum ReportStatusEnum {
	
	init(0, "待提交"), submit(1, "待审核"), refuse(-1, "审核不通过"), verified(2, "审核通过");

	private Integer code;
	private String name;
	private static Map<Integer, ReportStatusEnum> holder = new HashMap<>();

	static {
		for (ReportStatusEnum reportStatusEnum : values()) {
			holder.put(reportStatusEnum.getCode(), reportStatusEnum);
		}
	}

	public static ReportStatusEnum getByCode(Integer i) {
		return holder.get(i);
	}

	ReportStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
