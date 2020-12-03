//import java.awt.BorderLayout;
//import java.awt.EventQueue;
//
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//import javax.swing.border.EmptyBorder;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.SwingConstants;
//import java.awt.Font;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import javax.swing.JTextField;
//import javax.swing.JButton;
//
//public class LoginMainPage extends JFrame implements ActionListener{
//	JOptionPane m = new JOptionPane();
//	private JPanel contentPane;
//	private static JTextField id_t;
//	private static JTextField pwd_t;
//	static JButton login_b, signup_b;
//	static String driver, url;
//	static Connection conn;
//	static Statement stmt;
//	static ResultSet rs;
//	static String tmpstr;
//	static long count = 0;
//	MainPage mp = new MainPage();
//	Member mem = new Member("회원가입");
//	
//	
//	public LoginMainPage() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 452, 241);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setLayout(null);
//		setContentPane(contentPane);
//		
//		JLabel lblNewLabel = new JLabel("\uB85C\uADF8\uC778 \uD398\uC774\uC9C0");
//		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 16));
//		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		lblNewLabel.setBounds(149, 10, 136, 43);
//		contentPane.add(lblNewLabel);
//		
//		JLabel lblNewLabel_1 = new JLabel("ID");
//		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 15));
//		lblNewLabel_1.setBounds(38, 80, 22, 15);
//		contentPane.add(lblNewLabel_1);
//		
//		id_t = new JTextField();
//		id_t.setColumns(10);
//		id_t.setBounds(103, 74, 203, 21);
//		contentPane.add(id_t);
//		
//		JLabel lblPwd = new JLabel("PWD");
//		lblPwd.setFont(new Font("굴림", Font.PLAIN, 15));
//		lblPwd.setBounds(38, 111, 43, 15);
//		contentPane.add(lblPwd);
//		
//		pwd_t = new JTextField();
//		pwd_t.setColumns(10);
//		pwd_t.setBounds(103, 105, 203, 21);
//		contentPane.add(pwd_t);
//		
//		login_b = new JButton("로그인");
//		login_b.addActionListener(this);
//		login_b.setBounds(333, 76, 78, 50);
//		contentPane.add(login_b);
//		
//		JLabel lblNewLabel_2 = new JLabel("\uC544\uC9C1 \uD68C\uC6D0\uC774 \uC544\uB2C8\uB77C\uBA74..?  >>");
//		lblNewLabel_2.setBounds(103, 136, 161, 15);
//		contentPane.add(lblNewLabel_2);
//		
//		signup_b = new JButton("회원가입");
//		signup_b.addActionListener(this);
//		signup_b.setBounds(261, 134, 91, 19);
//		contentPane.add(signup_b);
//	}
//	public static void dbConnect() {
//    	driver = "sun.jdbc.odbc.JdbcOdbcDriver";
//    	try{
//    		Class.forName("com.mysql.jdbc.Driver");
//    		System.out.println("드라이버 검색 성공!");        
//    	}catch(ClassNotFoundException e){
//    		System.err.println("error = " + e);
//    	}
//        
//    	
//        url = "jdbc:odbc:pricesearch";
//        conn = null;
//        stmt = null;
//        rs = null;
//        String url = "jdbc:mysql://localhost/pricesearch";
//        String sql = "Select * From member";
//		try {
//         
//            conn = DriverManager.getConnection(url,"root","apmsetup");
//
//            stmt = conn.createStatement( );
//
//            rs = stmt.executeQuery(sql);
//            
//            System.out.println("데이터베이스 연결 성공!");            
//         
//        }
//        catch(Exception e) {
//            System.out.println("데이터베이스 연결 실패!");
//        }
//	}
//	public static void query(String order, String sql) throws SQLException {
//		if (order == "select") {
//			rs = stmt.executeQuery(sql);
//		} 
//		else {
//			stmt.executeUpdate(sql);
//		}
//	}
//
//	public static void viewData() throws SQLException {
//		if(!rs.next()){
//			System.out.println("!rs.next()");
//			count--;
//		}
//		else{		
//			System.out.println("rs.next()");
//		}
//	}
//
//	public static void dbDis(){
//		try {
//			if (conn != null)
//				conn.close();
//			if (stmt != null)
//				stmt.close();
//			System.out.println("데이터베이스 연결 해제!");
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//	}
//	public static void main(String[] args) throws SQLException {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					LoginMainPage frame = new LoginMainPage();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		dbConnect();
//		query("select","select id,pwd from member where id like'"+id_t.getText()+"'");
//		count = 1;
//		viewData();
//		dbDis();
//		
//	}
//	public void actionPerformed(ActionEvent e) {
//		if(e.getSource()==login_b) {
//			dbConnect();
//			try {
//				query("select","select id,pwd from member where id='"+id_t.getText() + "'");
//				if(rs.next()) {
//					if(id_t.getText().equals(rs.getString("id"))) {
//						if(pwd_t.getText().equals(rs.getString("pwd"))) {
//							JOptionPane.showMessageDialog(null, "로그인 완료!");
//							mp.setVisible(true);
//						}
//						else JOptionPane.showMessageDialog(null, "비밀번호가 틀림!");
//					}
//				}
//				else if(!rs.next()) {
//					JOptionPane.showMessageDialog(null, "아이디가 틀림!");
//				}
//				else
//					JOptionPane.showMessageDialog(null, "오류");
//			}catch(Exception e1) {
//				e1.printStackTrace();
//			}
//			dbDis();
//		}
//		//============================================================================
//		if(e.getSource()==signup_b) {
//			mem.getContentPane();
//			mem.setVisible(true);
//		}
//	}
//}