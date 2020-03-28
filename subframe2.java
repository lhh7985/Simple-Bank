import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class subframe2 extends Gwangju_Bank{

	private JFrame subframe2;
	private JTextField cname;
	private JTextField c_ssn;
	private JTextField cphone;
	private JTextField c_caddr;
	private JTextField credit;

	
	
	public void connect() {
		try {
			//DB����
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// �α����� ID�� �Է��Ͻÿ�
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hh", "1234");
			System.out.println("������ �ε�");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void DataIn() {
		
		try {
			Statement stmt = conn.createStatement();
			// �� �ؽ�Ʈ �ʵ��� ���� �޾ƿ� ���ο� ���ڵ带 �߰�
			sql = "insert into customer (cname, ssn, cphone, caddr, credit)"
					+ " values('" +cname.getText() + "', '" + c_ssn.getText() + "', '"+ cphone.getText() + "', '" 
					+  c_caddr.getText() + "', '" + credit.getText() + "')";
			System.out.println(sql);

			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt > 0 ? "��� ����" : "��� ����");
		}

		catch (SQLException e) {
			e.printStackTrace();
		}
		

	}
	///////////////////////////////��ȣ ����/////////////////////////////////
	public void Update_cphone() {	
		
		try {
			 Statement stmt = conn.createStatement();
			
			// �й� �ʵ忡 �ִ� ���� �޾ƿͼ� �׿� �ش��ϴ� ���ڵ��� ����ȣ�� �ؽ�Ʈ �ʵ忡 �Էµ� ������ ������Ʈ
			//sql = "update customer set caddr = '����� ��ȵ�' where ssn = '960914-1369795'";
			sql = "update customer set cphone = '" + cphone.getText() + "' where ssn = '" + c_ssn.getText() +"'"+  "and cname = '" + cname.getText() + "'";
			
			System.out.println(sql);

			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt > 0 ? "���� ����" : "���� ����");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	///////////////////////////////�ܰ� ����/////////////////////////////////
	public void Update_caddr() {	
		
		try {
			 Statement stmt = conn.createStatement();
			
			// �й� �ʵ忡 �ִ� ���� �޾ƿͼ� �׿� �ش��ϴ� ���ڵ��� ����ȣ�� �ؽ�Ʈ �ʵ忡 �Էµ� ������ ������Ʈ
			//sql = "update customer set caddr = '����� ��ȵ�' where ssn = '960914-1369795'";
			sql = "update customer set caddr = '" + c_caddr.getText() + "' where ssn = '" + c_ssn.getText() +"'"+ "and cname = '" + cname.getText() + "'";;
			
			System.out.println(sql);

			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt > 0 ? "���� ����" : "���� ����");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	///////////////////////////////�ܰ� ����/////////////////////////////////
	public void Update_credit() {	
		
		try {
			 Statement stmt = conn.createStatement();
			
			// �й� �ʵ忡 �ִ� ���� �޾ƿͼ� �׿� �ش��ϴ� ���ڵ��� ����ȣ�� �ؽ�Ʈ �ʵ忡 �Էµ� ������ ������Ʈ
			//sql = "update customer set caddr = '����� ��ȵ�' where ssn = '960914-1369795'";
			sql = "update customer set credit = '" + credit.getText() + "' where ssn = '" + c_ssn.getText() +"'"+"and cname = '" + cname.getText() + "'";
			
			System.out.println(sql);

			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt > 0 ? "���� ����" : "���� ����");
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/////////////////////////////////������ ���� �Լ�////////////////////////////////////
	public void DataDel() {
		try {
			Statement stmt = conn.createStatement();
			
			// �ؽ�Ʈ �ʵ�� �̸��� �Է� �޾Ƽ� �ش��ϴ� ���ڵ带 ����
			sql = "delete from customer where ssn = '" + c_ssn.getText() + "'";
			
			System.out.println(sql);

			int cnt = stmt.executeUpdate(sql);
			System.out.println(cnt > 0 ? "���� ����" : "���� ����");
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
					subframe2 window = new subframe2();
					window.subframe2.setTitle("�������� ����");
					window.subframe2.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public subframe2() {
		connect();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		subframe2 = new JFrame();
		subframe2.setBounds(100, 100, 667, 435);
		subframe2.setTitle("�������� ����");
		//subframe2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		subframe2.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("*\uC774 \uB984");
		lblNewLabel.setFont(new Font("���ʷҹ���", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(49, 59, 62, 18);
		subframe2.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("*\uC8FC\uBBFC\uBC88\uD638");
		lblNewLabel_1.setFont(new Font("���ʷҹ���", Font.BOLD, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(269, 59, 87, 18);
		subframe2.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC804\uD654\uBC88\uD638");
		lblNewLabel_2.setFont(new Font("���ʷҹ���", Font.BOLD, 16));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(34, 129, 77, 18);
		subframe2.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\uC8FC \uC18C");
		lblNewLabel_3.setFont(new Font("���ʷҹ���", Font.BOLD, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(49, 205, 62, 18);
		subframe2.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\uC2E0\uC6A9\uB4F1\uAE09");
		lblNewLabel_4.setFont(new Font("���ʷҹ���", Font.BOLD, 16));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(34, 276, 77, 18);
		subframe2.getContentPane().add(lblNewLabel_4);
		
		cname = new JTextField();
		cname.setBounds(125, 56, 116, 24);
		subframe2.getContentPane().add(cname);
		cname.setColumns(10);
		
		c_ssn = new JTextField();
		c_ssn.setBounds(370, 56, 218, 24);
		subframe2.getContentPane().add(c_ssn);
		c_ssn.setColumns(10);
		
		cphone = new JTextField();
		cphone.setBounds(125, 126, 191, 24);
		subframe2.getContentPane().add(cphone);
		cphone.setColumns(10);
		
		c_caddr = new JTextField();
		c_caddr.setBounds(125, 202, 361, 24);
		subframe2.getContentPane().add(c_caddr);
		c_caddr.setColumns(10);
		
		credit = new JTextField();
		credit.setBounds(125, 273, 69, 24);
		subframe2.getContentPane().add(credit);
		credit.setColumns(10);
		
		JButton Insert = new JButton("\uC785 \uB825");
		Insert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataIn();
			}
		});
		Insert.setFont(new Font("���ʷҹ���", Font.BOLD, 16));
		Insert.setBounds(363, 332, 105, 27);
		subframe2.getContentPane().add(Insert);
		
		JButton Delete = new JButton("\uC0AD \uC81C");
		Delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DataDel();
			}
		});
		Delete.setFont(new Font("���ʷҹ���", Font.BOLD, 17));
		Delete.setBounds(504, 331, 105, 27);
		subframe2.getContentPane().add(Delete);
		
		JButton Update_phone = new JButton("\uBCC0\uACBD");
		Update_phone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update_cphone();
			}
		});
		Update_phone.setFont(new Font("���ʷҹ���", Font.BOLD, 16));
		Update_phone.setBounds(344, 127, 69, 27);
		subframe2.getContentPane().add(Update_phone);
		
		JButton Update_addr = new JButton("\uBCC0\uACBD");
		Update_addr.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update_caddr();
			}
		});
		Update_addr.setFont(new Font("���ʷҹ���", Font.BOLD, 16));
		Update_addr.setBounds(504, 203, 69, 27);
		subframe2.getContentPane().add(Update_addr);
		
		JButton Update_credit = new JButton("\uBCC0\uACBD");
		Update_credit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update_credit();
			}
		});
		Update_credit.setFont(new Font("���ʷҹ���", Font.BOLD, 16));
		Update_credit.setBounds(208, 274, 69, 27);
		subframe2.getContentPane().add(Update_credit);
		
		JLabel label = new JLabel("\uC8FC\uC758 - \uB370\uC774\uD130 \uC218\uC815 \uC2DC * \uD544\uC218 \uC785\uB825");
		label.setBounds(49, 371, 332, 18);
		subframe2.getContentPane().add(label);
		
		subframe2.setResizable(false);
		subframe2.setVisible(true);
		
	}
}
