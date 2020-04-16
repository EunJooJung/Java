package com.login.biz;

import java.util.List;

import com.login.dto.MyMemberDto;

public interface MyMemberBiz {
	
	//관리자(ADMIN) 기능
	//1. 회원 전체 정보 (탈퇴 회원 포함)
	public List<MyMemberDto> selectList();
	//2. 회원 전체 정보 (가입된 회원들만) : myenables = y
	public List<MyMemberDto> selectEnabled();
	//3. 회원 등급 조정
	public int updateRole(int myno, String role);
	
	//사용자(USER)기능
	//1. 로그인
	public MyMemberDto login(String myid, String mypw);
	//2. 회원가입
	//2-1 중복체크 
	public MyMemberDto idChk(String myid);
	//2-2 회원가입
	public int insertUser(MyMemberDto dto);
	//3. 내 정보 조회
	public MyMemberDto selectUser(int myno);
	//4. 내 정보 수정
	public int updateUser(MyMemberDto dto);
	//5. 회원 탈퇴 : update myenabled = 'n'
	public int deleteUser(int myno);
	


}
