package com.ty.batchexecution;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.ty.Coonectionpool.Connectionpool;

public class Batch_studentInsert 
{
	static Scanner sc = new Scanner(System.in);
	public static void insertStudent_batch() 
	{
        Connection con = Connectionpool.getConnection();
        String sql = "INSERT INTO students VALUES (?,?,?,?)";

        System.out.println("enter a how many students data to be added :");
        int n = sc.nextInt();
        int i = 1;
        PreparedStatement pst = null;
		try 
	    {
			pst = con.prepareStatement(sql);
		} 
		catch (SQLException e) 
		{
		    // TODO Auto-generated catch block
			e.printStackTrace();
		}
        while (i<=n)
        {
        	try
        	{
        		
                System.out.println("enter id :");
                pst.setInt(1, sc.nextInt());

                System.out.println("enter name :");
                pst.setString(2, sc.next());

                System.out.println("enter email :");
                pst.setString(3, sc.next());

                System.out.println("enter dob (yyyy-mm-dd):");
                pst.setDate(4, Date.valueOf(sc.next()));
                
                pst.addBatch();
                
                i++;
        	}
        	 catch (SQLException e) 
        	 {
                 e.printStackTrace();
             }
        }
            int res = 0;
			try 
			{
				res = pst.executeUpdate();
			} 
			catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

            if (res > 0)
                System.out.println("successfully inserted...!");
            else
                System.out.println("enter valid details...!");
            
            Connectionpool.receiveConnection(con);
        } 
       

    }
	
	

