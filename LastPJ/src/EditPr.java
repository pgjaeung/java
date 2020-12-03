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

public class EditPr extends JFrame implements ActionListener{

	JOptionPane m = new JOptionPane();
	private JPanel contentPane;
	private JTextField num_t;
	private JTextField name_t;
	private JTextField if_t;
	private JTextField price_t;
	static String driver, url;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static String tmpstr;
	static long count = 0;
	static JButton edit_b,load_b,back_b;

	public EditPr() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\uD488\uBAA9\uC218\uC815");
		lblNewLabel.setFont(new Font("굴림", Font.PLAIN, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 111, 40);
		contentPane.add(lblNewLabel);
		
		JLabel num = new JLabel("\uBC88\uD638");
		num.setHorizontalAlignment(SwingConstants.RIGHT);
		num.setFont(new Font("굴림", Font.PLAIN, 14));
		num.setBounds(135, 75, 50, 15);
		contentPane.add(num);
		
		num_t = new JTextField();
		num_t.setColumns(10);
		num_t.setBounds(197, 72, 55, 22);
		contentPane.add(num_t);
		
		JLabel name = new JLabel("\uC774\uB984");
		name.setHorizontalAlignment(SwingConstants.RIGHT);
		name.setFont(new Font("굴림", Font.PLAIN, 14));
		name.setBounds(67, 104, 118, 22);
		contentPane.add(name);
		
		name_t = new JTextField();
		name_t.setColumns(10);
		name_t.setBounds(197, 104, 153, 22);
		contentPane.add(name_t);
		
		JLabel information = new JLabel("\uBB3C\uAC00\uC815\uBCF4");
		information.setHorizontalAlignment(SwingConstants.RIGHT);
		information.setFont(new Font("굴림", Font.PLAIN, 14));
		information.setBounds(67, 169, 118, 22);
		contentPane.add(information);
		
		if_t = new JTextField();
		if_t.setColumns(10);
		if_t.setBounds(197, 170, 153, 22);
		contentPane.add(if_t);
		
		JLabel price = new JLabel("\uAC00\uACA9");
		price.setHorizontalAlignment(SwingConstants.RIGHT);
		price.setFont(new Font("굴림", Font.PLAIN, 14));
		price.setBounds(67, 136, 118, 22);
		contentPane.add(price);
		
		price_t = new JTextField();
		price_t.setColumns(10);
		price_t.setBounds(197, 137, 153, 22);
		contentPane.add(price_t);
		
		edit_b = new JButton("\uC218\uC815");
		edit_b.addActionListener(this);
		edit_b.setBounds(283, 219, 91, 23);
		contentPane.add(edit_b);
		
		load_b = new JButton("load");
		load_b.setBounds(259, 71, 91, 23);
		load_b.addActionListener(this);
		contentPane.add(load_b);
		
		back_b = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		back_b.setBounds(180, 219, 91, 23);
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
					EditPr frame = new EditPr();
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
				query("update", "update productList set pdname  = '" + name_t.getText() + "' , pdprice = '" + price_t.getText() +"' , pdcoment = '" 
			+ if_t.getText() + "' where pdid = " + num_t.getText() + "");
				//viewData();
				m.showMessageDialog(null, "데이터 수정 완료");
				
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			num_t.setText("");
			name_t.setText("");
			price_t.setText("");
			if_t.setText("");
			dbDis();
		}
		//======================================================================
		else if(e.getSource()==load_b) {
			dbConnect();
			try {
				query("select", "select * from productList where pdid = " +num_t.getText() + " ");
				while(rs.next()) {
						if(num_t.getText().equals(rs.getString("pdid"))) {
							num_t.setText(rs.getString("pdid"));
							name_t.setText(rs.getString("pdname"));
							price_t.setText(rs.getString("pdprice"));
							if_t.setText(rs.getString("pdcoment"));
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
