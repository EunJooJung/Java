package com.md.dao;

import static com.md.dto.JDBCTemplate.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.md.dto.MdDto;

public class MdDao {
	
	public List<MdDto> selectList(){
		
		Connection con = getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		
		String sql = " SELECT * FROM MDBOARD ORDER BY SEQ ";
		
		List<MdDto> list = new ArrayList<MdDto>();
		
		try {
			stmt = con.createStatement();
			System.out.println("3. query 준비 "+sql);
			
			rs = stmt.executeQuery(sql);
			System.out.println("4. 실행 및 리턴");
			while(rs.next()) {
				
				MdDto dto = new MdDto();
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
			close(rs);
			close(stmt);
			close(con);
		}
		return list;
	}
	
	public int multiDelete(String[] seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res=0;
		String sql  = " DELETE FROM MDBOARD WHERE SEQ = ? ";
		
		int[] cnt = null;
		
		try {
			pstm = con.prepareStatement(sql);
			
			for(int i = 0; i<seq.length; i++) {
				pstm.setString(1, seq[i]);
				pstm.addBatch();
				//메모리에 적재 후, executeBatch()메소드가 호출될 때 한번에 실행
				System.out.println("삭제할 번호: "+seq[i]);
			}
			System.out.println("3.query 준비 : " + sql);
			
			cnt = pstm.executeBatch(); 	//메모리에 있던 query를 한번에 실행, int[]로 리턴
			System.out.println("4. 실행 및 리턴");
			
			
			//[-2,-2,-3,...] 성공 혹은 실패의 값을 배열로 리턴해주면 성공했는지 실패했는지 알 수 있다.
			for(int i = 0; i<cnt.length; i++) {
				//-2 : 성공
				//-3 : 실패
				if(cnt[i]== -2) {
					res++;
				}
			}
				if(res==seq.length) {
					commit(con);
				}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
	}

	public int insert(MdDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql  = " INSERT INTO MDBOARD VALUES(MDBOARDSEQ.NEXTVAL, ? , ? , ?,SYSDATE) ";
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("3. query 준비 : "+sql);
			
			res=pstm.executeUpdate();
			System.out.println("4. 실행 및 리턴");
			
			if(res > 0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("[erorr]:3,4");
		}finally {
			close(pstm);
			close(con);
			System.out.println("5. db종료");
		}
		
		return res;
	}
		
		/*
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res =0;
		
		String sql = " INSERT INTO MDBOARD VALUES(MDBOARDSEQ.NEXTVAL,?,?,?,SYSDATE); ";
		
		try {
			pstm= con.prepareStatement(sql);
			
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			
			System.out.println("3. query, sql 준비");
			
			res = pstm.executeUpdate();
			System.out.println("4.실행 및 리턴");
			
			if(res>0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("[error]:3,4");
		}finally {
			close(pstm);
			close(con);
			System.out.println("db종료");
		}
		
		return res;
		
	}
	*/
	
	
	public MdDto selectOne(int seq) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MdDto dto = null;
		String sql  = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE FROM MDBOARD WHERE SEQ = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("3. query 준비" + sql);
			
			rs=pstm.executeQuery();
			System.out.println("4. 실행 및 리턴");
			while(rs.next()) {
				dto = new MdDto();
				dto.setSeq(rs.getInt(1));
				dto.setWriter(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setRegdate(rs.getDate(5));
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
		}
		
		return dto;
		
	}
	
	
	public int delete(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MDBOARD WHERE SEQ = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("3. query 준비");
			
			res = pstm.executeUpdate();
			System.out.println("4. 실행 및 리턴");
			
			if(res > 0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			System.out.println("[error] : 3,4");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		
		
		return res;
	}
	
	public int update(MdDto dto) {
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " UPDATE MDBOARD SET TITLE=?, CONTENT=? WHERE SEQ = ? ";
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("3. query준비");
			
			res = pstm.executeUpdate();
			System.out.println("4. 실행 및 리턴");
			if(res > 0) {
				commit(con);
			}
			
		} catch (SQLException e) {
			System.out.println("[error] : 3,4");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		
		
		return res;
	}
}
