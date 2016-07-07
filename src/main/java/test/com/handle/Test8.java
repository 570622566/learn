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
		
		
		String s = "020327&CpuInfo=Intel Pentium III Xeon 奔腾&&GraphicsCardInfo=NVIDIA GeForce 310&&AudioDeviceInfo=Realtek High Definition Audio&&PhysicalMemory=3478052864&&CpuID=BFEBFBFF000206A7&&HDiskID=unknown&&HDiskInfo=Kingmax USB2.0 FlashDisk USB Device&&MacAddress=18:03:73:DA:42:D0&&IpAddress=10.49.130.199&&OS=Microsoft Windows XP Professional&&SystemType=X86-based PC&&SystemInstallDate=2012-06-10 20:37:05&&LoginUserName=TJ90JAQ3BN7BMA2\\Administrator&&ComputerName=TJ90JAQ3BN7BMA2&&SysUserNames=Administrator,ASPNET,Guest,HelpAssistant,IUSR_TJ90JAQ3BN7BMA2,SUPPORT_388945a0,TComplexVRV&0302";
		s = s.substring(s.indexOf("&")+1, s.lastIndexOf("&"));
		String[] strings = s.split("&&");
		for (String str : strings) {
			System.out.println("key------"+str.substring(0,str.indexOf("=")));
			System.out.println("name-----"+str.substring(str.indexOf("=")+1));
		}
		
/*		String s1 = "27&CpuInfo=Intel(R) Core(TM) i7-3630QM CPU @ 2.40GHz&&GraphicsCardInfo=NVIDIA GeForce GTX 660M&&AudioDeviceInfo=Realtek High Definition Audio&&PhysicalMemory=8477241344&&CpuID=BFEBFBFF000306A9&&HDiskID=S19BNEAD300226W&&HDiskInfo=ST1000LM024 HN-M101MBB&&MacAddress=2C:D0:5A:55:9D:87&&IpAddress=192.168.3.58&&OS=Microsoft Windows 10 专业版&&SystemType=x64-based PC&&SystemInstallDate=2016-06-28 13:23:43&&LoginUserName=Y\\Administrator&&ComputerName=Y&&SysUserNames=Administrator,DefaultAccount,Guest&";
		System.out.println(s1.substring(0, 2));
		System.out.println(s1.indexOf("&"));*/

	}
}
