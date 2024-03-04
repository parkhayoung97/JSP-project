package JoinPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

import Tool.DBConnect;

import java.util.Date;


public class JoinDAO {
  
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
	      }
	      return conn;
	   }
	   

   public int insertInfo(MainPage.MemberDTO memberDTO) {
      int result = 0;
      try {
    	 conn = getConn();
         String sql = "insert into user values (?,?,?,?,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, memberDTO.getId());
         pstmt.setString(2, memberDTO.getPw());
         pstmt.setString(3, memberDTO.getName());         
         pstmt.setString(4, memberDTO.getBir());
         pstmt.setString(5, memberDTO.getEmail());
         pstmt.setString(6, memberDTO.getNumber());
         
         result = pstmt.executeUpdate();
      } catch (Exception e) {
         // TODO: handle exception
         System.out.println(e);
         if (e.getMessage().contains("PRIMARY")) {
            JOptionPane.showMessageDialog(null, "아이디 중복!");
            System.out.println(e);
         }else {
        	 JOptionPane.showMessageDialog(null, "공백을 채워주세요");
         }
      } finally {
         DBConnect.close(conn, pstmt);
      }
      return result;
   }

   public boolean DpCheck(String text) {
      conn =getConn();
      try {
         String sql = "select count(*) cnt from user where id=?;";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, text);
         rs = pstmt.executeQuery();
         if(rs.next()) {
            int cnt = rs.getInt("cnt");
            if(cnt>0) {
               return true;
            }
         }
      } catch (Exception e) {
         // TODO: handle exception
      }finally {
         DBConnect.close(rs, conn, pstmt);
      }return false;
   }

   
}