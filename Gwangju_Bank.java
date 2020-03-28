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

	
	public String sql;	//sql ��ɾ� �Է��� ���� STRING���� ����
	private JFrame frame;
	public JTextField txtPW;		// �α��νÿ� �н����� �ؽ�Ʈ  �ʵ�
	public JTextField txtID;		// �α��νÿ� ���̵� �ؽ�Ʈ �ʵ�
	
	public Connection conn = null;	
	
	private String colName[] = { "�� ��", "�ֹι�ȣ", "��ȭ��ȣ", "�� ��","�ſ���" };
	private String colName1[] = { "�����ڵ�", "�ֹι�ȣ", "�� ��", "�� ��", "����Ÿ��", "�����", "������", "��й�ȣ"};
	private String colName2[] = { "�� ��", "�ֹι�ȣ", "�����ȣ", "����ݾ�", "����Ÿ��", "������", "������"};
	private String colName3[] = { "�� ��", "���¹�ȣ","ī���ȣ", "ī��Ÿ��", "��й�ȣ"};
	
	private DefaultTableModel model = new DefaultTableModel(colName, 0);
	private DefaultTableModel model1 = new DefaultTableModel(colName1, 0);
	private DefaultTableModel model2 = new DefaultTableModel(colName2, 0);
	private DefaultTableModel model3 = new DefaultTableModel(colName3, 0);
	
	private JTable table = new JTable(model);
	private JTable table1 = new JTable(model1);
	private JTable table2 = new JTable(model2);
	private JTable table3 = new JTable(model3);
	
	private String row[] = new String[8];
	
	
	
	//////////////////////DB�� connect �ϴ� �Լ�///////////////////////////
	public void connect() {
		
		try {
			
			//DB����
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// �α��� �����ӿ��� �ؽ�Ʈ �ʵ��� ��(ID, PASSWORD)�� �޾ƿͼ� DB�� ����
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", txtID.getText(),txtPW.getText());
			System.out.println("������ �ε�");
			
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//////////////////////////////���ڵ� ��� select �Լ�//////////////////////////////////////////
	public void show_db() { 
		try { 
			// Quary 
			Statement stmt = conn.createStatement();
			
			// ������ csv/txt������ ���� �����͸� ������ ���̺�� �̸��� �����Ͻñ� �ٶ��ϴ�.  ex) from <���̺� ��>
			ResultSet rset = stmt.executeQuery("select cname, ssn, cphone, caddr, credit from customer");
			
			System.out.println("�� ��\t�ֹι�ȣ\t��ȭ��ȣ \t�ּ�\t�ſ���");
			
			// �� Į���� �¿� ����� ����
			table.getColumn("�� ��").setPreferredWidth(35);
			table.getColumn("�ֹι�ȣ").setPreferredWidth(110);
			table.getColumn("��ȭ��ȣ").setPreferredWidth(110);
			table.getColumn("�� ��").setPreferredWidth(150);
			table.getColumn("�ſ���").setPreferredWidth(20);
			
			
			while (rset.next()) { 
				// row �� �б� 
				for (int i = 1; i < 6; i++) { 
					System.out.print(rset.getString(i) + "\t"); 
					row[i - 1] = rset.getString(i);
				} 
				System.out.println();
				// JTable�� ���
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
			
			// ������ csv/txt������ ���� �����͸� ������ ���̺�� �̸��� �����Ͻñ� �ٶ��ϴ�.  ex) from <���̺� ��>
			ResultSet rset = stmt.executeQuery("select branch_no, ssn, acct_no, balance, atype, register_date, retire_date, password from account");
			
			System.out.println("�����ڵ�/t�ֹι�ȣ/t�� ��/t�� ��/t����Ÿ��/t�����/t������/t��й�ȣ");
			
			// �� Į���� �¿� ����� ����
			table1.getColumn("�����ڵ�").setPreferredWidth(5);
			table1.getColumn("�ֹι�ȣ").setPreferredWidth(100);
			table1.getColumn("�� ��").setPreferredWidth(90);
			table1.getColumn("�� ��").setPreferredWidth(110);
			table1.getColumn("����Ÿ��").setPreferredWidth(60);
			table1.getColumn("�����").setPreferredWidth(60);
			table1.getColumn("������").setPreferredWidth(60);
			table1.getColumn("��й�ȣ").setPreferredWidth(20);
			
			
			while (rset.next()) { 
				// row �� �б� 
				for (int i = 1; i < 9; i++) { 
					System.out.print(rset.getString(i) + "\t"); 
					row[i - 1] = rset.getString(i);
				} 
				System.out.println();
				// JTable�� ���
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
			
			// ������ csv/txt������ ���� �����͸� ������ ���̺�� �̸��� �����Ͻñ� �ٶ��ϴ�.  ex) from <���̺� ��>
			ResultSet rset = stmt.executeQuery("select customer.cname,customer.ssn, loan_no, amount, ltype, loan_date, by_date "
					+ "from loan, customer where loan.ssn = customer.ssn");
			
			System.out.println("�� ��/�ֹι�ȣ/t�����ȣ/t����ݾ�/t����Ÿ��/t������/t������");
			
			// �� Į���� �¿� ����� ����
			table2.getColumn("�� ��").setPreferredWidth(40);
			table2.getColumn("�ֹι�ȣ").setPreferredWidth(110);
			table2.getColumn("�����ȣ").setPreferredWidth(10);
			table2.getColumn("����ݾ�").setPreferredWidth(100);
			table2.getColumn("����Ÿ��").setPreferredWidth(70);
			table2.getColumn("������").setPreferredWidth(70);
			table2.getColumn("������").setPreferredWidth(70);

			
			
			while (rset.next()) { 
				// row �� �б� 
				for (int i = 1; i < 8; i++) { 
					System.out.print(rset.getString(i) + "\t"); 
					row[i - 1] = rset.getString(i);
				} 
				System.out.println();
				// JTable�� ���
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
			
			// ������ csv/txt������ ���� �����͸� ������ ���̺�� �̸��� �����Ͻñ� �ٶ��ϴ�.  ex) from <���̺� ��>
			ResultSet rset = stmt.executeQuery("select customer.cname, acct_no, card.card_no, card.ctype, card.password "
					+ "from card, customer where card.ssn = customer.ssn");
			
			System.out.println("�� ��\t���¹�ȣ\tī���ȣ\tī��Ÿ��\t��й�ȣ");
			
			// �� Į���� �¿� ����� ����
			table3.getColumn("�� ��").setPreferredWidth(20);
			table3.getColumn("���¹�ȣ").setPreferredWidth(100);
			table3.getColumn("ī���ȣ").setPreferredWidth(20);
			table3.getColumn("ī��Ÿ��").setPreferredWidth(40);
			table3.getColumn("��й�ȣ").setPreferredWidth(20);

			
			
			while (rset.next()) { 
				// row �� �б� 
				for (int i = 1; i < 6; i++) { 
					System.out.print(rset.getString(i) + "\t"); 
					row[i - 1] = rset.getString(i);
				} 
				System.out.println();
				// JTable�� ���
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
		frame.setLocationRelativeTo(null);			//�������� ��� ��ġ
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		// Frameâ�� �ݾ��� �� ���� ����
		frame.getContentPane().setLayout(null);
		frame.setSize(1072,571);
		
		ImageIcon icon = new ImageIcon("��������.png");		// �α��� ȭ�� ���
		ImageIcon iconS = new ImageIcon("��������2.png");		// �˻� â ������ ȭ�� ���
		
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
				table.setFont(new Font("���ʷҵ���", Font.PLAIN, 15));
				
				

				//SELECT �г� �� ��ư

				
						// JScrollPane : ȭ�鿡 �Ѿ ��� ��ũ�ѹٰ� ����
						JScrollPane scrollPane_1 = new JScrollPane(table);
						scrollPane_1.setBounds(0, 12, 846, 433);
						Pinfo1.add(scrollPane_1);
						table.setPreferredScrollableViewportSize(new Dimension(700, 400));
						table.setVisible(true);
								JButton Insert1 = new JButton("\uC218 \uC815");
								Insert1.setBounds(718, 457, 103, 31);
								Pinfo1.add(Insert1);
								Insert1.setFont(new Font("HY�߸���", Font.BOLD, 16));
								
										/////////////////////////////////// INSERT ///////////////////////////////////
								
										// INSERT ��ư�� ������ �� �׼�
										Insert1.addActionListener(new ActionListener() {
											public void actionPerformed(ActionEvent e) {
												new subframe2();
											}
										});
										
										JButton Select1 = new JButton("\uAC80 \uC0C9");
										Select1.setBounds(572, 457, 97, 31);
										Pinfo1.add(Select1);
										Select1.setFont(new Font("HY�߰��", Font.PLAIN, 16));
										
												// SELECT��ư�� Ŭ������ �� �׼��� ����
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
		table1.setFont(new Font("���ʷҵ���", Font.PLAIN, 15));
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
		insert2.setFont(new Font("HY�߰��", Font.BOLD, 16));
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
		select2.setFont(new Font("HY�߰��", Font.PLAIN, 16));
		select2.setBounds(572, 457, 97, 31);
		Pinfo2.add(select2);
		table1.setVisible(true);
		
		JPanel Pinfo3 = new JPanel();
		Pinfo3.setBackground(Color.WHITE);
		Pinfo3.setBounds(193, 0, 847, 524);
		panel_1.add(Pinfo3);
		
		table2.setCellSelectionEnabled(true);
		table2.setBackground(Color.WHITE);
		table2.setFont(new Font("���ʷҵ���", Font.PLAIN, 15));
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
		insert3.setFont(new Font("HY�߰��", Font.BOLD, 16));
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
		select3.setFont(new Font("HY�߰��", Font.BOLD, 16));
		select3.setBounds(572, 457, 97, 31);
		Pinfo3.add(select3);
		table2.setVisible(true);
		
		JPanel Pinfo4 = new JPanel();
		Pinfo4.setBackground(Color.WHITE);
		Pinfo4.setBounds(190, 0, 850, 524);
		panel_1.add(Pinfo4);
		Pinfo4.setLayout(null);
		table3.setFont(new Font("���ʷҵ���", Font.PLAIN, 15));
		
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
		insert4.setFont(new Font("HY�߰��", Font.BOLD, 16));
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
		select4.setFont(new Font("HY�߰��", Font.BOLD, 16));
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
		c_info2.setFont(new Font("���ʷҵ���", Font.BOLD, 16));
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
		c_info3.setFont(new Font("���ʷҵ���", Font.BOLD, 16));
		c_info3.setBounds(0, 313, 186, 35);
		panel_1.add(c_info3);
		
		JButton c_info1 = new JButton("\uACE0\uAC1D \uAC1C\uC778\uC815\uBCF4");
		c_info1.setBounds(0, 220, 186, 35);
		panel_1.add(c_info1);
		c_info1.setFont(new Font("���ʷҵ���", Font.BOLD, 16));
		
		JButton c_info4 = new JButton("\uACE0\uAC1D \uCE74\uB4DC\uC815\uBCF4");
		c_info4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pinfo1.setVisible(false);
				Pinfo3.setVisible(false);
				Pinfo2.setVisible(false);
				Pinfo4.setVisible(true);
			}
		});
		c_info4.setFont(new Font("���ʷҵ���", Font.BOLD, 16));
		c_info4.setBounds(0, 360, 186, 35);
		panel_1.add(c_info4);
		
		
		//////////////////////////////////// LOGIN////////////////////////////////////
		//�α����� ���� ������
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1054, 524);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
				JLabel lblNewLabel = new JLabel("ID :");
				lblNewLabel.setBounds(40, 285, 62, 18);
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel.setFont(new Font("����", Font.BOLD, 16));
				panel.add(lblNewLabel);
				
						JLabel lblNewLabel_1 = new JLabel("Password :");
						lblNewLabel_1.setBounds(0, 325, 102, 18);
						lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
						lblNewLabel_1.setFont(new Font("����", Font.BOLD, 16));
						panel.add(lblNewLabel_1);
						
								// �α��� �����ӿ��� LOG IN ��ư Ŭ���� �׼� - connect �Լ� ȣ��
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
										
												// �н����� �Է��� �ؽ�Ʈ �ʵ带 password �ʵ�� (���� ��µǵ���) ����
												txtPW = new JPasswordField();
												txtPW.setBounds(112, 320, 227, 31);
												panel.add(txtPW);
												txtPW.setColumns(10);
												
														// ID �Է��� �ؽ�Ʈ �ʵ�
														txtID = new JTextField();
														txtID.setBounds(112, 280, 227, 31);
														panel.add(txtID);
														txtID.setColumns(10);
														
																	// �α��� �� ���̴� 2��° �гο� �̹��� ����
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

