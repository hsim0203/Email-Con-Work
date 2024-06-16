package kr.ac.kopo.member.ui;

import kr.ac.kopo.member.vo.MemberVO;

public class LogoutUI extends BaseUI {

	@Override
	public void execute() throws Exception {
		MemberVO.loginVO = null;
		System.out.println("로그아웃되었습니다.");

	}

}
