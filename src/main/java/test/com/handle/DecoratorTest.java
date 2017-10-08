package test.com.handle;

public class DecoratorTest implements Test {

	private Test target;

	public DecoratorTest(Test target) {
		this.target = target;
	}

	@Override
	public int test(int i) {
		// TODO Auto-generated method stub
		return target.test(i);
	}

}
