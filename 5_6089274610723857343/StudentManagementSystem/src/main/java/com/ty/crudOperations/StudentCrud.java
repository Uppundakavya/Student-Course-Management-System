package com.ty.crudOperations;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.ty.Coonectionpool.Connectionpool;
import com.ty.batchexecution.Batch_studentInsert;

public class StudentCrud {

    static Scanner sc = new Scanner(System.in);

    
    
    public static void insertStudent() 
    {
    	
        System.out.println("enter choice :");
        System.out.println("1.insert multiple students.");
        System.out.println("2.insert only one student.");
        int n = sc.nextInt();
        switch (n)
        { 
        case 1 :
        {
        	Batch_studentInsert.insertStudent_batch();
        }
        break;
        case 2 :
        {
        	Connection con = Connectionpool.getConnection();
            String sql = "INSERT INTO students VALUES (?,?,?,?)";

            try (PreparedStatement pst = con.prepareStatement(sql)) {

                System.out.println("enter id :");
                pst.setInt(1, sc.nextInt());

                System.out.println("enter name :");
                pst.setString(2, sc.next());

                System.out.println("enter email :");
                pst.setString(3, sc.next());

                System.out.println("enter dob (yyyy-mm-dd):");
                pst.setDate(4, Date.valueOf(sc.next()));

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
        break;
        default :
        {
        	System.out.println("enter valid input....!");
        }
        }  
    }

    
    
    
    public static void updateStudent() {

        Connection con = Connectionpool.getConnection();

        System.out.println("Enter the ID to update:");
        int id = sc.nextInt();

        System.out.println("Choose field to update:");
        System.out.println("1. Name");
        System.out.println("2. Email");
        System.out.println("3. DOB");
        int choice = sc.nextInt();

        String sql = null;

        switch (choice) {
            case 1: 
            	{
            		sql = "UPDATE students SET name=? WHERE id=?"; break;
            	}
            case 2: 
            	{
            		sql = "UPDATE students SET email=? WHERE id=?"; break;
            	}
            case 3: 
            	{
            		sql = "UPDATE students SET dob=? WHERE id=?"; break;
            	}
            default:
            {
                System.out.println("Invalid choice!");
                return;
            }
        }

        try (PreparedStatement pst = con.prepareStatement(sql)) {


            switch (choice) {
                case 1:
                {
                    System.out.println("Enter new name:");
                    pst.setString(1, sc.next());
                    break;
                }
                case 2:
                {
                    System.out.println("Enter new email:");
                    pst.setString(1, sc.next());
                    break;
                }
                case 3:
                {
                    System.out.println("Enter new dob (yyyy-mm-dd):");
                    pst.setDate(1, Date.valueOf(sc.next()));
                    break;
                }
            }

            pst.setInt(2, id);

            int res = pst.executeUpdate();

            if (res > 0)
                System.out.println("Successfully updated!");
            else
                System.out.println("ID not found!");

        } 
        catch (SQLException e) {
            e.printStackTrace();
        }

        Connectionpool.receiveConnection(con);
    }

    
    public static void deleteStudent() {

        Connection con = Connectionpool.getConnection();
        PreparedStatement pst = null;

        System.out.println("Delete by:");
        System.out.println("1. ID");
        System.out.println("2. Name");
        System.out.println("3. Email");

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
                    sql = "DELETE FROM students WHERE name=?";
                    System.out.println("Enter Name:");
                    pst = con.prepareStatement(sql);
                    pst.setString(1, sc.next());
                    break;
                }
                case 3:
                {
                    sql = "DELETE FROM students WHERE email=?";
                    System.out.println("Enter Email:");
                    pst = con.prepareStatement(sql);
                    pst.setString(1, sc.next());
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

    
    

    public static void fetchStudent() {

        String sql = "SELECT * FROM students";
        Connection con = Connectionpool.getConnection();

        try (Statement st = con.createStatement()) 
        {
        	
        	ResultSet rs = st.executeQuery(sql);
        	ResultSetMetaData rsm = rs.getMetaData();
            while (rs.next()) 
            {
                System.out.println(rsm.getColumnName(1) + " : " + rs.getInt("id"));
                System.out.println(rsm.getColumnName(2) + " : " + rs.getString("name"));
                System.out.println(rsm.getColumnName(3) + " : " + rs.getString("email"));
                System.out.println(rsm.getColumnName(4) + " : " + rs.getDate("dob"));
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
