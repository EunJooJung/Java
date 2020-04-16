package com.mvc.dao;

import static com.db.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mvc.dto.MVCDto;

public class MVCDaoImpl implements MVCDao {

	@Override
	public List<MVCDto> selectList() {
		
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		List<MVCDto> list = new ArrayList<MVCDto>();
		
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(SELECT_LIST_SQL);
			System.out.println("3. query 준비");
			
			
			while(rs.next()) {
				
				/*
				 * MVCDto dto = new MVCDto(rs.getInt(1),
				 * 						   rs.getSteing(2),
				 * 						   rs.getString(3)
				 * 							.
				 * 							.
				 * */
				
				
				MVCDto dto = new MVCDto();
				dto.setSeq(rs.getInt(1));
				dto.setwriter(rs.getString(2));
				dto.settitle(rs.getString(3));
				dto.setcontent(rs.getString(4));
				dto.setregdate(rs.getDate(5));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
			close(con);
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
				dto.setwriter(rs.getString(2));
				dto.settitle(rs.getString(3));
				dto.setcontent(rs.getString(4));
				dto.setregdate(rs.getDate(5));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
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
			pstm.setString(1, dto.getwriter());
			pstm.setString(2, dto.gettitle());
			pstm.setString(3, dto.getcontetn());
			System.out.println("3. query 준비  : "+ INSERT_SQL);
			
			res=pstm.executeUpdate();
			
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
		
		try {
			pstm=con.prepareStatement(UPDATE_SQL);
			pstm.setString(1, dto.gettitle());
			pstm.setString(2, dto.getcontetn());
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
			pstm=con.prepareStatement(DELETE_SQL);
			pstm.setInt(1, seq);
			
			res=pstm.executeUpdate();
			
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
	public boolean multiDelete(String[] seqs) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		int[] cnt = null;
		
		try {
			pstm = con.prepareStatement(DELETE_SQL);
			for(int i=0; i<seqs.length; i++) {
			pstm.setString(1, seqs[i]);
			pstm.addBatch(); //데이터베이스에 직접 전달하기 전에 메모리에 저장 함 ->executeBatch(); 수행 될 때 메모리에 있던 명령 전달
			System.out.println("삭제할 번호 : "+DELETE_SQL);
			}
			
			cnt=pstm.executeBatch();
			System.out.println("4. 실행 및 리턴");
			
			for(int i=0; i<cnt.length; i++) {
				if(cnt[i]==-2) {
					//성공하면 -2 실패하면 -3
					res++;
				}
				
			}
			
			if(res==seqs.length) {
				commit(con);
			}else {
				rollback(con);
				res=0;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		
		
		return (res==seqs.length)?true:false;
	
	}

}
