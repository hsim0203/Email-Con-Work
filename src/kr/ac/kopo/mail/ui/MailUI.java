package kr.ac.kopo.mail.ui;

import kr.ac.kopo.trash.ui.ITrashUI;
import kr.ac.kopo.trash.ui.TrashUI;

public class MailUI extends BaseUI {
	private int mailmenu() {
		System.out.println("==================================================================================");
		System.out.print("1. 전체 메일함\t");
		System.out.println("2. 수신 메일함");
		System.out.print("3. 송신 메일함\t");
		System.out.println("4. 메 일 쓰 기");
		System.out.print("5. 휴  지  통\t");
		System.out.println("9. 돌 아 가 기");
		System.out.println("0.  종   료  ");
		System.out.println("==================================================================================");
		
		int type = scanInt("원하시는 기능을 선택하세요 : ");
		return type;		
	}
	
	boolean ty = true;
	@Override
	public void mlexecute() throws Exception {
		while(ty) {
			try {
			IMailUI mui = null;
			ITrashUI trui = null;
			int type = mailmenu();
			switch(type) {
			case 1:
				mui = new AllMailUI();
				break;
			case 2:
				mui = new ReceiveMailUI();
				break;
			case 3:
				mui = new SentMailUI();
				break;
			case 4:
				mui = new WriteMailUI();
				break;
			case 5:
				trui = new TrashUI();
				break;
			case 9:
				ty = false;
				break;
			case 0:
				mui = new ExitUI();
				break;
			default:
				System.out.println("다시 시도해주세요 ");
				break;
			}
			if(mui != null) {
				mui.mlexecute();
			}else if(trui != null) {
				trui.texecute();
			}
			}catch (NumberFormatException e) {
				System.out.println("잘못된 입력입니다. ");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
