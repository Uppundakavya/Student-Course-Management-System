package com.ty.Coonectionpool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.ty.load_and_connection_creation.CreateConnection;

public class Connectionpool  
{
	private static List<Connection> pool = new ArrayList<>();
    
//    private static String url = "jdbc:postgresql://localhost:5432/studentdb";
//    private static String user = "postgres";
//    private static String pass = "root";
//    private static String path = "org.postgresql.Driver";
	
	
    private static final int pool_size  = 5;
    static Scanner sc = new Scanner(System.in);
    static 
    {
//   	 try 
//   	 {
//			Class.forName(path);
    	System.out.println("enter the your choice :");
    	System.out.println("1.load:");
    	System.out.println("2.register:");
    	int n = sc.nextInt();
    	switch(n)
    	{
    	case 1 :
    	{
    		com.ty.load_and_connection_creation.LoadDriver.load();
    	}
    	break;
    	case 2 :
    	{
    		com.ty.load_and_connection_creation.RegisterDriver.register();
    	}
    	break;
    	default :
    	{
    		  System.out.println("enter a valid input....!");
    	}
    	}
//   	 }
//   	 catch (ClassNotFoundException e) 
//   	 {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//	 }
   	       
   	 for (int i =0;i<pool_size;i++)
   	 {
   		 pool.add(createConnection());
   	 }
   	
    }
    
    
    public static Connection   getConnection()
    {
   	 Connection con = null;
//   	 try 
//   	 {
//   		 
//   		 
//   		con = DriverManager.getConnection(url,user,pass);
//     } 
//   	 catch (SQLException e) 
//   	 {
//			e.printStackTrace();
//     }
   	 
       System.out.println("enter your choice of creating connection:");
       System.out.println("1.three string objects");
       System.out.println("2.one string object");
       System.out.println("3.string and properties");
       int c = sc.nextInt();
       switch(c)
       {
       case 1 :
       {
    	 con =  CreateConnection.getConnection1();
       }
       break;
       case 2 :
       {
    	   con =  CreateConnection.getConnection2();
       }
       break;
       case 3 :
       {
    	   con =  CreateConnection.getConnection3();
       }
       break;
       default :
       {
    	   System.out.println("enter valid input...!");
       }
       }
   	 return con;
    }
    
    public static  Connection createConnection()
    {
   	if (!pool.isEmpty())
   	{
		return pool.remove(0);
   	}
   	else
   	{
   		return CreateConnection.getConnection1();
   	}
   	 
    }
    
    public static void receiveConnection(Connection con)
    {
   	 if (pool.size()>pool_size)
   	 {
   		 pool.add(con);
   	 }
   	 else
   	 {
   		 try 
   		 {
				con.close();
		 } 
   		 catch (SQLException e) 
   		 {
				// TODO Auto-generated catch block
				e.printStackTrace();
		 }
   	 }
    }
}
