package com.mvc.service;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.mvc.dao.MyMVCDao;
import com.mvc.dao.MyMVCDaoImpl;
import com.mvc.dto.MyMVCDto;

public class MyMVCBizImpl implements MyMVCBiz{
	private MyMVCDao dao = new MyMVCDaoImpl();

	@Override
	public List<MyMVCDto> selectAll() {
		Connection con = getConnection();
		
		List<MyMVCDto> res = dao.selectAll(con);
		
		close(con);
		
		return res;
	}

	@Override
	public MyMVCDto selectOne(int seq) {
		Connection con = getConnection();
		
		MyMVCDto res = dao.selectOne(con, seq);
		
		close(con);
		
		return res;
	}

	@Override
	public boolean insert(MyMVCDto dto) {
		Connection con = getConnection();
		boolean res = dao.insert(con, dto);
		close(con);
		return res;
	}

	@Override
	public boolean update(MyMVCDto dto) {
		Connection con = getConnection();
		boolean res = dao.update(con, dto);
		close(con);
		return res;
	}

	@Override
	public boolean delete(int seq) {
		Connection con = getConnection();
		boolean res = dao.delete(con, seq);
		close(con);
		return res;
	}

}
