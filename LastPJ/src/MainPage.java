import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenuItem;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JEditorPane;
import javax.swing.JTextField;

public class MainPage extends JFrame implements ActionListener{

	private JPanel contentPane;
	JButton list_b, search_b, logout_b, board_b,chat_b, season_b;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainPage frame = new MainPage();
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
	public MainPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 544, 485);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\uB18D\uC218\uCD95\uC0B0\uBB3C \uBB3C\uAC00 \uAC80\uC0C9\uAE30");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("ÇÔÃÊ·Òµ¸¿ò", Font.PLAIN, 23));
		lblNewLabel.setBounds(91, 10, 328, 42);
		contentPane.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(0, 62, 563, 16);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBackground(Color.BLACK);
		separator_1.setBounds(0, 198, 563, 16);
		contentPane.add(separator_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setBackground(Color.BLACK);
		separator_1_1.setBounds(0, 318, 231, 16);
		contentPane.add(separator_1_1);
		
		JSeparator separator_1_1_1 = new JSeparator();
		separator_1_1_1.setBackground(Color.BLACK);
		separator_1_1_1.setBounds(299, 318, 242, 16);
		contentPane.add(separator_1_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("\uC18C\uC15C");
		lblNewLabel_2.setFont(new Font("µ¸¿ò", Font.PLAIN, 17));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(227, 300, 72, 33);
		contentPane.add(lblNewLabel_2);
		
		list_b = new JButton("List");
		list_b.addActionListener(this);
		list_b.setBackground(Color.ORANGE);
		list_b.setForeground(Color.WHITE);
		list_b.setFont(new Font("±¼¸²", Font.BOLD, 16));
		list_b.setBounds(88, 132, 123, 42);
		contentPane.add(list_b);
		
		JLabel lblNewLabel_1 = new JLabel("\uBB3C\uAC00 \uB9AC\uC2A4\uD2B8");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(36, 84, 222, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("\uBB3C\uAC00 \uAC80\uC0C9\uD558\uAE30");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(282, 84, 181, 26);
		contentPane.add(lblNewLabel_1_1);
		
		search_b = new JButton("Search");
		search_b.addActionListener(this);
		search_b.setBackground(Color.ORANGE);
		search_b.setForeground(Color.WHITE);
		search_b.setFont(new Font("±¼¸²", Font.BOLD, 16));
		search_b.setBounds(314, 132, 123, 42);
		contentPane.add(search_b);
		
		JLabel lblNewLabel_1_2 = new JLabel("\uC81C\uCCA0 \uD488\uBAA9");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setBounds(151, 208, 222, 26);
		contentPane.add(lblNewLabel_1_2);
		
		season_b = new JButton("Season");
		season_b.addActionListener(this);
		season_b.setForeground(Color.WHITE);
		season_b.setFont(new Font("±¼¸²", Font.BOLD, 16));
		season_b.setBackground(Color.ORANGE);
		season_b.setBounds(198, 244, 123, 42);
		contentPane.add(season_b);
		
		chat_b = new JButton("Chat");
		chat_b.setForeground(Color.WHITE);
		chat_b.addActionListener(this);
		chat_b.setFont(new Font("±¼¸²", Font.BOLD, 16));
		chat_b.setBackground(Color.ORANGE);
		chat_b.setBounds(88, 378, 123, 42);
		contentPane.add(chat_b);
		
		JLabel lblNewLabel_1_3 = new JLabel("\uC2E4\uC2DC\uAC04 \uCC44\uD305");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setBounds(36, 342, 222, 26);
		contentPane.add(lblNewLabel_1_3);
		
		board_b = new JButton("Board");
		board_b.setForeground(Color.WHITE);
		board_b.addActionListener(this);
		board_b.setFont(new Font("±¼¸²", Font.BOLD, 16));
		board_b.setBackground(Color.ORANGE);
		board_b.setBounds(314, 378, 123, 42);
		contentPane.add(board_b);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("\uAC8C\uC2DC\uD310");
		lblNewLabel_1_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3_1.setBounds(268, 342, 222, 26);
		contentPane.add(lblNewLabel_1_3_1);
		
		logout_b = new JButton("\uB85C\uADF8\uC544\uC6C3");
		logout_b.setBounds(427, 10, 91, 23);
		logout_b.addActionListener(this);
		contentPane.add(logout_b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==logout_b) {
			dispose();
			LoginMain lm = new LoginMain();
			lm.setVisible(true);
		}
		else if(e.getSource()==list_b) {
			try {List l = new List();
			l.setVisible(true);
			l.dbConnect();
			l.viewData();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==search_b) {
			SearchPr sp = new SearchPr();
			sp.setVisible(true);
		}
		else if(e.getSource()==season_b) {
			try {
				SeasonPr sp1 = new SeasonPr();
				sp1.dbConnect();
				sp1.viewData();
				sp1.setVisible(true);
			}catch(Exception e1) {
				e1.printStackTrace();
			}
		}
		else if(e.getSource()==chat_b) {
			Chatting c = new Chatting();
			c.setVisible(true);
		}
		else if(e.getSource()==board_b) {
			Board b = new Board();
			b.setVisible(true);
		}
	}
}
