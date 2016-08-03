package test.com.enums;

public class Enum3 {
  Enum1 degree;
  
  public Enum3(Enum1 degree) {
		this.degree = degree;
	}
  
  public void describe() {
		System.out.println("this Enum1 is");
		switch (degree) {
		case NOT:
			System.out.println("not spicy at all");
			break;
		case MILD:
		case MEDIUM:
			System.out.println("a little hot");
			break;
		case FLAMING:
			System.out.println("maybe too hot");
			break;
		default:
			break;
		}
	}
  
  public static void main(String[] args) {
		Enum3 plain = new Enum3(Enum1.NOT);
		Enum3 greenChile = new Enum3(Enum1.MEDIUM);
		Enum3 jalapeno = new Enum3(Enum1.HOT);

		plain.describe();
		greenChile.describe();
		jalapeno.describe();
	}
  
}
