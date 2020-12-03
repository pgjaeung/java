import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;
import javax.swing.JOptionPane;

public class Member extends JFrame implements ActionListener{
	
	JOptionPane m = new JOptionPane();
	static Label id, pwd, pwdcheck, name, telNo;
	static String driver, url;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static String tmpstr;
	static long count = 0;

	JPanel contentPane_1;
	JLabel lblPwdCheck;
	JLabel lblName;
	JLabel lblPhoneNumber;
	static JTextField id_t;
	static JTextField pwd_t;
	static JTextField pwdcheck_t;
	static JTextField name_t;
	static JTextField telNo_t;
	JButton join_b, cancel_b, checkid_b;

	public Member(String title) {
		super(title);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 463, 301);
		contentPane_1 = new JPanel();
		contentPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane_1.setLayout(null);
		
		setContentPane(contentPane_1);
		
		JLabel lblNewLabel_1 = new JLabel("\uD68C\uC6D0\uAC00\uC785 \uD654\uBA74");
		lblNewLabel_1.setFont(new Font("돋움", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(26, 10, 92, 15);
		contentPane_1.add(lblNewLabel_1);
		
	    join_b = new JButton("Join");
		join_b.addActionListener(this);
		join_b.setBackground(Color.GREEN);
		join_b.setBounds(105, 218, 91, 23);
		contentPane_1.add(join_b);
		
		cancel_b = new JButton("Cancel");
		cancel_b.addActionListener(this);
		cancel_b.setBackground(Color.ORANGE);
		cancel_b.setForeground(Color.BLACK);
		cancel_b.setBounds(253, 218, 91, 23);
		contentPane_1.add(cancel_b);
		
		checkid_b = new JButton("Check");
		checkid_b.addActionListener(this);
		checkid_b.setForeground(Color.WHITE);
		checkid_b.setBackground(Color.BLACK);
		checkid_b.setBounds(359, 34, 78, 23);
		contentPane_1.add(checkid_b);
		
		id_t = new JTextField();
		id_t.setBounds(143, 35, 203, 21);
		contentPane_1.add(id_t);
		id_t.setColumns(10);
		
		pwd_t = new JTextField();
		pwd_t.setColumns(10);
		pwd_t.setBounds(143, 66, 203, 21);
		contentPane_1.add(pwd_t);
		
		pwdcheck_t = new JTextField();
		pwdcheck_t.setColumns(10);
		pwdcheck_t.setBounds(143, 97, 203, 21);
		contentPane_1.add(pwdcheck_t);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 15));
		lblNewLabel.setBounds(26, 44, 22, 15);
		contentPane_1.add(lblNewLabel);
		
		JLabel lblPwd = new JLabel("PWD");
		lblPwd.setFont(new Font("굴림", Font.PLAIN, 15));
		lblPwd.setBounds(26, 75, 43, 15);
		contentPane_1.add(lblPwd);
		
		lblPwdCheck = new JLabel("PWD CHECK");
		lblPwdCheck.setFont(new Font("굴림", Font.PLAIN, 15));
		lblPwdCheck.setBounds(26, 103, 105, 15);
		contentPane_1.add(lblPwdCheck);
		
		lblName = new JLabel("Name");
		lblName.setFont(new Font("굴림", Font.PLAIN, 15));
		lblName.setBounds(26, 134, 105, 15);
		contentPane_1.add(lblName);
		
		name_t = new JTextField();
		name_t.setColumns(10);
		name_t.setBounds(143, 128, 203, 21);
		contentPane_1.add(name_t);
		
		lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setFont(new Font("굴림", Font.PLAIN, 15));
		lblPhoneNumber.setBounds(26, 165, 105, 15);
		contentPane_1.add(lblPhoneNumber);
		
		telNo_t = new JTextField();
		telNo_t.setColumns(10);
		telNo_t.setBounds(143, 159, 203, 21);
		contentPane_1.add(telNo_t);
		

		
		contentPane_1.setVisible(true);
	}
	public static void dbConnect() {
    	driver = "sun.jdbc.odbc.JdbcOdbcDriver";
    	try{
    		Class.forName("com.mysql.jdbc.Driver");
    		System.out.println("드라이버 검색 성공!");        
    	}catch(ClassNotFoundException e){
    		System.err.println("error = " + e);
    	}
        
    	
        url = "jdbc:odbc:pricesearch";
        conn = null;
        stmt = null;
        rs = null;
        String url = "jdbc:mysql://localhost/pricesearch";
        String sql = "Select * From member";
		try {
         
            conn = DriverManager.getConnection(url,"root","apmsetup");

            stmt = conn.createStatement( );

            rs = stmt.executeQuery(sql);
            
            System.out.println("데이터베이스 연결 성공!");            
         
        }
        catch(Exception e) {
            System.out.println("데이터베이스 연결 실패!");
        }
	}
	public static void query(String order, String sql) throws SQLException {
		if (order == "select") {
			rs = stmt.executeQuery(sql);
		} 
		else {
			stmt.executeUpdate(sql);
		}
	}

	public static void viewData() throws SQLException {
		if(!rs.next()){
			System.out.println("!rs.next()");
			count--;
		}
		else{		
				System.out.println("rs.next()");
				id_t.setText(rs.getString("id"));
				pwd_t.setText(rs.getString("pwd"));
				pwdcheck_t.setText(rs.getString("pwdcheck"));
				name_t.setText(rs.getString("name"));
				telNo_t.setText(rs.getString("telNo"));
		}
	}

	public static void dbDis(){
		try {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
			System.out.println("데이터베이스 연결 해제!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static void main(String[] args) throws SQLException {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Member frame = new Member("회원가입");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		dbConnect();
		query("select", "select * from member where num = 1");
		count = 1;
		viewData();
		dbDis();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == join_b) {
			dbConnect();
			try {
				query("insert", "insert into member values('"+id_t.getText()+"','"+pwd_t.getText()+"','"
			+pwdcheck_t.getText()+"','"+name_t.getText()+"','"+telNo_t.getText()+"')");

			} catch (Exception e1) {
				e1.printStackTrace();
			}			
			m.showMessageDialog(null, "회원가입 완료!");
			dbDis();
		} 
		if(e.getSource() == cancel_b) {
			dispose();
		}
		if(e.getSource() == checkid_b) {
			try {
				dbConnect();
				query("select","select id from member where id='"+id_t.getText() + "'");
					if(rs.next()) {
						if(rs.getString("id").equals(id_t.getText())) {
							m.showMessageDialog(null, "중복된 아이디");
						}
					}
					else if(!rs.next()) {
						m.showMessageDialog(null, "사용가능");
					}
					else {
						m.showMessageDialog(null, "오류");
					}

			}catch(Exception e2) {
				e2.printStackTrace();
			}
			dbDis();
		}
		
	}

}
