package kolokwium;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;

import kolokwium.Jdbc;

import java.awt.Color;


public class Login extends JFrame 
{
	private JPanel contentPane; 
	public JTextField txtImie;
	private JTextField txtHaslo;
	private JButton btnRejestracja;
	private JButton btnLogowanie;
	public static String qimie;
	
	private boolean login=false;
	
	protected static final String String = null;
	
	public static void main(String[] args) 
	{
		
		
		SwingUtilities.invokeLater(new Runnable() {
		      public void run() {
		    	Jdbc.main(null);
		        Login frame = new Login();
		        frame.setVisible(true);
		      }
		    });
	}


	public Login() 
	{	
		setTitle("Logowanie");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 200);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
		txtImie = new JTextField();
		txtImie.setBounds(250, 22, 99, 20);
		contentPane.add(txtImie);
		txtImie.setColumns(10);
		txtHaslo = new JTextField();
		txtHaslo.setBounds(250, 55, 99, 20);
		contentPane.add(txtHaslo);
		txtHaslo.setColumns(10);
		
		
		JLabel lblImie = new JLabel("Imie");
		lblImie.setBounds(160, 25, 86, 14);
		contentPane.add(lblImie);
		JLabel lblHaslo = new JLabel("Haslo");
		lblHaslo.setBounds(160, 58, 86, 14);
		contentPane.add(lblHaslo);
		
		
		btnLogowanie = new JButton("Zalogowac sie");
		btnLogowanie.setForeground(new Color(255, 255, 255));
		btnLogowanie.setBackground(new Color(0, 0, 0));

		btnLogowanie.setBounds(160, 93, 189, 23);
		contentPane.add(btnLogowanie);
		btnLogowanie.addActionListener(new ActionListener() 
                {
		                

						public void actionPerformed(ActionEvent e) 
                                {	
		                	String imie = txtImie.getText();
		                	
		                	Thread loginThread = new Thread() {
		                		
		                	      public void run() {
		                	    	  
		                	    	  Connection conn = null;
		              				try   {
		              					String imie = "";
		              					String haslo = "";
		              					imie = txtImie.getText().trim();
		              					haslo = txtHaslo.getText().trim();
		                                      
		              				if (imie.equals("")|| haslo.equals(""))
		              				{
		              					JOptionPane.showMessageDialog(null,"Wypelnij powyzsze pola lub zarejestruj sie !","BLAD",JOptionPane.ERROR_MESSAGE);
		              				}
		              				
		              				else  
		                                 {
		              					if(Jdbc.connectionStatus=false)
		              						login=false;
		              				
		              					else {
		              					try {
		              						
		              			            Class.forName("com.mysql.jdbc.Driver").newInstance();
		              			            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
		              			            Statement stmt = (Statement) con.createStatement();
		              			           String query = "SELECT imie, haslo FROM test.uzytkowniki;";
		              			            stmt.executeQuery(query);
		              			            ResultSet rs = stmt.getResultSet();

		              			            while(rs.next()){
		              			                String db_imie = rs.getString("imie");
		              			                String db_haslo = rs.getString("haslo");

		              			                if(imie.equals(db_imie) && haslo.equals(db_haslo)){			                   
		              			                	System.out.println("OK");
		              			                    login = true;
		              			                    
		              			                }
		              			            }
		              			        } catch (InstantiationException e1) {
		              			            e1.printStackTrace();
		              			        } catch (IllegalAccessException e1) {
		              			            e1.printStackTrace();
		              			        } catch (ClassNotFoundException e1) {
		              			            e1.printStackTrace();
		              			        } catch (SQLException e1) {
		              			            e1.printStackTrace();
		              			        }
		              					
		              				conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");
		              					System.out.println("Polaczenie z baza danych: ... OK");
		              					}
		              					if(login == false)
		              					{
		              						JOptionPane.showMessageDialog(null,"Nieprawidlowe dane do logowania","BLAD",JOptionPane.ERROR_MESSAGE);
		              					}else {
		              						new Questions().setVisible(true);
		              						
		              						qimie=imie;
		              						
		              						dispose();
		              					}
		              					  
		              					
		              					((java.sql.Connection)conn).close();
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
		                	    };
		                	    
		                	    
                               
                                loginThread.start();
                                }
		});
	
	
		btnRejestracja = new JButton("Rejestracja");
		btnRejestracja.setForeground(new Color(255, 255, 255));
		btnRejestracja.setBackground(new Color(0, 0, 0));
		btnRejestracja.setBounds(160, 122, 189, 23);
		contentPane.add(btnRejestracja);
	
		btnRejestracja.addActionListener(new ActionListener() 
		{

		    public void actionPerformed(ActionEvent e) {
		        
		        new Register().setVisible(true);
		        dispose();
		    }

		});
				
		
}}
