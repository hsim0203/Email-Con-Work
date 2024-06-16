package kr.ac.kopo.mail.ui;

import kr.ac.kopo.mail.vo.MailVO;

public class AllDelMailUI extends BaseUI {

	@Override
	public void mlexecute() throws Exception {
			int mailNO = scanInt("삭제할 메일 번호를 입력하세요 : ");
			mailService.alldelmail(mailNO,user);


	}

}
