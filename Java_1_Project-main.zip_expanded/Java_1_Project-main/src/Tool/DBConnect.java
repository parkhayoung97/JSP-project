package Tool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBConnect {
   public static Connection getConnection() {
      Connection con = null;
      String url="jdbc:mysql://localhost:3306/io";
      String id="root";
      String pw="1234";
      String driver="com.mysql.cj.jdbc.Driver";
      
      try {
         Class.forName(driver);
         con=DriverManager.getConnection(url,id,pw);
         System.out.println("접속성공");
      }catch (Exception e) {
         System.out.println("접속실패");
      }
         return con;
      }
   public static void close(Connection con,Statement stmt) {
      try {
         if(con!=null) {
            con.close();
         }
         if(stmt!=null) {
            stmt.close();
         }
      }catch(Exception e) {
         
      }
   }
         
   public static void close(ResultSet rs,Connection con,Statement stmt) {
      try {
         if(rs!=null) {
            rs.close();
         }
         if(con!=null) {
            con.close();
         }
         if(stmt!=null) {
            stmt.close();
         }
      }catch(Exception e) {
         
      }
   }

   
   }