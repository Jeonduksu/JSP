package com.login.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.login.dto.MyMemberDto;

import static common.JDBCTemplate.*;
public class MyMemberDao {
	
	public MyMemberDto login(String id, String pw) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MyMemberDto res = new MyMemberDto();
		
		String sql = "SELECT * FROM MYMEMBER WHERE MYID=? AND MYPW=? AND MYENABLED=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.setString(2, pw);
			pstm.setString(3, "Y");
			System.out.println("03. 쿼리 준비 : "+sql);
			
			rs = pstm.executeQuery();
			System.out.println("04.쿼리 실행 및 리턴");
			
			while(rs.next()) {
				res.setMyno(rs.getInt(1));
				res.setMyid(rs.getString(2));
				res.setMypw(rs.getString(3));
				res.setMyname(rs.getString(4));
				res.setMyaddr(rs.getString(5));
				res.setMyphone(rs.getString(6));
				res.setMyemail(rs.getString(7));
				res.setMyenabled(rs.getString(8));
				res.setMyrole(rs.getString(9));
			}
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05. db 종료 \n");
		}
		
		
		return res;
	}
	
	public List<MyMemberDto> selectAll(){
		return null;
	}
	
	public String idChk(String id) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String res = null;
		
		String sql = "SELECT * FROM MYMEMBER WHERE MYID=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, id);
			System.out.println("03.쿼리 준비: "+sql);
			
			rs = pstm.executeQuery();
			System.out.println("04.쿼리 실행 및 리턴");
			
			while(rs.next()) {
				res = rs.getString(2);
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
	
	public int insertUser(MyMemberDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		
		String sql = "INSERT INTO VALUES(null,?,?,?,?,?,?,'Y','USER')";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1,dto.getMyid());
			pstm.setString(2,dto.getMypw());
			pstm.setString(3,dto.getMyname());
			pstm.setString(4,dto.getMyaddr());
			pstm.setString(5,dto.getMyphone());
			pstm.setString(6,dto.getMyemail());
			System.out.println("03.쿼리 준비: " + sql);
			
			res = pstm.executeUpdate();
			System.out.println("04.쿼리 실행 및 리턴");
			
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
			System.out.println("05. db 종료\n");
		}
		
		return res;
	}
	
	public MyMemberDto selectUser(int myno) {
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		MyMemberDto res = null;
		
		String sql = "SELECT * FROM MYMEMBER WHERE MYNO=?";
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, myno);
			System.out.println("03. 쿼리 준비 : "+sql);
			
			rs=pstm.executeQuery();
			System.out.println("04.쿼리 실행 및 리턴");
			
			while(rs.next()) {
				res = new MyMemberDto();
				res.setMyno(rs.getInt(1));
				res.setMyid(rs.getString(2));
				res.setMypw(rs.getString(3));
				res.setMyname(rs.getString(4));
				res.setMyaddr(rs.getString(5));
				res.setMyphone(rs.getString(6));
				res.setMyemail(rs.getString(7));
				res.setMyenabled(rs.getString(8));
				res.setMyrole(rs.getString(9));
			}
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally{
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05. db 종료\n");
		}
		return res;
	}
	
	public int updateRole(int myno, String myrole) {
		Connection con = getConnection();
		PreparedStatement pstm =null;
		int res = 0;
		
		String sql = "UPDATE MYMEMBER SET MYROLE=? WHERE MYNO=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(res, myrole);
			pstm.setInt(2, myno);
			System.out.println("03. 쿼리 준비: "+ sql);
			
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
			System.out.println("05. db 종료\n");
		}
		
		
		return res;
	}
}
