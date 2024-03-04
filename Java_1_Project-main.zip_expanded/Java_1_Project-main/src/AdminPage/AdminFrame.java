package AdminPage;

import java.sql.*;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultTextUI;


class AirFrame extends JFrame implements ActionListener, MouseListener {

   AirDAO airDAO;
   AirDTO airDTO;
   JLabel jlCode, jlName, jlNation,imageLabel;
   JTextField jtCode, jtName, jtNation;
   JButton jbAdd, jbDel, jbChange;
   JTable table;
   Vector data, col;
   ImageIcon icon;
   


   
   public AirFrame() {
      JPanel panel = new JPanel();
      airDAO = new AirDAO();
      setBounds(100, 100, 1000,540);
      setTitle("관리자 페이지");
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setResizable(false);
      
      panel=new Mypanel();
      panel.setLayout(null);
      setContentPane(panel);
      
      
      ImageIcon ii = new ImageIcon("Image\\RightAirPlane.gif");
      JLabel imageLabel = new JLabel();
      imageLabel.setIcon(ii);
      imageLabel.setBounds(30,190,355,265);
      imageLabel.setOpaque(true);
      panel.add(imageLabel);
      
      //코드   
      panel.add(jlCode = new JLabel("code", JLabel.CENTER));
      jlCode.setFont(new Font("맑은고딕", Font.BOLD, 15));
      jlCode.setBounds(10, 60, 100, 30);
      jlCode.setForeground(new Color(240,192,214));

      panel.add(jtCode = new JTextField());
      jtCode.setFont(new Font("맑은고딕", Font.BOLD, 15));
      jtCode.setHorizontalAlignment(JTextField.CENTER);
      jtCode.setBounds(100, 60, 150, 30);
      jtCode.setBorder(new LineBorder(new Color(240,192,214),2));

      // 이름
      panel.add(jlName = new JLabel("name", JLabel.CENTER));
      jlName.setFont(new Font("맑은고딕", Font.BOLD, 15));
      jlName.setBounds(10, 100, 100, 30);
      jlName.setForeground(new Color(240,192,214));
      panel.add(jtName = new JTextField());
      jtName.setFont(new Font("맑은고딕", Font.BOLD, 15));
      jtName.setHorizontalAlignment(JTextField.CENTER);
      jtName.setBounds(100, 100, 150, 30);
      jtName.setBorder(new LineBorder(new Color(240,192,214),2));

      // 국가
      panel.add(jlNation = new JLabel("nation", JLabel.CENTER));
      jlNation.setFont(new Font("맑은고딕", Font.BOLD, 15));
      jlNation.setBounds(10, 140, 100, 30);
      jlNation.setForeground(new Color(240,192,214));
      panel.add(jtNation = new JTextField());
      jtNation.setFont(new Font("맑은고딕", Font.BOLD, 15));
      jtNation.setHorizontalAlignment(JTextField.CENTER);
      jtNation.setBounds(100, 140, 150, 30);
      jtNation.setBorder(new LineBorder(new Color(240,192,214),2));

      // 버튼
      panel.add(jbAdd = new JButton("add"));
      jbAdd.setFont(new Font("메이플스토리", Font.BOLD, 13));
      jbAdd.setBounds(270, 60, 100, 30);
      jbAdd.addActionListener(this);
      jbAdd.setForeground(new Color(240,192,214));
      jbAdd.setBorder(new LineBorder(new Color(240,192,214),2));
      jbAdd.setForeground(Color.white);
      jbAdd.setBackground(new Color(203,234,251));
      

      panel.add(jbDel = new JButton("delete"));
      jbDel.setFont(new Font("메이플스토리",Font.BOLD,13));
      jbDel.setBounds(270, 100, 100, 30);
      jbDel.addActionListener(this);
      jbDel.setForeground(new Color(240,192,214));
      jbDel.setBorder(new LineBorder(new Color(240,192,214),2));
      jbDel.setForeground(Color.white);
      jbDel.setBackground(new Color(203,234,251));
      

      panel.add(jbChange = new JButton("change"));
      jbChange.setFont(new Font("메이플스토리", Font.BOLD, 13));
      jbChange.setBounds(270, 140, 100, 30);
      jbChange.addActionListener(this);
      jbChange.setForeground(new Color(240,192,214));
      jbChange.setBorder(new LineBorder(new Color(240,192,214),2));
      jbChange.setForeground(Color.white);
      jbChange.setBackground(new Color(203,234,251));

      // 컬럼 백터
      col = new Vector();
      col.add("code");
      col.add("name");
      col.add("nation");
      
      
      
      // 테이블 수정 못하게 DefultTable 사용
      DefaultTableModel model = new DefaultTableModel(airDAO.getAirPort(), col) {
         public boolean isCellEditable(int row, int column) {
            return false;
         }
         
      };
      // 디폴트 테이블을 테이블에 더해서 스크롤 패널에 넣음
      table = new JTable(model);
      table.addMouseListener(this);
      JScrollPane scroll = new JScrollPane(table);
      panel.add(scroll);
      scroll.setBounds(400, 60, 550, 400);
      table.setBackground(Color.white);
      table.setForeground(new Color(240,192,214));
      table.getTableHeader().setBackground(new Color(240,192,214));
      table.getTableHeader().setForeground(Color.white);
      table.getTableHeader().setFont(new Font("MV Boli",Font.BOLD,13));
      table.setFont(new Font("메이플스토리",Font.PLAIN,15));
      
      setVisible(true);
   }

   public void jTableSet() {
      table.getTableHeader().setReorderingAllowed(false);
      table.getTableHeader().setResizingAllowed(true);
      table.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
      
      //
      DefaultTableCellRenderer celAlingCenter=
            new DefaultTableCellRenderer();
      celAlingCenter.setHorizontalAlignment(JLabel.CENTER);
      
      
      
      //컬럼별 사이즈 조절&정렬
      table.getColumnModel().getColumn(0).setPreferredWidth(10);
      table.getColumnModel().getColumn(0).setCellRenderer(celAlingCenter);
      
      table.getColumnModel().getColumn(1).setPreferredWidth(10);
      table.getColumnModel().getColumn(1).setCellRenderer(celAlingCenter);

      table.getColumnModel().getColumn(2).setPreferredWidth(10);
      table.getColumnModel().getColumn(2).setCellRenderer(celAlingCenter);
      
   }

   @Override
   public void mouseClicked(MouseEvent e) {
      int rowIndex = table.getSelectedRow();
      jtCode.setText(table.getValueAt(rowIndex, 0) + "");
      jtName.setText(table.getValueAt(rowIndex, 1) + "");
      jtNation.setText(table.getValueAt(rowIndex, 2) + "");
      

   }

   @Override
   public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   @Override
   public void actionPerformed(ActionEvent e) {
      String buttonFlag = e.getActionCommand();
      if (buttonFlag.equals("add")) {
         try {
            contentSet();
            int result = airDAO.insertAirPort(airDTO);
            if (result == 1) {
               JOptionPane.showMessageDialog(this, "추가완료");
               jTableRefresh();
               contentClear();
            } else {
               JOptionPane.showMessageDialog(this, "실패하였습니다");

            }
         } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "값을 입력하세요");
         }
      } else if (buttonFlag.equals("delete")) {
         try {
            contentSet();
            int result = airDAO.deleteAirPort(airDTO);
            if (result == 1) {
               JOptionPane.showMessageDialog(this, "삭제완료");
               jTableRefresh();
               contentSet();
            } else {
               JOptionPane.showMessageDialog(this, "실패하였습니다");
            }
         } catch (Exception e2) {
            JOptionPane.showMessageDialog(this, "값을 입력하세요");
         }
      } else if (buttonFlag.equals("change")) {
         try {
            contentSet();
            int result = airDAO.updateAirPort(airDTO);
            if (result == 1) {
               JOptionPane.showMessageDialog(this, "수정하였습니다");
               jTableRefresh();
               contentClear();
               jtName.setFocusable(true);
            }
         } catch (Exception e2) {
            JOptionPane.showMessageDialog(this, "수정 실패");
            JOptionPane.showMessageDialog(this, "값을 입력하세요");

         } finally {

         }
      }

   }

   public void jTableRefresh() {
      DefaultTableModel model = new DefaultTableModel(airDAO.getAirPort(), col) {
         public boolean isCellEditable(int row, int column) {
            return false;
         }
      };
      table.setModel(model);
      jTableSet();
   }

   public void contentClear() {
      jtCode.setText("");
      jtName.setText("");
      jtNation.setText("");

   }

   public void contentSet() {
      airDTO = new AirDTO();
      String code = jtCode.getText();
      String name = jtName.getText();
      String nation = jtNation.getText();

      airDTO.setCode(code);
      airDTO.setName(name);
      airDTO.setNation(nation);

   }

      
   }
class Mypanel extends JPanel {

      public ImageIcon backIc = new ImageIcon("Image\\res.png"); //여기 이미지 주소
      public Image backImg = backIc.getImage();
      public Image changebackImg = backImg.getScaledInstance(984, 500, Image.SCALE_SMOOTH);
      int backY = 0;

      public Mypanel() {

         setFocusable(true);
   }

   @Override
      protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.drawImage(changebackImg, 0, backY, this);
      }
   }



public class AdminFrame {

   public static void main(String[] args) {
      new AirFrame().setLocationRelativeTo(null);;
   }

}
