package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.MyDto;

public class MyDao {
	
	
	public List<MyDto> selectList(){	//리턴타입이 list<MyDto>인 이유?
		
		
		//	1.driver 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1. driver연결");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 2. 계정 연결
		//date sources > properties > driver properties
		String url="jdbc:oracle:thin:@localhost:1521:xe";
		String user="kh";
		String password="kh";
		
		Connection con = null;
		
		try {
			con=DriverManager.getConnection(url, user, password);
			System.out.println("2.계정 연결");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//3.Query준비
		
		Statement stmt = null;
		ResultSet rs = null; //명령에 반환된 테이블을 담을 객체 생성
		String sql = " SELECT * FORM MYBOARD ORDER BY MYNO DESC ";
		
		List<MyDto> list = new ArrayList<MyDto>();
		
		try {
			stmt=con.createStatement();
			System.out.println("3. query준비 : "+sql);
			
			//4.실행 및 리턴
			rs=stmt.executeQuery(sql);
			while(rs.next()) {
				MyDto dto = new MyDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString("MYNAME"));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
				
				list.add(dto);
			}
			System.out.println("4.실행 및 리턴");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//5. db 종료
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return list;
	}
	
	public MyDto selectOne(int myno) {
		
		return null;
	}
	
	public int insert(MyDto dto) {
		
		return 0;
	}
	
	public int update(MyDto dto) {
		
		return 0;
	}
	
	public int delete(int myno) {
		
		return 0;
	}

}
