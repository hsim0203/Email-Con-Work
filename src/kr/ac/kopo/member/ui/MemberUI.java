package kr.ac.kopo.member.ui;

import kr.ac.kopo.mail.ui.IMailUI;
import kr.ac.kopo.mail.ui.MailUI;
import kr.ac.kopo.member.vo.MemberVO;

public class MemberUI extends BaseUI {
	
	private int menu() {
		System.out.println("==================================================================================");
		if(MemberVO.loginVO == null) {
			System.out.println("1. 로그인");
			System.out.println("2. 회원가입");
			System.out.println("0. 종료");
		}
		else {
		System.out.println("1. 메일함");
		System.out.println("9. 로그아웃");
		System.out.println("0. 종료");}
		System.out.println("==================================================================================");
		int type = scanInt("항목을 선택하세요 : ");
		return type;
	}
	
	
	@Override
	public void execute() throws Exception {
		while(true) {
			try {
			IMemberUI mui = null;
			IMailUI mlui = null;
			int type = menu();
			if(MemberVO.loginVO == null) {
				switch (type) {			
				case 1:
					mui = new LoginUI();
					break;
				case 2:
					mui = new RegisterUI();
					break;
				case 9:
					mui = new LogoutUI();
					break;
				case 0:
					mui = new ExitUI();
					break;
			}} else {
				switch (type) {	
				case 1:
					mlui = new MailUI();
					break;
				case 9:
					mui = new LogoutUI();
					break;
				case 0:
					mui = new ExitUI();
					break;
				default:
					System.out.println("다시 시도해주세요 ");
					break;
			}}
			if(mui !=null) {
				mui.execute();
			}else if(mlui != null) {
				mlui.mlexecute();
			}
			}catch (NumberFormatException e) {
				System.out.println("잘못된 입력입니다. ");
			}catch (Exception e) {
				e.printStackTrace();
			}
			
		}

	}

}
