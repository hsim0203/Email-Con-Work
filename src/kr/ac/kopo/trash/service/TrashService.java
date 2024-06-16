package kr.ac.kopo.trash.service;

import java.util.List;

import kr.ac.kopo.trash.dao.TrashDAO;
import kr.ac.kopo.trash.vo.TrashVO;

public class TrashService {
	
	private TrashDAO trashDao;
	
	public TrashService() {
		trashDao = new TrashDAO();
	}
	
	//휴지통 출력
	public List<TrashVO> allTrash(String user) throws Exception {
		return trashDao.trashAll(user);
	}
	//메일 내용 보기
	public TrashVO readTrashMail(int mailNO, String user) throws Exception{
		return trashDao.rTMail(mailNO, user);
	}
	
	//메일 전체 삭제
	public void tDAll(String user) throws Exception {
		trashDao.tDeleteAll(user);
	}
	
	//메일 선택 삭제
	public void tDelOne(int mailNO, String user) throws Exception{
		trashDao.DelOne(mailNO,user);
	}
}
