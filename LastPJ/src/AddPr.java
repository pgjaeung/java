import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
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

public class AddPr extends JFrame implements ActionListener {
	JOptionPane m = new JOptionPane();
	private JPanel Pane1;
	private static JTextField name_t;
	private static JTextField if_t;
	private static JTextField price_t;
	private static JTextField num_t;
	private JLabel price;
	static String driver, url;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static String tmpstr;
	static long count = 0;
	JButton add_b,back_b;

	/**
	 * Create the frame.
	 */
	public AddPr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		Pane1 = new JPanel();
		Pane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		Pane1.setLayout(null);
		setContentPane(Pane1);
		
		JLabel lblNewLabel = new JLabel("\uD488\uBAA9\uCD94\uAC00");
		lblNewLabel.setFont(new Font("돋움", Font.PLAIN, 16));
		lblNewLabel.setBounds(43, 10, 75, 37);
		Pane1.add(lblNewLabel);
		
		JLabel name = new JLabel("\uCD94\uAC00\uD560 \uD488\uBAA9 \uC774\uB984");
		name.setHorizontalAlignment(SwingConstants.RIGHT);
		name.setFont(new Font("굴림", Font.PLAIN, 14));
		name.setBounds(45, 89, 118, 22);
		Pane1.add(name);
		
		name_t = new JTextField();
		name_t.setBounds(175, 89, 153, 22);
		Pane1.add(name_t);
		name_t.setColumns(10);
		
		JLabel information = new JLabel("\uBB3C\uAC00\uC815\uBCF4");
		information.setHorizontalAlignment(SwingConstants.RIGHT);
		information.setFont(new Font("굴림", Font.PLAIN, 14));
		information.setBounds(45, 129, 118, 22);
		Pane1.add(information);
		
		if_t = new JTextField();
		if_t.setColumns(10);
		if_t.setBounds(175, 130, 153, 22);
		Pane1.add(if_t);
		
		price_t = new JTextField();
		price_t.setColumns(10);
		price_t.setBounds(175, 171, 153, 22);
		Pane1.add(price_t);
		
		price = new JLabel("\uAC00\uACA9");
		price.setHorizontalAlignment(SwingConstants.RIGHT);
		price.setFont(new Font("굴림", Font.PLAIN, 14));
		price.setBounds(45, 170, 118, 22);
		Pane1.add(price);
		
		add_b = new JButton("ADD");
		add_b.setBackground(Color.CYAN);
		add_b.setForeground(SystemColor.textHighlight);
		add_b.setBounds(297, 205, 91, 28);
		add_b.addActionListener(this);
		
		Pane1.add(add_b);
		
		JLabel num = new JLabel("\uBC88\uD638");
		num.setFont(new Font("굴림", Font.PLAIN, 14));
		num.setHorizontalAlignment(SwingConstants.RIGHT);
		num.setBounds(113, 57, 50, 15);
		Pane1.add(num);
		
		num_t = new JTextField();
		num_t.setColumns(10);
		num_t.setBounds(175, 54, 55, 22);
		Pane1.add(num_t);
		
		back_b = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		back_b.setForeground(SystemColor.textHighlight);
		back_b.setBackground(Color.CYAN);
		back_b.addActionListener(this);
		back_b.setBounds(175, 205, 91, 28);
		Pane1.add(back_b);
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
				num_t.setText(String.valueOf(rs.getLong("")));
				name_t.setText(rs.getString(""));
				price_t.setText(rs.getString(""));
				if_t.setText(rs.getString(""));
						
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
					AddPr frame = new AddPr();
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
		if (e.getSource() == add_b) {
			int a = Integer.parseInt(num_t.getText());
			dbConnect();
			try {
				query("insert", "insert into productList  values('"+a+"','"+name_t.getText()+"','"
			+price_t.getText()+"','"+if_t.getText()+"')");
				m.showMessageDialog(null, "추가 완료");
			} catch (Exception e1) {
				e1.printStackTrace();
			}			
			
			dbDis();
		} 
		else if(e.getSource()==back_b) {
			dbDis();
			dispose();
			return;
		}
	}
}
