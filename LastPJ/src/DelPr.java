import java.awt.Color;
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

public class DelPr extends JFrame implements ActionListener{
	JOptionPane m = new JOptionPane();
	static String driver, url;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static String tmpstr;
	static long count = 0;
	private JPanel contentPane;
	private static JTextField drop_t;
	static JButton drop_b, back_b;


	public DelPr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\uD488\uBAA9\uC0AD\uC81C");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		lblNewLabel.setBounds(12, 10, 128, 36);
		contentPane.add(lblNewLabel);
		
		JLabel drop_l = new JLabel("\uD488\uBAA9ID\uC785\uB825");
		drop_l.setHorizontalAlignment(SwingConstants.RIGHT);
		drop_l.setFont(new Font("굴림", Font.PLAIN, 15));
		drop_l.setBounds(12, 106, 128, 28);
		contentPane.add(drop_l);
		
		drop_t = new JTextField();
		drop_t.setHorizontalAlignment(SwingConstants.RIGHT);
		drop_t.setBounds(152, 106, 115, 28);
		contentPane.add(drop_t);
		drop_t.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("ID\uC785\uB825\uC2DC \uC790\uB3D9\uC0AD\uC81C");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(268, 238, 156, 25);
		contentPane.add(lblNewLabel_2);
		
		drop_b = new JButton("Delete");
		drop_b.setBackground(Color.RED);
		drop_b.setForeground(Color.WHITE);
		drop_b.setBounds(287, 106, 91, 28);
		drop_b.addActionListener(this);
		contentPane.add(drop_b);
		
		back_b = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		back_b.setForeground(Color.WHITE);
		back_b.addActionListener(this);
		back_b.setBackground(Color.RED);
		back_b.setBounds(287, 149, 91, 28);
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
        String sql = "Select * From productList";
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
					DelPr frame = new DelPr();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		dbConnect();
		//
		count = 1;
		viewData();
		dbDis();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == drop_b) {
			dbConnect();
			try {
				query("delete", "delete from productList where pdid = " +drop_t.getText() + " ");
				//viewData();
				m.showMessageDialog(null, "데이터베이스 삭제 완료");
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			dbDis();
		}
		else if(e.getSource()==back_b) {
			dispose();
		}
	}
}
