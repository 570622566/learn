package test.com.handle;

import java.util.concurrent.atomic.AtomicBoolean;

public class Test8 {


	
	public static void main(String[] args) {
		AtomicBoolean wakeupCalled = new AtomicBoolean(true);
		
		if(wakeupCalled.getAndSet(false)){
		
			System.out.println("aa");
		}
		if(wakeupCalled.getAndSet(false)){
			
			System.out.println("bb");
		}
		
		
		String s = "020327&CpuInfo=Intel(R) Core(TM) i7-3630QM CPU @ 2.40GHz&&GraphicsCardInfo=NVIDIA GeForce GTX 660M&&AudioDeviceInfo=Realtek High Definition Audio&&PhysicalMemory=8477241344&&CpuID=BFEBFBFF000306A9&&HDiskID=S19BNEAD300226W&&HDiskInfo=ST1000LM024 HN-M101MBB&&MacAddress=2C:D0:5A:55:9D:87&&IpAddress=192.168.3.58&&OS=Microsoft Windows 10 专业版&&SystemType=x64-based PC&&SystemInstallDate=2016-06-28 13:23:43&&LoginUserName=Y\\Administrator&&ComputerName=Y&&SysUserNames=Administrator,DefaultAccount,Guest&0302";
		s = s.substring(s.indexOf("&")+1, s.lastIndexOf("&"));
		String[] strings = s.split("&&");
		for (String str : strings) {
			System.out.println(str.substring(str.indexOf("=")+1));
		}
		
		String s1 = "27&CpuInfo=Intel(R) Core(TM) i7-3630QM CPU @ 2.40GHz&&GraphicsCardInfo=NVIDIA GeForce GTX 660M&&AudioDeviceInfo=Realtek High Definition Audio&&PhysicalMemory=8477241344&&CpuID=BFEBFBFF000306A9&&HDiskID=S19BNEAD300226W&&HDiskInfo=ST1000LM024 HN-M101MBB&&MacAddress=2C:D0:5A:55:9D:87&&IpAddress=192.168.3.58&&OS=Microsoft Windows 10 专业版&&SystemType=x64-based PC&&SystemInstallDate=2016-06-28 13:23:43&&LoginUserName=Y\\Administrator&&ComputerName=Y&&SysUserNames=Administrator,DefaultAccount,Guest&";
		System.out.println(s1.substring(0, 2));
		System.out.println(s1.indexOf("&"));

	}
}
