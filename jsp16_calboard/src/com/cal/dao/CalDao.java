package com.cal.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cal.dto.CalDto;

import static com.cal.db.JDBCTemplate.*;

public class CalDao {
	
	public int insertCalBoard(CalDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " INSERT INTO CALBOARD VALUES(CALBOARDSEQ.NEXTVAL, ?, ?, ?, ?, SYSDATE) ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getId());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			pstm.setString(4, dto.getMdate());
			
			res = pstm.executeUpdate();
			
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
	
	public List<CalDto> selectCalList(String id, String yyyymmdd){
		
		Connection con = getConnection();
		ResultSet rs = null;
		PreparedStatement pstm = null;
		List<CalDto> list = new ArrayList<CalDto>();
		
		
		String sql = " SELECT * FROM CALBOARD WHERE ID = ? AND SUBSTR(MDATE,1,8)=? ";
		
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyymmdd);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				CalDto dto = new CalDto(rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4),
						rs.getString(5),
						rs.getDate(6)
						);
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public List<CalDto> getCalViewList(String id, String yyyyMM){
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<CalDto> list = new ArrayList<CalDto>();
		
		
		String sql = " SELECT * FROM ( " + 
				" SELECT (ROW_NUMBER() OVER(PARTITION BY SUBSTR(MDATE,1,8) ORDER BY MDATE)) RN, " + 
				" SEQ, ID, TITLE, CONTENT, MDATE, REGDATE " + 
				" FROM CALBOARD " + 
				" WHERE ID=? AND SUBSTR(MDATE,1,6)=? " + 
				" ) " + 
				" WHERE RN BETWEEN 1 AND 3 ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMM);
			
			rs= pstm.executeQuery();
			
			while(rs.next()) {
				CalDto dto = new CalDto(rs.getInt(2),
										rs.getString(3),
										rs.getString(4),
										rs.getString(5),
										rs.getString(6),
										rs.getDate(7)
										);
			list.add(dto);			
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm, rs, con);
		}
		
		return list;
	}
	
	public int getCalCount(String id, String yyyyMMdd) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		int res = 0;
		
		String sql = " SELECT COUNT(*) FROM CALBOARD WHERE ID=? AND SUBSTR(MDATE, 1, 8) = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, yyyyMMdd);
			System.out.println("query준비");
			
			rs = pstm.executeQuery();
			System.out.println("실행 및 리턴");
			
			while(rs.next()) {
				res= rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			System.out.println("[error] : 3, 4");
			e.printStackTrace();
		}finally {
			close(pstm, rs, con);
		}
		
		
		return res;
	}
	
	
}
