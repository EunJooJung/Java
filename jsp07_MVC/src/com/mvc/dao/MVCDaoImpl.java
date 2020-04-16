package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mvc.dto.MVCDto;
import static com.mvc.dto.JDBCTemplate.*;


public class MVCDaoImpl implements MVCDao {

	@Override
	public List<MVCDto> selectList() {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<MVCDto> list = new ArrayList<MVCDto>();
		
		try {
			pstm = con.prepareStatement(SELECT_LIST_SQL);
			
			rs = pstm.executeQuery();
			System.out.println("4. 실행 및 리턴");
			
			while(rs.next()) {
			MVCDto dto = new MVCDto();
			dto.setSeq(rs.getInt(1));
			dto.setWriter(rs.getString(2));
			dto.setTitle(rs.getString(3));
			dto.setContent(rs.getString(4));
			dto.setRegdate(rs.getDate(5));
			
			list.add(dto);
			}
			
		} catch (SQLException e) {
			System.out.println("[error] : 3, 4");
			e.printStackTrace();
		}finally {
			close(pstm, rs, con);
			System.out.println("5. db종료");
		}
		
		return list;
	}

	@Override
	public MVCDto selectOne(int seq) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MVCDto dto = null;
		
		try {
			pstm = con.prepareStatement(SELECT_ONE_SQL);
			pstm.setInt(1, seq);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				dto = new MVCDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
				
			}
			
			
		} catch (SQLException e) {
			System.out.println("[error] : 3, 4");
			e.printStackTrace();
		}finally {
			close(pstm, rs, con);
			System.out.println("5. db종료");
		}
		
		return dto;
	}

	@Override
	public int insert(MVCDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(INSERT_SQL);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			
			res = pstm.executeUpdate();
			
			if(res>0) {
				commit(con);
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

	@Override
	public int update(MVCDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		//UPDATE MVCBOARD SET TITLE = ?, CONTENT=? WHERE SEQ =?
		try {
			pstm = con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			
			res = pstm.executeUpdate();
			
			if(res>0) {
				commit(con);
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

	@Override
	public int delete(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(DELETE_SQL);
			pstm.setInt(1, seq);
			
			res = pstm.executeUpdate();
			
			if(res >0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return res;
	}

}
