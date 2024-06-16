package kr.ac.kopo.member.ui;

import java.util.Scanner;

import kr.ac.kopo.member.service.MemberService;

public abstract class BaseUI implements IMemberUI{

	private Scanner sc;
	protected MemberService memberService;
	
	public BaseUI() {
		sc = new Scanner(System.in);
		memberService = new MemberService();
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
