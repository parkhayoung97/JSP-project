package MainPage;

import java.sql.*;
import java.util.Vector;

public class MemberDAO {

	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://192.168.0.58/io";
	private static final String USER = "yang";
	private static final String PW = "1234";

	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	public Connection getConn() {

		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USER, PW);
			System.out.println("연결 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("연결 실패");
			System.out.println(e);
		}
		return conn;
	}

	public void close() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeSelect() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	// 마이페이지에서 회원 탈퇴 클릭시 회원 정보 및 회원 아이디가 포함된 예약 정보 삭제
	public void Delete(MemberDTO dto) {
		try {
			System.out.println("회원정보 삭제");
			conn = getConn();
			System.out.println(dto.getId());
			String sql = "delete from user where id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.executeUpdate();
		}catch(Exception e) {
			
		}finally {
			close();
		}
	}

	
	// 로그인 체크

	public int LoginCheck(MemberDTO dto) {
		// TODO Auto-generated method stub
		int result = 0;
		try {
			conn = getConn();
			String sql = "select * from user where id=? and pw =?;";
			System.out.println("2");
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (!dto.getId().equals("admin") && dto.getPw().equals(rs.getString("pw"))) {
					result = 1;
				} else if (dto.getId().equals("admin") && dto.getPw().equals(rs.getString("pw"))) {
					result = 2;
				}
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setBir(rs.getString("bir"));
				dto.setEmail(rs.getString("email"));
				dto.setNumber(rs.getString("number"));
			}

		} catch (Exception e) {
			
		//ppt 개선 사항 : close로 resultset을 닫지 않고 있었음. 명시적 생성자를 사용필요
		} finally {
			closeSelect();
		}
		return result;
	}

	// 메인화면에서 로그인한 아이디에 맞는 이름 출력
	public String MainName(MemberDTO dto) {
		String name = null;
		try {
			conn = getConn();
			String sql = "select name from user where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				name = rs.getString("name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeSelect();
		}
		return name;
	}

	// 마이페이지에서 회원정보 조회
	public void UserCheck(MemberDTO dto) {	// +
		// TODO Auto-generated method stub
		try {
			System.out.println("회원정보 재정의");
			conn = getConn();
			String sql = "select * from user where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPw());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setBir(rs.getString("bir"));
				dto.setNumber(rs.getString("number"));
			}
		} catch (Exception e) {
		}finally {
			closeSelect();
		}
	}

	// 마이페이지에서 회원정보 수정
	public void Update(MemberDTO dto) {
		// TODO Auto-generated method stub
		try {
			System.out.println("회원정보 수정");
			conn = getConn();
			String sql = "update user set pw=?, email=?, number=? where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPw());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getNumber());
			pstmt.setString(4, dto.getId());
			pstmt.executeUpdate();
			while (rs.next()) {
				dto.setPw(rs.getString("pw"));
				dto.setEmail(rs.getString("email"));
				dto.setNumber(rs.getString("number"));
				dto.setId(rs.getString("id"));
			}
		} catch (Exception e) {
		} finally {
			close();
		}
	}

	// -----------도착지 출발지 code-------------
	   public Vector getScore() {
	      Vector depart = new Vector();
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         con = getConn();
	         String sql = "select code, nation, city from airport order by code desc";
	         pstmt = con.prepareStatement(sql);
	         rs= pstmt.executeQuery();   	         
	         while (rs.next()) {
	            
	            String code = rs.getString("code");
	            String code1 = rs.getString("nation");
	            String code2 = rs.getString("city");
	            Vector row = new Vector();
	            row.add(code);
	            row.add(code1);
	            row.add(code2);
	            depart.add(row);
	            
	         }
	         
	      } catch (Exception e) {
	         System.out.println(e);
	      }finally {
	         closeSelect();
	         
	      }
	      return depart;
	      }
	   
	   public Vector getScore1() {
	      Vector arrival = new Vector();
	      Connection con = null;
	      PreparedStatement pstmt = null;
	      ResultSet rs = null;
	      try {
	         con = getConn();
	         String sql = "select code, nation, city from airport1 order by code desc";
	         pstmt = con.prepareStatement(sql);
	         rs= pstmt.executeQuery();   
	         
	         while (rs.next()) {
	            
	            String code = rs.getString("code");
	            String code1 = rs.getString("nation");
	            String code2 = rs.getString("city");
	            Vector row = new Vector();
	            row.add(code);
	            row.add(code1);
	            row.add(code2);
	            arrival.add(row);
	            
	         }
	         
	      } catch (Exception e) {
	         System.out.println(e);
	      }finally {
	         closeSelect();
	         
	      }
	      return arrival;
	      }
}