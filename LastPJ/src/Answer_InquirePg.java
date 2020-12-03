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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Answer_InquirePg extends JFrame implements ActionListener{

	JTextArea answer_t,content_t;
	JPanel inquire_answer;
	JTextField title_t;
	JTextField num_t;
	static String driver, url;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static String tmpstr;
	static long count = 0;
	JButton answer_b, cancel_b,dataload_b;

	public Answer_InquirePg() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 486);
		inquire_answer = new JPanel();
		inquire_answer.setBorder(new EmptyBorder(5, 5, 5, 5));
		inquire_answer.setLayout(null);
		setContentPane(inquire_answer);
		
		JLabel lblNewLabel = new JLabel("\uBB38\uC758\uD558\uAE30");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		lblNewLabel.setBounds(12, 10, 72, 23);
		inquire_answer.add(lblNewLabel);
		
		JLabel title_l = new JLabel("\uC81C\uBAA9");
		title_l.setHorizontalAlignment(SwingConstants.RIGHT);
		title_l.setFont(new Font("굴림", Font.PLAIN, 14));
		title_l.setBounds(12, 50, 50, 18);
		inquire_answer.add(title_l);
		
		title_t = new JTextField();
		title_t.setColumns(10);
		title_t.setBounds(86, 50, 326, 21);
		inquire_answer.add(title_t);
		
		JLabel content_l = new JLabel("\uB0B4\uC6A9");
		content_l.setHorizontalAlignment(SwingConstants.RIGHT);
		content_l.setFont(new Font("굴림", Font.PLAIN, 14));
		content_l.setBounds(12, 86, 50, 18);
		inquire_answer.add(content_l);
		
		answer_b = new JButton("\uB2F5\uBCC0\uD558\uAE30");
		answer_b.addActionListener(this);
		answer_b.setBounds(109, 416, 91, 23);
		inquire_answer.add(answer_b);
		
		cancel_b = new JButton("\uCDE8\uC18C");
		cancel_b.setBounds(248, 416, 91, 23);
		cancel_b.addActionListener(this);
		inquire_answer.add(cancel_b);
		
		JLabel answer_l = new JLabel("\uB2F5\uBCC0");
		answer_l.setHorizontalAlignment(SwingConstants.RIGHT);
		answer_l.setFont(new Font("굴림", Font.PLAIN, 14));
		answer_l.setBounds(12, 253, 50, 18);
		inquire_answer.add(answer_l);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(86, 86, 326, 142);
		inquire_answer.add(scrollPane);
		
		content_t = new JTextArea();
		scrollPane.setViewportView(content_t);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(86, 243, 326, 142);
		inquire_answer.add(scrollPane_1);
		
		answer_t = new JTextArea();
		scrollPane_1.setViewportView(answer_t);
		
		dataload_b = new JButton("load");
		dataload_b.addActionListener(this);
		dataload_b.setBounds(346, 16, 66, 18);
		inquire_answer.add(dataload_b);
		
		num_t = new JTextField();
		num_t.setBounds(243, 16, 96, 18);
		inquire_answer.add(num_t);
		num_t.setColumns(10);
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
					Answer_InquirePg frame = new Answer_InquirePg();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		dbConnect();
		query("select","select * from inquiry where count=0");
		count = -1;
		viewData();
		dbDis();
	}
	public void actionPerformed(ActionEvent e) {
		//===================================================
		if (e.getSource() == answer_b) {
			dbConnect();
			try {
				query("update","update inquiry set answer='"+answer_t.getText()+"' where idofNum = " + num_t.getText() + "");
				JOptionPane.showMessageDialog(null, "수정 완료!");
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			title_t.setText("");
			content_t.setText("");
			answer_t.setText("");
			dbDis();
		}
		//===================================================
		else if(e.getSource() == cancel_b) {
			dispose();
		}
		//===================================================
		else if(e.getSource()==dataload_b) {
			dbConnect();
			try {
				query("select", "select * from inquiry where idofNum = " +num_t.getText()+"");
				while(rs.next()) {
						if(num_t.getText().equals(rs.getString("idofNum"))) {
							title_t.setText(rs.getString("title"));
							content_t.setText(rs.getString("content"));
							answer_t.setText(rs.getString("answer"));
						}
						JOptionPane.showMessageDialog(null, "데이터 로드");
				}
				viewData();
			}catch(Exception e2) {
				e2.printStackTrace();
			}
			dbDis();
		}
		//===================================================
	}
}
