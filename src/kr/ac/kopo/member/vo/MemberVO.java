package kr.ac.kopo.member.vo;

public class MemberVO {

	// 로그인 상태정보 기억
	public static MemberVO loginVO;
	
	private String id;
	private String password;
	private String name;
	private String birthday;
	private String phoneNumber;
	private String regDate;
	
	public MemberVO() {
		super();
	}
	
	public MemberVO(String id, String password, String name) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
	}
	
	public MemberVO(String id, String password, String name, String birthday, String phoneNumber, String regDate) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.birthday = birthday;
		this.phoneNumber = phoneNumber;
		this.regDate = regDate;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", password=" + password + ", name=" + name + ", birthday=" + birthday
				+ ", phoneNumber=" + phoneNumber + ", regDate=" + regDate + "]";
	}

	
	
	
	
}
