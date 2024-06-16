package kr.ac.kopo.mail.ui;

import kr.ac.kopo.mail.vo.MailVO;

public class WriteMailUI extends BaseUI {

	@Override
	public void mlexecute() throws Exception {
		System.out.println("========== 새 글 등 록 ==========");
		
		String receiver = scanStr("이메일을 받을사람의 아이디를 입력하시오. : ");
		String title = scanStr("제목을 입력하시오 : ");
		String emailBody = scanStr("메일 내용을 입력하시오 : ");
		
		MailVO mail = new MailVO();
		mail.setReceiver(receiver);
		mail.setTitle(title);
		mail.setEmailBody(emailBody);
		mail.setWriter(user);
		
		mailService.writeMail(mail);	

	}

}
