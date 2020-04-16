package com.login.biz;

import java.util.List;

import com.login.dao.MyMemberDao;
import com.login.dao.MyMemberDaoImpl;
import com.login.dto.MyMemberDto;

public class MyMemberBizImpl implements MyMemberBiz {
	
	private MyMemberDao dao = new MyMemberDaoImpl();

	@Override
	public List<MyMemberDto> selectList() {
		// TODO Auto-generated method stub
		return dao.selectList();
	}

	@Override
	public List<MyMemberDto> selectEnabled() {
		// TODO Auto-generated method stub
		return dao.selectEnabled();
	}

	@Override
	public int updateRole(int myno, String role) {
		// TODO Auto-generated method stub
		return dao.updateRole(myno, role);
	}

	@Override
	public MyMemberDto login(String myid, String mypw) {
		
		return dao.login(myid, mypw);
	}

	@Override
	public MyMemberDto idChk(String myid) {
		
		return dao.idChk(myid);
	}

	@Override
	public int insertUser(MyMemberDto dto) {
		
		return dao.insertUser(dto);
	}

	@Override
	public MyMemberDto selectUser(int myno) {
		
		return dao.selectUser(myno);
	}

	@Override
	public int updateUser(MyMemberDto dto) {
		
		return dao.updateUser(dto);
	}

	@Override
	public int deleteUser(int myno) {
		// TODO Auto-generated method stub
		return 0;
	}

}
