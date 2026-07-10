package Statements;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.ty.Coonectionpool.Connectionpool;

public class CallableStatement_function
{
    public static CallableStatement callableStatement_fun1()
    {
		CallableStatement st = null;
    	
//    	Connection con = Connectionpool.getConnection();
    	try(Connection con = Connectionpool.getConnection();) 
    	{
            st = con.prepareCall("select get_student_avg(?)");
		} 
    	catch (SQLException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return st;
    	
    }

	public static CallableStatement callableStatement_fun() {
		// TODO Auto-generated method stub
		return null;
	}
}
