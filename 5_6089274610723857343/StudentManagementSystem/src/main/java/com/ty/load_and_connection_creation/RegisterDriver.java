package com.ty.load_and_connection_creation;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

public class RegisterDriver 
{
   public static void register()
   {
	   Driver d = new Driver();
	   try
	   {
		DriverManager.registerDriver(d);
	   } 
	   catch (SQLException e) 
	   {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
   }
}
