package JoinPage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import MainPage.LoginMain;
import MainPage.MemberDTO;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

public class JoinMain extends JFrame {
   JoinDAO joinDAO;
   MemberDTO memberDTO;
   private JPanel contentPane;
   private JTextField IdTextF;
   private JTextField NameTextF;
   public JTextField BirthTextF;
   private JTextField PhoneTextF;
   private JTextField MailTextF;
   private JPasswordField PwTextF;
   private JPasswordField DpCheckPwTextF;
   private JComboBox MailListCB;

   /**
    * Launch the application.
    */
   
   public static void main(String[] args) {

      EventQueue.invokeLater(new Runnable() {
         
         public void run() {
            try {
               JoinMain frame = new JoinMain();
               frame.setLocationRelativeTo(null);
               frame.setVisible(true);

            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public JoinMain() {
      
      setResizable(false);
      
      
      joinDAO = new JoinDAO();
      setTitle("회원가입");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 437, 546);
      contentPane = new JPanel();
      contentPane.setBackground(Color.white);
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

   // esc로 창 닫기
   		contentPane.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
   				"Cancel");
   		contentPane.getActionMap().put("Cancel", new AbstractAction() {

   			@Override
   			public void actionPerformed(ActionEvent e) {
   				dispose();
   				new LoginMain();
   			}
   		});
      
      setContentPane(contentPane);
      contentPane.setLayout(null);
      

      JLabel IdLabel = new JLabel("ID");
      IdLabel.setBounds(40, 56, 57, 20);
      contentPane.add(IdLabel);
      IdLabel.setFont(new Font("MV Boli",Font.BOLD,15));
      IdLabel.setForeground(new Color(240,192,214));
      
      

      JLabel DpCheckLabel = new JLabel("New label");
      DpCheckLabel.setBounds(127, 77, 223, 15);
      contentPane.add(DpCheckLabel);
      DpCheckLabel.setVisible(false);
      

      IdTextF = new JTextField();
      IdTextF.setBounds(127, 50, 116, 21);
      contentPane.add(IdTextF);
      IdTextF.setColumns(10);
      IdTextF.setBorder(new LineBorder(new Color(203,234,251),2));

      JButton DpCheckBtn = new JButton("중복확인");
      DpCheckBtn.setBounds(255, 49, 97, 25);
      contentPane.add(DpCheckBtn);
      DpCheckBtn.setBackground(new Color(240,192,214));
      DpCheckBtn.setBorder(new LineBorder(new Color(240,192,214)));
      DpCheckBtn.setFont(new Font("메이플스토리",Font.ITALIC,13));
      DpCheckBtn.setForeground(Color.white);
      
      JLabel PwLabel = new JLabel("비밀번호");
      PwLabel.setBounds(40, 108, 57, 25);
      contentPane.add(PwLabel);
      PwLabel.setFont(new Font("메이플스토리",Font.ITALIC,13));
      PwLabel.setForeground(new Color(240,192,214));
      

      JLabel DpPwLabel = new JLabel("비밀번호 확인");
      DpPwLabel.setBounds(40, 160, 86, 25);
      contentPane.add(DpPwLabel);
      DpPwLabel.setFont(new Font("메이플스토리",Font.ITALIC,13));
      DpPwLabel.setForeground(new Color(240,192,214));

      JLabel PwDpCheckLabel = new JLabel();
      PwDpCheckLabel.setBounds(127, 183, 300, 25);
      contentPane.add(PwDpCheckLabel);
      PwDpCheckLabel.setVisible(false);

      JLabel NameLabel = new JLabel("성명");
      NameLabel.setBounds(40, 214, 57, 25);
      contentPane.add(NameLabel);
      NameLabel.setFont(new Font("메이플스토리",Font.ITALIC,13));
      NameLabel.setForeground(new Color(240,192,214));

      NameTextF = new JTextField();
      NameTextF.setColumns(10);
      NameTextF.setBounds(127, 208, 116, 21);
      contentPane.add(NameTextF);
      NameTextF.setBorder(new LineBorder(new Color(203,234,251),2));

      JLabel BirthLabel = new JLabel("생년월일");
      BirthLabel.setBounds(40, 264, 57, 25);
      contentPane.add(BirthLabel);
      BirthLabel.setFont(new Font("메이플스토리",Font.ITALIC,13));
      BirthLabel.setForeground(new Color(240,192,214));

      BirthTextF = new JTextField();
      BirthTextF.setColumns(10);
      BirthTextF.setBounds(127, 258, 116, 21);
      BirthTextF.setEnabled(false);
      contentPane.add(BirthTextF);

      JLabel PhoneLabel = new JLabel("전화번호");
      PhoneLabel.setBounds(40, 319, 57, 25);
      contentPane.add(PhoneLabel);
      PhoneLabel.setFont(new Font("메이플스토리",Font.ITALIC,13));
      PhoneLabel.setForeground(new Color(240,192,214));

      PhoneTextF = new JTextField();
      PhoneTextF.setColumns(10);
      PhoneTextF.setBounds(127, 313, 116, 21);
      contentPane.add(PhoneTextF);
      PhoneTextF.setBorder(new LineBorder(new Color(203,234,251),2));

      JLabel MailLabel = new JLabel("E-mail");
      MailLabel.setBounds(40, 374, 57, 25);
      contentPane.add(MailLabel);
      MailLabel.setFont(new Font("MV Boli",Font.BOLD,15));
      MailLabel.setForeground(new Color(240,192,214));

      MailTextF = new JTextField();
      MailTextF.setColumns(10);
      MailTextF.setBounds(127, 368, 116, 21);
      contentPane.add(MailTextF);
      MailTextF.setBorder(new LineBorder(new Color(203,234,251),2));


      JButton JoinBtn = new JButton("회원가입");
      JoinBtn.setBounds(50, 441, 97, 23);
      contentPane.add(JoinBtn);
      JoinBtn.setEnabled(false);
      JoinBtn.setBackground(Color.white);
      JoinBtn.setBorder(new LineBorder(new Color(240,192,214),2));
      JoinBtn.setFont(new Font("메이플스토리",Font.BOLD,13));
      JoinBtn.setForeground(new Color(240,192,214));

      JButton ResetBtn = new JButton("초기화");

      ResetBtn.setBounds(159, 441, 97, 23);
      contentPane.add(ResetBtn);
      ResetBtn.setBackground(Color.white);
      ResetBtn.setBorder(new LineBorder(new Color(240,192,214),2));
      ResetBtn.setFont(new Font("메이플스토리",Font.BOLD,13));
      ResetBtn.setForeground(new Color(240,192,214));

      JLabel AtLabel = new JLabel("@");
      AtLabel.setBounds(248, 374, 19, 15);
      contentPane.add(AtLabel);
      AtLabel.setForeground(new Color(240,192,214));

      JButton CancelBtn = new JButton("취소");
      CancelBtn.setBounds(268, 441, 97, 23);
      contentPane.add(CancelBtn);
      CancelBtn.setBackground(Color.white);
      CancelBtn.setBorder(new LineBorder(new Color(240,192,214),2));
      CancelBtn.setFont(new Font("메이플스토리",Font.BOLD,13));
      CancelBtn.setForeground(new Color(240,192,214));

      PwTextF = new JPasswordField();
      PwTextF.setBounds(127, 102, 116, 21);
      contentPane.add(PwTextF);
      PwTextF.setEnabled(false);
      PwTextF.setBorder(new LineBorder(new Color(203,234,251),2));
      

      DpCheckPwTextF = new JPasswordField();
      DpCheckPwTextF.setBounds(127, 157, 116, 21);
      contentPane.add(DpCheckPwTextF);
      DpCheckPwTextF.setEnabled(false);
      DpCheckPwTextF.setBorder(new LineBorder(new Color(203,234,251),2));

      String[] maillist = { null, "gmail.com", "naver.com", "kakao.com" };
      MailListCB = new JComboBox(maillist);
      MailListCB.setBounds(270, 367, 116, 23);
      contentPane.add(MailListCB);

      JButton BirthBtn = new JButton("달력");
      BirthBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {            
            new CustomCalendar();
         }
      });
      BirthBtn.setBounds(255, 257, 97, 23);
      contentPane.add(BirthBtn);
      BirthBtn.setBackground(new Color(240,192,214));
      BirthBtn.setBorder(new LineBorder(new Color(240,192,214)));
      BirthBtn.setFont(new Font("메이플스토리",Font.ITALIC,13));
      BirthBtn.setForeground(Color.white);

      // 중복확인버튼
      IdTextF.addKeyListener(new KeyAdapter() {
         public void keyReleased(KeyEvent e) {
            DpCheckLabel.setVisible(true);
            DpCheckLabel.setText("중복 확인 버튼을 눌러주세요");
            DpCheckLabel.setForeground(Color.red);
            JoinBtn.setEnabled(false);
         }
      });

      // 비밀번호 체크(비밀번호체크 텍스트필드 기준)
      DpCheckPwTextF.addKeyListener(new KeyAdapter() {
         public void keyReleased(KeyEvent e) {

            if (PwTextF.getText().equals(DpCheckPwTextF.getText())) {
               PwDpCheckLabel.setVisible(true);
               PwDpCheckLabel.setForeground(Color.blue);
               PwDpCheckLabel.setText("비밀번호가 일치합니다.");
               JoinBtn.setEnabled(true);
            } else {
               PwDpCheckLabel.setVisible(true);
               PwDpCheckLabel.setForeground(new Color(240,192,214));
               PwDpCheckLabel.setText("비밀번호가 일치하지 않습니다.");
               JoinBtn.setEnabled(false);
            }

         }
      });
      // 비밀번호 체크(비밀번호 텍스트필드 기준)
      PwTextF.addKeyListener(new KeyAdapter() {
         public void keyReleased(KeyEvent e) {

            if (PwTextF.getText().equals(DpCheckPwTextF.getText())) {
               PwDpCheckLabel.setVisible(true);
               PwDpCheckLabel.setForeground(Color.blue);
               PwDpCheckLabel.setText("비밀번호가 일치합니다.");
               JoinBtn.setEnabled(true);
            } else {
               PwDpCheckLabel.setVisible(true);
               PwDpCheckLabel.setForeground(new Color(240,192,214));
               PwDpCheckLabel.setText("비밀번호가 일치하지 않습니다.");
               JoinBtn.setEnabled(false);
            }
            // 둘 중 하나를 지우면 비밀번호체크 라벨 숨김
            if (PwTextF.getText().equals("") || DpCheckPwTextF.getText().equals("")) {
               PwDpCheckLabel.setVisible(false);
            }

         }
      });
      // 아이디 중복체크 버튼
      DpCheckBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            // joinDAO = new JoinDAO();
            PwTextF.setText(null);
            DpCheckPwTextF.setText(null);
            DpCheckLabel.setVisible(false);
            if (IdTextF.getText() != null) {
               if (joinDAO.DpCheck(IdTextF.getText())) {
                  DpCheckLabel.setVisible(true);
                  DpCheckLabel.setForeground(Color.red);
                  DpCheckLabel.setText("이미 사용중인 아이디입니다.");
                  PwTextF.setEnabled(false);
                  DpCheckPwTextF.setEnabled(false);

               } else {
                  DpCheckLabel.setVisible(true);
                  DpCheckLabel.setForeground(new Color(240,192,214));
                  DpCheckLabel.setText("사용 가능한 아이디입니다.");
                  PwTextF.setEnabled(true);
                  DpCheckPwTextF.setEnabled(true);
               }
            }
         }
      });

      ResetBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            IdTextF.setText(null);
            PwTextF.setText(null);
            DpCheckPwTextF.setText(null);
            NameTextF.setText(null);
            PhoneTextF.setText(null);
            // BirthTextF.setText(null);
            MailTextF.setText(null);
            PwDpCheckLabel.setVisible(false);
            DpCheckLabel.setVisible(false);
            MailListCB.setSelectedItem(null);
            

         }
      });
      JoinBtn.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            try {
               contentSet();
               int result = joinDAO.insertInfo(memberDTO);
               if (result == 1) {
                  JOptionPane.showMessageDialog(null, "회원 가입을 환영합니다.");
                  System.out.println("가입완료");
                  dispose();
                  new LoginMain();

               } else {
                  JOptionPane.showMessageDialog(null, "가입실패","Message",JOptionPane.ERROR_MESSAGE);
               }
            } catch (Exception e2) {
               JOptionPane.showMessageDialog(null, "공백을 입력해주세요");
               System.out.println(e2);
            }
         }

      });
      CancelBtn.addActionListener(new ActionListener() {
         JTextField tf=new JTextField();
         public void actionPerformed(ActionEvent e) {
            int result=JOptionPane.showConfirmDialog(null,"로그인 페이지로 돌아가시겠습니까?","Confirm",JOptionPane.YES_NO_OPTION);
            if(result==JOptionPane.CLOSED_OPTION)
               tf.setText("Just Closed without Selection");
            else if(result==JOptionPane.YES_OPTION) 
               new LoginMain();
            else 
               tf.setText("Just Closed without Selection");
         }
      });

   }
    
   public void contentSet() {
      memberDTO = MemberDTO.getInstance();
      String id = IdTextF.getText();
      String pw = PwTextF.getText();
      String name = NameTextF.getText();
      String birth = BirthTextF.getText();
      String phone = PhoneTextF.getText();
      String mail = MailTextF.getText() + "@" + MailListCB.getSelectedItem();

      memberDTO.setId(id);
      memberDTO.setPw(pw);
      memberDTO.setName(name);
      memberDTO.setBir(birth);
      memberDTO.setNumber(phone);
      memberDTO.setEmail(mail);

   }
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

      public CustomCalendar() {
         year = now.get(Calendar.YEAR);// 2021년
         month = now.get(Calendar.MONTH) + 1; // 0월 == 1월
         date = now.get(Calendar.DATE);      
         
         for (int i = year; i >= year - 50; i--) {
            yearModel.addElement(i);
         }
         for (int i = 1; i <= 12; i++) {
            monthModel.addElement(i);
         }
         cntCenter.setBackground(new Color(255,255,255));
         cntNorth.setBackground(new Color(255,255,255));
         cntNorth.setForeground(new Color(240,192,214));
         ////////////////////////// 프레임///////////////////////////////////////////
         // 상단 지역
         add("North", bar);
         bar.setLayout(new FlowLayout());
         bar.setSize(600, 600);
         bar.add(lastMonth);
         lastMonth.setBackground(new Color(240,192,214));
         nextMonth.setBackground(new Color(240,192,214));
         lastMonth.setFont(new Font("HoloLens MDL2Assets", Font.PLAIN, 15));
         lastMonth.setForeground(Color.white);
         lastMonth.setBorder(new LineBorder(new Color(240,192,214)));
         nextMonth.setFont(new Font("HoloLens MDL2Assets", Font.PLAIN, 15));
         nextMonth.setForeground(Color.white);
         nextMonth.setBorder(new LineBorder(new Color(240,192,214)));
         yLbl.setForeground(Color.white);
         mLbl.setForeground(Color.white);
         yLbl.setFont(new Font("메이플스토리", Font.ITALIC, 13));
         mLbl.setFont(new Font("메이플스토리", Font.ITALIC, 13));
         
         ////////////////////////// 달력/////////////////////////////////////////////
         bar.add(yearCombo);                  
         yearCombo.setModel(yearModel);
         yearCombo.setSelectedItem(year);
         yearCombo.setBackground(Color.white);
         yearCombo.setFont(new Font("MV Boli",Font.BOLD,10));
         

         bar.add(yLbl);
         bar.add(monthCombo);         
         monthCombo.setModel(monthModel);
         monthCombo.setSelectedItem(month);
         monthCombo.setBackground(Color.white);
         monthCombo.setFont(new Font("MV Boli",Font.BOLD,10));
         
         bar.add(mLbl);
         bar.add(nextMonth);         
         bar.setBackground(new Color(240,192,214));

         // 중앙 지역
//         add("Center", center);
//         // 중앙 상단 지역
//         center.add("North", cntNorth);
//         for (int i = 0; i < dw.length; i++) {
//            JLabel dayOfWeek = new JLabel(dw[i], JLabel.CENTER);
//            if (i == 0)
//               dayOfWeek.setForeground(new Color(252,148,150));
//            else if (i == 6)
//               dayOfWeek.setForeground(new Color(112,180,248));
//            cntNorth.add(dayOfWeek);
//         }
         
         add("Center", center);
         // 중앙 상단 지역
         center.add("North", cntNorth);
         for (int i = 0; i < dw.length; i++) {
            JLabel dayOfWeek = new JLabel(dw[i], JLabel.CENTER);
            if (i == 0)
               dayOfWeek.setForeground(new Color(252,148,150));
            else if (i == 6)
               dayOfWeek.setForeground(new Color(112,180,248));
            cntNorth.add(dayOfWeek).setFont(new Font("메이플스토리", Font.ITALIC, 13));
           
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
         //setSize(400, 300);
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
            	   yy--;
                   mm = 12;
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
            day.setFont(new Font("MV Boli",Font.PLAIN,15));
            day.setForeground(new Color(146,146,146));
            if ((week + i) % 7 == 0) {
               cntCenter.add(day).setForeground(new Color(112,180,248));
               cntCenter.add(day).setFont(new Font("MV Boli",Font.PLAIN,15));
               day.setText(1 + i + "");
            } else if ((week + i) % 7 == 1) {
               cntCenter.add(day).setForeground(new Color(252,148,150));
               cntCenter.add(day).setFont(new Font("MV Boli",Font.PLAIN,15));
               
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

                  if (clickCheck == 0) {
                     
                     BirthTextF.setText(y + "/" + m + "/" + str);
                     BirthTextF.setEnabled(false);
                     System.out.println(y + "/" + m + "/" + str);
                     clickCheck++;
                     dispose();
                     clickCheck--;

                  }
               }

            });
         }

      }
   
   }
   

   }