package kr.ac.kopo.member.ui;

import kr.ac.kopo.member.vo.MemberVO;

public class LoginUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		System.out.println("<<<<<<<<<< 로그인 서비스 >>>>>>>>>>");
		String id = scanStr("아이디를 입력하세요 : ");
		String password = scanStr("패스워드를 입력하세요 : ");	
		
		MemberVO member = new MemberVO();
		member.setId(id);
		member.setPassword(password);
		
		MemberVO loginVO = memberService.login(member);
		if(loginVO == null) {
			System.out.println("아이디 또는 패스워드를 잘못입력하셨습니다");
		} else {
			MemberVO.loginVO = loginVO;
			System.out.println("환영합니다. [" + loginVO.getName() + "님]");
		}

	}

}
