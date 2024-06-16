package kr.ac.kopo.trash.ui;

import java.util.List;

import kr.ac.kopo.trash.vo.TrashVO;

public class TrashUI extends BaseUI {
	private int trashmenu() {
		System.out.println("==================================================================================");
		System.out.println("1. 메일 내용 보기");
		System.out.print("2. 전체 삭제\t");
		System.out.println("3. 선택 삭제");
		System.out.print("9. 돌 아 가 기\t");
		System.out.println("0.  종   료  ");
		System.out.println("==================================================================================");
		int type = scanInt("원하시는 기능을 선택하세요 : ");
		return type;		
	}
	
	boolean fa = true;
	@Override
	public void texecute() throws Exception {
		while(fa) {
			List<TrashVO> list = trashService.allTrash(user);
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("   		****** 전 체 메 일 함 *****   ");
			System.out.println("----------------------------------------------------------------------------------");
			System.out.println("번호\t송신자\t\t수신자\t\t제목\t\t\t시간");
			System.out.println("----------------------------------------------------------------------------------");
			if(list == null || list.size() == 0) {
				System.out.println("게시글이 존재하지 않습니다");
			} else {
				for(TrashVO trash : list) {
					/*System.out.println(trash.getNo() + "\t" + trash.getWriter() + "\t"+ trash.getTitle() + "\t\t"
							  + trash.getRegDate());*/
					System.out.print(trash.getNo() + "\t");
					if(trash.getWriter().length() >= 8) 
						System.out.print(trash.getWriter() + "\t");
					else
						System.out.print(trash.getWriter() + "\t\t");
					
					if(trash.getReceiver().length() >= 8) 
						System.out.print(trash.getReceiver() + "\t");
					else
						System.out.print(trash.getReceiver() + "\t\t");
					
					if(trash.getTitle().length() >= 16) 
						System.out.print(trash.getTitle() + "\t\t");
					else if(trash.getTitle().length() > 8 && trash.getTitle().length() < 16) 
						System.out.print(trash.getTitle() + "\t\t\t");
					else
						System.out.print(trash.getTitle() + "\t\t\t");
					
					if(trash.getRegDate().length() >= 8) 
						System.out.println(trash.getRegDate() + "\t");
					else
						System.out.println(trash.getRegDate() + "\t\t");
				}
			}
			ITrashUI tui = null;
			int type = trashmenu();
			switch(type) {
			case 1:
				tui = new ReadTrashUI();
				break;
			case 2:
				tui = new TDelAllUI();
				break;
			case 3:
				tui = new TDelOneUI();
				break;
			case 9:
				fa = false;
				break;
			case 0:
				tui = new ExitUI();
				break;
			default:
				System.out.println("다시 시도해주세요 : ");
				break;
			}
			if(tui != null) {
				tui.texecute();
			}
		
		}
	}
	
	
}
