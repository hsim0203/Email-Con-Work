package kr.ac.kopo.trash.ui;

public class TDelOneUI extends BaseUI {

	@Override
	public void texecute() throws Exception {
		int mailNO = scanInt("삭제할 메일 번호를 입력하세요 : ");
		trashService.tDelOne(mailNO,user);
	}

}
