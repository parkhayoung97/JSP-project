package AdminPage;

import java.sql.*;
import java.util.Vector;



public class AirDAO {
   private static final String DRIVER="com.mysql.cj.jdbc.Driver";
   private static final String URL="jdbc:mysql://192.168.0.58/io";
   private static final String USER = "yang";
   private static final String PASS = "1234";
   
   public  Connection getConn() {
      Connection con=null;
      
      try {
         Class.forName(DRIVER);
         con = DriverManager.getConnection(URL, USER, PASS);
         System.out.println("연결성공");
      }catch (Exception e) {
         System.out.println("연결실패");
         System.out.println(e);
      }finally {
         
      }
      return con;
   }
   public Vector getAirPort() {
      Vector data = new Vector();
      Connection con = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      try {
         con = getConn();
         String sql = "select*from airport order by city asc ";
         //String sql = "select*from airport";
         pstmt = con.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            String code = rs.getString("code");
            String nation = rs.getString("nation");
            String name = rs.getString("city");     
            Vector row = new Vector();
            row.add(code);
            row.add(name);
            row.add(nation);
            data.add(row);
         }
      }

      catch (Exception e) {
         System.out.println(e);
      } finally {
         try {
            if (rs != null) {
               rs.close();
            }
            if (pstmt != null) {
               pstmt.close();
            }
            if (con != null) {
               con.close();
            }

         } catch (Exception e2) {
            // TODO: handle exception
         }

      }

      return data;
   }
   public int insertAirPort(AirDTO dto) {
      Connection con = null;
      PreparedStatement pstmt = null;
      int result = 0;
      try {
         con = getConn();
         String sql = "insert into airport  values(?,?,?)";
         pstmt = con.prepareStatement(sql);
         pstmt.setString(1, dto.getCode());
         pstmt.setString(2, dto.getNation());
         pstmt.setString(3, dto.getName());        
         result = pstmt.executeUpdate();

      } catch (Exception e) {
         System.out.println("추가 오류");
      } finally {
         try {
            if (pstmt != null)
               pstmt.close();
            if (con != null)
               con.close();
         } catch (Exception e) {

         }
      }
      return result;
   }
   public int deleteAirPort(AirDTO dto) {
      Connection con = null;
      PreparedStatement pstmt = null;
      int result = 0;
      try {
         con=getConn();
         String sql="delete from airport where city=?";
         pstmt= con.prepareStatement(sql);
         pstmt.setString(1, dto.getName());
         result=pstmt.executeUpdate();
      } catch (Exception e) {
         System.out.println(e);
            System.out.println("삭제 실패");
            

      }finally {
         try {
            if (pstmt != null) {
                     pstmt.close();
                  }
                  if (con != null) {
                     con.close();
         }
                  } catch (Exception e2) {
            
         }
      }
      return result;
   }
      public int updateAirPort(AirDTO dto) {
         Connection con = null;
         PreparedStatement pstmt = null;
         int result = 0;
         try {
            con=getConn();
            String sql="update airport set code=?,nation=?,city=?";
            pstmt= con.prepareStatement(sql);
               pstmt.setString(1, dto.getCode());              
               pstmt.setString(2, dto.getNation()); 
               pstmt.setString(3, dto.getName()); 
               result = pstmt.executeUpdate();

         } catch (Exception e) {
               System.out.println("수정 실패");
               System.out.println(e);

         } finally {
               try {
                  if (pstmt != null) {
                     pstmt.close();
                  }
                  if (con != null) {
                     con.close();
                  }
               } catch (Exception e2) {
                  
               }
      return result;
         }
      }
         
            }