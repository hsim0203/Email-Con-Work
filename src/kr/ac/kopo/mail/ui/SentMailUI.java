package kr.ac.kopo.mail.ui;

import java.util.List;

import kr.ac.kopo.mail.vo.MailVO;

public class SentMailUI extends BaseUI {

	@Override
	public void mlexecute() throws Exception {
		
		List<MailVO> list = mailService.sendMail(user);
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("   		****** 송 신 메 일 함 *****   ");
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("번호\t수신자\t\t제목\t\t\t시간");
		System.out.println("----------------------------------------------------------------------------------");
		
		
		if(list == null || list.size() == 0) {
			System.out.println("게시글이 존재하지 않습니다");
		} else {
			for(MailVO mail : list) {
			/*	System.out.println(mail.getNo() + "\t" + mail.getReceiver() + "\t\t"+ mail.getTitle() + "\t\t"
						  + mail.getRegDate());*/
				System.out.print(mail.getNo() + "\t");
				if(mail.getReceiver().length() >= 8) 
					System.out.print(mail.getReceiver() + "\t");
				else
					System.out.print(mail.getReceiver() + "\t\t");
				
				if(mail.getTitle().length() >= 16) 
					System.out.print(mail.getTitle() + "\t\t");
				else if(mail.getTitle().length() >= 8 && mail.getTitle().length() < 16) 
					System.out.print(mail.getTitle() + "\t\t\t");
				else
					System.out.print(mail.getTitle() + "\t\t\t");
				
				if(mail.getRegDate().length() >= 8) 
					System.out.println(mail.getRegDate() + "\t");
				else
					System.out.println(mail.getRegDate() + "\t\t");
			}
			
		}
		

		System.out.println("----------------------------------------------------------------------------------");
		IMailUI srui = null;
		srui = new ReadSenMailUI();
		if(srui != null) {
			srui.mlexecute();
		}
	}	


}
