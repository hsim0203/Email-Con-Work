package kr.ac.kopo.mail.ui;

import java.util.Scanner;

import kr.ac.kopo.mail.service.MailService;
import kr.ac.kopo.member.vo.MemberVO;

public abstract class BaseUI implements IMailUI {

	private Scanner sc;
	protected MailService mailService;
	
	protected String user =  MemberVO.loginVO.getId();
	
	public BaseUI() {
		sc = new Scanner(System.in);
		mailService = new MailService();
	}
	
	protected String scanStr(String msg) {
		System.out.print(msg);
		return sc.nextLine();
	}
	
	protected int scanInt(String msg) {
		String num = scanStr(msg);
		return Integer.parseInt(num);
	}
	

}
