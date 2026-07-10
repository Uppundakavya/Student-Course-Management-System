package com.ty.proceduresandfunctions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.ty.Coonectionpool.Connectionpool;

public class Function_get_student_avg
{
	
	public static void get_student_avg(Scanner sc)
    {
   	 
   	 
//     CallableStatement st =  CallableStatement_function.callableStatement_fun();  
     
     
   	try 
   	{
   		Connection con = Connectionpool.getConnection();
   		CallableStatement st  = con.prepareCall("select get_student_avg(?)");
   		    System.out.println("enter the student_id :");
			st.setInt(1, sc.nextInt());
			
            ResultSet rs =st.executeQuery();
    	    
    	    System.out.println("done");
    	   
    	    while(rs.next())
    	    {
    	    	System.out.println("GPA : " + rs.getInt(1));
    	    }
			
//			System.out.println("GPA : " + res );
			
			 Connectionpool.receiveConnection(con);
			 
		} 
   	catch (SQLException e) 
   	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
