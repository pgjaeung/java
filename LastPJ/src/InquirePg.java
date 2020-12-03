import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class InquirePg extends JFrame implements ActionListener{
	//=======================================================
	JOptionPane m = new JOptionPane();
	private JPanel contentPane;
	private JTextField title_t;
	JTextArea content_t;
	JTextField inquiry_t;
	static String driver, url;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static String tmpstr;
	JButton inquire_b, cancel_b;
	static int count = 1;
//=======================================================
	public InquirePg() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 483, 325);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\uBB38\uC758\uD558\uAE30");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		lblNewLabel.setBounds(24, 22, 72, 23);
		contentPane.add(lblNewLabel);
		
		title_t = new JTextField();
		title_t.setBounds(98, 62, 326, 21);
		contentPane.add(title_t);
		title_t.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\uC81C\uBAA9");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(24, 62, 50, 18);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uB0B4\uC6A9");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1_1.setFont(new Font("굴림", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(24, 98, 50, 18);
		contentPane.add(lblNewLabel_1_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(98, 98, 326, 143);
		contentPane.add(scrollPane);
		
		content_t = new JTextArea();
		scrollPane.setViewportView(content_t);
		
		inquire_b = new JButton("\uBB38\uC758\uD558\uAE30");
		inquire_b.setBounds(131, 255, 91, 23);
		inquire_b.addActionListener(this);
		contentPane.add(inquire_b);
		
		cancel_b = new JButton("\uCDE8\uC18C");
		cancel_b.setBounds(270, 255, 91, 23);
		cancel_b.addActionListener(this);
		contentPane.add(cancel_b);
		
		inquiry_t = new JTextField();
		inquiry_t.setBounds(349, 12, 50, 21);
		contentPane.add(inquiry_t);
		inquiry_t.setColumns(10);
		
		JLabel num_l = new JLabel("\uBB38\uC758\uBC88\uD638");
		num_l.setBounds(299, 11, 61, 23);
		contentPane.add(num_l);
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
        String sql = "Select * From inquiry";
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
					InquirePg frame = new InquirePg();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
		});
		dbConnect();
		viewData();
		dbDis();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==inquire_b) {
			dbConnect();
			try {
				if(rs.next()) {
					query("insert", "insert into inquiry values ('"+title_t.getText()+"','"+content_t.getText()+"','"+null+"','"+inquiry_t.getText()+"')");
					m.showMessageDialog(null, "문의완료!");
				}

			}catch(Exception e1) {
				e1.getStackTrace();
			}
			dbDis();
			inquiry_t.setText("");
			title_t.setText("");
			content_t.setText("");
		}
		if(e.getSource()==cancel_b) {
			dispose();
		}
	}
	
}
