import java.awt.Color;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class LoginMain extends JFrame implements ActionListener{
	JScrollPane scrollPane;
	ImageIcon icon;
	static JButton signup_b,login_b;
	static JTextField id_t, pwd_t;
	JOptionPane m = new JOptionPane();
	static String driver, url;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static String tmpstr;
	static long count = 0;
	MainPage mp = new MainPage();
	Member mem = new Member("회원가입");
	static String[] adminid = {"jaeung"};
	static String[] adminpwd = {"1234"};

	/**
	 * Create the frame.
	 */
	public LoginMain() {
		icon = new ImageIcon("C:/Users/ASUS/eclipse-workspace/TestWB/img/tom.jpg");
		
		setTitle("로그인 화면");
		setSize(537, 250);
		
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				 g.drawImage(icon.getImage(), 0, 0, null);
				 setOpaque(false);
				 super.paintComponent(g);
			}
		};
		
		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);
		background.setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("농수축산물 물가 정보 시스템");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBackground(new Color(152, 251, 152));
		lblNewLabel.setFont(new Font("돋움체", Font.BOLD, 22));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(105, 10, 337, 51);
		background.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("굴림", Font.BOLD, 15));
		lblNewLabel_1.setBounds(69, 98, 22, 15);
		background.add(lblNewLabel_1);
		
		id_t = new JTextField();
		id_t.setColumns(10);
		id_t.setBounds(134, 92, 203, 21);
		background.add(id_t);
		
		JLabel lblPwd = new JLabel("PWD");
		lblPwd.setForeground(Color.ORANGE);
		lblPwd.setFont(new Font("굴림", Font.BOLD, 15));
		lblPwd.setBounds(69, 129, 43, 15);
		background.add(lblPwd);
		
		pwd_t = new JTextField();
		pwd_t.setColumns(10);
		pwd_t.setBounds(134, 123, 203, 21);
		background.add(pwd_t);
		
		login_b = new JButton("로그인");
		login_b.setForeground(Color.WHITE);
		login_b.setBackground(Color.ORANGE);
		login_b.addActionListener(this);
		login_b.setBounds(364, 94, 78, 50);
		background.add(login_b);
		
		JLabel lblNewLabel_2 = new JLabel("\uC544\uC9C1 \uD68C\uC6D0\uC774 \uC544\uB2C8\uB77C\uBA74..?  >>");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBackground(Color.ORANGE);
		lblNewLabel_2.setBounds(134, 154, 161, 15);
		background.add(lblNewLabel_2);
		
		signup_b = new JButton("회원가입");
		signup_b.setBackground(Color.ORANGE);
		signup_b.setForeground(Color.WHITE);
		signup_b.addActionListener(this);
		signup_b.setBounds(292, 152, 91, 19);
		background.add(signup_b);
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
		LoginMain frame = new LoginMain();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(537, 250);
		frame.setVisible(true);
		dbConnect();
		query("select","select id,pwd from member where id like'"+id_t.getText()+"'");
		count = 1;
		viewData();
		dbDis();
		
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==login_b) {
			dbConnect();
			try {
				query("select","select id,pwd from member where id='"+id_t.getText() + "'");
				if(rs.next()) {
					if(id_t.getText().equals(rs.getString("id"))) {
						if(pwd_t.getText().equals(rs.getString("pwd"))) {
							JOptionPane.showMessageDialog(null, "로그인 완료!");
							mp.setVisible(true);
							dispose();
						}
						else JOptionPane.showMessageDialog(null, "비밀번호가 틀림!");
					}
				}
				else if(!rs.next()) {
					 if(id_t.getText().equals("root")) {
							if(pwd_t.getText().equals("apmsetup")) {
								JOptionPane.showMessageDialog(null, "관리자 로그인!");
								AdminPg ap = new AdminPg();
								ap.setVisible(true);
								dispose();
								return;
							}
						}
					JOptionPane.showMessageDialog(null, "아이디가 틀림!");
				}
				else
					JOptionPane.showMessageDialog(null, "오류");
				
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			dbDis();
		}
		//============================================================================
		if(e.getSource()==signup_b) {
			mem.getContentPane();
			mem.setVisible(true);
		}
	}
}
