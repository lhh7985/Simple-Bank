import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Loan_In extends Gwangju_Bank{

	private JFrame frame;
	private JTextField branch_no;
	private JTextField loan_no;
	private JTextField ssn;
	private JTextField amount;
	private JTextField ltype;
	private JTextField loan_date;
	private JTextField by_date;

	
	
	
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
			sql = "insert into loan (branch_no, loan_no, ssn, amount, ltype, loan_date, by_date)"
					+ " values('" + branch_no.getText() + "', '" + loan_no.getText() + "', '"+ ssn.getText() + "', '\"" 
					+  amount.getText() + "\"', '" + ltype.getText() + "', '" + loan_date.getText() + "', '"
					+ by_date.getText() + "')";
			System.out.println(sql);

			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt > 0 ? "등록 성공" : "등록 실패");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	///////////////////////////////대출액 변경/////////////////////////////////
	public void Update_amount() {					
		try {
			Statement stmt = conn.createStatement();
			
			// 학번 필드에 있는 값을 받아와서 그에 해당하는 레코드의 폰번호를 텍스트 필드에 입력된 값으로 업데이트
			sql = "update loan set amount = '" + amount.getText() + "' where loan_no = '" + loan_no.getText()+ "'";
			
			System.out.println(sql);

			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt > 0 ? "갱신 성공" : "갱신 실패");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	///////////////////////////////대출 타입 변경/////////////////////////////////
	public void Update_type() {					
		try {
			Statement stmt = conn.createStatement();
			
			// 학번 필드에 있는 값을 받아와서 그에 해당하는 레코드의 폰번호를 텍스트 필드에 입력된 값으로 업데이트
			sql = "update loan set ltype = '" + ltype.getText() + "' where loan_no = '" + loan_no.getText()+ "'";
			
			System.out.println(sql);

			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt > 0 ? "갱신 성공" : "갱신 실패");
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	///////////////////////////////대출 날짜 및 납기일 변경/////////////////////////////////
	public void Update_date() {					
		try {
			Statement stmt = conn.createStatement();
			
			// 학번 필드에 있는 값을 받아와서 그에 해당하는 레코드의 폰번호를 텍스트 필드에 입력된 값으로 업데이트
			sql = "update loan set loan_date = '" + loan_date.getText() + "', by_date ='"+ by_date.getText() + "' where loan_no = '" + loan_no.getText()+ "'";
			
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
			sql = "delete from loan where loan_no = '" + loan_no.getText() + "'";
			
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
					Loan_In window = new Loan_In();
					window.frame.setTitle("대출정보 수정");
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
	public Loan_In() {
		connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 702, 445);
		frame.setTitle("대출정보 수정");
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC740\uD589\uBC88\uD638");
		lblNewLabel.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel.setBounds(29, 59, 75, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("*\uB300\uCD9C\uBC88\uD638");
		lblNewLabel_1.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_1.setBounds(174, 59, 86, 18);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC8FC\uBBFC\uBC88\uD638");
		lblNewLabel_2.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(337, 59, 82, 18);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uB300\uCD9C\uAE08\uC561");
		lblNewLabel_3.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_3.setBounds(29, 123, 75, 18);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uB300\uCD9C\uD0C0\uC785");
		lblNewLabel_4.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_4.setBounds(29, 191, 75, 18);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\uB300\uCD9C\uC77C");
		lblNewLabel_5.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_5.setBounds(29, 251, 75, 18);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("\uB9CC\uAE30\uC77C");
		lblNewLabel_6.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_6.setBounds(322, 253, 62, 18);
		frame.getContentPane().add(lblNewLabel_6);
		
		branch_no = new JTextField();
		branch_no.setBounds(102, 58, 62, 24);
		frame.getContentPane().add(branch_no);
		branch_no.setColumns(10);
		
		loan_no = new JTextField();
		loan_no.setBounds(263, 58, 62, 24);
		frame.getContentPane().add(loan_no);
		loan_no.setColumns(10);
		
		ssn = new JTextField();
		ssn.setBounds(432, 58, 217, 24);
		frame.getContentPane().add(ssn);
		ssn.setColumns(10);
		
		amount = new JTextField();
		amount.setBounds(102, 122, 368, 24);
		frame.getContentPane().add(amount);
		amount.setColumns(10);
		
		ltype = new JTextField();
		ltype.setBounds(102, 190, 158, 24);
		frame.getContentPane().add(ltype);
		ltype.setColumns(10);
		
		loan_date = new JTextField();
		loan_date.setBounds(102, 250, 116, 24);
		frame.getContentPane().add(loan_date);
		loan_date.setColumns(10);
		
		by_date = new JTextField();
		by_date.setBounds(398, 250, 116, 24);
		frame.getContentPane().add(by_date);
		by_date.setColumns(10);
		
		JButton Insert = new JButton("\uC785 \uB825");
		Insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataIn();
			}
		});
		Insert.setFont(new Font("함초롬바탕", Font.BOLD, 17));
		Insert.setBounds(398, 338, 105, 27);
		frame.getContentPane().add(Insert);
		
		JButton Delete = new JButton("\uC0AD \uC81C");
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataDel();
			}
		});
		Delete.setFont(new Font("함초롬바탕", Font.BOLD, 17));
		Delete.setBounds(517, 338, 105, 27);
		frame.getContentPane().add(Delete);
		
		JButton Update_amount = new JButton("\uBCC0\uACBD");
		Update_amount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update_amount();
			}
		});
		Update_amount.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		Update_amount.setBounds(492, 121, 75, 27);
		frame.getContentPane().add(Update_amount);
		
		JLabel label = new JLabel("\uC8FC\uC758 - \uB370\uC774\uD130 \uC218\uC815 \uC2DC * \uD544\uC218 \uC785\uB825, \uC0AD\uC81C \uC2DC \uB300\uCD9C\uBC88\uD638 \uC785\uB825");
		label.setBounds(53, 380, 381, 18);
		frame.getContentPane().add(label);
		
		JButton Update_date = new JButton("\uB0A0\uC9DC \uBCC0\uACBD");
		Update_date.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Update_date();
			}
		});
		Update_date.setFont(new Font("함초롬바탕", Font.BOLD, 15));
		Update_date.setBounds(544, 249, 105, 27);
		frame.getContentPane().add(Update_date);
		
		JButton btnNewButton = new JButton("\uBCC0\uACBD");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update_type();
			}
		});
		btnNewButton.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		btnNewButton.setBounds(296, 189, 88, 27);
		frame.getContentPane().add(btnNewButton);
		
		frame.setResizable(false);
		frame.setVisible(true);
	}
}
