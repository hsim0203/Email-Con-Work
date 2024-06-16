package kr.ac.kopo.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import kr.ac.kopo.member.vo.MemberVO;
import kr.ac.kopo.util.ConnectionFactory;

public class MemberDAO {

	public void insert(MemberVO member) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TBL_MEMBER(ID,PASSWORD,NAME,BIRTHDAY,PHONE_NUMBER)");
		sql.append(" VALUES(?, ?, ?,?,?)");
	try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	) {
		pstmt.setString(1, member.getId());
		pstmt.setString(2, member.getPassword());
		pstmt.setString(3, member.getName());
		pstmt.setString(4, member.getBirthday());
		pstmt.setString(5, member.getPhoneNumber());
		pstmt.executeUpdate();
		System.out.println("회원 가입을 완료하였습니다.");
	} catch (java.sql.SQLIntegrityConstraintViolationException e) {
		System.out.println("중복된 아이디입니다. 다시 시도해 주세요");
	} catch (Exception e) {
		e.printStackTrace();
	}	
}
	
	
	//로그인시
	public MemberVO login(MemberVO member) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select id, password, name ");
		sql.append("  from tbl_member ");
		sql.append(" where id = ? and password = ? ");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String id = rs.getString("id");
				String password = rs.getString("password");
				String name = rs.getString("name");
				
				MemberVO loginVO = new MemberVO();
				loginVO.setId(id);	
				loginVO.setPassword(password);
				loginVO.setName(name);
				
				return loginVO;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}

}
