import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;

public class Board extends JFrame implements ActionListener{

	private JPanel contentPane;
	JButton inquiry_b,free_b,back_b;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Board frame = new Board();
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
	public Board() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\uAC8C\uC2DC\uD310");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("µ¸¿ò", Font.PLAIN, 16));
		lblNewLabel.setBounds(159, 10, 98, 23);
		contentPane.add(lblNewLabel);
		
		free_b = new JButton("\uC790\uC720 \uAC8C\uC2DC\uD310");
		free_b.setForeground(Color.WHITE);
		free_b.addActionListener(this);
		free_b.setBackground(Color.ORANGE);
		free_b.setFont(new Font("±¼¸²", Font.BOLD, 20));
		free_b.setBounds(35, 53, 180, 167);
		contentPane.add(free_b);
		
		inquiry_b = new JButton("\uBB38\uC758\uD558\uAE30");
		inquiry_b.setForeground(Color.WHITE);
		inquiry_b.setBackground(Color.GREEN);
		inquiry_b.addActionListener(this);
		inquiry_b.setFont(new Font("±¼¸²", Font.BOLD, 20));
		inquiry_b.setBounds(216, 53, 180, 167);
		contentPane.add(inquiry_b);
		
		back_b = new JButton("\uB4A4\uB85C\uAC00\uAE30");
		back_b.setBounds(305, 230, 91, 23);
		back_b.addActionListener(this);
		contentPane.add(back_b);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==inquiry_b) {
			try {
				InquireList_mem il= new InquireList_mem();
				il.setVisible(true);
				il.dbConnect();
				il.viewData();
			}catch(Exception e1) {
				e1.printStackTrace();
			}
			dispose();
		}
		else if(e.getSource()==back_b) {
			dispose();
		}
		
	}
	
}
