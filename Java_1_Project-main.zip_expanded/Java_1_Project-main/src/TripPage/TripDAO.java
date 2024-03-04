package TripPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import MainPage.MemberDTO;

public class TripDAO {

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
         System.out.println("연결 성공3");
      } catch (Exception e) {
         e.printStackTrace();
         System.out.println("연결 실패");
      }
      return conn;
   }

   // 공항 목록
   public Vector getAirPort() {
      Vector data = new Vector();
      try {
         conn = getConn();
         String sql = "select * from airport order by city asc ";
         pstmt = conn.prepareStatement(sql);
         rs = pstmt.executeQuery();
         while (rs.next()) {
            String code = rs.getString("code");
            String city = rs.getString("city");
            String nation = rs.getString("nation");
            String c = code + " (" + city + ", " + nation +")";
            data.add(c);
         }
      } catch (Exception e) {

      } finally {
         closeSelect();
      }
      return data;
   }

   // 예약 등록
   public void insert(TripDTO dto) {

      try {
         System.out.println("insert");
         System.out.println(dto.getTripuser());
         System.out.println(dto.getGo());
         System.out.println(dto.getPeple());
         conn = getConn();
         String sql = "insert into trip (startcode, endcode, tripuser, go, back, peple, seatclax) values (?,?,?,?,?,?,?)";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getStartcode());
         pstmt.setString(2, dto.getEndcode());
         pstmt.setString(3, dto.getTripuser());
         pstmt.setString(4, dto.getGo());
         pstmt.setString(5, dto.getBack());
         pstmt.setString(6, dto.getPeple());
         pstmt.setString(7, dto.getSeatclax());
         pstmt.executeUpdate();

//            tripuser, startcode, endcode,

      } catch (Exception e) {
         System.out.println(e);
      } finally {
         close();
      }
   }

   // 예약 조회(id)
   public Vector getTrip(MemberDTO dto) {
      Vector data = new Vector();
      try {
         conn = getConn();
         String sql = "select * from trip where tripuser=?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setString(1, dto.getId());
         rs = pstmt.executeQuery();
         while(rs.next()) {
            int num = rs.getInt("num");
            String startcode = rs.getString("startcode");
            String endcode = rs.getString("endcode");
            String go = rs.getString("go");
            String back = rs.getString("back");
            String peple = rs.getString("peple");
            String seatclax = rs.getString("seatclax");
            Vector row = new Vector();
            row.add(num);
            row.add(startcode);
            row.add(endcode);
            row.add(go);
            row.add(back);
            row.add(peple);
            row.add(seatclax);
            data.add(row);
//            System.out.println(num);
         }
      }catch(Exception e) {
         System.out.println(e);
      }finally {
         closeSelect();
      }
      return data;
   }
   
   // 예약 조회(num)
   public TripDTO getTripNum(TripDTO dto) {
//      TripDTO d = new TripDTO();   
      try {
         conn = getConn();
         String sql = "select * from trip where num=?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, dto.getNum());
         rs = pstmt.executeQuery();
         while(rs.next()) {
            System.out.println(rs.getString("startcode"));
            dto.setTripuser(rs.getString("tripuser"));
            dto.setNum(rs.getInt("num"));
            dto.setStartcode(rs.getString("startcode"));
            dto.setEndcode(rs.getString("endcode"));
            dto.setGo(rs.getString("go"));
            dto.setBack(rs.getString("back"));
            dto.setPeple(rs.getString("peple"));
            dto.setSeatclax(rs.getString("seatclax"));
         }
      }catch(Exception e) {
         System.out.println(e);
      }finally {
         closeSelect();
      }
      return dto;
   }
   
   // 예약 조회
   public String start(TripDTO dto) {
      String start = "";   
      try {
         conn = getConn();
         String sql = "select nation,city from airport where code=?";
         pstmt = conn.prepareStatement(sql);         
         pstmt.setString(1, dto.getStartcode());
         rs = pstmt.executeQuery();
         while(rs.next()) {
            start = rs.getString("nation") + ", " + rs.getString("city");
         }
      }catch(Exception e) {
         System.out.println(e);
      }finally {
         closeSelect();
      }
      return start;
   }
   public String end(TripDTO dto) {
      String end = "";
      Connection conn = null;
      PreparedStatement pstmt = null;
      ResultSet rs = null;   
      try {
         conn = getConn();
         String sql = "select nation,city from airport where code=?";
         pstmt = conn.prepareStatement(sql);         
         pstmt.setString(1, dto.getEndcode());
         rs = pstmt.executeQuery();
         while(rs.next()) {
            end = rs.getString("nation") + ", " + rs.getString("city");
         }
      }catch(Exception e) {
         System.out.println(e);
      }finally {
         closeSelect();
      }
      return end;
   }

   // 예약 삭제
   public void delete(TripDTO dto) {

      try {
         conn = getConn();
         String sql = "delete from trip where num=?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, dto.getNum());
         pstmt.executeUpdate();

      } catch (Exception e) {
         System.out.println(e);
      } finally {
         close();
      }
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

}