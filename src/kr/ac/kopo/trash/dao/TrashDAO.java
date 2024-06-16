package kr.ac.kopo.trash.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.trash.vo.TrashVO;
import kr.ac.kopo.util.ConnectionFactory;

public class TrashDAO {
	//휴지통 실행시 전채 게시글 가져오기
	public List<TrashVO> trashAll(String user) throws Exception {
		
		List<TrashVO> list = new ArrayList<TrashVO>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ROWNUM AS rw, WRITER, TITLE,REGDATE, RECEIVER, EMAIL_BODY");
		sql.append("  FROM (");
		sql.append("		SELECT WRITER, TITLE ,to_char(REG_DATE,'YYYY-MM-DD') AS REGDATE, RECEIVER, EMAIL_BODY");
		sql.append("		  FROM TBL_TRASH");
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
			while(rs.next()) {
				String writer  = rs.getString("WRITER");
				String receiver = rs.getString("RECEIVER");
				String title   = rs.getString("TITLE");
				String emailBody = rs.getString("EMAIL_BODY");
				String regDate = rs.getString("REGDATE");
				int    no 	   = rs.getInt("rw");
				TrashVO trash = new TrashVO(writer,receiver,title,emailBody,regDate,no);
				list.add(trash);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//메일 내용 읽기
	public TrashVO rTMail(int mailNO, String user) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT WRITER, RECEIVER, TITLE, reg_date, EMAIL_BODY");
		sql.append("  FROM (");
		sql.append("	SELECT ROWNUM AS rw, WRITER, RECEIVER, TITLE, reg_date, EMAIL_BODY");
		sql.append("	  FROM (");
		sql.append("		SELECT WRITER, RECEIVER, TITLE , reg_date, EMAIL_BODY");
		sql.append("		  FROM TBL_TRASH");
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
					TrashVO trash    = new TrashVO(writer,receiver,title,emailBody,regDate);
					return trash;
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		return null;
	}

	// 전체 게시글 삭제

	public void tDeleteAll(String user) throws Exception {
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM TBL_TRASH ");
		sql.append(" WHERE receiver IN (");
		sql.append("		SELECT receiver FROM TBL_TRASH WHERE RECEIVER = ? )");
		sql.append("    OR WRITER IN (");
		sql.append("		SELECT writer FROM TBL_TRASH WHERE WRITER = ? )");
		
		try(
				Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			) {
				pstmt.setString(1, user);
				pstmt.setString(2, user);
				pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	// 선택 메일 삭제
		public void DelOne(int mailNO, String user) throws Exception{
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM TBL_TRASH");
			sql.append("  WHERE NO IN (");
			sql.append("               SELECT NO FROM (");
			sql.append("                       SELECT ROWNUM AS RW, NO");
			sql.append("                         FROM (");
			sql.append("                               SELECT no FROM TBL_TRASH");
			sql.append("                                WHERE RECEIVER = ? OR WRITER = ?");
			sql.append("                                ORDER BY REG_DATE DESC");
			sql.append("                              )");
			sql.append("                      )");
			sql.append("                WHERE RW = ?");
			sql.append("              )");
			try (
					Connection conn = new ConnectionFactory().getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql.toString());
				){
				pstmt.setString(1, user);
				pstmt.setString(2, user);
				pstmt.setInt(3, mailNO);
				pstmt.executeUpdate();
				System.out.println("삭제가 완료되었습니다. ");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
}
