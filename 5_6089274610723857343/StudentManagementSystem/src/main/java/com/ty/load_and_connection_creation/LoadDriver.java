package com.ty.load_and_connection_creation;

public class LoadDriver 
{
     public static void load()
     {
    	 try 
    	 {
			Class.forName("org.postgresql.Driver");
		 } 
    	 catch (ClassNotFoundException e) 
    	 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
     }
}
