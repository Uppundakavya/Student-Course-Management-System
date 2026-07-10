package com.ty.crudOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.ty.Coonectionpool.Connectionpool;

public class CourseCrud 
{
	
	static Scanner sc = new Scanner(System.in);

	public static  void insertCourses() 
	{
        Connection con = Connectionpool.getConnection();
        String sql = "INSERT INTO courses VALUES (?,?,?)";

        try (PreparedStatement pst = con.prepareStatement(sql)) {

            System.out.println("enter id :");
            pst.setInt(1, sc.nextInt());

            System.out.println("enter name :");
            pst.setString(2, sc.next());

            System.out.println("enter credits :");
            pst.setInt(3, sc.nextInt());

            int res = pst.executeUpdate();

            if (res > 0)
                System.out.println("successfully inserted...!");
            else
                System.out.println("enter valid details...!");
        } 
        catch (SQLException e) {
            e.printStackTrace();
        }
        Connectionpool.receiveConnection(con);
        
    }
	
	
	
	
	public static  void updateCourses() 
	{
		
        Connection con = Connectionpool.getConnection();

        System.out.println("Enter the ID to update:");
        int id = sc.nextInt();

        System.out.println("Choose field to update:");
        System.out.println("1. Name");
        System.out.println("2. Credits");
        int choice = sc.nextInt();

        String sql = null;

        switch (choice) {
            case 1: 
            	{
            		sql = "UPDATE courses SET name=? WHERE id=?"; break;
            	}
            case 2: 
            	{
            		sql = "UPDATE courses SET Credits=? WHERE id=?"; break;
            	}
            default:
            {
                System.out.println("Invalid choice!");
                return;
            }
        }

        try (PreparedStatement pst = con.prepareStatement(sql)) {


            switch (choice) 
            {
                case 1:
                {
                    System.out.println("Enter new name:");
                    pst.setString(1, sc.next());
                    break;
                }
                case 2:
                {
                    System.out.println("Enter new Credits:");
                    pst.setInt(1, sc.nextInt());
                    break;
                }
            }

            pst.setInt(2, id);

            int res = pst.executeUpdate();

            if (res > 0)
            {
                 System.out.println("Successfully updated!");
            }
            else
            {
                System.out.println("ID not found!");

            }
        } 
        catch (SQLException e) 
        {
            e.printStackTrace();
        }

        Connectionpool.receiveConnection(con);
        
    }
	
	
	
	
	public static void deleteCourses() {

        Connection con = Connectionpool.getConnection();
        PreparedStatement pst = null;

        System.out.println("Delete by:");
        System.out.println("1. Id");
        System.out.println("2. Name");
        System.out.println("3. Credits");

        int n = sc.nextInt();
        String sql = null;

        try {

            switch (n) {
                case 1:
                {
                    sql = "DELETE FROM courses WHERE id=?";
                    System.out.println("Enter ID:");
                    pst = con.prepareStatement(sql);
                    pst.setInt(1, sc.nextInt());
                    break;
                }

                case 2:
                {
                    sql = "DELETE FROM courses WHERE name=?";
                    System.out.println("Enter Name:");
                    pst = con.prepareStatement(sql);
                    pst.setString(1, sc.next());
                    break;
                }
                case 3:
                {
                    sql = "DELETE FROM courses WHERE credits=?";
                    System.out.println("Enter Credits:");
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
            {
                System.out.println("Successfully deleted!");
            }
            else
            {
            	 System.out.println("Record not found!");
            }

        } 
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        Connectionpool.receiveConnection(con);
    }
	
	
	
	public static void fetchCourses() {

        String sql = "SELECT * FROM courses";
        Connection con = Connectionpool.getConnection();

        try (Statement st = con.createStatement()) 
        {
        	ResultSet rs = st.executeQuery(sql);
            while (rs.next()) 
            {
                System.out.println("ID : " + rs.getInt("id"));
                System.out.println("Name : " + rs.getString("name"));
                System.out.println("Credits : " + rs.getInt("credits"));
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
