package test.com.designpattern.behavior.adapter.example;

public class Test {
	public static void main(String[] args) {

	/*	// 初始化一个德国插座对象， 用一个德标接口引用它
		DBSocketInterface dbSoket = new DBSocket();

		// 创建一个旅馆对象
		Hotel hotel = new Hotel(dbSoket);

		// 在旅馆中给手机充电
		hotel.charge();*/
		
		/**
		 * 
		 * 现在我去德国旅游，带去的三项扁头的手机充电器。如果没有带电源适配器，我是不能充电的，
		 * 因为不可能为了我一个旅客而为我更改墙上的插座，更不可能为我专门盖一座使用中国国标插座的宾馆。
		 * 因为人家德国人一直这么使用，并且用的挺好，俗话说入乡随俗，我就要自己想办法来解决问题。对应到我们的代码中，也就是说，
		 * 上面的Hotel类，DBSocket类，DBSocketInterface接口都是不可变的（由德国的客户提供），如果我想使用这一套API，
		 * 那么只能自己写代码解决。
		 * 
		 */
		
		/**
		 * 1    适配器对象实现原有接口
		   2    适配器对象组合一个实现新接口的对象（这个对象也可以不实现一个接口，只是一个单纯的对象）
 		   3    对适配器原有接口方法的调用被委托给新接口的实例的特定方法
		 */
		
		
		GBSocketInterface gbSocket = new GBSocket();  
		Hotel hotel1 = new Hotel();  
		// 由于没法充电，我拿出随身带去的适配器，并且将我带来的充电器插在适配器的上端插孔中。这个上端插孔是符合国标的，我的充电器完全可以插进去。
		SocketAdapter socketAdapter = new SocketAdapter(gbSocket);  
		hotel1.setSocket(socketAdapter);  
		hotel1.charge();  
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
