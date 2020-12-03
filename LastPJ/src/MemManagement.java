import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;


public class MemManagement extends JFrame implements ActionListener{
	JOptionPane m = new JOptionPane();
	private JPanel contentPane;
	
	static String[] head = new String[] {"ID", "PWD","NAME","TelNo"};
	static DefaultTableModel model1 = new DefaultTableModel(head,0);
	
	private JTable table;
	static String driver, url;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static String tmpstr;
	static long count = 0;
	JButton back_b, search_b;

	/**
	 * Create the frame.
	 */
	public MemManagement() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		
		JLabel title = new JLabel("회원정보");
		title.setFont(new Font("굴림", Font.PLAIN, 18));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBounds(263, 20, 152, 28);
		contentPane.add(title);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(31, 96, 615, 291);
		contentPane.add(panel);
		
		table = new JTable(model1);
		table.setFont(new Font("굴림", Font.PLAIN, 14));
		table.setPreferredScrollableViewportSize(new Dimension(600, 250));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		scrollPane.setViewportView(table);
		
		search_b = new JButton("검색");
		search_b.setFont(new Font("굴림", Font.PLAIN, 11));
		search_b.setBounds(579, 62, 57, 23);
		search_b.addActionListener(this);
		contentPane.add(search_b);
		
		back_b = new JButton("뒤로가기");
		back_b.setFont(new Font("바탕", Font.PLAIN, 16));
		back_b.setBounds(280, 413, 124, 28);
		back_b.addActionListener(this);
		contentPane.add(back_b);
		
		contentPane.setVisible(true);
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
			try {
				query("select","select * from member");
				
				while(rs.next()) {
					model1.addRow(new Object[] {rs.getString("id"), rs.getString("pwd"),rs.getString("name"),rs.getString("telNo")});
				}
			}catch(Exception e1) {
				e1.getStackTrace();
			}
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
					MemManagement frame = new MemManagement();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		dbConnect();
		query("select", "select * from member	");
		count = 1;
		viewData();
		dbDis();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back_b) {
			dispose();
		}
//		if(e.getSource()== search_b) {
//			dbConnect();
//			try {
//				query("select","select pdname from productList where pdname="+search_t.getText() + " ");
//				m.showMessageDialog(null, "검색완료");
//			}catch(Exception e1) {
//				e1.getStackTrace();
//			}
//			dbDis();
//		}
	}
}
