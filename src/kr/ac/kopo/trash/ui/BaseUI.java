package kr.ac.kopo.trash.ui;

import java.util.Scanner;

import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.trash.service.TrashService;

public abstract class BaseUI implements ITrashUI{

	private Scanner sc;
	protected TrashService trashService;
	protected String user =  MemberVO.loginVO.getId();
	
	public BaseUI() {
		sc = new Scanner(System.in);
		trashService = new TrashService();
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
