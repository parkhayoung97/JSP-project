package MainPage;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import AdminPage.AdminFrame;
import JoinPage.JoinMain;
import Tool.RoundedButton;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.security.PublicKey;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginMain extends JFrame {
	private JTextField IdTextF_Login;	
	private JPasswordField PwTextF_Login;
	private JPanel contentPane;
	RoundedButton LoginBtn;
	
	MemberDAO dao = new MemberDAO();
	MemberDTO dto = MemberDTO.getInstance();

	public LoginMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 423, 700);
		setResizable(false);
		setTitle("로그인 페이지");
		contentPane = (JPanel) getContentPane();
		ImageIcon ii = new ImageIcon("Image/BlueSky.gif");
		//ImageIcon ii = new ImageIcon("Image/Night3.gif");
		ImageIcon i2 = new ImageIcon("Image/Logo.png");
		JLabel Logo_login = new JLabel();
		Logo_login.setBounds(180, -75, 500, 455);
		Logo_login.setIcon(i2);
		contentPane.add(Logo_login);
		
		
		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//contentPane.setBackground(new Color(108, 180, 255));
		

		IdTextF_Login = new JTextField();
		IdTextF_Login.setBounds(120, 470, 116, 21);
		contentPane.add(IdTextF_Login);

		PwTextF_Login = new JPasswordField();
		PwTextF_Login.setBounds(120, 502, 116, 21);
		contentPane.add(PwTextF_Login);

		// 엔터 로그인 이벤트
		PwTextF_Login.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();
				if (key == KeyEvent.VK_ENTER) {
					LoginBtn.doClick();
				}
			}
		});

		LoginBtn = new RoundedButton("로그인");
		LoginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dto.setId(IdTextF_Login.getText());
				dto.setPw(PwTextF_Login.getText());
				int result = dao.LoginCheck(dto);
				if (result == 1) {
					//JOptionPane.showMessageDialog(null, "로그인 성공");
					setVisible(false);
					new MainFrame().setLocationRelativeTo(null);;
				} else if (result == 2) {
					JOptionPane.showMessageDialog(null, "관리자 로그인 성공");
					dispose();
					//new AdminFrame().setLocationRelativeTo(null);;
					new AdminPage.AdminFrame().main(null);;
				} else {
					JOptionPane.showMessageDialog(null, "로그인 실패 비밀번호를 확인해 주세요");
				}
			}
		});

		LoginBtn.setBounds(30, 546, 97, 23);		
		contentPane.add(LoginBtn);
		setVisible(true);

		RoundedButton JoinBtn_Login = new RoundedButton("회원가입");
		JoinBtn_Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new JoinMain().main(null);
			}
		});
		JoinBtn_Login.setBounds(139, 546, 97, 23);

		contentPane.add(JoinBtn_Login);

		JLabel IdLabel_Login = new JLabel("ID");
		IdLabel_Login.setBounds(36, 475, 57, 15);
		IdLabel_Login.setFont(new Font("굴림", Font.BOLD, 15));
		contentPane.add(IdLabel_Login);

		JLabel PwLabel_Login = new JLabel("Password");
		PwLabel_Login.setBounds(36, 507, 80, 15);
		PwLabel_Login.setFont(new Font("굴림", Font.BOLD, 15));
		contentPane.add(PwLabel_Login);
		JLabel imageLabel = new JLabel();
		imageLabel.setSize(470, 700);
		imageLabel.setIcon(ii);
		contentPane.add(imageLabel);
	}

	public void contentSet() {
		String id = IdTextF_Login.getText();
		String pw = PwTextF_Login.getText();
	}

}