package com.ty.load_and_connection_creation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class CreateConnection
{
	private static String url = "jdbc:postgresql://localhost:5432/studentdb";
	private static String user = "postgres";
	private static String pass = "root";
	
	private static String url1 = "jdbc:postgresql://localhost:5432/studentdb?user=postgres&password=root";
	
     public static Connection getConnection1()
     {
    	 Connection con = null;
    	 try 
    	 {
			con = DriverManager.getConnection(url,user,pass);
		 } 
    	 catch (SQLException e)
    	 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
    	 
    		 return con;
     }
     
     
     public static Connection getConnection2()
     {
    	 Connection con = null;
    	 try 
    	 {
			con = DriverManager.getConnection(url1);
		 } 
    	 catch (SQLException e)
    	 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
    	 
    		 return con;
     }
     
     
     public static Connection getConnection3()
     {
    	 Connection con = null;
    	 
    	 try 
    	 {
    		FileInputStream file = new FileInputStream("studentdb.properties");
    		Properties p = new Properties();
    		p.load(file);
			con = DriverManager.getConnection(p.getProperty("url"),p);
		 } 
    	 catch (SQLException e)
    	 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 } 
    	 catch (FileNotFoundException e) 
    	 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	 catch (IOException e) 
    	 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
    	 
    		 return con;
     }
     
     
}
