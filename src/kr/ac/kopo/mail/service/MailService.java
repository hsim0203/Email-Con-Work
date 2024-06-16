package kr.ac.kopo.mail.service;

import java.util.List;

import kr.ac.kopo.mail.dao.MailDAO;
import kr.ac.kopo.mail.vo.MailVO;

public class MailService {
	
	private MailDAO mailDao;
	
	public MailService() {
		mailDao = new MailDAO();
	}
	
	
	// 전체메일
	public List<MailVO> allMail(String user) throws Exception {		
		return mailDao.selectAll(user);	//메일 DAO의 셀렉트all을 반환
	}
	//수신메일
	public List<MailVO> receiveMail(String user) throws Exception {		
		return mailDao.selectrecive(user);	
	}
	//송신메일
	public List<MailVO> sendMail(String user) throws Exception {		
		return mailDao.selectsend(user);	
	}
	//메일 쓰기
	public void writeMail(MailVO mail) throws Exception {
		mailDao.mailWrite(mail);
	}

	//전체메일에서 읽기
	public MailVO readallmail(int mailNO,String user) throws Exception{
		return mailDao.allRead(mailNO, user);
	}
	//수신메일 읽기
	public MailVO readrecmail(int mailNO,String user) throws Exception{
		return mailDao.recRead(mailNO, user);
	}
	//송신메일 읽기
	public MailVO readsenmail(int mailNO,String user) throws Exception{
		return mailDao.senRead(mailNO, user);
	}
	
	//전체메일에서 삭제
	public void  alldelmail(int mailNo, String user) throws Exception{
		mailDao.aldelmail(mailNo, user);
	}
}
