package com.ty.crudOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.ty.Coonectionpool.Connectionpool;
import com.ty.TransactionManagement.Transaction;

public class EnrollmentCrud 
{
	static Scanner sc = new Scanner(System.in);
	
	 public static void insertEnrollment() 
	 {
		 
		    System.out.println("enter your choice :");
		    System.out.println("1.single enrollment");
		    System.out.println("2.multiple enrollments");
		    int n = sc.nextInt();
		    switch (n)
		    {
		    case 1 :
		    {
		    	Connection con = Connectionpool.getConnection();
		        String sql = "INSERT INTO enrollments(id,student_id,course_id) VALUES (?,?,?)";

		        try (PreparedStatement pst = con.prepareStatement(sql)) {

		            System.out.println("enter id :");
		            pst.setInt(1, sc.nextInt());

		            System.out.println("enter Student_id :");
		            pst.setInt(2, sc.nextInt());

		            System.out.println("enter Course_id :");
		            pst.setInt(3, sc.nextInt());
		            
//		            pst.setString(4,"null");

		            int res = pst.executeUpdate();

		            if (res > 0)
		            {
		                System.out.println("successfully inserted...!");
		            }
		            else
		            {
		                System.out.println("enter valid details...!");
		            }
		        } 
		        catch (SQLException e) 
		        {
		            e.printStackTrace();
		        }
		        Connectionpool.receiveConnection(con);
		    }
		    break;
		    
		    case 2 :
		    {
		    	Transaction.enrollMultipleCourses();
		    }
		    break;
		    
		    default :
		    {
		    	System.out.println("invalid input....!");
		    }
		    }
	        
	    }
	 
	 
	 
	 public static void updateEnrollments() 
	 {

	        Connection con = Connectionpool.getConnection();

	        System.out.println("Enter the student id to update:");
	        int id = sc.nextInt();
            
	        String sql = "UPDATE students SET grade=? WHERE Student_id=?";

	        try (PreparedStatement pst = con.prepareStatement(sql))
	        {
	            System.out.println("Enter new grade :");
	            pst.setInt(1, sc.nextInt());
	            pst.setInt(2, id);
	            int res = pst.executeUpdate();

	            if (res > 0)
	                System.out.println("Successfully updated!");
	            else
	                System.out.println("Student_ID not found!");
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }

	        Connectionpool.receiveConnection(con);
	    }
	 
	 
	 
	 public static void deleteEnrollment() {

	        Connection con = Connectionpool.getConnection();
	        PreparedStatement pst = null;

	        System.out.println("Delete by:");
	        System.out.println("1. ID");
	        System.out.println("2. Student_id");
	        System.out.println("3. Course_id");

	        int n = sc.nextInt();
	        String sql = null;

	        try {

	            switch (n) {
	                case 1:
	                {
	                    sql = "DELETE FROM students WHERE id=?";
	                    System.out.println("Enter ID:");
	                    pst = con.prepareStatement(sql);
	                    pst.setInt(1, sc.nextInt());
	                    break;
	                }

	                case 2:
	                {
	                    sql = "DELETE FROM students WHERE student_id=?";
	                    System.out.println("Enter Student_id:");
	                    pst = con.prepareStatement(sql);
	                    pst.setInt(1, sc.nextInt());
	                    break;
	                }
	                case 3:
	                {
	                    sql = "DELETE FROM students WHERE Course_id=?";
	                    System.out.println("Enter course_id:");
	                    pst = con.prepareStatement(sql);
	                    pst.setInt(1, sc.nextInt());
	                    break;
	                }

	                default:
	                {
	                    System.out.println("Invalid option!");
	                    return;
	                }
	            }

	            int res = pst.executeUpdate();

	            if (res > 0)
	                System.out.println("Successfully deleted!");
	            else
	                System.out.println("Record not found!");

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        Connectionpool.receiveConnection(con);
	   
	 }
	 
	 
	 public static void fetchEnrollments() {

	        String sql = "SELECT * FROM enrollments where Student_id = ?";
	        Connection con = Connectionpool.getConnection();
            System.out.println("enter the student_id to get details :");
	        try 
	        {
	        	PreparedStatement st = con.prepareStatement(sql);
	        	st.setInt(1, sc.nextInt());
	        	ResultSet rs = st.executeQuery();
	            while (rs.next()) 
	            {
	                System.out.println("ID : " + rs.getInt("id"));
	                System.out.println("Student_id : " + rs.getInt("Student_id"));
	                System.out.println("Course_id : " + rs.getInt("course_id"));
	                System.out.println("grade : " + rs.getInt("grade"));
	                System.out.println("-----------------------------------");
	            }
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }

	        Connectionpool.receiveConnection(con);
	    }
}
