package MainPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.security.PublicKey;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Vector;

import javax.security.auth.Refreshable;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Tool.FadeLogo;
import Tool.MovingImg;
import Tool.RoundedButton;
import Tool.RoundedButton2;
import TripPage.AirPortDTO;
import TripPage.TripDAO;
import TripPage.TripDTO;

// 개선 사항 : 분담 전에 변수명 통일 작업이 필요했음. 더 객관적인 변수명 사용해야함. ex) 버튼이라면 내용+Btn 으로 통일 하는 식.
public class MainFrame extends JFrame {
   JButton Btn_Userinfo, Btn_Cancel, Btn_UserinfoDel, Btn_ReserveDel, Btn_ReservePage, Btn_MyPage, Btn_LogOut,
         Btn_UserinfoUpdate, Btn_Reserve, Btn_Reserveinfo, Btn_MyPagetoUserinfoUpdate, Btn_Back;
   JPanel Panel_Main, Panel_Reserve, Panel_MyPagetoUserinfo, Panel_MyPagetoUpdate, Panel_MyPagetoReserveList,
         Panel_MyPagetoReserveDetail;
   // 개선 사항 : 라벨을 통일했기에 수정할때 알아보기 어려움. 이벤트를 생성할때 .equal "라벨내용"을 사용해야함.
   JLabel Lb_MyPagetoReserveDetail, Lb_MyPagetoUserinfo, Lb_ReserveList, Lb_MainPage, Lb_MyPagetoUpdate,
         Lb_ReservePage, Lb_Name, Lb_UserinfoOutput_Id, Lb_UserinfoOutput_dtoId, Lb_UserinfoOutput_Name,
         Lb_UserinfoOutput_dtoName, Lb_UserinfoOutput_Birth, Lb_UserinfoOutput_dtoBirth, Lb_UserinfoOutput_Email,
         Lb_UserinfoOutput_dtoEmail, Lb_UserinfoOutput_Phone, Lb_UserinfoOutput_dtoPhone;
   JLabel num, tripUser, startC, endC, go, back, peple, seatC, nation, city; // + 예약 상세 페이지
   JLabel ReserveListLabel1, ReserveListLabel3, ReserveListLabel4, ReserveListLabel5, ReserveListLabel6,
         ReserveListLabel7, ReserveListLabel9, ReserveListLabel11, ReserveListLabel13, ReserveListLabel15;
   JTextField trip, start, end, dayGo, dayBack, peo, seat; // 예약
   JTable jTable;
   JTextField TextF_Depart, TextF_Arrival, jtUpdate1, jtUpdate2, jtUpdate3, jtUpdate4, jtUpdate5, jtUpdate6;
   JComboBox Departures, Arrivals, NumberOfPeople, SeatClass;
   MemberDTO dto = MemberDTO.getInstance();
   MemberDAO dao = new MemberDAO();
   TripDAO tripDAO = new TripDAO();
   TripDTO tripDTO = new TripDTO();
   String st, en;
   int date2, date3, date4;
   Vector col;
   DefaultTableModel model;

   // +
   JLabel startR, endR, dayGoR, dayBackR, peoR, seatR; // 예약
   JLabel jlUpdate1, jlUpdate2, jlUpdate3, jlUpdate4, jlUpdate5, jlUpdate6; // 수정

   // 수정버튼 클릭 이후 수정된 정보 업데이트 메소드
   public void refrash(MemberDTO dto) {
      Lb_UserinfoOutput_dtoEmail.setText(dto.getEmail());
      Lb_UserinfoOutput_dtoPhone.setText(dto.getNumber());
   }

   public void resD(TripDTO dto) {
      ReserveListLabel1.setText(String.valueOf(dto.getNum()));
      ReserveListLabel3.setText(dto.getStartcode());
      ReserveListLabel5.setText(dto.getEndcode());
      ReserveListLabel7.setText(dto.getGo());
      ReserveListLabel9.setText(dto.getBack());
      ReserveListLabel11.setText(dto.getPeple());
      ReserveListLabel13.setText(dto.getSeatclax());
      ReserveListLabel15.setText(dto.getTripuser());
   }

   public MainFrame() {
      // 개선 사항 : 패널을 따로따로 클래스화 시키는게 코드가 간결해짐.
      Panel_Main = new JPanel(); // 메인 페이지
      Panel_Reserve = new JPanel(); // 예약 페이지
      Panel_MyPagetoUserinfo = new JPanel(); // 마이페이지-유저정보 select
      Panel_MyPagetoUpdate = new JPanel(); // 마이페이지-수정 update
      Panel_MyPagetoReserveList = new JPanel(); // 마이페이지-예약리스트s
      Panel_MyPagetoReserveDetail = new JPanel(); // 마이페이지-예약상세d

      setBackground(Color.WHITE); // +
      Panel_Main.setBackground(Color.WHITE); // +
      Panel_Reserve.setBackground(Color.white);
      Panel_MyPagetoUserinfo.setBackground(Color.WHITE); // +
      Panel_MyPagetoUpdate.setBackground(Color.WHITE);
      Panel_MyPagetoReserveList.setBackground(Color.WHITE);
      Panel_MyPagetoReserveDetail.setBackground(Color.WHITE);

      // 프레임
      setLayout(null);
      setLocationRelativeTo(null);

      // 예약하기 버튼
      Btn_ReservePage = new RoundedButton2("예약하기");
      add(Btn_ReservePage);
      Btn_ReservePage.setBounds(540, 20, 100, 20);
      Btn_ReservePage.setBorderPainted(false);
      Btn_ReservePage.setFocusPainted(false);
      Btn_ReservePage.setBackground(Color.white);
      Btn_ReservePage.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
      Btn_ReservePage.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            contentClear();
            Panel_Reserve.setVisible(true);
            Panel_Main.setVisible(false);
            Panel_MyPagetoUserinfo.setVisible(false);
            Panel_MyPagetoUpdate.setVisible(false);
            Panel_MyPagetoReserveList.setVisible(false);
            Panel_MyPagetoReserveDetail.setVisible(false);
         }
      });

      // 마이페이지 버튼
      Btn_MyPage = new RoundedButton2("마이페이지");
      add(Btn_MyPage);
      Btn_MyPage.setBounds(650, 20, 115, 20);
      Btn_MyPage.setBorderPainted(false);
      Btn_MyPage.setFocusPainted(false);
      Btn_MyPage.setBackground(Color.white);
      Btn_MyPage.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
      Btn_MyPage.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            dao.UserCheck(dto); // +
            Panel_MyPagetoUserinfo.setVisible(true);
            Panel_Main.setVisible(false);
            Panel_Reserve.setVisible(false);
            Panel_MyPagetoUpdate.setVisible(false);
            Panel_MyPagetoReserveList.setVisible(false);
            Panel_MyPagetoReserveDetail.setVisible(false);
         }
      });

      // 로그아웃 버튼
      Btn_LogOut = new RoundedButton2("로그아웃");
      add(Btn_LogOut);
      Btn_LogOut.setBounds(770, 20, 100, 20); // +
      Btn_LogOut.setBorderPainted(false); // +
      Btn_LogOut.setFocusPainted(false); // +
      Btn_LogOut.setBackground(Color.white);
      Btn_LogOut.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
      Btn_LogOut.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            int result1 = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION);
            if (result1 == JOptionPane.YES_OPTION) {
               dispose();
               new LoginMain();
            }
         }
      });

      // 로고 라벨 삽입해서 메인화면으로 가기(mouseClick)
      ImageIcon lo = new ImageIcon("Image\\small_logo.png");
      JLabel logo = new JLabel(lo);
      logo.setIcon(lo);
      logo.setBounds(8, 0, 60, 60);
      add(logo);
      logo.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            dao.UserCheck(dto);
            Panel_Main.setVisible(true);
            Panel_MyPagetoUserinfo.setVisible(false);
            Panel_Reserve.setVisible(false);
            Panel_MyPagetoUpdate.setVisible(false);
            Panel_MyPagetoReserveList.setVisible(false);
            Panel_MyPagetoReserveDetail.setVisible(false);
         }
      });

      // -------------------------------------------------------------------
      // Panel_Main 메인 페이지

      Panel_Main.setLayout(null);
      Panel_Main.setSize(900, 600);

      // 이름 출력
      Panel_Main.add(Lb_Name = new JLabel(dto.getName() + "님 환영합니다."));
      Lb_Name.setBounds(70, 100, 300, 30);
      Lb_Name.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
      Lb_Name.setForeground(new Color(243, 179, 210));
      
      // 메인화면 떠오르는 로고
        JLabel fadeLogo;
        fadeLogo = new FadeLogo();
        fadeLogo.setBounds(290, 60, 400, 400);
        fadeLogo.setOpaque(false);

        // 메인화면 날아가는 비행기
        JLabel movingAirplane;
        movingAirplane = new MovingImg();
        movingAirplane.setBackground(null);
        movingAirplane.setBounds(200, 0, 1200, 600);
        
        // 메인화면 구름 배경
        ImageIcon sky = new ImageIcon("Image\\Main2.png");
        JLabel BackgroundSky = new JLabel();
        BackgroundSky.setIcon(sky);
        BackgroundSky.setBounds(-50, 50, 1200, 600);
        BackgroundSky.add(movingAirplane);
        BackgroundSky.add(fadeLogo);
        Panel_Main.add(BackgroundSky);

      // 메인 이미지
      // 출처 https://kr.freepik.com/free-vector/traveling-landing-page-web-design_5671208.htm#query=%EB%B9%84%ED%96%89%EA%B8%B0&position=5&from_view=search&track=sph
//      ImageIcon mai = new ImageIcon("Image\\main.png");
//      JLabel main = new JLabel();
//      main.setIcon(mai);
//      main.setBounds(-120, 120, 1200, 600);
//      Panel_Main.add(main);

      // -------------------------------------------------------------------------------

      // Panel_Reserve 예약하기
      Panel_Reserve.setLayout(null);
      Panel_Reserve.setSize(900, 600);

      // 출발 공항 리스트
      Vector depart = new Vector();
      depart = tripDAO.getAirPort();

      // 도착 공항 리스트
      Vector arrival = new Vector();
      arrival = tripDAO.getAirPort();

      // 예약 요소
      Panel_Reserve.add(startR = new JLabel("출발지"));
      startR.setBounds(130, 180, 300, 23);
      startR.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
      Departures = new JComboBox(depart);
      Departures.setBounds(130, 200, 300, 50);
      Panel_Reserve.add(Departures);
      Departures.setBackground(Color.white);

      Panel_Reserve.add(endR = new JLabel("도착지"));
      endR.setBounds(460, 180, 300, 23);
      endR.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
      Arrivals = new JComboBox(arrival);
      Arrivals.setBounds(460, 200, 300, 50);
      Panel_Reserve.add(Arrivals);
      Arrivals.setBackground(Color.white);

      Panel_Reserve.add(dayGoR = new JLabel("가는날"));
      dayGoR.setBounds(130, 300, 300, 23);
      dayGoR.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
      Panel_Reserve.add(TextF_Depart = new JTextField());
      TextF_Depart.setBounds(130, 320, 200, 50);
      TextF_Depart.setBackground(Color.black);      
      // combobox를 눌렀을 때 달력 프레임 생성
      // 개선 사항 : listener가 아닌 adapter로 받아서 코드 단순화 필요
      TextF_Depart.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            date2 = 0;            
            CustomCalendar dCalendar =  new CustomCalendar(date2);
            date3++;
           
            
         }
      });

      Panel_Reserve.add(dayBackR = new JLabel("오는날"));
      dayBackR.setBounds(370, 300, 300, 23);
      dayBackR.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
      Panel_Reserve.add(TextF_Arrival = new JTextField());
      TextF_Arrival.setBounds(370, 320, 200, 50);
      TextF_Arrival.setBackground(Color.black);
      // combobox를 눌렀을 때 달력 프레임 생성
      TextF_Arrival.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            date2 = 1;
            new CustomCalendar(date2);
            date4++;       
            
         }
      });

      Panel_Reserve.add(peoR = new JLabel("인원"));
      peoR.setBounds(130, 420, 300, 23);
      peoR.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
      String[] numPeople = { "1명", "2명", "3명", "4명", "5명" };
      NumberOfPeople = new JComboBox(numPeople);
      NumberOfPeople.setBounds(130, 440, 100, 50);
      Panel_Reserve.add(NumberOfPeople);
      NumberOfPeople.setBackground(Color.white);

      Panel_Reserve.add(seatR = new JLabel("좌석"));
      seatR.setBounds(280, 420, 300, 23);
      seatR.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
      String[] SClass = { "이코노미", "비지니스", "퍼스트" };
      SeatClass = new JComboBox(SClass);
      SeatClass.setBounds(280, 440, 100, 50);
      Panel_Reserve.add(SeatClass);
      SeatClass.setBackground(Color.white);

      // 예약하기 버튼
      Btn_Reserve = new RoundedButton("예약하기");
      Panel_Reserve.add(Btn_Reserve);
      Btn_Reserve.setBounds(655, 440, 100, 50);
      Btn_Reserve.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            tripDTO.setTripuser(dto.getId());
//            if (Departures.getSelectedIndex() == -1 || Arrivals.getSelectedIndex() == -1
//                  || TextF_Depart.getText().equals("가는날") || TextF_Arrival.getText().equals("오는날")
//                  || NumberOfPeople.getSelectedIndex() == -1 || SeatClass.getSelectedIndex() == -1) {
//               JOptionPane.showMessageDialog(null, "공백을 입력해주세요");
//            } else 
            if (Departures.getSelectedItem().equals(Arrivals.getSelectedItem())) {
               JOptionPane.showMessageDialog(null, "같은 지역은 선택하실 수 없습니다.");
               Departures.setSelectedIndex(0);
               Arrivals.setSelectedIndex(0);
            } else if (TextF_Depart.getText().equals(TextF_Arrival.getText())) {
               JOptionPane.showMessageDialog(null, "가는날과 오는날은 다르게 입력해주세요.");
               TextF_Depart.setText("가는날");
               TextF_Arrival.setText("오는날");
            } else if (dayMinusCheck() == 1) {
               JOptionPane.showMessageDialog(null, "가는날이 오는날보다 늦을순 없습니다.");
               TextF_Depart.setText("가는날");
               TextF_Arrival.setText("오는날");
            } else {
               // 입력 요소 tripDTO에 저장
               String de = Departures.getSelectedItem().toString().substring(0, 3);
               tripDTO.setStartcode(de);
               String ar = Arrivals.getSelectedItem().toString().substring(0, 3);
               tripDTO.setEndcode(ar);
               tripDTO.setGo(TextF_Depart.getText());
               tripDTO.setBack(TextF_Arrival.getText());
               tripDTO.setPeple((String) NumberOfPeople.getSelectedItem());
               tripDTO.setSeatclax((String) SeatClass.getSelectedItem());                     
               int result2 = JOptionPane.showConfirmDialog(null,
                     "예약 하신 내용 \n출발지 : " + Departures.getSelectedItem() + "\n도착지 : " + Arrivals.getSelectedItem()
                           + "\n" + "인원수 : " + NumberOfPeople.getSelectedItem() + "\n가는날 : "
                           + TextF_Depart.getText() + "\n오는날 : " + TextF_Arrival.getText() + "\n좌석클래스 : "
                           + SeatClass.getSelectedItem() + "\n이 맞나요?",
                     "예약 확정", JOptionPane.YES_NO_OPTION);
               // 예약 확인
               if (result2 == JOptionPane.YES_OPTION) {
                  // 예약 저장 insert 실행
                  tripDAO.insert(tripDTO);
                  JOptionPane.showMessageDialog(null, "예약되었습니다");
                  Panel_Reserve.setVisible(false);
                  Panel_MyPagetoReserveList.setVisible(true);
               }
            }
            // 테이블 초기화
            jTableRefresh();
         }
      });

      // 예약 페이지 배경 이미지
      ImageIcon bo = new ImageIcon("Image\\res.png");
      JLabel book = new JLabel();
      book.setIcon(bo);
      book.setBounds(40, 0, 1200, 600);
      Panel_Reserve.add(book);

      Panel_Reserve.setVisible(false);

      // --------------------------------------------------------------------------
      // Panel_MyPagetoUserinfo 마이페이지 유저 정보 조회 페이지

      Panel_MyPagetoUserinfo.setLayout(null);
      Panel_MyPagetoUserinfo.setSize(900, 700);

      // 유저정보 출력화면
      Lb_UserinfoOutput_Id = new JLabel("ID");
      Panel_MyPagetoUserinfo.add(Lb_UserinfoOutput_Id);
      Lb_UserinfoOutput_Id.setBounds(230, 190, 97, 23);
      Lb_UserinfoOutput_Id.setFont(new Font("휴먼모음T", Font.PLAIN, 15));

      Lb_UserinfoOutput_dtoId = new JLabel(dto.getId());
      Panel_MyPagetoUserinfo.add(Lb_UserinfoOutput_dtoId);
      Lb_UserinfoOutput_dtoId.setBounds(280, 190, 97, 23);
      Lb_UserinfoOutput_dtoId.setFont(new Font("휴먼모음T", Font.PLAIN, 15));

      Lb_UserinfoOutput_Name = new JLabel("NAME");
      Panel_MyPagetoUserinfo.add(Lb_UserinfoOutput_Name);
      Lb_UserinfoOutput_Name.setBounds(450, 190, 97, 23);
      Lb_UserinfoOutput_Name.setFont(new Font("휴먼모음T", Font.PLAIN, 18));

      Lb_UserinfoOutput_dtoName = new JLabel(dto.getName());
      Panel_MyPagetoUserinfo.add(Lb_UserinfoOutput_dtoName);
      Lb_UserinfoOutput_dtoName.setBounds(650, 190, 97, 23);
      Lb_UserinfoOutput_dtoName.setFont(new Font("휴먼모음T", Font.PLAIN, 18));

      Lb_UserinfoOutput_Birth = new JLabel("BIRTH");
      Panel_MyPagetoUserinfo.add(Lb_UserinfoOutput_Birth);
      Lb_UserinfoOutput_Birth.setBounds(450, 250, 97, 23);
      Lb_UserinfoOutput_Birth.setFont(new Font("휴먼모음T", Font.PLAIN, 18));

      Lb_UserinfoOutput_dtoBirth = new JLabel(dto.getBir());
      Panel_MyPagetoUserinfo.add(Lb_UserinfoOutput_dtoBirth);
      Lb_UserinfoOutput_dtoBirth.setBounds(650, 250, 97, 23);
      Lb_UserinfoOutput_dtoBirth.setFont(new Font("휴먼모음T", Font.PLAIN, 18));

      Lb_UserinfoOutput_Email = new JLabel("EMAIL");
      Panel_MyPagetoUserinfo.add(Lb_UserinfoOutput_Email);
      Lb_UserinfoOutput_Email.setBounds(450, 310, 97, 23);
      Lb_UserinfoOutput_Email.setFont(new Font("휴먼모음T", Font.PLAIN, 18));

      Lb_UserinfoOutput_dtoEmail = new JLabel(dto.getEmail());
      Panel_MyPagetoUserinfo.add(Lb_UserinfoOutput_dtoEmail);
      Lb_UserinfoOutput_dtoEmail.setBounds(650, 310, 200, 23);
      Lb_UserinfoOutput_dtoEmail.setFont(new Font("휴먼모음T", Font.PLAIN, 18));

      Lb_UserinfoOutput_Phone = new JLabel("TEL");
      Panel_MyPagetoUserinfo.add(Lb_UserinfoOutput_Phone);
      Lb_UserinfoOutput_Phone.setBounds(450, 370, 97, 23);
      Lb_UserinfoOutput_Phone.setFont(new Font("휴먼모음T", Font.PLAIN, 18));

      Lb_UserinfoOutput_dtoPhone = new JLabel(dto.getNumber());
      Panel_MyPagetoUserinfo.add(Lb_UserinfoOutput_dtoPhone);
      Lb_UserinfoOutput_dtoPhone.setBounds(650, 370, 200, 23);
      Lb_UserinfoOutput_dtoPhone.setFont(new Font("휴먼모음T", Font.PLAIN, 18));

      // 유저 정보 수정 버튼
      Btn_MyPagetoUserinfoUpdate = new RoundedButton("정보 수정");
      Panel_MyPagetoUserinfo.add(Btn_MyPagetoUserinfoUpdate);
      Btn_MyPagetoUserinfoUpdate.setBounds(580, 450, 97, 23);
      Btn_MyPagetoUserinfoUpdate.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // 유저 요소 확인 select
            dao.UserCheck(dto);
            Panel_MyPagetoUserinfo.setVisible(false);
            Panel_MyPagetoUpdate.setVisible(true);
         }
      });
      
      // 유저 정보 삭제 버튼
      Btn_UserinfoDel = new RoundedButton("회원 탈퇴");
      Panel_MyPagetoUserinfo.add(Btn_UserinfoDel);
      Btn_UserinfoDel.setBounds(700, 450, 97, 23);
      Btn_UserinfoDel.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            int result3 = JOptionPane.showConfirmDialog(null, "회원 탈퇴하시겠습니까?", "회원 탈퇴", JOptionPane.YES_NO_OPTION);
            // 유저 삭제 여부 확인
            if (result3 == JOptionPane.YES_OPTION) {
               // 유저 정보 삭제 delete 실행
               JOptionPane.showMessageDialog(null, "탈퇴되었습니다");
               dao.Delete(dto);
               dispose();
               new LoginMain().setLocationRelativeTo(null);;
            }
         }
      });

      // 예약정보 조회 버튼
      Panel_MyPagetoUserinfo.add(Btn_Reserveinfo = new RoundedButton("예약정보 조회"));
      Btn_Reserveinfo.setBounds(49, 120, 97, 23);
      Btn_Reserveinfo.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Panel_MyPagetoUserinfo.setVisible(false);
            Panel_MyPagetoReserveList.setVisible(true);
         }
      });

      // 마이페이지 조회 배경 이미지
      ImageIcon my = new ImageIcon("Image\\pro.png");
      JLabel myPage = new JLabel();
      myPage.setIcon(my);
      myPage.setBounds(40, 0, 1200, 600);
      Panel_MyPagetoUserinfo.add(myPage);
      
      Panel_MyPagetoUserinfo.setVisible(false);

      // --------------------------------------------------------------------------
      // 유저 수정 페이지
      Panel_MyPagetoUpdate.setLayout(null);
      Panel_MyPagetoUpdate.setSize(900, 700);

      // 유저 수정 출력화면
      Panel_MyPagetoUpdate.add(jlUpdate1 = new JLabel("ID"));
      jlUpdate1.setBounds(300, 150, 97, 23);
      jlUpdate1.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
      Panel_MyPagetoUpdate.add(jtUpdate1 = new JTextField(dto.getId()));
      jtUpdate1.setBounds(500, 150, 200, 23);
      jtUpdate1.setFont(new Font("휴먼모음T", Font.BOLD, 18));
      jtUpdate1.setEnabled(false);

      Panel_MyPagetoUpdate.add(jlUpdate2 = new JLabel("PASSWORD"));
      jlUpdate2.setBounds(300, 200, 97, 23);
      jlUpdate2.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
      Panel_MyPagetoUpdate.add(jtUpdate2 = new JTextField(dto.getPw()));
      jtUpdate2.setBounds(500, 200, 200, 23);
      jtUpdate2.setFont(new Font("휴먼모음T", Font.BOLD, 18));

      Panel_MyPagetoUpdate.add(jlUpdate3 = new JLabel("NAME"));
      jlUpdate3.setBounds(300, 250, 97, 23);
      jlUpdate3.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
      Panel_MyPagetoUpdate.add(jtUpdate3 = new JTextField(dto.getName()));
      jtUpdate3.setBounds(500, 250, 200, 23);
      jtUpdate3.setFont(new Font("휴먼모음T", Font.BOLD, 18));
      jtUpdate3.setEnabled(false);

      Panel_MyPagetoUpdate.add(jlUpdate4 = new JLabel("BRITH"));
      jlUpdate4.setBounds(300, 300, 97, 23);
      jlUpdate4.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
      Panel_MyPagetoUpdate.add(jtUpdate4 = new JTextField(dto.getBir()));
      jtUpdate4.setBounds(500, 300, 200, 23);
      jtUpdate4.setFont(new Font("휴먼모음T", Font.BOLD, 18));
      jtUpdate4.setEnabled(false);

      Panel_MyPagetoUpdate.add(jlUpdate5 = new JLabel("EAMIL"));
      jlUpdate5.setBounds(300, 350, 97, 23);
      jlUpdate5.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
      Panel_MyPagetoUpdate.add(jtUpdate5 = new JTextField(dto.getEmail()));
      jtUpdate5.setBounds(500, 350, 200, 23);
      jtUpdate5.setFont(new Font("휴먼모음T", Font.BOLD, 18));

      Panel_MyPagetoUpdate.add(jlUpdate6 = new JLabel("TEL"));
      jlUpdate6.setBounds(300, 400, 97, 23);
      jlUpdate6.setFont(new Font("휴먼모음T", Font.PLAIN, 15));
      Panel_MyPagetoUpdate.add(jtUpdate6 = new JTextField(dto.getNumber()));
      jtUpdate6.setBounds(500, 400, 200, 23);
      jtUpdate6.setFont(new Font("휴먼모음T", Font.BOLD, 18));

      // 유저 정보 수정 버튼
      Btn_UserinfoUpdate = new RoundedButton("수정");
      Panel_MyPagetoUpdate.add(Btn_UserinfoUpdate);
      Btn_UserinfoUpdate.setBounds(49, 120, 97, 23);
      // 버튼 클릭 시
      Btn_UserinfoUpdate.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            int result4 = JOptionPane.showConfirmDialog(null, "수정하시겠습니까?", "수정확인", JOptionPane.YES_NO_OPTION);
            if (result4 == JOptionPane.YES_OPTION) {
               JOptionPane.showMessageDialog(null, "수정되었습니다");   
               dto.setPw(jtUpdate2.getText());
               dto.setEmail(jtUpdate5.getText());
               dto.setNumber(jtUpdate6.getText());
               // 유저 수정 실행 update
               dao.Update(dto);
               // 유저 정보 조회 select
               dao.UserCheck(dto);
               // 유저 정보 화면 적용
               refrash(dto);
               Panel_MyPagetoUpdate.setVisible(false);
               Panel_MyPagetoUserinfo.setVisible(true);
            }
            
         }
      });
      
      // 돌아가기 버튼
      Btn_Cancel = new RoundedButton("취소");
      Panel_MyPagetoUpdate.add(Btn_Cancel);
      Btn_Cancel.setBounds(49, 170, 97, 23);
      Btn_Cancel.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // 조회 패널로 돌아가기
            Panel_MyPagetoUpdate.setVisible(false);
            Panel_MyPagetoUserinfo.setVisible(true);
         }
      });

      // 유저 수정 배경 이미지
      ImageIcon up = new ImageIcon("Image\\update.png");
      JLabel update = new JLabel();
      update.setIcon(up);
      update.setBounds(40, 0, 1200, 600);
      Panel_MyPagetoUpdate.add(update);
      
      Panel_MyPagetoUpdate.setVisible(false);

      // ================= 예약 ===============
      // --------------------------------------------------------------------------
      // jpMyPage_res 예약 리스트 페이지
      Panel_MyPagetoReserveList.setLayout(null);
      Panel_MyPagetoReserveList.setSize(900, 700);   

      // 아이디 출력
      Lb_UserinfoOutput_Id = new JLabel("ID:   " + dto.getId());
      Panel_MyPagetoReserveList.add(Lb_UserinfoOutput_Id);
      Lb_UserinfoOutput_Id.setBounds(70, 150, 97, 23);
      Lb_UserinfoOutput_Id.setFont(new Font("휴먼모음T", Font.PLAIN, 15));

      // 예약정보 테이블 출력 jtable
      // 컬럼 벡터
      col = new Vector();
      col.add("예약번호");
      col.add("출발공항");
      col.add("도착공항");
      col.add("가는날");
      col.add("오는날");
      col.add("인원수");
      col.add("클래스");

      // 테이블 수정 불가 DefaultTableModel 사용
      model = new DefaultTableModel(tripDAO.getTrip(dto), col) {
         public boolean inCellEditable(int row, int column) {
            return false;
         }
      };

      // 디폴트 테이블을 테이블에 더해서 스크롤 패널에 넣기
      jTable = new JTable(model);
      jTable.getTableHeader().setBackground(new Color(255, 139, 160));
      jTable.addMouseListener(new MouseAdapter() {
         @Override
         public void mouseClicked(MouseEvent e) {
            // 행 클릭 시
//            jTable.setBackground(new Color(243, 179, 210));
            int rowIndex = jTable.getSelectedRow();
            System.out.println(jTable.getValueAt(rowIndex, 0));
            tripDTO.setNum((int) jTable.getValueAt(rowIndex, 0));
            // 예약 요소 조회 실행 select
            tripDTO = tripDAO.getTripNum(tripDTO);
            // 예약 요소 출력
            resD(tripDTO);
            // 여행 국가 요소 조회 후 입력
            st = tripDAO.start(tripDTO);
            System.out.println(st);
            ReserveListLabel4.setText(st);
            en = tripDAO.end(tripDTO);
            ReserveListLabel6.setText(en);
            Panel_MyPagetoReserveList.setVisible(false);
            Panel_MyPagetoReserveDetail.setVisible(true);
         }
      });

      JScrollPane scroll = new JScrollPane(jTable);
      jTableSet();
      Panel_MyPagetoReserveList.add(scroll);
      scroll.setBounds(59, 190, 770, 250);

      // 유저정보 조회 버튼
      Btn_Userinfo = new RoundedButton("유저정보 조회");
      Panel_MyPagetoReserveList.add(Btn_Userinfo);
      Btn_Userinfo.setBounds(700, 100, 97, 23);
      Btn_Userinfo.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // 유저 조회 패널로 돌아가기
            dao.UserCheck(dto);
            Panel_MyPagetoReserveList.setVisible(false);
            Panel_MyPagetoUserinfo.setVisible(true);
         }
      });

      // 예약 리스트 배경 이미지
      ImageIcon r = new ImageIcon("Image\\res.png");
      JLabel rr = new JLabel();
      rr.setIcon(r);
      rr.setBounds(40, 0, 1200, 600);
      Panel_MyPagetoReserveList.add(rr);
      
      Panel_MyPagetoReserveList.setVisible(false);

      // --------------------------------------------------------------------------
      // jpMyPage_resD 예약 상세 페이지
      Panel_MyPagetoReserveDetail.setLayout(null);
      Panel_MyPagetoReserveDetail.setSize(900, 700);

      // 예약정보 상세 출력 Label
      ReserveListLabel1 = new JLabel();
      Panel_MyPagetoReserveDetail.add(ReserveListLabel1);
      ReserveListLabel1.setBounds(300, 160, 97, 23);

      ReserveListLabel3 = new JLabel();
      Panel_MyPagetoReserveDetail.add(ReserveListLabel3);
      ReserveListLabel3.setBounds(340, 240, 97, 50);
      ReserveListLabel3.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
      ReserveListLabel3.setForeground(new Color(255, 255, 255));

      ReserveListLabel4 = new JLabel();
      Panel_MyPagetoReserveDetail.add(ReserveListLabel4);
      ReserveListLabel4.setBounds(300, 280, 150, 50);
      ReserveListLabel4.setForeground(new Color(255, 255, 255));

      ReserveListLabel5 = new JLabel();
      Panel_MyPagetoReserveDetail.add(ReserveListLabel5);
      ReserveListLabel5.setBounds(660, 240, 97, 50);
      ReserveListLabel5.setFont(new Font("휴먼모음T", Font.PLAIN, 30));
      ReserveListLabel5.setForeground(new Color(255, 255, 255));

      ReserveListLabel6 = new JLabel();
      Panel_MyPagetoReserveDetail.add(ReserveListLabel6);
      ReserveListLabel6.setBounds(640, 280, 150, 50);
      ReserveListLabel6.setForeground(new Color(255, 255, 255));

      ReserveListLabel7 = new JLabel();
      Panel_MyPagetoReserveDetail.add(ReserveListLabel7);
      ReserveListLabel7.setBounds(330, 360, 97, 23);
      ReserveListLabel7.setForeground(new Color(255, 255, 255));

      ReserveListLabel9 = new JLabel();
      Panel_MyPagetoReserveDetail.add(ReserveListLabel9);
      ReserveListLabel9.setBounds(660, 360, 97, 23);
      ReserveListLabel9.setForeground(new Color(255, 255, 255));

      ReserveListLabel11 = new JLabel();
      Panel_MyPagetoReserveDetail.add(ReserveListLabel11);
      ReserveListLabel11.setBounds(520, 440, 97, 23);

      ReserveListLabel13 = new JLabel();
      Panel_MyPagetoReserveDetail.add(ReserveListLabel13);
      ReserveListLabel13.setBounds(690, 440, 97, 23);

      ReserveListLabel15 = new JLabel();
      Panel_MyPagetoReserveDetail.add(ReserveListLabel15);
      ReserveListLabel15.setBounds(330, 440, 97, 23);

      // 예약정보 삭제 버튼
      Btn_ReserveDel = new RoundedButton("예약 삭제하기");
      Panel_MyPagetoReserveDetail.add(Btn_ReserveDel);
      Btn_ReserveDel.setBounds(49, 120, 97, 23);
      Btn_ReserveDel.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            int result5 = JOptionPane.showConfirmDialog(null, "예약을 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
            if (result5 == JOptionPane.YES_OPTION) {
               JOptionPane.showMessageDialog(null, "예약이 삭제되었습니다");
               // 예약 삭제 실행 delete
               tripDAO.delete(tripDTO);
               jTableRefresh();
               Panel_MyPagetoReserveDetail.setVisible(false);
               Panel_MyPagetoReserveList.setVisible(true);
            }            
         }
      });
      
      // 돌아가기 버튼
      Btn_Back = new RoundedButton("목록으로 돌아가기");
      Panel_MyPagetoReserveDetail.add(Btn_Back);
      Btn_Back.setBounds(49, 170, 110, 23);
      Btn_Back.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            Panel_MyPagetoReserveDetail.setVisible(false);
            Panel_MyPagetoReserveList.setVisible(true);
         }
      });

      // 예약 상세 페이지 배경 이미지1
      // 출처 https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.urbanbrush.net%2Fdownloads%2F%25ED%2595%25AD%25EA%25B3%25B5%25EA%25B6%258C-%25EC%259D%25BC%25EB%259F%25AC%25EC%258A%25A4%25ED%258A%25B8-ai-%25EB%25AC%25B4%25EB%25A3%258C%25EB%258B%25A4%25EC%259A%25B4%25EB%25A1%259C%25EB%2593%259C-free-air-ticket-vector%2F&psig=AOvVaw0CiRJg_AcaAOCmQRJlvGLj&ust=1679533888063000&source=images&cd=vfe&ved=0CA4QjhxqFwoTCNj4yrit7v0CFQAAAAAdAAAAABAD
      ImageIcon res = new ImageIcon("Image\\ticket5.png");
      JLabel resD = new JLabel();
      resD.setIcon(res);
      resD.setBounds(225, 0, 1200, 600);
      Panel_MyPagetoReserveDetail.add(resD);

      // 예약 상세 페이지 배경 이미지2
      ImageIcon d = new ImageIcon("Image\\update.png");
      JLabel de = new JLabel();
      de.setIcon(d);
      de.setBounds(40, 0, 1200, 600);
      Panel_MyPagetoReserveDetail.add(de);
      
      Panel_MyPagetoReserveDetail.setVisible(false);

      // ---------------------------------------------------------
      // ++
      // 프레임 사이즈 고정
      setResizable(false);
      // 프레임에 패널 추가
      add(Panel_Main);
      add(Panel_Reserve);
      add(Panel_MyPagetoUserinfo);
      add(Panel_MyPagetoUpdate);
      add(Panel_MyPagetoReserveList);
      add(Panel_MyPagetoReserveDetail);

      setTitle("Hi Air");
      setSize(900, 600);
      setVisible(true);
      setDefaultCloseOperation(EXIT_ON_CLOSE);

   }
   // --달력---------------------------------------------

   class CustomCalendar extends JFrame implements ActionListener {
      int clickCheck = 0;
      // 상단 지역
      JPanel bar = new JPanel();
      JButton lastMonth = new JButton("◀");

      JComboBox<Integer> yearCombo = new JComboBox<Integer>();
      DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();

      JLabel yLbl = new JLabel("년 ");

      JComboBox<Integer> monthCombo = new JComboBox<Integer>();
      DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();
      JLabel mLbl = new JLabel("월");
      JButton nextMonth = new JButton("▶");
      // 중앙 지역
      JPanel center = new JPanel(new BorderLayout());
      // 중앙 상단 지역
      JPanel cntNorth = new JPanel(new GridLayout(0, 7));
      
      // 중앙 중앙 지역
      JPanel cntCenter = new JPanel(new GridLayout(0, 7));

      // 요일 입력
      String dw[] = { "일", "월", "화", "수", "목", "금", "토" };

      Calendar now = Calendar.getInstance();
      int year, month, date;

      public CustomCalendar(int date2) {    	 
         year = now.get(Calendar.YEAR);// 2021년
         month = now.get(Calendar.MONTH) + 1; // 0월 == 1월
         date = now.get(Calendar.DATE);
         for (int i = year; i <= year + 50; i++) {
            yearModel.addElement(i);
         }
         for (int i = 1; i <= 12; i++) {
            monthModel.addElement(i);
         }
         ////////////////////////// 프레임///////////////////////////////////////////
         // 상단 지역
         add("North", bar);
         bar.setLayout(new FlowLayout());
         bar.setSize(600, 600);

         bar.add(lastMonth);
         ////////////////////////// 달력/////////////////////////////////////////////
         bar.add(yearCombo);
         yearCombo.setModel(yearModel);
         yearCombo.setSelectedItem(year);

         bar.add(yLbl);
         bar.add(monthCombo);
         monthCombo.setModel(monthModel);
         monthCombo.setSelectedItem(month);

         bar.add(mLbl);
         bar.add(nextMonth);
         bar.setBackground(null);

         // 중앙 지역
         add("Center", center);
         // 중앙 상단 지역
         center.add("North", cntNorth);
         for (int i = 0; i < dw.length; i++) {
            JLabel dayOfWeek = new JLabel(dw[i], JLabel.CENTER);
            if (i == 0)
               dayOfWeek.setForeground(Color.red);
            else if (i == 6)
               dayOfWeek.setForeground(Color.blue);
            cntNorth.add(dayOfWeek);
         }

         // 중앙 중앙 지역
         center.add("Center", cntCenter);
         dayPrint(year, month);
         

         // 이벤트
         yearCombo.addActionListener(this);
         monthCombo.addActionListener(this);
         lastMonth.addActionListener(this);
         nextMonth.addActionListener(this);

         // frame 기본 셋팅
         setBounds(1180, 350, 400, 400);
         // setSize(400, 300);
         setVisible(true);
         setResizable(false);
         setDefaultCloseOperation(DISPOSE_ON_CLOSE);
      }

      // 이벤트 처리
      @Override
      public void actionPerformed(ActionEvent e) {
         Object obj = e.getSource();
         if (obj instanceof JButton) {
            JButton eventBtn = (JButton) obj;
            int yy = (Integer) yearCombo.getSelectedItem();
            int mm = (Integer) monthCombo.getSelectedItem();
            if (eventBtn.equals(lastMonth)) { // 전달
               if (mm == 1 && yy == year) {
               } else if (mm == 1) {
                  yy--;
                  mm = 12;
               } else {
                  mm--;
               }
            } else if (eventBtn.equals(nextMonth)) { // 다음달
               if (mm == 12) {
                  yy++;
                  mm = 1;
               } else {
                  mm++;
               }
            }
            yearCombo.setSelectedItem(yy);
            monthCombo.setSelectedItem(mm);
         } else if (obj instanceof JComboBox) { // 콤보박스 이벤트 발생시
            createDayStart();
         }
      }

      private void createDayStart() {
         cntCenter.setVisible(false); // 패널 숨기기
         cntCenter.removeAll(); // 날짜 출력한 라벨 지우기
         dayPrint((Integer) yearCombo.getSelectedItem(), (Integer) monthCombo.getSelectedItem());
         cntCenter.setVisible(true); // 패널 재출력
      }

      // 날짜 출력
      public void dayPrint(int y, int m) {
         Calendar cal = Calendar.getInstance();
         cal.set(y, m - 1, 1);
         int week = cal.get(Calendar.DAY_OF_WEEK); // 1일에 대한 요일
         int lastDate = cal.getActualMaximum(Calendar.DAY_OF_MONTH); // 1월에 대한 마지막 요일
         for (int i = 1; i < week; i++) { // 1월 1일 전까지 공백을 표시해라
            cntCenter.add(new JLabel(""));
         }

         for (int i = 0; i <= lastDate - 1; i++) { // 1월 마지막 날까지 숫자를 적어라, 그리고 토요일 일요일은 색깔을 입혀라
            JLabel day = new JLabel();

            day.setHorizontalAlignment(JLabel.CENTER);
            if ((week + i) % 7 == 0) {
               cntCenter.add(day).setForeground(Color.blue);
               day.setText(1 + i + "");
            } else if ((week + i) % 7 == 1) {
               cntCenter.add(day).setForeground(Color.red);
               day.setText(1 + i + "");
            } else {
               cntCenter.add(day);
               day.setText(1 + i + "");
            }
            day.addMouseListener((MouseListener) new MouseAdapter() {
               public void mouseClicked(MouseEvent me) {
                  JLabel mouseClick = (JLabel) me.getSource();
                  String str = mouseClick.getText();
                  String y = "" + yearCombo.getSelectedItem();
                  String m = "" + monthCombo.getSelectedItem();

                  // 받은 "요일"이 1자리면 0을 붙여라
                  if (str.equals(""))
                     ;
                  else if (str.length() == 1)
                     str = "0" + str;

                  // 받은 "월"이 1자리면 0을 붙여라
                  if (m.length() == 1)
                     m = "0" + m;
                  if (date2 == 0) {
                     if (clickCheck == 0) {
                        TextF_Depart.setText(y + "/" + m + "/" + str);
                        TextF_Depart.setEnabled(false);
                        System.out.println(y + "/" + m + "/" + str);
                        clickCheck++;
                        dispose();
                        clickCheck--;

                     }
                  } else if (date2 == 1) {
                     if (clickCheck == 0) {
                        TextF_Arrival.setText(y + "/" + m + "/" + str);
                        TextF_Arrival.setEnabled(false);
                        System.out.println(y + "/" + m + "/" + str);
                        clickCheck++;
                        dispose();
                        clickCheck--;
                     }
                  }
               }
            });
         }
      }
   }

   public void jTableSet() {
      // 이동과 길이 조절 여러개 선택 되는 것을 방지
      jTable.getTableHeader().setReorderingAllowed(false);
      jTable.getTableHeader().setResizingAllowed(false);

      jTable.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

      // 컬럼 정렬에 필요한 메소드
      DefaultTableCellRenderer cellAlignCenter = new DefaultTableCellRenderer();
      cellAlignCenter.setHorizontalAlignment(JLabel.CENTER);

      DefaultTableCellRenderer cellAlignRight = new DefaultTableCellRenderer();
      cellAlignRight.setHorizontalAlignment(JLabel.RIGHT);

      DefaultTableCellRenderer cellAlignLeft = new DefaultTableCellRenderer();
      cellAlignLeft.setHorizontalAlignment(JLabel.LEFT);

      // 컬럼별 사이즈 조절 & 정렬
      jTable.getColumnModel().getColumn(0).setPreferredWidth(10);
      jTable.getColumnModel().getColumn(0).setCellRenderer(cellAlignCenter);

      jTable.getColumnModel().getColumn(1).setPreferredWidth(10);
      jTable.getColumnModel().getColumn(1).setCellRenderer(cellAlignCenter);

      jTable.getColumnModel().getColumn(2).setPreferredWidth(10);
      jTable.getColumnModel().getColumn(2).setCellRenderer(cellAlignCenter);

      jTable.getColumnModel().getColumn(3).setPreferredWidth(10);
      jTable.getColumnModel().getColumn(3).setCellRenderer(cellAlignCenter);

      jTable.getColumnModel().getColumn(4).setPreferredWidth(10);
      jTable.getColumnModel().getColumn(4).setCellRenderer(cellAlignCenter);

      jTable.getColumnModel().getColumn(5).setPreferredWidth(10);
      jTable.getColumnModel().getColumn(5).setCellRenderer(cellAlignCenter);
   }

   LocalDate now = LocalDate.now();
   // 연도, 월(문자열, 숫자), 일, 일(year 기준), 요일(문자열, 숫자)
   int year = now.getYear();
   int monthValue = now.getMonthValue();
   String month = String.valueOf(monthValue);
   int dayOfMonth = now.getDayOfMonth();

   // 예약 textField 초기화
   public void contentClear() {
      Departures.setSelectedIndex(0);
      Arrivals.setSelectedIndex(0);
      NumberOfPeople.setSelectedIndex(0);
      SeatClass.setSelectedIndex(0);
      if (month.equals("1") || month.equals("2") || month.equals("3") || month.equals("4") || month.equals("5")
            || month.equals("6") || month.equals("7") || month.equals("8") || month.equals("9")) {
         month = "0" + month;
      }
      TextF_Depart.setForeground(new Color(163, 184, 204));
      TextF_Arrival.setForeground(new Color(163, 184, 204));
      TextF_Depart.setText(String.valueOf(year) + "/" + month + "/" + dayOfMonth);
      TextF_Arrival.setText(String.valueOf(year) + "/" + month + "/" + dayOfMonth);
   }

   // 테이블 내용을 갱신하는 메서드
   public void jTableRefresh() {
      model = new DefaultTableModel(tripDAO.getTrip(dto), col) {
         public boolean isCellEditable(int row, int column) {
            return false;
         }
      };
      jTable.setModel(model);
      jTableSet();
   }

   public int dayMinusCheck() {
      int result = 0;
      int start = Integer.valueOf(TextF_Depart.getText().replace("/", ""));
      int arrive = Integer.valueOf(TextF_Arrival.getText().replace("/", ""));
      int minusCheck = arrive - start;
      if (minusCheck < 0) {
         result = 1;
      }
      return result;
   }

}