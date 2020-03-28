import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Account_In extends Gwangju_Bank {

	private JFrame frame;
	private JTextField branch_no;
	private JTextField ssn;
	private JTextField acct_no;
	private JTextField balance;
	private JTextField atype;
	private JTextField register_date;
	private JTextField retire_date;
	private JTextField password;

	private String sql;
	
	
	
	
	public void connect() {
		try {
			//DB연결
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 로그인할 ID를 입력하시오
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hh", "1234");
			System.out.println("성공적 로딩");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	
	////////////////////////////////데이터 입력 함수///////////////////////////////////
	public void DataIn() {
		// Quary
		try {
			Statement stmt = conn.createStatement();

			// 각 텍스트 필드의 값을 받아와 새로운 레코드를 추가
			sql = "insert into account (branch_no, ssn, acct_no, balance, atype, register_date, retire_date, password)"
					+ " values('" + branch_no.getText() + "', '" + ssn.getText() + "', '"+ acct_no.getText() + "', '\"" 
					+  balance.getText() + "\"', '" + atype.getText() + "', '" + register_date.getText() + "', '"
					+ retire_date.getText() + "'," + password.getText() + ")";
			System.out.println(sql);

			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt > 0 ? "등록 성공" : "등록 실패");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	
	///////////////////////////////잔고 변경/////////////////////////////////
	public void Update_balance() {					//폰 바꿨니?
		try {
			Statement stmt = conn.createStatement();
			
			// 학번 필드에 있는 값을 받아와서 그에 해당하는 레코드의 폰번호를 텍스트 필드에 입력된 값으로 업데이트
			sql = "update account set balance = '" + balance.getText() + "' where acct_no = '" + acct_no.getText() + "'";
			
			System.out.println(sql);

			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt > 0 ? "갱신 성공" : "갱신 실패");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	///////////////////////////////비밀번호 변경/////////////////////////////////
	public void Update_password() {					//폰 바꿨니?
		try {
			Statement stmt = conn.createStatement();
			
			// 학번 필드에 있는 값을 받아와서 그에 해당하는 레코드의 폰번호를 텍스트 필드에 입력된 값으로 업데이트
			sql = "update account set password = '" + password.getText() + "' where acct_no = '" + acct_no.getText() + "'";
			
			System.out.println(sql);

			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt > 0 ? "갱신 성공" : "갱신 실패");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/////////////////////////////////데이터 삭제 함수////////////////////////////////////
	public void DataDel() {
		try {
			Statement stmt = conn.createStatement();
			
			// 텍스트 필드로 이름을 입력 받아서 해당하는 레코드를 삭제
			sql = "delete from account where acct_no = '" + acct_no.getText() + "'";
			
			System.out.println(sql);

			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt > 0 ? "삭제 성공" : "삭제 실패");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Account_In window = new Account_In();
					window.frame.setTitle("계좌정보 수정");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Account_In() {
		connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 749, 491);
		frame.setTitle("계좌정보 수정");
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC740\uD589\uCF54\uB4DC");
		lblNewLabel.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(14, 44, 87, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\uC8FC\uBBFC\uBC88\uD638");
		lblNewLabel_1.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_1.setBounds(237, 44, 87, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("*\uACC4 \uC88C");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_2.setBounds(28, 117, 87, 18);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uC794 \uACE0");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_3.setBounds(39, 180, 62, 18);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uD1B5\uC7A5\uD0C0\uC785");
		lblNewLabel_4.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(14, 240, 87, 18);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\uB4F1\uB85D\uC77C");
		lblNewLabel_5.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_5.setBounds(262, 240, 62, 18);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\uD574\uC9C0\uC77C");
		lblNewLabel_6.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_6.setBounds(482, 240, 62, 18);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_7.setBounds(14, 320, 87, 18);
		frame.getContentPane().add(lblNewLabel_7);
		
		branch_no = new JTextField();
		branch_no.setBounds(104, 41, 73, 24);
		frame.getContentPane().add(branch_no);
		branch_no.setColumns(10);
		
		ssn = new JTextField();
		ssn.setBounds(338, 41, 244, 24);
		frame.getContentPane().add(ssn);
		ssn.setColumns(10);
		
		acct_no = new JTextField();
		acct_no.setBounds(104, 116, 264, 24);
		frame.getContentPane().add(acct_no);
		acct_no.setColumns(10);
		
		balance = new JTextField();
		balance.setBounds(104, 179, 348, 24);
		frame.getContentPane().add(balance);
		balance.setColumns(10);
		
		atype = new JTextField();
		atype.setBounds(104, 237, 116, 24);
		frame.getContentPane().add(atype);
		atype.setColumns(10);
		
		register_date = new JTextField();
		register_date.setBounds(318, 237, 116, 24);
		frame.getContentPane().add(register_date);
		register_date.setColumns(10);
		
		retire_date = new JTextField();
		retire_date.setBounds(539, 237, 116, 24);
		frame.getContentPane().add(retire_date);
		retire_date.setColumns(10);
		
		password = new JTextField();
		password.setBounds(104, 317, 87, 24);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		JButton Insert = new JButton("\uC785 \uB825");
		Insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DataIn();
			}
		});
		
		Insert.setFont(new Font("함초롬바탕", Font.BOLD, 18));
		Insert.setBounds(434, 376, 105, 27);
		frame.getContentPane().add(Insert);
		
		JButton Delete = new JButton("\uC0AD \uC81C");
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataDel();
			}
		});
		Delete.setFont(new Font("함초롬바탕", Font.BOLD, 18));
		Delete.setBounds(575, 376, 105, 27);
		frame.getContentPane().add(Delete);
		
		JButton Update_balance = new JButton("\uBCC0\uACBD");
		Update_balance.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update_balance();
			}
		});
		Update_balance.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		Update_balance.setBounds(482, 178, 73, 27);
		frame.getContentPane().add(Update_balance);
		
		JButton Update_password = new JButton("\uBCC0\uACBD");
		Update_password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update_password();
			}
		});
		Update_password.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		Update_password.setBounds(213, 318, 73, 27);
		frame.getContentPane().add(Update_password);
		
		JLabel label = new JLabel("\uC8FC\uC758 - \uB370\uC774\uD130 \uC218\uC815 \uC2DC * \uD544\uC218 \uC785\uB825");
		label.setBounds(39, 426, 332, 18);
		frame.getContentPane().add(label);
		
		frame.setResizable(false);
		frame.setVisible(true);
		
	}
}
