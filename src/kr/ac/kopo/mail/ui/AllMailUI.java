package kr.ac.kopo.mail.ui;

import java.util.List;

import kr.ac.kopo.mail.vo.MailVO;



public class AllMailUI extends BaseUI {

	
	@Override
	public void mlexecute() throws Exception {
		
		List<MailVO> list = mailService.allMail(user);
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("   		****** 전 체 메 일 함 *****   ");
		System.out.println("----------------------------------------------------------------------------------");
		System.out.println("번호\t송신자\t\t수신자\t\t제목\t\t\t시간");
		System.out.println("----------------------------------------------------------------------------------");
		
		
		if(list == null || list.size() == 0) {
			System.out.println("게시글이 존재하지 않습니다");
		} else {
			for(MailVO mail : list) {
				System.out.print(mail.getNo() + "\t");
				if(mail.getWriter().length() >= 8) 
					System.out.print(mail.getWriter() + "\t");
				else
					System.out.print(mail.getWriter() + "\t\t");
				
				if(mail.getReceiver().length() >= 8) 
					System.out.print(mail.getReceiver() + "\t");
				else
					System.out.print(mail.getReceiver() + "\t\t");
				
				if(mail.getTitle().length() >= 16) 
					System.out.print(mail.getTitle() + "\t\t");
				else if(mail.getTitle().length() > 8 && mail.getTitle().length() < 16) 
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
		IMailUI arui = null;
		arui = new ReadAllMailUI();
		if(arui != null) {
			arui.mlexecute();
		}
		
	}
}
	
/*	private double madetalemenu() {
		System.out.println("자세히 보고싶은 메일은 번호를 입력하세요.");
		System.out.println("뒤로 돌아가고 싶으면 -1을 입력하세요.");
		double type = scanInt("항목을 선택하세요.");
		return type; 
	}*/
	

