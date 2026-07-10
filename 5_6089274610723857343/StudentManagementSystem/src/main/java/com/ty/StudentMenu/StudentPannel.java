package com.ty.StudentMenu;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.ty.crudOperations.EnrollmentCrud;
import com.ty.crudOperations.StudentCrud;
import com.ty.proceduresandfunctions.Function_get_student_avg;

public class StudentPannel 
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        char ch = 'y';

        while (ch == 'Y' || ch == 'y')
        {
            System.out.println("\n===== Student Menu =====");
            System.out.println("1. Register New Student");
            System.out.println("2. Update Student Profile");
            System.out.println("3. Enroll in a Course");
            System.out.println("4. View Enrolled Courses");
            System.out.println("5. View Grades");
            System.out.println("6. View GPA");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();

//            try {
//                choice = sc.nextInt();
//            } 
//            catch (InputMismatchException e) {
//                System.out.println("Invalid input! Please enter numbers only.");
//                sc.nextLine(); // clear buffer
//                continue;
//            }

            switch(choice)
            {
                case 1:
                    StudentCrud.insertStudent();
                    break;

                case 2:
                    StudentCrud.updateStudent();
                    break;

                case 3:
                    EnrollmentCrud.insertEnrollment();
                    break;

                case 4:
                    EnrollmentCrud.fetchEnrollments(); 
                    break;

                case 5:
                    EnrollmentCrud.fetchEnrollments();  // or fetchGrades() if you create it
                    break;

                case 6:
                    Function_get_student_avg.get_student_avg(sc);
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
            System.out.println("Enter Y/y to continue OR N/n to exit: ");
            ch = sc.next().charAt(0);
        }

//        sc.close();
        System.out.println("Exiting Student Menu...");
    }
}
