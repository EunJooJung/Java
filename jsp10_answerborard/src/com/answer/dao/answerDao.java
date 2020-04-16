package com.answer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.answer.dto.answerDto;

import static com.answer.db.JDBCTemplate.*;

public class answerDao {
	
	//select list
	public List<answerDto> SelectList(){
		
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		List<answerDto> list = new ArrayList<answerDto>(); 
		String sql = " SELECT * FROM ANSWERBOARD ORDER BY GROUPNO DESC,GROUPSEQ ";
		
		
		try {
			stmt = con.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				answerDto dto = new answerDto();
				dto.setBoardno(rs.getInt(1));
				dto.setGroupno(rs.getInt(2));
				dto.setGroupseq(rs.getInt(3));
				dto.setTitletab(rs.getInt(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setWriter(rs.getString(7));
				dto.setRegdate(rs.getDate(8));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("[error] : 3, 4 ");
			e.printStackTrace();
		}finally {
			close(rs, stmt, con);
		}
		
		
		return list;
		
	}
	
	//select one
	public answerDto SelectOne(int boardno) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		answerDto dto = null;
		
		String sql = " SELECT * FROM ANSWERBOARD WHERE BOARDNO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, boardno);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				dto = new answerDto();
				dto.setBoardno(rs.getInt(1));
				dto.setGroupno(rs.getInt(2));
				dto.setGroupseq(rs.getInt(3));
				dto.setTitletab(rs.getInt(4));
				dto.setTitle(rs.getString(5));
				dto.setContent(rs.getString(6));
				dto.setWriter(rs.getString(7));
				dto.setRegdate(rs.getDate(8));
			}
			
			
		} catch (SQLException e) {
			System.out.println("[error] : 3, 4 ");
			e.printStackTrace();
		}finally {
			close(rs, pstm, con);
		}
		
		
		return dto;
	}
	
	//insert
	public int insert(answerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " INSERT INTO ANSWERBOARD VALUES(BOARDNOSEQ.NEXTVAL, GROUPNOSEQ.NEXTVAL, 1, 0, ?,?,?,SYSDATE) ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setString(3, dto.getWriter());
			
			res= pstm.executeUpdate();
			
			if(res > 0) {
				commit(con);
			}else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			System.out.println("[error] : 3, 4 ");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			
		}
		
		return res;
		
	}
	
	
	
	//insertRe
	public int insertRe(answerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		int cnt[] = null;
		
		String update = " UPDATE ANSWERBOARD SET GROUPSEQ = GROUPSEQ+1 "
				+ " WHERE GROUPNO = (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO =? "
				+ " AND GROUPSEQ > (SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO =? ";
		
		String insert = " INSERT INTO ANSWERBOARD "
				+ " VALUES( "
				+ " BOARDNOSEQ.NEXTVAL, "
				+ " (SELECT GROUPNO FROM ANSWERBOARD WHERE BOARDNO=?), "
				+ " (SELECT GROUPSEQ FROM ANSWERBOARD WHERE BOARDNO=?)+1, "
				+ " (SELECT TITLETAB FROM ANSWERBOARD WHERE BOARDNO=?)+1, ?, ?, ?, "
				+ " SYSDATE) ";
		
		try {
			pstm = con.prepareStatement(update);
			pstm.setInt(1, dto.getBoardno());
			pstm.setInt(2, dto.getBoardno());
			pstm.addBatch();
			
			pstm = con.prepareStatement(insert);
			pstm.setInt(1, dto.getBoardno());
			pstm.setInt(2, dto.getBoardno());
			pstm.setInt(3, dto.getBoardno());
			pstm.setString(4, dto.getTitle());
			pstm.setString(5, dto.getContent());
			pstm.setString(6, dto.getWriter());
			pstm.addBatch();
			
			cnt = pstm.executeBatch();
			
			for(int i = 0; i<cnt.length; i++) {
				if(cnt[i] == -2) {
					res++;
				}
			}
			if(res>0) {
				commit(con);
				
			}else {
				rollback(con);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}
	
	
	//update
	public int update(answerDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = "UPDATE ANSWERBOARD SET TITLE=?, CONTENT=? WHERE BOARDNO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getBoardno());
			
			res = pstm.executeUpdate();
			
			if(res>0) {
				commit(con);
			}else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			System.out.println("[error] : 3, 4 ");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}
	
	//delete
	
	public int delete(int boardno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM ANSWERBOARD WHERE BOARDNO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, boardno);
			
			res = pstm.executeUpdate();
			
			if(res>0) {
				commit(con);
			}else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("[error] : 3, 4 ");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		
		return res;
	}
	
}
