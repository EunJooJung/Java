package com.md.dao;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.md.dto.MdDto;




public class MdDao extends sqlMapConfig {
	
	private String namespace = "muldel.";
	
	public List<MdDto> selectList(){
		
		List<MdDto> list = null;
		
		SqlSession session = null;
		
		session = getSqlSessionFactory().openSession();
		list = session.selectList(namespace+"selectList");
		
		return list;
	}
	
	public int multiDelete(String[] seq) {
		
		int res = 0;
		
		SqlSession session = null;
		Map<String, String[]> map = new HashMap<String, String[]>();
		map.put("seqs", seq);
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete(namespace+"muldel",map);
			
			if(res==seq.length) {
				session.commit();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return res;
	}

	public int insert(MdDto dto) {
		
		int res = 0;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.insert(namespace+"insert",dto);
			
			if(res>0) {
				session.commit();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
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
		MdDto dto = null;
		SqlSession session = null;
		
			try {
				session = getSqlSessionFactory().openSession();
				dto = session.selectOne(namespace+"selectOne",seq);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				session.close();
			}
		
		
		return dto;
		
	}
	
	
	public int delete(int seq) {
		
		int res = 0;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.delete(namespace+"delete",seq);
			
			if(res>0) {
				session.commit();
				session.close();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return res;
	}
	
	public int update(MdDto dto) {
		
		int res = 0;
		SqlSession session = null;
		
		try {
			session = getSqlSessionFactory().openSession(false);
			res = session.update(namespace+"update",dto);
			
			if(res>0) {
				session.commit();
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		
		return res;
	}
}
