package kr.ac.kopo.mail.ui;

import kr.ac.kopo.mail.vo.MailVO;

public class ReadAllMailUI extends BaseUI {

	@Override
	public void mlexecute() throws Exception {
		
		boolean ra = true;
		while(ra) {
			System.out.println("0을 입력할시 뒤로 갑니다. -1을 입력시 삭제매뉴로 갑니다. ");
			int mailNO = scanInt("조회할 메일 번호를 입력하세요 : ");
			if(mailNO == 0) {
				ra = false;
				break;
			}else if(mailNO == -1) {
				IMailUI adui = null;
				adui = new AllDelMailUI();
				if(adui != null) {
					adui.mlexecute();
				}
			}
			else {
				MailVO mail = mailService.readallmail(mailNO,user);
				
				System.out.println("--------------------------------------------------------");
				if(mail == null) {
					System.out.println("입력하신 [" + mailNO + "]번 메일은 존재하지 않습니다");
				} else {
					System.out.println("제     목 : " + mail.getTitle());
					System.out.println("송  신  자 : " + mail.getWriter());
					System.out.println("수  신  자 : " + mail.getReceiver());
					System.out.println("송 신 시 간 : " + mail.getRegDate());
					System.out.println("내     용 : " + mail.getEmailBody());
				}
				System.out.println("--------------------------------------------------------");
			}
		}

	}

}
