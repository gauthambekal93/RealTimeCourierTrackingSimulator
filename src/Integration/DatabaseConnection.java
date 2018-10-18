/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;

import java.sql.DriverManager;

/**
 *
 * @author gauth
 */
//DATABASE CONNECTIVITY
class DatabaseConnection {
public static java.sql.Connection connection;
    
/**
     * @param args the command line arguments
     */
public java.sql.Connection getConnection()
{
    try{
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("JDBC SUCCESSFUL");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tracking","root","Hydrogen@01");
        System.out.println("Connection established");
        //Statement createStatement = connection.createStatement();
       // createStatement.execute("insert into testing values ('gautham93')");
       return connection;
        
    }
    catch(Exception e)
    {
       System.out.println(e);
    }
    return null;
    
}
}

