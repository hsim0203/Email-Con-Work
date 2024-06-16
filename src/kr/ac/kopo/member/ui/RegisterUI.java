package kr.ac.kopo.member.ui;

import kr.ac.kopo.member.vo.MemberVO;

public class RegisterUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		System.out.println("<<<<<<<<<< 회 원 가 입 >>>>>>>>>>");
		
		String oid = scanStr("아이디를 입력하여 주세요 : ");
		String id = oid.trim();
		String password = "";
		String password2 = "";
		int cnt = 0;
		boolean a = true;
		boolean b = true;
		boolean c = true;
		while(a) {
			while(b) {
				password = scanStr("비밀번호를 입력하여 주세요(6자리 이상) : ");
				if(password.length() >= 6) {
					b = false;
				}else {
					System.out.println("비밀번호가 6자리 미만입니다. 다시 입력해주세요");
				}
			}
			while(c) {
				password2 = scanStr("비밀번호를 한번 더 입력하여 주세요(6자리 이상) : ");
				if(password2.length() >= 6) {
					c = false;
				}else {
					System.out.println("비밀번호가 6자리 미만입니다. 다시 입력해주세요");
				}			
			}
			for(int i = 0 ; i < password.length() ; i++) {
				if(password.charAt(i) == password2.charAt(i)) {
					cnt++;
				}
			}
			if(cnt == password.length()) {
				a = false;
			}else {
				System.out.println("비밀번호가 틀립니다. 다시 시도해주세요. ");
				cnt = 0;
				b = true;
				c = true;
			}	
		}

		String name = scanStr("이름을 입력하여 주세요 : ");
		boolean d = true;
		String birthday = "";
		while(d) {
			birthday = scanStr("생년월일 6자리를 입력하여 주세요 : ");
			if(birthday.length() == 6) {
				d = false;
			}else {
				System.out.println("생년월일이 6자리 미만입니다. 다시 입력하여 주세요");
			}
		}
		String phoneNumber = scanStr("전화번호를 입력하여 주세요 (-를 포함해서 입력해 주세요.) : ");

	
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setBirthday(birthday);
		member.setPhoneNumber(phoneNumber);
	
		memberService.register(member);


	}

}
