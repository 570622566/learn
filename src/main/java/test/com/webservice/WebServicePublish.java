package test.com.webservice;

import javax.xml.ws.Endpoint;

public class WebServicePublish {
	
	
	public static void main(String[] args) {
			String address = "http://localhost:9999/WS_Server/Webservice";
		         //使用Endpoint类提供的publish方法发布WebService，发布时要保证使用的端口号没有被其他应用程序占用
		   Endpoint.publish(address , new WebServiceImpl());
		   System.out.println("发布webservice成功!");
	}
	
}
