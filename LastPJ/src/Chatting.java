import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class Chatting extends JFrame
{ 

       private JPanel contentPane;
       private JButton btnSend;
       private JTextArea taDisplay;

       /**
        * Launch the application.
        */
       public static void main(String[] args)
       {
           EventQueue.invokeLater(new Runnable()
           {
               public void run()
               {
                   try
                   {
                       Chatting frame = new Chatting();
                       frame.setVisible(true);
                   }
                   catch (Exception e)
                   {
                       e.printStackTrace();
                   }
               }
           });
       }

       /**
        * Create the frame.
        */
       public Chatting()
       {
           setResizable(false);
           setTitle("실시간 채팅");
           setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           setBounds(100, 100, 440, 316);

           JMenuBar menuBar = new JMenuBar();
           setJMenuBar(menuBar);

           JMenu mnFile = new JMenu("File");
           menuBar.add(mnFile);

           JMenuItem mntmConnect = new JMenuItem("Connect...");
           mnFile.add(mntmConnect);

           JMenuItem mntmSaveChatLog = new JMenuItem("Save chat log...");
           mnFile.add(mntmSaveChatLog);

           JMenuItem mntmSettings = new JMenuItem("Settings...");
           mnFile.add(mntmSettings);

           JMenuItem mntmClose = new JMenuItem("Close");
           mnFile.add(mntmClose);
           
           JMenu mnEdit = new JMenu("Edit");
           menuBar.add(mnEdit);

           JMenu mnView = new JMenu("View");
           menuBar.add(mnView);

           JMenu mnHelp = new JMenu("Help");
           menuBar.add(mnHelp);
           contentPane = new JPanel();
           contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
           contentPane.setLayout(new BorderLayout(0, 0));
           setContentPane(contentPane);
           JPanel panel = new JPanel();
           contentPane.add(panel, BorderLayout.CENTER);
           panel.setLayout(null);

           btnSend = new JButton("Send");
           btnSend.addMouseListener(new MouseAdapter()
           {
               @Override
               public void mouseClicked(MouseEvent arg0)
               {
                   taDisplay.append("Send clicked.\n");
               }
           });
           btnSend.setBounds(314, 197, 100, 50);
           panel.add(btnSend);

           taDisplay = new JTextArea();
           taDisplay.setLineWrap(true);
           taDisplay.setEditable(false);
           taDisplay.setBounds(10, 11, 404, 180);
           panel.add(taDisplay);

           JScrollPane spInput = new JScrollPane();
           spInput.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
           spInput.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
           spInput.setBounds(10, 197, 294, 49);
           panel.add(spInput);

           JTextArea taInput = new JTextArea();
           taInput.setLineWrap(true);
           spInput.setViewportView(taInput);
       }
}