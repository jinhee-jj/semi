package com.kh.cool.board.freeboard.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.cool.board.freeboard.model.dao.FreeBoardDao;
import com.kh.cool.board.freeboard.model.vo.FreeBoard;
import com.kh.cool.board.freeboard.model.vo.PageInfo;

import static com.kh.cool.common.JDBCTemplate.*;

public class FreeBoardService {

	public ArrayList<FreeBoard> selectList(int community, PageInfo pi) {
		
		Connection con = getConnection();
		
		ArrayList<FreeBoard> list = new FreeBoardDao().selectList(con, community, pi);
		
		close(con);
		
		return list;
	}

	public int insertFreeBoard(FreeBoard newFreeBoard) {
		
		Connection con = getConnection();
		
		int result = new FreeBoardDao().insertFreeBoard(con, newFreeBoard);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public FreeBoard selectListOne(int num) {

		Connection con = getConnection();
		
		FreeBoardDao bd = new FreeBoardDao();
		int count = bd.updateCount(con, num);
		
		FreeBoard result = null;
		if(count > 0) {
			result = bd.selectListOne(con,num);
			
			if(result != null) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public ArrayList<FreeBoard> FreeBoardSearch(String radio, String select, String search) {
		Connection con = getConnection();
		
		ArrayList<FreeBoard> list = new FreeBoardDao().FreeBoardSearch(con, radio, select, search);
		
		close(con);
		
		return list;
	}

	public int boardDelete(int num) {
		Connection con = getConnection();
		
		int result = new FreeBoardDao().boardDelete(con,num);
		
		if(result > 0) {
			commit(con);
		} else {
			rollback(con);
		}
		
		close(con);
		
		return result;
	}

	public FreeBoard updateBoard(int num, FreeBoard updateBoard) {
		Connection con = getConnection();
		
		FreeBoard changeFreeBoard = null;
		FreeBoardDao freeBoardDao = new FreeBoardDao();
		
		int result = freeBoardDao.updateBoard(con, num, updateBoard);
		
		if(result > 0) {
			changeFreeBoard = freeBoardDao.changeBoard(con, num, updateBoard);
			
			if(changeFreeBoard != null) {
				commit(con);
			} else {
				rollback(con);
			}
			
		} else {
			rollback(con);
		}
		
		close(con);
		
		return changeFreeBoard;
	}

	public FreeBoard updateOneBoard(int num) {
		Connection con = getConnection();
		
		FreeBoard result = new FreeBoardDao().updateOneBoard(con, num);
		
		close(con);
		
		return result;
	}

	public int getListCount() {
		Connection con = getConnection();
		
		int listCount = new FreeBoardDao().getListCount(con);
		
		close(con);
		
		return listCount;
	}


}
