package kr.ac.kopo.main;

import kr.ac.kopo.member.ui.MemberUI;

public class MailMain {
	
	public static void main(String[] args) {
		
		try {
			new MemberUI().execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
