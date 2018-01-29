package kolokwium;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import kolokwium.Jdbc;
import kolokwium.Login;
import kolokwium.Questions;

import java.awt.Font;
import javax.swing.SwingConstants;

public class Results extends JFrame 
{
	private JPanel contentPane;

	
	protected static final String String = null;
	private JButton btnExit;
	private JButton btnZapiszDoBazy;
	int results=Questions.score;
	public static void main(String[] args) 
	{
		Jdbc.main(null);
	
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try  
				{
					Results frame = new Results();
					frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}


	public Results()
	{	
		setTitle("Wyniki");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		String result=String.valueOf(results);
		JLabel finalresult = new JLabel(result+"/7");
		finalresult.setHorizontalAlignment(SwingConstants.CENTER);
		finalresult.setFont(new Font("Tahoma", Font.PLAIN, 65));
		finalresult.setBounds(150, 11, 250, 73);
		contentPane.add(finalresult);
		
		
		btnExit = new JButton("Wyjdz");
		btnExit.setBackground(new Color(0, 0, 0));
		btnExit.setForeground(new Color(255, 255, 255));
		btnExit.setBounds(150, 125, 250, 23);
		contentPane.add(btnExit);
		btnExit.addActionListener(new ActionListener() 
                {
		                public void actionPerformed(ActionEvent e) 
                                {				   
		                	System.exit(0);
                                }
		});
		
		btnZapiszDoBazy = new JButton("Zapisz do bazy danych");
		btnZapiszDoBazy.setForeground(new Color(255, 255, 255));
		btnZapiszDoBazy.setBackground(new Color(0, 0, 0));
		btnZapiszDoBazy.setBounds(150, 95, 250, 23);
		contentPane.add(btnZapiszDoBazy);
		btnZapiszDoBazy.addActionListener(new ActionListener() 
        {
                public void actionPerformed(ActionEvent e) 
                        {
                	Thread dbThread = new Thread() {
      			      public void run() {
                	
                	Connection conn = null;
                	try {  
                			
                		  	conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
      						System.out.println("Polaczenie z baza danych: ... OK");	
      						
//      						String id="SELECT id FROM users where  ";
      						String sql = "update uzytkowniki set result='"+result+"' where imie='"+Login.qimie+"' ";
      						((Connection)conn).createStatement().executeUpdate(sql);

                		 
      						System.out.println("Wynik zapisany do bazy danych");
      						JOptionPane.showMessageDialog(null,"Zapisane do bazy","Powiadomienie",JOptionPane.PLAIN_MESSAGE);

                		  } catch (SQLException se) 
        			{
                				
              				se.printStackTrace();
              			}
              			catch (Exception a) 
              			{
              				a.printStackTrace();
              			}
                	
                	
                        }
                	 };
     			    dbThread.start();
     			  }
});
	
		
		

}}