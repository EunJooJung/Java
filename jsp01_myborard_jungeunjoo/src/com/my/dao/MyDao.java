package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.MyDto;

public class MyDao extends JDBCTemplate {
	
	
	//전체출력 -게시판 한줄한줄을 dto에 담을거고 여러줄이 나오니까 list에 담는다
	public List<MyDto> selectList(){
		
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet res = null;
		
		String sql = " SELECT * FROM MYBOARD ";
		
		List<MyDto> list = new ArrayList<MyDto>();	//return받을 애 
		
		try {
			stmt=con.createStatement();
			
			res=stmt.executeQuery(sql);
			
			while(res.next()) {
				MyDto dto = new MyDto();
				dto.setMyno(res.getInt(1));
				dto.setMyname(res.getString(2));
				dto.setMytitle(res.getString(3));
				dto.setMycontent(res.getString(4));
				dto.setMydate(res.getDate(5));
				
				list.add(dto);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[EORROR] : 4");
		}finally {
			close(res);
			close(stmt);
			close(con);
			System.out.println("5.db종료");
		}
		return list;	
	}
	
	//글 하나 출력	primary key 라서 no를 가져온다.
	public MyDto selectOne(int myno) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MyDto dto = new MyDto();
		
		String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE FROM MYBOARD WHERE MYNO = ? ";
		
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("3. query 준비 : "+sql);
			
			rs=pstm.executeQuery();
			System.out.println("4.query 실행 및 리턴");
			while(rs.next()) {
				dto = new MyDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString(2));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
			}
		} catch (SQLException e) {
			System.out.println("[EORROR] : 3,4");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return dto;
	}
	
	//글추가 dto를 가지고 ? 파라미터를 왜MyDto로 설정하는지? 모르게쏘ㅓ요
	public int insert(MyDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = " INSERT INTO MYBOARD VALUES(MYSEQ.NEXTVAL, ?,?,?, SYSDATE) ";
		
		try {
			pstm=con.prepareStatement(sql);
			
			pstm.setString(1, dto.getMyname());
			pstm.setString(2, dto.getMytitle());
			pstm.setString(3, dto.getMycontent());
			System.out.println("3. query, sql 준비");
			
			res= pstm.executeUpdate();
			System.out.println("4.실행 및 리턴");
			
			if(res>0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("[error] : 3, 4");
		}finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
			
		}
		
		return res;
	}
	
	
	
	//글수정 
	public int update(MyDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MYBOARD SET MYTITLE=?, MYCONTENT=? WHERE MYNO = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getMytitle());
			pstm.setString(2, dto.getMycontent());
			pstm.setInt(3, dto.getMyno());
			
			if(res>0) {
				con.commit();
			}else {
				con.rollback();
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
				
		return res;
	}
	
	
	
	//글삭제
	public int delete(int myno) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql =" DELETE FROM MYBOARD WHERE MYNO = ? ";
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("3.query 준비 "+sql);
			
			res = pstm.executeUpdate();
			System.out.println("4.실행 및 리턴");
			if(res>0) {
				commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("5.db종료");
		}
		
		
		return res;
	}
	

}


