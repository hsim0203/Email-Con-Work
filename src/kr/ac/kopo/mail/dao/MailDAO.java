package kr.ac.kopo.mail.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.mail.vo.MailVO;
import kr.ac.kopo.util.ConnectionFactory;

public class MailDAO {
	//전체메일
	public List<MailVO> selectAll(String user) throws Exception {
		
		List<MailVO> list = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ROWNUM AS rw, WRITER, TITLE, REGDATE, RECEIVER, EMAIL_BODY");
		sql.append("  FROM (");
		sql.append("		SELECT WRITER, TITLE ,to_char(REG_DATE,'YYYY-MM-DD') AS REGDATE, RECEIVER, EMAIL_BODY");
		sql.append("		  FROM TBL_EMAIL");
		sql.append("         WHERE WRITER = ? OR RECEIVER = ? ");
		sql.append("         ORDER BY REG_DATE DESC");
		sql.append("       )");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			pstmt.setString(1, user);
			pstmt.setString(2, user);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) { //rs.next()의 값이 없어질때까지 출력
				String writer  = rs.getString("WRITER");
				String receiver = rs.getString("RECEIVER");
				String title   = rs.getString("TITLE");
				String emailBody = rs.getString("EMAIL_BODY");
				String regDate = rs.getString("REGDATE");
				int    no 	   = rs.getInt("rw");
				MailVO mail    = new MailVO(writer,receiver,title,emailBody,regDate,no);
				list.add(mail);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	//수신메일
	public List<MailVO> selectrecive(String user) throws Exception {
		List<MailVO> list = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ROWNUM AS rw, WRITER, TITLE,REGDATE, RECEIVER, EMAIL_BODY");
		sql.append("  FROM (");
		sql.append("		SELECT WRITER, TITLE ,to_char(REG_DATE,'YYYY-MM-DD') AS REGDATE, RECEIVER, EMAIL_BODY");
		sql.append("		  FROM TBL_EMAIL");
		sql.append("         WHERE RECEIVER = ? ");
		sql.append("         ORDER BY REG_DATE DESC");
		sql.append("       )");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, user);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) { //rs.next()의 값이 없어질때까지 출력
					String writer  = rs.getString("writer");
					String receiver = rs.getString("RECEIVER");
					String title   = rs.getString("title");
					String emailBody = rs.getString("email_body");
					String regDate = rs.getString("regdate");
					int    no 	   = rs.getInt("rw");
					
					MailVO mail    = new MailVO(writer,receiver,title,emailBody,regDate,no);
					list.add(mail);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return list;
	}
	
	
	//송신메일
	public List<MailVO> selectsend(String user) throws Exception {
		List<MailVO> list = new ArrayList<>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ROWNUM AS rw, WRITER, TITLE,REGDATE, RECEIVER, EMAIL_BODY");
		sql.append("  FROM (");
		sql.append("		SELECT WRITER, TITLE ,to_char(REG_DATE,'YYYY-MM-DD') AS REGDATE, RECEIVER, EMAIL_BODY");
		sql.append("		  FROM TBL_EMAIL");
		sql.append("         WHERE WRITER = ? ");
		sql.append("         ORDER BY REG_DATE DESC");
		sql.append("       )");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, user);
				ResultSet rs = pstmt.executeQuery();
				while(rs.next()) { //rs.next()의 값이 없어질때까지 출력
					int    no 	   = rs.getInt("rw");
					String writer  = rs.getString("writer");
					String receiver = rs.getString("RECEIVER");
					String title   = rs.getString("title");
					String emailBody = rs.getString("email_body");
					String regDate = rs.getString("regdate");
					MailVO mail    = new MailVO(writer,receiver,title,emailBody,regDate,no);
					list.add(mail);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		return list;
	}
	
	//메일쓰기
	public void mailWrite(MailVO mail) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT  INTO TBL_EMAIL(NO, WRITER ,RECEIVER, TITLE, EMAIL_BODY, ID)");
		sql.append(" values(seq_TBL_EMAIL_no.nextval,?, ?, ?, ?, ?) ");
		
		try(
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		) {
			
			pstmt.setString(1, mail.getWriter());
			pstmt.setString(2, mail.getReceiver());
			pstmt.setString(3, mail.getTitle());
			pstmt.setString(4, mail.getEmailBody());
			pstmt.setString(5, mail.getWriter());	
			pstmt.executeUpdate();
			System.out.println("성공적으로 발신되었습니다.");
		}catch (java.sql.SQLIntegrityConstraintViolationException e) {
			System.out.println("수신 아이디가 존재하지 않습니다. ");
		}
		catch (Exception e2) {
			e2.printStackTrace();
		}
	}
	
	//전체 메일에서 읽기
	public MailVO allRead(int mailNO,String user) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT WRITER, RECEIVER, TITLE, reg_date, EMAIL_BODY");
		sql.append("  FROM (");
		sql.append("	SELECT ROWNUM AS rw, WRITER, RECEIVER, TITLE, reg_date, EMAIL_BODY");
		sql.append("	  FROM (");
		sql.append("		SELECT WRITER, RECEIVER, TITLE , reg_date, EMAIL_BODY");
		sql.append("		  FROM TBL_EMAIL");
		sql.append("		 WHERE WRITER = ? OR RECEIVER = ? ");
		sql.append("		 ORDER BY REG_DATE DESC");
		sql.append("		)");
		sql.append("	)");
		sql.append("WHERE rw = ? ");
		try(			
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			pstmt.setString(1, user);
			pstmt.setString(2, user);
			pstmt.setInt(3, mailNO);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) { 
				String writer  = rs.getString("writer");
				String receiver = rs.getString("RECEIVER");
				String title   = rs.getString("title");
				String emailBody = rs.getString("email_body");
				String regDate = rs.getString("reg_date");
				MailVO mail    = new MailVO(writer,receiver,title,emailBody,regDate);
				return mail;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	//수신 메일 읽기
	public MailVO recRead(int mailNO,String user) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT WRITER, RECEIVER, TITLE, reg_date, EMAIL_BODY");
		sql.append("  FROM (");
		sql.append("	SELECT ROWNUM AS rw, WRITER, RECEIVER, TITLE, reg_date, EMAIL_BODY");
		sql.append("	  FROM (");
		sql.append("		SELECT WRITER, RECEIVER, TITLE , reg_date, EMAIL_BODY");
		sql.append("		  FROM TBL_EMAIL");
		sql.append("		 WHERE RECEIVER = ? ");
		sql.append("		 ORDER BY REG_DATE DESC");
		sql.append("		)");
		sql.append("	)");
		sql.append("WHERE rw = ? ");
		try(			
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			pstmt.setString(1, user);
			pstmt.setInt(2, mailNO);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) { 
				String writer  = rs.getString("writer");
				String receiver = rs.getString("RECEIVER");
				String title   = rs.getString("title");
				String emailBody = rs.getString("email_body");
				String regDate = rs.getString("reg_date");
				MailVO mail    = new MailVO(writer,receiver,title,emailBody,regDate);
				return mail;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	//송신 메일 읽기
	public MailVO senRead(int mailNO,String user) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT WRITER, RECEIVER, TITLE, reg_date, EMAIL_BODY");
		sql.append("  FROM (");
		sql.append("	SELECT ROWNUM AS rw, WRITER, RECEIVER, TITLE, reg_date, EMAIL_BODY");
		sql.append("	  FROM (");
		sql.append("		SELECT WRITER, RECEIVER, TITLE , reg_date, EMAIL_BODY");
		sql.append("		  FROM TBL_EMAIL");
		sql.append("		 WHERE WRITER = ? ");
		sql.append("		 ORDER BY REG_DATE DESC");
		sql.append("		)");
		sql.append("	)");
		sql.append("WHERE rw = ? ");
		try(			
			Connection conn = new ConnectionFactory().getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
			pstmt.setString(1, user);
			pstmt.setInt(2, mailNO);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) { 
				String writer  = rs.getString("writer");
				String receiver = rs.getString("RECEIVER");
				String title   = rs.getString("title");
				String emailBody = rs.getString("email_body");
				String regDate = rs.getString("reg_date");
				MailVO mail    = new MailVO(writer,receiver,title,emailBody,regDate);
				return mail;
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	//전체메일에서 삭제
	public void aldelmail(int mailNo, String user) throws Exception{
		//데이터 휴지통으로 복제
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO TBL_TRASH SELECT *  FROM TBL_EMAIL WHERE NO = ( ");
		sql.append("            SELECT NO ");
		sql.append("               FROM ( ");
		sql.append("                     SELECT ROWNUM AS rw, m1.* ");
		sql.append("                       FROM ( ");
		sql.append("                            SELECT * ");
		sql.append("                              FROM TBL_EMAIL ");
		sql.append("                             WHERE WRITER = ? OR RECEIVER = ? ");
		sql.append("                             ORDER BY REG_DATE DESC ");
		sql.append("                             ) m1 ");
		sql.append("                    ) ");
		sql.append("              WHERE rw = ? ");
		sql.append(" )");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, user);
			pstmt.setString(2, user);
			pstmt.setInt(3, mailNo);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		//원본삭제
		sql = new StringBuilder();
		sql.append("DELETE FROM TBL_EMAIL");
		sql.append("  WHERE NO IN (");
		sql.append("               SELECT NO FROM (");
		sql.append("                       SELECT ROWNUM AS RW, NO");
		sql.append("                         FROM (");
		sql.append("                               SELECT no FROM TBL_EMAIL");
		sql.append("                                WHERE RECEIVER = ? OR WRITER = ?");
		sql.append("                                ORDER BY REG_DATE DESC");
		sql.append("                              )");
		sql.append("                      )");
		sql.append("                WHERE RW = ?");
		sql.append("              )");
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			){
			pstmt.setString(1, user);
			pstmt.setString(2, user);
			pstmt.setInt(3, mailNo);
			pstmt.executeUpdate();
			System.out.println("삭제가 완료되었습니다. ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
} 
