package kolokwium;

import java.io.IOException;
import java.awt.EventQueue;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Jdbc extends Thread
{
  // Default no of threads to 10
  private static int iloscWatkow = 10;

  int idWatku;

  static  int nextId = 1;
  static  Connection s_conn = null;
  static  boolean   connectionStatus = false;

  
  synchronized static int getNextId()
  {
      return nextId++;
  }

  public static void main (String args []) 
  {
	    
	    
	  
    try  
    {  
     
      DriverManager.registerDriver(new com.mysql.jdbc.Driver());

   
      if (connectionStatus)
          s_conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root","");

      // WATKI
      Thread[] threadList = new Thread[iloscWatkow];

      // narodzennia potokiv
      for (int i = 0; i < iloscWatkow; i++)
      {
          threadList[i] = new Jdbc();
          threadList[i].start();
      }
    
      // vsi v odyn czas
      setPermit ();
      
      // wait
      for (int i = 0; i < iloscWatkow; i++)
      {
          threadList[i].join();
      }

      if (connectionStatus)
      {
          s_conn.close();
          s_conn = null;
      }
          
    }
    catch (Exception e)
    {
       e.printStackTrace();
    }
    

  }  

  public Jdbc()
  {
     super();
     // id do watku
     idWatku = getNextId();
  }

  public void run()
  {
    Connection conn = null;
    ResultSet  rs   = null;
    Statement  st = null;

    try
    {    

      if (connectionStatus)
        st = s_conn.createStatement (); 
      else
      {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/test", "root","");
        st = conn.createStatement (); 
      }

      while (!getPermit())
        yield();
          
      rs = st.executeQuery ("Select * from uzytkowniki");
          
      while (rs.next())
      {
        System.out.println(idWatku +" Watek "+ "| Id Uzytkownika : " + rs.getInt(1) + "| Imie : " + rs.getString(2));
        yield();
      }
      

      rs.close();
      rs = null;
  
    
      st.close();
      st = null;
  
    
      if ((!connectionStatus) && (conn != null))
      {
         conn.close();
         conn = null;
      }
      System.out.println(idWatku +" Watek");
    }
    catch (Exception e)
    {
      System.out.println(idWatku +" Watek -" +" Exception: " + e);
      e.printStackTrace();
      return;
    }
  }

  static boolean permit = false;
  static synchronized void setPermit () { 
	  permit = true; 
	  }
  synchronized boolean getPermit () {
	  return permit; 
	  }

}
