package Statements;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.ty.Coonectionpool.Connectionpool;

public class CallableState_procedure
{
    public static CallableStatement callableStatement()
    {
		CallableStatement st = null;
    	
//    	Connection con = Connectionpool.getConnection();
    	try(Connection con = Connectionpool.getConnection();) 
    	{
            st = con.prepareCall("call assign_grade(?,?,?)");
		} 
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return st;
    	
    }
    

}
