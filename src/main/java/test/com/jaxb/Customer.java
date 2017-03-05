package test.com.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="customer_s") 
public class Customer {
	
	private String name;
	private int id;
	private int age;
	public String getName() {
		return name;
	}
	@XmlElement 
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	
    @XmlAttribute(name="s_id",required=true)  
	public void setId(int id) {
		this.id = id;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", id=" + id + ", age=" + age + "]";
	}
	
}
