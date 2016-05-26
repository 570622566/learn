package test.com.jackson;

public class AccountBean {
	
	
	private int id;
	private String name ;
	private String address;
	private Birthday birthday;
    private String email;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Birthday getBirthday() {
		return birthday;
	}
	public void setBirthday(Birthday birthday) {
		this.birthday = birthday;
	}
	@Override
	public String toString() {
		return "AccountBean [id=" + id + ", name=" + name + ", address=" + address + ", birthday=" + birthday
				+ ", email=" + email + "]";
	}
	
	
	
}
