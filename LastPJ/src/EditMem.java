import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class EditMem extends JFrame implements ActionListener{
	JOptionPane m = new JOptionPane();
	private JPanel contentPane;
	private JTextField id_t;
	private JTextField pwd_t;
	private JTextField pwdcheck_t;
	private JTextField name_t;
	private JTextField telNo_t;
	static String driver, url;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static String tmpstr;
	static long count = 0;
	static JButton edit_b,load_b,back_b;
	/**
	 * Launch the application.
	 */
	public EditMem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 346);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("회원정보수정");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		lblNewLabel.setBounds(38, 10, 111, 40);
		contentPane.add(lblNewLabel);
		
		JLabel id_l = new JLabel("ID");
		id_l.setHorizontalAlignment(SwingConstants.RIGHT);
		id_l.setFont(new Font("굴림", Font.PLAIN, 14));
		id_l.setBounds(151, 62, 50, 15);
		contentPane.add(id_l);
		
		JLabel pwd_l = new JLabel("PWD");
		pwd_l.setHorizontalAlignment(SwingConstants.RIGHT);
		pwd_l.setFont(new Font("굴림", Font.PLAIN, 14));
		pwd_l.setBounds(82, 93, 118, 22);
		contentPane.add(pwd_l);
		
		id_t = new JTextField();
		id_t.setColumns(10);
		id_t.setBounds(213, 59, 96, 22);
		contentPane.add(id_t);
		
		load_b = new JButton("load");
		load_b.setBounds(311, 58, 55, 23);
		load_b.setFont(new Font("굴림", Font.PLAIN, 9));
		load_b.addActionListener(this);
		contentPane.add(load_b);
		
		pwd_t = new JTextField();
		pwd_t.setColumns(10);
		pwd_t.setBounds(212, 93, 153, 22);
		contentPane.add(pwd_t);
		
		pwdcheck_t = new JTextField();
		pwdcheck_t.setColumns(10);
		pwdcheck_t.setBounds(212, 126, 153, 22);
		contentPane.add(pwdcheck_t);
		
		JLabel pwdcheck = new JLabel("PWDCHECK");
		pwdcheck.setHorizontalAlignment(SwingConstants.RIGHT);
		pwdcheck.setFont(new Font("굴림", Font.PLAIN, 14));
		pwdcheck.setBounds(82, 125, 118, 22);
		contentPane.add(pwdcheck);
		
		JLabel lblName = new JLabel("NAME");
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("굴림", Font.PLAIN, 14));
		lblName.setBounds(82, 158, 118, 22);
		contentPane.add(lblName);
		
		name_t = new JTextField();
		name_t.setColumns(10);
		name_t.setBounds(212, 159, 153, 22);
		contentPane.add(name_t);
		
		edit_b = new JButton("\uC218\uC815");
		edit_b.setBounds(177, 241, 91, 23);
		edit_b.addActionListener(this);
		contentPane.add(edit_b);
		
		JLabel lblTelno = new JLabel("TelNo");
		lblTelno.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelno.setFont(new Font("굴림", Font.PLAIN, 14));
		lblTelno.setBounds(82, 195, 118, 22);
		contentPane.add(lblTelno);
		
		telNo_t = new JTextField();
		telNo_t.setColumns(10);
		telNo_t.setBounds(212, 196, 153, 22);
		contentPane.add(telNo_t);
		
		back_b = new JButton("뒤로가기");
		back_b.setBounds(311, 241, 91, 23);
		back_b.addActionListener(this);
		contentPane.add(back_b);
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
					EditMem frame = new EditMem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		dbConnect();
//		query("select", "select * from productList where pdid = 1");
		count = 1;
		viewData();
		dbDis();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == edit_b) {
			dbConnect();
			try {
				query("update", "update member set id  = '" + id_t.getText() + "' , pwd = '" + pwd_t.getText() +"' , pwdcheck = '" 
						+ pwdcheck_t.getText() +"' , name = '" + name_t.getText() +"' , telNo = '" + telNo_t.getText() +
						"' where id = '" + id_t.getText() + "'");
				m.showMessageDialog(null, "데이터 수정 완료");
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			id_t.setText("");
			pwd_t.setText("");
			pwdcheck_t.setText("");
			name_t.setText("");
			telNo_t.setText("");
			dbDis();
		}
		//======================================================================
		else if(e.getSource()==load_b) {
			dbConnect();
			try {
				query("select", "select * from member where id = '" +id_t.getText() + "'");
				while(rs.next()) {
						if(id_t.getText().equals(rs.getString("id"))) {
							id_t.setText(rs.getString("id"));
							pwd_t.setText(rs.getString("pwd"));
							pwdcheck_t.setText(rs.getString("pwdcheck"));
							name_t.setText(rs.getString("name"));
							telNo_t.setText(rs.getString("telNo"));
						}
						m.showMessageDialog(null, "데이터 로드");
				}
				viewData();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			dbDis();
		}
		//======================================================================
		else if(e.getSource()==back_b) {
			dispose();
		}
	}

}
