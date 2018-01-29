package kolokwium;

import java.awt.event.ActionEvent;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import kolokwium.Jdbc;

import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Register extends JFrame 
{
	private JPanel contentPane; 
	private JTextField txtImie;
	private JTextField txtNazwisko;
	private JTextField txtEmail;
	private JTextField txtHaslo;
	private JButton btnRejestracja;
	protected static final String String = null;
	
	
	public static void main(String[] args) 
	{
		 Socket clientSocket = null; 
			try {
				clientSocket = new Socket("localhost", 9999); 
			    } catch (IOException e) {
			      System.err.println(e);
			    }
	
	SwingUtilities.invokeLater(new Runnable() {
	      public void run() {
	    	Jdbc.main(null);
	        Register frame = new Register();
	        frame.setVisible(true);
	      }
	    });
	}


	public Register() 
	{	
		setTitle("Rejestracja");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));

		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNazwisko = new JTextField();
		txtNazwisko.setBounds(135, 25, 100, 20);
		contentPane.add(txtNazwisko);
		txtNazwisko.setColumns(10);
		txtEmail = new JTextField();
		txtEmail.setBounds(135, 60, 100, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);
		txtImie = new JTextField();
		txtImie.setBounds(355, 25, 100, 20);
		contentPane.add(txtImie);
		txtImie.setColumns(10);
		txtHaslo = new JTextField();
		txtHaslo.setBounds(355, 60, 100, 20);
		contentPane.add(txtHaslo);
		txtHaslo.setColumns(10);
		
		JLabel lblNazwisko = new JLabel("Nazwisko");
		lblNazwisko.setBounds(65,30,55,14);
		contentPane.add(lblNazwisko);
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(75, 65, 45, 14);
		contentPane.add(lblEmail);
		JLabel lblImie = new JLabel("Imie");
		lblImie.setHorizontalAlignment(SwingConstants.RIGHT);
		lblImie.setBounds(295, 30, 45, 14);
		contentPane.add(lblImie);
		JLabel lblHaslo = new JLabel("Haslo");
		lblHaslo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHaslo.setBounds(295, 65, 45, 14);
		contentPane.add(lblHaslo);
	
		btnRejestracja = new JButton("OK");
		btnRejestracja.setForeground(new Color(255, 255, 255));
		btnRejestracja.setBackground(new Color(0, 0, 0));

		btnRejestracja.setBounds(235, 105, 60, 40);
		contentPane.add(btnRejestracja);
		btnRejestracja.addActionListener(new ActionListener() 
                {
			
			
			public void actionPerformed(ActionEvent e) {
			    Thread registerThread = new Thread() {
			      public void run() {

			    	  Jdbc.main(null);
	                    if(Jdbc.connectionStatus = true) {

                          
			Connection conn = null;
			try  
                          {
			String nazwisko= "";
			String email= "";
			String imie = "";
			String haslo = "";
			int result = 0;
			
			nazwisko = txtNazwisko.getText().trim();
			email = txtEmail.getText().trim();
			imie = txtImie.getText().trim();
			haslo = txtHaslo.getText().trim();
			
			if (imie.equals("")|| haslo.equals(""))
			{
				JOptionPane.showMessageDialog(null,"Wypelnij wszystkie pola \nnie mozesz zostawic puste !","BLAD",JOptionPane.ERROR_MESSAGE);
			}
			else  
                          {
				String IQuery = "INSERT INTO `uzytkowniki`(`imie`,`nazwisko`,`email`,`haslo`,`result`) VALUES('"+imie+"','"+nazwisko+"','"+email+"', '"+haslo+"','"+result+"')";
				conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
				System.out.println("Polaczenie z baza danych: ... OK");
				  
			((Connection)conn).createStatement().execute(IQuery);
				
				((java.sql.Connection)conn).close();
				 new Login().setVisible(true); 
			        dispose();
			}				
		}
		catch (SQLException se) 
		{
		
			se.printStackTrace();
		}
		catch (Exception a) 
		{
			a.printStackTrace();
		}
                          }
			      }
			    };
			    registerThread.start();
			  }
			
			
	                	      
		                
		});
	}
	
	
}