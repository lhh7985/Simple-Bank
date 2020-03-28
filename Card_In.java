import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Card_In extends Gwangju_Bank{

	private JFrame frame;
	private JTextField branch_no;
	private JTextField ssn;
	private JTextField acct_no;
	private JTextField card_no;
	private JTextField ctype;
	private JTextField password;

	public void connect() {
		try {
			// DB연결
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 로그인할 ID를 입력하시오
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hh", "1234");
			System.out.println("성공적 로딩");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	////////////////////////////////데이터 입력 함수///////////////////////////////////
	public void DataIn() {
			// Quary
		try {
			Statement stmt = conn.createStatement();

			// 각 텍스트 필드의 값을 받아와 새로운 레코드를 추가
			sql = "insert into card (branch_no, ssn, acct_no, card_no, ctype, password)" + " values('"
					+ branch_no.getText() + "', '" + ssn.getText() + "', '" + acct_no.getText() + "', '"
					+ card_no.getText() + "', '" + ctype.getText() + "', '" + password.getText() + "')";
			System.out.println(sql);

			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt > 0 ? "등록 성공" : "등록 실패");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	///////////////////////////////대출액 변경/////////////////////////////////
	public void Update_password() {
		try {
			Statement stmt = conn.createStatement();

			// 학번 필드에 있는 값을 받아와서 그에 해당하는 레코드의 폰번호를 텍스트 필드에 입력된 값으로 업데이트
			sql = "update card set password = '" + password.getText() + "' where acct_no = '" + acct_no.getText()+ " '";

			System.out.println(sql);

			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt > 0 ? "갱신 성공" : "갱신 실패");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/////////////////////////////////데이터 삭제 함수////////////////////////////////////
	public void DataDel() {
		try {
			Statement stmt = conn.createStatement();

			// 텍스트 필드로 이름을 입력 받아서 해당하는 레코드를 삭제
			sql = "delete from card where card_no = '" + card_no.getText() + "'";

			System.out.println(sql);

			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt > 0 ? "삭제 성공" : "삭제 실패");
		} catch (SQLException e) {
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
					Card_In window = new Card_In();
					window.frame.setTitle("카드정보 수정");
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
	public Card_In() {
		connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 723, 421);
		frame.setTitle("카드정보 수정");
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("\uC740\uD589\uCF54\uB4DC");
		lblNewLabel.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel.setBounds(14, 48, 88, 18);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("\uC8FC\uBBFC\uBC88\uD638");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_1.setBounds(232, 48, 98, 18);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("*\uACC4 \uC88C");
		lblNewLabel_2.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(40, 128, 62, 18);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("\uCE74\uB4DC\uBC88\uD638");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_3.setBounds(14, 204, 88, 18);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("\uCE74\uB4DC\uD0C0\uC785");
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_4.setBounds(232, 204, 85, 18);
		frame.getContentPane().add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("\uBE44\uBC00\uBC88\uD638");
		lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_5.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		lblNewLabel_5.setBounds(455, 204, 73, 18);
		frame.getContentPane().add(lblNewLabel_5);

		JButton Insert = new JButton("\uC785 \uB825");
		Insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataIn();
			}
		});
		Insert.setFont(new Font("함초롬바탕", Font.BOLD, 17));
		Insert.setBounds(409, 314, 105, 27);
		frame.getContentPane().add(Insert);

		branch_no = new JTextField();
		branch_no.setBounds(116, 45, 87, 24);
		frame.getContentPane().add(branch_no);
		branch_no.setColumns(10);

		ssn = new JTextField();
		ssn.setBounds(344, 47, 289, 24);
		frame.getContentPane().add(ssn);
		ssn.setColumns(10);

		acct_no = new JTextField();
		acct_no.setBounds(116, 125, 246, 24);
		frame.getContentPane().add(acct_no);
		acct_no.setColumns(10);

		card_no = new JTextField();
		card_no.setBounds(116, 201, 116, 24);
		frame.getContentPane().add(card_no);
		card_no.setColumns(10);

		ctype = new JTextField();
		ctype.setBounds(324, 201, 116, 24);
		frame.getContentPane().add(ctype);
		ctype.setColumns(10);

		password = new JTextField();
		password.setBounds(530, 201, 116, 24);
		frame.getContentPane().add(password);
		password.setColumns(10);

		JButton Delete = new JButton("\uC0AD \uC81C");
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataDel();
			}
		});
		Delete.setFont(new Font("함초롬바탕", Font.BOLD, 17));
		Delete.setBounds(541, 314, 105, 27);
		frame.getContentPane().add(Delete);

		JButton Update_password = new JButton("\uBE44\uBC00\uBC88\uD638 \uBCC0\uACBD");
		Update_password.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update_password();
			}
		});
		Update_password.setFont(new Font("함초롬바탕", Font.BOLD, 16));
		Update_password.setBounds(99, 317, 148, 27);
		frame.getContentPane().add(Update_password);
		
		JLabel lblNewLabel_6 = new JLabel("\uC8FC\uC758 - \uB370\uC774\uD130 \uC218\uC815 \uC2DC * \uD544\uC218 \uC785\uB825, \uC0AD\uC81C \uC2DC \uCE74\uB4DC\uBC88\uD638 \uC785\uB825");
		lblNewLabel_6.setBounds(56, 356, 384, 18);
		frame.getContentPane().add(lblNewLabel_6);

		frame.setResizable(false);
		frame.setVisible(true);
	}
}
