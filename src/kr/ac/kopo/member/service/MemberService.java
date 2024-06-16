package kr.ac.kopo.member.service;

import kr.ac.kopo.member.dao.MemberDAO;
import kr.ac.kopo.member.vo.MemberVO;

public class MemberService {
	
	private MemberDAO memberDao;
	
	public MemberService() {
		memberDao = new MemberDAO();
	}

	//회원가입 서비스
	public void register(MemberVO member) {
		memberDao.insert(member);
	}

	
	// 로그인 서비스
	public MemberVO login(MemberVO member) throws Exception {		
		return memberDao.login(member);
	}
}
