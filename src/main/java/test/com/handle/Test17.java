package test.com.handle;

public class Test17 {
	
	
	public static void main(String[] args) {
		
		
		getStack();
	}

	private static void getStack() {
		// TODO Auto-generated method stub
		
		StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
		
		for (StackTraceElement stackTraceElement : stackTrace) {
			System.out.println(stackTraceElement.getClassName()+"--"+stackTraceElement.getFileName()+"--"
					+stackTraceElement.getMethodName());
		}
	}
}
