package com.ty.databasemetadata;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import com.ty.Coonectionpool.Connectionpool;

public class DatabaseMeta 
{
    public static void  dbmetadata()
    {
    	 Connection con =  Connectionpool.getConnection();
    	 try 
    	 {
			DatabaseMetaData dmd = con.getMetaData();
			System.out.println("DB product name: " + dmd.getDatabaseProductName() );
			System.out.println("DB version: " +  dmd.getDatabaseProductVersion());
			System.out.println("Driver version: "+ dmd.getDriverVersion());

		 } 
    	 catch (SQLException e) 
    	 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
    }
}
