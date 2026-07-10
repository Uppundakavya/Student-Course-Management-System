package com.ty.TransactionManagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Scanner;

import com.ty.Coonectionpool.Connectionpool;

public class Transaction 
{
	public static void enrollMultipleCourses() 
	{

	    Connection con = Connectionpool.getConnection();
        Scanner sc = new Scanner(System.in);

	    
//	       Scanner sc = new Scanner(System.in);
	    String checkStudent = "SELECT id FROM students WHERE id = ?";
	    String checkCourse = "SELECT id FROM courses WHERE id = ?";
	    String insertSql = "INSERT INTO enrollments(id, student_id, course_id) VALUES (?,?,?)";

	    try {

	        con.setAutoCommit(false);   // -------- START TRANSACTION --------

	        // 1) ASK USER DETAILS

	        
	        System.out.print("Enter student id: ");
	        int studentId = sc.nextInt();

	        System.out.print("How many courses to enroll?: ");
	        int count = sc.nextInt();

	        int[] courses = new int[count];
	        for (int i = 0; i < count; i++) 
	        {
	            System.out.print("Enter course id " + (i + 1) + ": ");
	            courses[i] = sc.nextInt();
	        }



	        // 2) VALIDATE STUDENT ONCE

	        try (PreparedStatement pst = con.prepareStatement(checkStudent)) {
	            pst.setInt(1, studentId);
	            ResultSet rs = pst.executeQuery();

	            if (!rs.next()) {
	                System.out.println("Student does not exist....!");
	                con.rollback();
	                return;
	            }
	        }

	        System.out.println(" Student verified.");



	        // 3) LOOP COURSES WITH SAVEPOINT PER COURSE

	        for (int courseId : courses) 
	        {

	            Savepoint sp = con.setSavepoint("course_" + courseId);
	            
	            System.out.println("Processing Course " + courseId );

	            //Validate Course
	            
	            try (PreparedStatement pst = con.prepareStatement(checkCourse)) 
	            {
	                pst.setInt(1, courseId);
	                ResultSet rs = pst.executeQuery();

	                if (!rs.next()) 
	                {
	                    System.out.println(" Course " + courseId + " does not exist...!");
	                    System.out.println("Rolling back only this course...");
	                    con.rollback(sp);      // rollback only this course
	                    continue;              // move to next course
	                }
	            }

	            System.out.println(" Course " + courseId + " verified.");


	            // Insert Enrollment
	            try (PreparedStatement pst = con.prepareStatement(insertSql)) 
	            {
                    System.out.println("enter id :");
	                pst.setInt(1, sc.nextInt());
	                pst.setInt(2, studentId);
	                pst.setInt(3, courseId);
	                pst.executeUpdate();
	                System.out.println("Enrolled student into course " + courseId);
	            } 
	            catch (Exception e) 
	            {
	                System.out.println("Error enrolling course " + courseId);
	                con.rollback(sp);   // rollback only this course
	            }
	        }



	        // 4) COMMIT ALL SUCCESSFUL COURSE ENROLLMENTS

	        con.commit();
	        System.out.println(" All valid course enrollments committed!");

	    }
	    catch (Exception e) 
	    {
	        try 
	        {
	            con.rollback();
	            System.out.println(" Major error! Entire transaction rolled back.");
	        } 
	        catch (SQLException ex) 
	        {
	            ex.printStackTrace();
	        }

	        e.printStackTrace();

	    } 
	    finally 
	    {
	        try 
	        {
	            con.setAutoCommit(true);
	        } 
	        catch (SQLException e) 
	        {
	            e.printStackTrace();
	        }
	        
	        Connectionpool.receiveConnection(con);
	    }
	    
	    sc.close();
	}


}
