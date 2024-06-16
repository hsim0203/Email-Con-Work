package kr.ac.kopo.trash.ui;

import kr.ac.kopo.trash.vo.TrashVO;

public class ReadTrashUI extends BaseUI {

	@Override
	public void texecute() throws Exception {
		
		boolean ra = true;
		while(ra) {
			System.out.println("0을 입력할시 뒤로 갑니다. ");
			int mailNO = scanInt("조회할 메일 번호를 입력하세요 : ");
			if(mailNO == 0) {
				ra = false;
				break;
			}else {
				TrashVO trash = trashService.readTrashMail(mailNO,user);
				
				System.out.println("--------------------------------------------------------");
				if(trash == null) {
					System.out.println("입력하신 [" + mailNO + "]번 메일은 존재하지 않습니다");
				} else {
					System.out.println("제     목 : " + trash.getTitle());
					System.out.println("송  신  자 : " + trash.getWriter());
					System.out.println("수  신  자 : " + trash.getReceiver());
					System.out.println("송 신 시 간 : " + trash.getRegDate());
					System.out.println("내     용 : " + trash.getEmailBody());
				}
				System.out.println("--------------------------------------------------------");
			}
		}


	}

}
