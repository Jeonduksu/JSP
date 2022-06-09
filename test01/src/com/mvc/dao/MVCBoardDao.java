package com.mvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import static common.JDBCTemplate.*;
import com.mvc.dto.MVCBoardDto;

public class MVCBoardDao {
	
	public List<MVCBoardDto> selectAll(){
		Connection con = getConnection();
		Statement stmt= null;
		ResultSet rs = null;
		List<MVCBoardDto> res = new ArrayList<MVCBoardDto>();
		
		String sql = "SELECT * FROM MVCBOARD ORDER BY SEQ DESC";
		
		try {
			stmt = con.createStatement();
			System.out.println("03.쿼리 준비"+sql);
			
			rs = stmt.executeQuery(sql);
			System.out.println("04.쿼리 실행 및 리턴");
			
			while(rs.next()) {
				MVCBoardDto tmp = new MVCBoardDto(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getDate(5));
				
				res.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		}finally {
			close(rs);
			close(stmt);
			close(con);
			System.out.println("05. db 종료 \n");
		}
		
		return res;
	}
	
	public MVCBoardDto selectOne(int seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MVCBoardDto res = new MVCBoardDto();
		
		String sql = "SELECT * FROM MVCBOARD WHERE SEQ=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			System.out.println("03.쿼리 준비: " + sql);
			
			rs = pstm.executeQuery();
			System.out.println("04.쿼리 실행 및 리턴");
			while(rs.next()) {
				res.setSeq(rs.getInt(1));
				res.setWriter(rs.getString(2));
				res.setTitle(rs.getString(3));
				res.setContent(rs.getString(4));
				res.setRegdate(rs.getDate(5));
			}
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05. db 종료\n");
		}
		return res;
	}
	
	public int insert(MVCBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm=null;
		int res = 0;
		
		String sql = "INSERT INTO MVCBOARD VALUES(NULL,?,?,?,NOW())";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("03. 쿼리 준비: " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. 쿼리 실행 및 리턴");
			
			if(res>0) {
				commit(con);
			}else {
				rollback(con);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		return res;
	}
	
	public int update(MVCBoardDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = "UPDATE MVCBOARD SET TITLE=?, CONTENT=? WHERE SEQ=?";
		
		try {
			pstm = con.prepareStatement(sql);
			
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			System.out.println("03.쿼리 준비 : " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04. 쿼리 실행 및 리턴");
			
			if(res>0) {
				commit(con);
			}else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("05. db 종료");
		}
		
		return res;
	}
	
	public int multiDelete(String[] seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		int[] cnt = null;
		
		String sql = "DELETE FROM MVCBOARD WHERE SEQ=?";
		
		try {
			pstm = con.prepareStatement(sql);
			for(int i=0; i<seq.length; i++) {
				pstm.setString(1, seq[i]);
				pstm.addBatch();
				System.out.println("03. 쿼리 준비 : " + sql + "[seq=" + seq[i]+"]");
			}
			
			cnt = pstm.executeBatch();
			System.out.println("04. 쿼리 실행 및 리턴");
			
			for(int i=0; i<cnt.length; i++) {
				if(cnt[i] == 1) {
					res++;
				}
			}
			
			if(seq.length == res) {
				commit(con);
			}else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("3/4 단계 오류");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
			System.out.println("05. db 종료 \n");
		}
		
		
		return res;
	}
	
}
