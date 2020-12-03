import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class InquireList_mem extends JFrame implements ActionListener{

	static String[] head = new String[] {"제목","내용","답변"};
	static DefaultTableModel model1 = new DefaultTableModel(head,0);
	
	private JTable table;
	JPanel inquirypane;
	static String driver, url;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static String tmpstr;
	static long count = 0;
	JButton back_b,answer_b;
	static String temp;

	public InquireList_mem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 362);
		inquirypane = new JPanel();
		inquirypane.setBorder(new EmptyBorder(5, 5, 5, 5));
		inquirypane.setLayout(null);
		setContentPane(inquirypane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 38, 441, 238);
		inquirypane.add(panel);
		
		table = new JTable(model1);
		table.setFont(new Font("굴림", Font.PLAIN, 14));
		table.setPreferredScrollableViewportSize(new Dimension(435, 236));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		scrollPane.setViewportView(table);
		
		JLabel title = new JLabel("\uBB38\uC758 \uB9AC\uC2A4\uD2B8");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("굴림", Font.PLAIN, 18));
		title.setBounds(158, 0, 152, 28);
		inquirypane.add(title);
		
		back_b = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		back_b.setFont(new Font("바탕", Font.PLAIN, 14));
		back_b.setForeground(Color.WHITE);
		back_b.setBackground(Color.GRAY);
		back_b.addActionListener(this);
		back_b.setBounds(356, 286, 97, 21);
		inquirypane.add(back_b);
		
		answer_b = new JButton("문의하기");
		answer_b.setForeground(Color.WHITE);
		answer_b.setBackground(Color.GRAY);
		answer_b.setFont(new Font("바탕", Font.PLAIN, 14));
		answer_b.setBounds(247, 286, 97, 21);
		answer_b.addActionListener(this);
		inquirypane.add(answer_b);
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
			count--;
		}
		else{		
			try {
				query("select","select * from inquiry");
				
				while(rs.next()) {
					model1.addRow(new Object[] {rs.getString("title"),rs.getString("content"),rs.getString("answer")});
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
					InquireList_mem frame = new InquireList_mem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		dbConnect();
		count = 1;
		viewData();
		dbDis();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==answer_b) {
			dbConnect();
			try {
				table.getSelectedRow();
				InquirePg ap = new InquirePg();
				ap.setVisible(true);
				
				
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			dbDis();
		}
		else if(e.getSource()==back_b) {
			Board bo = new Board();
			bo.setVisible(true);
			dispose();
		}
	}
	
}
