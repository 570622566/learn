package test.com.handle;

import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Test12 {

	public static void main(String[] args) {
		
		String s = "{\"NextButton\":\"我确认完成该软件的安装\",\"EndButton\":\"我确认已完成所有软件安装\",\"NextText\":\"您尚未完成本应用的安装\",\"ReadMe\":\"您好，按公安网内终端使用规定，要求必须安装规定的软件，请配合安装向导完成安装。\",\"Notice\":\"您已完成终端规定软件的安装，请重启计算机完成系统配置，谢谢。\",\"Soft\":\"[{\\\"Desc\\\":\\\"腾讯QQ（简称“QQ”）是腾讯公司开发的一款基于Internet的即时通信（IM）软件。腾讯QQ支持在线聊天、视频通话、点对点断点续传文件、共享文件、网络硬盘、自定义面板、QQ邮箱等多种功能，并可与多种通讯终端相连。2015年，QQ继续为用户创造良好的通讯体验！其标志是一只戴着红色围巾的小企鹅\\\",\\\"fileName\\\":\\\"[C:/rogram Files (x86)/Tencent/QQ/Bin/QQ.exe,C:/Program Files (x86)/Tencent/QQ/QQUninst.exe]\\\",\\\"forceNext\\\":\\\"false\\\",\\\"Process\\\":\\\"QQ\\\",\\\"regName\\\":\\\"腾讯QQ\\\",\\\"Name\\\":\\\"腾讯QQ\\\",\\\"url\\\":\\\"http://im.qq.com/\\\"}]\"}";

		JSONObject jsonObject = JSON.parseObject(s);
		System.out.println(jsonObject.get("NextButton"));
        List<DownloadSoftware> list = JSON.parseArray(jsonObject.getString("Soft"), DownloadSoftware.class);  
        for (DownloadSoftware downloadSoftware : list) {
			System.out.println(downloadSoftware.getFileName());
			System.out.println(downloadSoftware.getDesc());
		}
        
		
		
	}
}
