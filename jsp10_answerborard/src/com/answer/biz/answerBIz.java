package com.answer.biz;

import java.util.List;

import com.answer.dao.answerDao;
import com.answer.dto.answerDto;

public class answerBIz {
	
	private answerDao dao = new answerDao();
	
	public List<answerDto> SelectList(){
		
		return dao.SelectList();
	}
	
	public answerDto SelectOne(int boardno) {
		
		return dao.SelectOne(boardno);
	}
	
	public int insert(answerDto dto) {
		
		return dao.insert(dto);		
	}
	
	public int insertRe(answerDto dto) {
		
		return dao.insertRe(dto);
	}
	
	public int update(answerDto dto) {
		
		return dao.update(dto);
	}
	
	public int delete(int boardno) {
		
		return dao.delete(boardno);
	}

}
