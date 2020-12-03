import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import java.awt.Color;

public class AdminPg extends JFrame implements ActionListener {

	private JPanel admainPane;
	JButton answer_b, mainpage_b, search_b, edit_b, add_b, drop_b, searchMem_b,
	edit_b_1,drop_b_1;
	static String driver, url;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static String tmpstr;
	static long count = 0;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminPg frame = new AdminPg();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public AdminPg() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 361, 300);
		admainPane = new JPanel();
		admainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		admainPane.setLayout(null);
		setContentPane(admainPane);
		
		JLabel lblNewLabel = new JLabel("\uAD00\uB9AC\uC790 \uD654\uBA74");
		lblNewLabel.setFont(new Font("돋움", Font.BOLD, 17));
		lblNewLabel.setBounds(12, 10, 115, 28);
		admainPane.add(lblNewLabel);
		
		add_b = new JButton("\uD488\uBAA9\uCD94\uAC00");
		add_b.setForeground(Color.WHITE);
		add_b.setBackground(Color.BLACK);
		add_b.addActionListener(this);
		add_b.setFont(new Font("돋움체", Font.PLAIN, 12));
		add_b.setBounds(34, 140, 81, 28);
		admainPane.add(add_b);
		
		edit_b = new JButton("\uD488\uBAA9\uC218\uC815");
		edit_b.setForeground(Color.WHITE);
		edit_b.setBackground(Color.BLACK);
		edit_b.addActionListener(this);
		edit_b.setFont(new Font("돋움체", Font.PLAIN, 12));
		edit_b.setBounds(127, 140, 81, 28);
		admainPane.add(edit_b);
		
		drop_b = new JButton("\uD488\uBAA9\uC0AD\uC81C");
		drop_b.setForeground(Color.WHITE);
		drop_b.setBackground(Color.BLACK);
		drop_b.addActionListener(this);
		drop_b.setFont(new Font("돋움체", Font.PLAIN, 12));
		drop_b.setBounds(220, 140, 81, 28);
		admainPane.add(drop_b);
		
		searchMem_b = new JButton("\uD68C\uC6D0\uC815\uBCF4\uC870\uD68C");
		searchMem_b.setForeground(Color.WHITE);
		searchMem_b.addActionListener(this);
		searchMem_b.setBackground(Color.BLACK);
		searchMem_b.setFont(new Font("돋움체", Font.PLAIN, 12));
		searchMem_b.setBounds(186, 58, 115, 43);
		admainPane.add(searchMem_b);
		
		mainpage_b = new JButton("\uBA54\uC778\uD398\uC774\uC9C0");
		mainpage_b.setForeground(Color.WHITE);
		mainpage_b.setBackground(Color.BLACK);
		mainpage_b.addActionListener(this);
		mainpage_b.setFont(new Font("돋움체", Font.PLAIN, 12));
		mainpage_b.setBounds(34, 58, 115, 43);
		admainPane.add(mainpage_b);
		
		answer_b = new JButton("\uBB38\uC758\uAD00\uB9AC");
		answer_b.setForeground(Color.WHITE);
		answer_b.addActionListener(this);
		answer_b.setBackground(Color.BLACK);
		answer_b.setFont(new Font("돋움체", Font.PLAIN, 12));
		answer_b.setBounds(34, 180, 81, 28);
		admainPane.add(answer_b);
		
		edit_b_1 = new JButton("\uD68C\uC6D0\uC218\uC815");
		edit_b_1.setForeground(Color.WHITE);
		edit_b_1.addActionListener(this);
		edit_b_1.setBackground(Color.BLACK);
		edit_b_1.setFont(new Font("돋움체", Font.PLAIN, 12));
		edit_b_1.setBounds(127, 180, 81, 28);
		admainPane.add(edit_b_1);
		
		drop_b_1 = new JButton("\uD68C\uC6D0\uC0AD\uC81C");
		drop_b_1.setForeground(Color.WHITE);
		drop_b_1.setBackground(Color.BLACK);
		drop_b_1.addActionListener(this);
		drop_b_1.setFont(new Font("돋움체", Font.PLAIN, 12));
		drop_b_1.setBounds(220, 180, 81, 28);
		admainPane.add(drop_b_1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==mainpage_b) {
			MainPage mg = new MainPage();
			mg.setVisible(true);
			dispose();
		}
		else if(e.getSource()==searchMem_b) {
			try {
				MemManagement mm = new MemManagement();
				mm.dbConnect();
				mm.viewData();
				mm.query("select", "select * from member	");
				mm.setVisible(true);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		
		}
		else if(e.getSource()==add_b) {
			AddPr ap = new AddPr();
			ap.setVisible(true);
		}
		else if(e.getSource()==edit_b) {
			EditPr ep = new EditPr();
			ep.setVisible(true);
		}
		else if(e.getSource()==drop_b) {
			DelPr dp = new DelPr();
			dp.setVisible(true);
		}
		else if(e.getSource()==answer_b) {
			try {
				InquireList iq = new InquireList();
				iq.dbConnect();
				iq.viewData();
				iq.setVisible(true);
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==edit_b_1) {
			EditMem em = new EditMem();
			em.setVisible(true);
		}
		else if(e.getSource()==drop_b_1) {
			DelMem dm = new DelMem();
			dm.setVisible(true);
		}
	}
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
//        String sql = "Select * From productList";
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
//				System.out.println("rs.next()");
//				
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
}
