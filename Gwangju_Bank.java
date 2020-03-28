import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;




public class Gwangju_Bank {

	
	public String sql;	//sql 명령어 입력을 위한 STRING변수 선언
	private JFrame frame;
	public JTextField txtPW;		// 로그인시에 패스워드 텍스트  필드
	public JTextField txtID;		// 로그인시에 아이디 텍스트 필드
	
	public Connection conn = null;	
	
	private String colName[] = { "이 름", "주민번호", "전화번호", "주 소","신용등급" };
	private String colName1[] = { "은행코드", "주민번호", "계 좌", "잔 고", "통장타입", "등록일", "해지일", "비밀번호"};
	private String colName2[] = { "이 름", "주민번호", "대출번호", "대출금액", "대출타입", "대출일", "만기일"};
	private String colName3[] = { "이 름", "계좌번호","카드번호", "카드타입", "비밀번호"};
	
	private DefaultTableModel model = new DefaultTableModel(colName, 0);
	private DefaultTableModel model1 = new DefaultTableModel(colName1, 0);
	private DefaultTableModel model2 = new DefaultTableModel(colName2, 0);
	private DefaultTableModel model3 = new DefaultTableModel(colName3, 0);
	
	private JTable table = new JTable(model);
	private JTable table1 = new JTable(model1);
	private JTable table2 = new JTable(model2);
	private JTable table3 = new JTable(model3);
	
	private String row[] = new String[8];
	
	
	
	//////////////////////DB에 connect 하는 함수///////////////////////////
	public void connect() {
		
		try {
			
			//DB연결
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 로그인 프레임에서 텍스트 필드의 값(ID, PASSWORD)을 받아와서 DB와 연결
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", txtID.getText(),txtPW.getText());
			System.out.println("성공적 로딩");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//////////////////////////////레코드 출력 select 함수//////////////////////////////////////////
	public void show_db() { 
		try { 
			// Quary 
			Statement stmt = conn.createStatement();
			
			// 계정에 csv/txt파일을 통해 데이터를 삽입한 테이블로 이름을 설정하시기 바랍니다.  ex) from <테이블 명>
			ResultSet rset = stmt.executeQuery("select cname, ssn, cphone, caddr, credit from customer");
			
			System.out.println("이 름\t주민번호\t전화번호 \t주소\t신용등급");
			
			// 각 칼럼의 좌우 사이즈를 설정
			table.getColumn("이 름").setPreferredWidth(35);
			table.getColumn("주민번호").setPreferredWidth(110);
			table.getColumn("전화번호").setPreferredWidth(110);
			table.getColumn("주 소").setPreferredWidth(150);
			table.getColumn("신용등급").setPreferredWidth(20);
			
			
			while (rset.next()) { 
				// row 값 읽기 
				for (int i = 1; i < 6; i++) { 
					System.out.print(rset.getString(i) + "\t"); 
					row[i - 1] = rset.getString(i);
				} 
				System.out.println();
				// JTable에 출력
				model.addRow(row); 
			}
			
			} catch (SQLException e) { 
				e.printStackTrace(); 
			}
	}
	//////////////////////////////////////////////////////////
	public void show_db1() { 
		try { 
			// Quary 
			Statement stmt = conn.createStatement();
			
			// 계정에 csv/txt파일을 통해 데이터를 삽입한 테이블로 이름을 설정하시기 바랍니다.  ex) from <테이블 명>
			ResultSet rset = stmt.executeQuery("select branch_no, ssn, acct_no, balance, atype, register_date, retire_date, password from account");
			
			System.out.println("은행코드/t주민번호/t계 좌/t잔 고/t통장타입/t등록일/t해지일/t비밀번호");
			
			// 각 칼럼의 좌우 사이즈를 설정
			table1.getColumn("은행코드").setPreferredWidth(5);
			table1.getColumn("주민번호").setPreferredWidth(100);
			table1.getColumn("계 좌").setPreferredWidth(90);
			table1.getColumn("잔 고").setPreferredWidth(110);
			table1.getColumn("통장타입").setPreferredWidth(60);
			table1.getColumn("등록일").setPreferredWidth(60);
			table1.getColumn("해지일").setPreferredWidth(60);
			table1.getColumn("비밀번호").setPreferredWidth(20);
			
			
			while (rset.next()) { 
				// row 값 읽기 
				for (int i = 1; i < 9; i++) { 
					System.out.print(rset.getString(i) + "\t"); 
					row[i - 1] = rset.getString(i);
				} 
				System.out.println();
				// JTable에 출력
				model1.addRow(row); 
			}
			
			} catch (SQLException e) { 
				e.printStackTrace(); 
			}
	}
	public void show_db2() { 
		try { 
			// Quary 
			Statement stmt = conn.createStatement();
			
			// 계정에 csv/txt파일을 통해 데이터를 삽입한 테이블로 이름을 설정하시기 바랍니다.  ex) from <테이블 명>
			ResultSet rset = stmt.executeQuery("select customer.cname,customer.ssn, loan_no, amount, ltype, loan_date, by_date "
					+ "from loan, customer where loan.ssn = customer.ssn");
			
			System.out.println("이 름/주민번호/t대출번호/t대출금액/t대출타입/t대출일/t만기일");
			
			// 각 칼럼의 좌우 사이즈를 설정
			table2.getColumn("이 름").setPreferredWidth(40);
			table2.getColumn("주민번호").setPreferredWidth(110);
			table2.getColumn("대출번호").setPreferredWidth(10);
			table2.getColumn("대출금액").setPreferredWidth(100);
			table2.getColumn("대출타입").setPreferredWidth(70);
			table2.getColumn("대출일").setPreferredWidth(70);
			table2.getColumn("만기일").setPreferredWidth(70);

			
			
			while (rset.next()) { 
				// row 값 읽기 
				for (int i = 1; i < 8; i++) { 
					System.out.print(rset.getString(i) + "\t"); 
					row[i - 1] = rset.getString(i);
				} 
				System.out.println();
				// JTable에 출력
				model2.addRow(row); 
			}
			
			} catch (SQLException e) { 
				e.printStackTrace(); 
			}
	}
	
	public void show_db3() { 
		try { 
			// Quary 
			Statement stmt = conn.createStatement();
			
			// 계정에 csv/txt파일을 통해 데이터를 삽입한 테이블로 이름을 설정하시기 바랍니다.  ex) from <테이블 명>
			ResultSet rset = stmt.executeQuery("select customer.cname, acct_no, card.card_no, card.ctype, card.password "
					+ "from card, customer where card.ssn = customer.ssn");
			
			System.out.println("이 름\t계좌번호\t카드번호\t카드타입\t비밀번호");
			
			// 각 칼럼의 좌우 사이즈를 설정
			table3.getColumn("이 름").setPreferredWidth(20);
			table3.getColumn("계좌번호").setPreferredWidth(100);
			table3.getColumn("카드번호").setPreferredWidth(20);
			table3.getColumn("카드타입").setPreferredWidth(40);
			table3.getColumn("비밀번호").setPreferredWidth(20);

			
			
			while (rset.next()) { 
				// row 값 읽기 
				for (int i = 1; i < 6; i++) { 
					System.out.print(rset.getString(i) + "\t"); 
					row[i - 1] = rset.getString(i);
				} 
				System.out.println();
				// JTable에 출력
				model3.addRow(row); 
			}
			
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
					Gwangju_Bank window = new Gwangju_Bank();
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
	public Gwangju_Bank() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 855, 536);
		frame.setLocationRelativeTo(null);			//프레임이 가운데 위치
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// Frame창을 닫았을 때 실행 종료
		frame.getContentPane().setLayout(null);
		frame.setSize(1072,571);
		
		ImageIcon icon = new ImageIcon("광주은행.png");		// 로그인 화면 배경
		ImageIcon iconS = new ImageIcon("광주은행2.png");		// 검색 창 에서의 화면 배경
		
		JPanel panel_1 = new JPanel(){
			public void paintComponent(Graphics g) {
			Dimension d = getSize();
			g.drawImage(iconS.getImage(), 0, 0, d.width, d.height,null);
			setOpaque(false);
			super.paintComponent(g);
		}
	};
		
		
		JPanel panel_3 = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(iconS.getImage(), 0, 0, d.width, d.height, null);
			}
		};
																	

		panel_1.setBounds(0, 0, 1054, 524);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		panel_1.setVisible(false);
		
		JPanel Pinfo1 = new JPanel() ;
		Pinfo1.setBounds(196, 0, 858, 524);
		panel_1.add(Pinfo1);
		Pinfo1.setBackground(Color.WHITE);
		Pinfo1.setLayout(null);
				table.setCellSelectionEnabled(true);
				table.setBackground(Color.WHITE);
				table.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
				
				

				//SELECT 패널 및 버튼

				
						// JScrollPane : 화면에 넘어갈 경우 스크롤바가 생김
						JScrollPane scrollPane_1 = new JScrollPane(table);
						scrollPane_1.setBounds(0, 12, 846, 433);
						Pinfo1.add(scrollPane_1);
						table.setPreferredScrollableViewportSize(new Dimension(700, 400));
						table.setVisible(true);
								JButton Insert1 = new JButton("\uC218 \uC815");
								Insert1.setBounds(718, 457, 103, 31);
								Pinfo1.add(Insert1);
								Insert1.setFont(new Font("HY견명조", Font.BOLD, 16));
								
										/////////////////////////////////// INSERT ///////////////////////////////////
								
										// INSERT 버튼을 눌렀을 때 액션
										Insert1.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												new subframe2();
											}
										});
										
										JButton Select1 = new JButton("\uAC80 \uC0C9");
										Select1.setBounds(572, 457, 97, 31);
										Pinfo1.add(Select1);
										Select1.setFont(new Font("HY견고딕", Font.PLAIN, 16));
										
												// SELECT버튼을 클릭했을 때 액션을 설정
												Select1.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
														DefaultTableModel model = (DefaultTableModel) table.getModel();
														model.setNumRows(0);
														show_db();
													}
												});
		
		JPanel Pinfo2 = new JPanel();
		Pinfo2.setBackground(Color.WHITE);
		Pinfo2.setBounds(196, 0, 844, 524);
		panel_1.add(Pinfo2);
		
		table1.setCellSelectionEnabled(true);
		table1.setBackground(Color.WHITE);
		table1.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		Pinfo2.setLayout(null);
		
		JScrollPane scrollPane_3 = new JScrollPane(table1);
		scrollPane_3.setBounds(14, 12, 830, 433);
		Pinfo2.add(scrollPane_3);
		table1.setPreferredScrollableViewportSize(new Dimension(844,400));
		
		JButton insert2 = new JButton("\uC218 \uC815");
		insert2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Account_In();
			}
		});
		insert2.setFont(new Font("HY견고딕", Font.BOLD, 16));
		insert2.setBounds(718, 457, 103, 31);
		Pinfo2.add(insert2);
		
		JButton select2 = new JButton("\uAC80 \uC0C9");
		select2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model1 = (DefaultTableModel) table1.getModel();
				model1.setNumRows(0);
				show_db1();
			}
		});
		select2.setFont(new Font("HY견고딕", Font.PLAIN, 16));
		select2.setBounds(572, 457, 97, 31);
		Pinfo2.add(select2);
		table1.setVisible(true);
		
		JPanel Pinfo3 = new JPanel();
		Pinfo3.setBackground(Color.WHITE);
		Pinfo3.setBounds(193, 0, 847, 524);
		panel_1.add(Pinfo3);
		
		table2.setCellSelectionEnabled(true);
		table2.setBackground(Color.WHITE);
		table2.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		Pinfo3.setLayout(null);
		
		JScrollPane scrollPane_2 = new JScrollPane(table2);
		scrollPane_2.setBounds(72, 5, 702, 433);
		Pinfo3.add(scrollPane_2);
		table2.setPreferredScrollableViewportSize(new Dimension(700,400));
		
		JButton insert3 = new JButton("\uC218 \uC815");
		insert3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Loan_In();
			}
		});
		insert3.setFont(new Font("HY견고딕", Font.BOLD, 16));
		insert3.setBounds(718, 457, 103, 31);
		Pinfo3.add(insert3);
		
		JButton select3 = new JButton("\uAC80 \uC0C9");
		select3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
				model2.setNumRows(0);
				show_db2();
			}
		});
		select3.setFont(new Font("HY견고딕", Font.BOLD, 16));
		select3.setBounds(572, 457, 97, 31);
		Pinfo3.add(select3);
		table2.setVisible(true);
		
		JPanel Pinfo4 = new JPanel();
		Pinfo4.setBackground(Color.WHITE);
		Pinfo4.setBounds(190, 0, 850, 524);
		panel_1.add(Pinfo4);
		Pinfo4.setLayout(null);
		table3.setFont(new Font("함초롬돋움", Font.PLAIN, 15));
		
		JScrollPane scrollPane = new JScrollPane(table3);
		scrollPane.setBounds(124, 5, 602, 433);
		Pinfo4.add(scrollPane);
		table3.setPreferredScrollableViewportSize(new Dimension(600,400));
		
		JButton insert4 = new JButton("\uC218 \uC815");
		insert4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Card_In();
			}
		});
		insert4.setFont(new Font("HY견고딕", Font.BOLD, 16));
		insert4.setBounds(718, 464, 103, 31);
		Pinfo4.add(insert4);
		
		JButton select4 = new JButton("\uAC80 \uC0C9");
		select4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				DefaultTableModel model3 = (DefaultTableModel) table3.getModel();
				model3.setNumRows(0);
				show_db3();
			}
		});
		select4.setFont(new Font("HY견고딕", Font.BOLD, 16));
		select4.setBounds(572, 463, 97, 31);
		Pinfo4.add(select4);
		table3.setVisible(true);
		
		
		JButton c_info2 = new JButton("\uACE0\uAC1D \uACC4\uC88C,\uCE74\uB4DC \uC815\uBCF4");
		c_info2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pinfo1.setVisible(false);
				Pinfo3.setVisible(false);
				Pinfo4.setVisible(false);
				Pinfo2.setVisible(true);
			}
		});
		
		
		panel_3.setBounds(0, 0, 204, 166);
		panel_1.add(panel_3);
		c_info2.setFont(new Font("함초롬돋움", Font.BOLD, 16));
		c_info2.setBounds(0, 266, 186, 35);
		panel_1.add(c_info2);
		
		JButton c_info3 = new JButton("\uACE0\uAC1D \uB300\uCD9C\uD604\uD669");
		c_info3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pinfo1.setVisible(false);
				Pinfo2.setVisible(false);
				Pinfo4.setVisible(false);
				Pinfo3.setVisible(true);
			}
		});
		c_info3.setFont(new Font("함초롬돋움", Font.BOLD, 16));
		c_info3.setBounds(0, 313, 186, 35);
		panel_1.add(c_info3);
		
		JButton c_info1 = new JButton("\uACE0\uAC1D \uAC1C\uC778\uC815\uBCF4");
		c_info1.setBounds(0, 220, 186, 35);
		panel_1.add(c_info1);
		c_info1.setFont(new Font("함초롬돋움", Font.BOLD, 16));
		
		JButton c_info4 = new JButton("\uACE0\uAC1D \uCE74\uB4DC\uC815\uBCF4");
		c_info4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pinfo1.setVisible(false);
				Pinfo3.setVisible(false);
				Pinfo2.setVisible(false);
				Pinfo4.setVisible(true);
			}
		});
		c_info4.setFont(new Font("함초롬돋움", Font.BOLD, 16));
		c_info4.setBounds(0, 360, 186, 35);
		panel_1.add(c_info4);
		
		
		//////////////////////////////////// LOGIN////////////////////////////////////
		//로그인할 떄의 프레임
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1054, 524);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
				JLabel lblNewLabel = new JLabel("ID :");
				lblNewLabel.setBounds(40, 285, 62, 18);
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel.setFont(new Font("굴림", Font.BOLD, 16));
				panel.add(lblNewLabel);
				
						JLabel lblNewLabel_1 = new JLabel("Password :");
						lblNewLabel_1.setBounds(0, 325, 102, 18);
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
						lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 16));
						panel.add(lblNewLabel_1);
						
								// 로그인 프레임에서 LOG IN 버튼 클릭시 액션 - connect 함수 호출
								JButton btnNewButton = new JButton("Log in");
								btnNewButton.setBounds(45, 380, 259, 43);
								btnNewButton.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										connect();
										panel.setVisible(false);
										panel_1.setVisible(true);
										frame.setSize(1072, 571);
									}
								});
								
										btnNewButton.setFont(new Font("Sitka Small", Font.BOLD, 27));
										panel.add(btnNewButton);
										
												// 패스워드 입력할 텍스트 필드를 password 필드로 (별로 출력되도록) 설정
												txtPW = new JPasswordField();
												txtPW.setBounds(112, 320, 227, 31);
												panel.add(txtPW);
												txtPW.setColumns(10);
												
														// ID 입력할 텍스트 필드
														txtID = new JTextField();
														txtID.setBounds(112, 280, 227, 31);
														panel.add(txtID);
														txtID.setColumns(10);
														
																	// 로그인 후 보이는 2번째 패널에 이미지 삽입
																	JPanel panel_2 = new JPanel() {
																		public void paintComponent(Graphics g) {
																			Dimension d = getSize();
																			g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
																		}
																	};
																	panel_2.setBounds(0, 0, 1054, 524);
																	panel.add(panel_2);
																	panel_2.setLayout(null);
		
		c_info1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Pinfo2.setVisible(false);
				Pinfo3.setVisible(false);
				Pinfo4.setVisible(false);
				Pinfo1.setVisible(true);
			}
		});
	}
}

