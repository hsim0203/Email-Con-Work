package kr.ac.kopo.trash.ui;

public class TDelAllUI extends BaseUI {

	@Override
	public void texecute() throws Exception {
		trashService.tDAll(user);
		System.out.println("삭제가 완료되었습니다. ");
	}

}
