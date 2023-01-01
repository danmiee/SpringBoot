package edu.pnu.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.pnu.domain.MemberVO;

public class MemberDaoH2Impl implements MemberInterface {

	private Connection con = null;
	
	public MemberDaoH2Impl() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/mission5", "sa", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<MemberVO> getMembers() {
		String sqlString = "select * from member order by id";
		
		Statement st = null;
		ResultSet rs = null;
		List<MemberVO> list = new ArrayList<>();
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sqlString);
			
			while(rs.next()) {
				list.add(new MemberVO(
						rs.getInt("id"),
						rs.getString("name"),
						rs.getString("pass"),
						rs.getDate("regidate")
						));
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public MemberVO getMember(int id) {
		String sqlString = String.format("select * from member where id='%d'",id);
		
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sqlString);
			rs.next();
			
			MemberVO m = new MemberVO();
			m.setId(rs.getInt("id"));
			m.setName(rs.getString("name"));
			m.setPass(rs.getString("pass"));
			m.setRegidate(rs.getDate("regidate"));
			
			return m;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public int getNextId() {
		String sqlString = "select max(id) from member";
		
		Statement st = null;
		ResultSet rs = null;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery(sqlString);
			rs.next();
			return rs.getInt(1)+1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 1;
	}
	
	public MemberVO addMember(MemberVO vo) {
		int id = getNextId();
		String sqlString = String.format("insert into member(id, name, pass, regidate) values ('%d','%s','%s','%s')", 
				id, vo.getName(), vo.getPass(), new Date(System.currentTimeMillis()));
		
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sqlString);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return getMember(id);
	}
	
	public MemberVO updateMember(MemberVO vo) {
		int id = vo.getId();
		String sqlString = String.format("update member set name='%s', pass='%s' where id='%d'", 
				vo.getName(), vo.getPass(), id);
		
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sqlString);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return getMember(id);
	}
	
	public MemberVO removeMember(int id) {
		MemberVO m = getMember(id);
		String sqlString = String.format("delete from member where id = '%d'", id);
		
		Statement st = null;
		
		try {
			st = con.createStatement();
			st.executeUpdate(sqlString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}
}
