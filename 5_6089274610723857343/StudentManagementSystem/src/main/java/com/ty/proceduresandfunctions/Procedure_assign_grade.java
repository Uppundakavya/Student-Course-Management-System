package com.ty.proceduresandfunctions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.ty.Coonectionpool.Connectionpool;

public class Procedure_assign_grade 
{
     public static void updateGrade()
     {
    	 Scanner sc = new Scanner(System.in);
//    	CallableStatement st = CallableState_procedure.callableStatement();
    	
    	try 
    	{
    		Connection con = Connectionpool.getConnection();
    		CallableStatement st = con.prepareCall("call get_grade(?,?,?,?)");
    		System.out.println("enter the updated grade :");
			st.setInt(1, sc.nextInt());
    		System.out.println("enter the student_id :");
			st.setInt(2, sc.nextInt());
			System.out.println("enter the course_id :");
			st.setInt(3, sc.nextInt());
			
			st.registerOutParameter(4, java.sql.Types.INTEGER);
//			System.out.println("enter the updated grade :");
//			st.setInt(1, sc.nextInt());
			
//			either (1) the row count for SQL Data Manipulation Language (DML) statements or 
//			(2) 0 for SQL statements that return nothing
			
			st.execute();
			int res = st.getInt(4);
			System.out.println(res+" row got updated");

            if (res==1)
                System.out.println("successfully inserted...!");
            else
                System.out.println("enter valid details...!");
			
            Connectionpool.receiveConnection(con);
            
//            sc.close();
			
		} 
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
}


