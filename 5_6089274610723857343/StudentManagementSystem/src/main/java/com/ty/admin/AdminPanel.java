package com.ty.admin;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ty.crudOperations.CourseCrud;
import com.ty.crudOperations.EnrollmentCrud;
import com.ty.crudOperations.StudentCrud;
import com.ty.proceduresandfunctions.Procedure_assign_grade;

public class AdminPanel 
{

//		 Add / Update / Delete courses (CRUD)
//		   View all students
//		   View all courses
//		   View all enrollments
//		   Assign grades

	
	 public static void main(String[] args)
	    {
	        Scanner sc = new Scanner(System.in);
	        char ch = 'y';

	        while (ch == 'Y' || ch == 'y')
	        {
	            System.out.println("===== Admin Panel =====");
	            System.out.println("1. Add Course");
	            System.out.println("2. Update Course");
	            System.out.println("3. Delete Course");
	            System.out.println("4. View All Students");
	            System.out.println("5. View All Courses");
	            System.out.println("6. View All Enrollments");
	            System.out.println("7. Assign Grade");
	            System.out.println("Enter your choice: ");

	            int choice = 0;

	            try 
	            {
	                choice = sc.nextInt();
	            } 
	            catch (InputMismatchException e) 
	            {
	                System.out.println("Invalid input! Please enter numbers only.");
	                sc.nextLine(); // clear buffer
	                continue;      // restart loop
	            }

	            switch (choice)
	            {
	                case 1:
	                    CourseCrud.insertCourses();
	                    break;

	                case 2:
	                    CourseCrud.updateCourses();
	                    break;

	                case 3:
	                    CourseCrud.deleteCourses();
	                    break;

	                case 4:
	                    StudentCrud.fetchStudent();
	                    break;

	                case 5:
	                    CourseCrud.fetchCourses();
	                    break;

	                case 6:
	                    EnrollmentCrud.fetchEnrollments();
	                    break;

	                case 7:
	                    Procedure_assign_grade.updateGrade();
	                    break;

	                default:
	                    System.out.println("Invalid choice! Please select from the menu.");
	            }

	            System.out.println("Enter Y/y to continue OR N/n to exit: ");
	            ch = sc.next().charAt(0);
	        }

	        sc.close();
	        System.out.println("Exiting Admin Panel...");
	    }
}
