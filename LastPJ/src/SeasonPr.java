import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Icon;


public class SeasonPr extends JFrame implements ActionListener{
	
	static String driver, url;
	static Connection conn;
	static Statement stmt;
	static ResultSet rs;
	static String tmpstr;
	String month[] = new String[] {
			"1월","2월","3월","4월","5월","6월",
			"7월","8월","9월","10월","11월","12월"
	};
	JComboBox combo = new JComboBox(month);
	Object obj = combo.getSelectedItem();
	static String[] head = new String[] {"번호", "품명","가격","간단한 정보"};
	static DefaultTableModel model1 = new DefaultTableModel(head,0);
	private JPanel contentPane;
	JTable table;
	JButton back_b;
	
	public SeasonPr() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 688, 501);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		
		setContentPane(contentPane);
		
		JLabel title = new JLabel("제철 품목");
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
		
		JLabel TodayLabel = new JLabel("수정 날짜 :  ");
		TodayLabel.setFont(new Font("굴림", Font.PLAIN, 11));
		TodayLabel.setBounds(516, 397, 130, 23);
		contentPane.add(TodayLabel);
		
		back_b = new JButton("뒤로가기");
		back_b.setFont(new Font("바탕", Font.PLAIN, 16));
		back_b.setBounds(280, 413, 124, 28);
		back_b.addActionListener(this);
		contentPane.add(back_b);
		
		combo.setBounds(584, 63, 51, 23);
		contentPane.add(combo);
		
		contentPane.setVisible(true);
	}
	public void jTableMouseClicked(java.awt.event.MouseEvent evt) {
		obj = combo.getSelectedItem();
		System.out.println("Item : "+obj);
		
		int tb1columns = table.getColumnCount();
		int selectionrow= table.getSelectedRow();
		int selectioncolumn = table.getSelectedColumn();
		if(selectioncolumn > 1) {
			for(int i = 2; i<tb1columns;i++) {
				System.out.println(table.getValueAt(selectionrow, selectioncolumn));
			}
		}
		else {
			System.out.println(table.getValueAt(selectionrow, selectioncolumn));
		}
	}
	//===================================================
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

		}
		else{		
			try {
				query("select","select * from productList");
				
				while(rs.next()) {
					model1.addRow(new Object[] {rs.getString("pdid"), rs.getString("pdname"),rs.getString("pdprice"),rs.getString("pdcoment")});
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
					SeasonPr frame = new SeasonPr();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		dbConnect();
		query("select", "select * from productList where pdid = 1");
		viewData();
		dbDis();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == back_b) {
			//m.showMessageDialog(null, "시스템 종료");
			dispose();
		}
	}
//=============================================================
}
