/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package databaseconnection;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gauth
 * //THIS FILE IS ONLY FOR REFERENCE PURPOSE AND NOT LINKED TO THE PROGRAM
 */


public class DatabaseConnection {
public static java.sql.Connection connection;
    
/**
     * @param args the command line arguments
     */
public DatabaseConnection()
{
    try{
        Class.forName("com.mysql.jdbc.Driver");
        System.out.println("JDBC SUCCESSFUL");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Tracking","root","Hydrogen@01");
        System.out.println("Connection established");
        //Statement createStatement = connection.createStatement();
       // createStatement.execute("insert into testing values ('gautham93')");
        
    }
    catch(Exception e)
    {
       System.out.println(e);
    }
    

}


public static void main(String[] args) {
        // TODO code application logic here
        new DatabaseConnection();
        generatePackets packets =new generatePackets();
        packets.setShipTime(10f);
        packets.setDeliveryTime(20f);
        packets.setSource("nc");
        packets.setDestination("fl");
        packets.setStatus("Pending");
        
    try {
        packets.addPacketDb(connection.createStatement(),packets);
    } catch (SQLException ex) {
        Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, ex);
    }
      
    }
    
}



class generatePackets
{
    int packetId;
    float shipTime;
    float deliveryTime;
    String source;
    String destination;
    String packageStatus;

    public float getShipTime() {
        return shipTime;
    }

    public void setShipTime(float shipTime) {
        this.shipTime = shipTime;
    }

    public float getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(float deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStatus() {
        return packageStatus;
    }

    public void setStatus(String packageStatus) {
        this.packageStatus = packageStatus;
    }
   
    public void addPacketDb(Statement createStatement,generatePackets packets)
    {
        float a=packets.getShipTime();
        float b=packets.getDeliveryTime();
        String c=packets.getSource();
        String d=packets.getDestination();
        String e=packets.getStatus();
        
        try {          
           createStatement.execute("insert into packetData (shipDate,deliveryDate,source,destination,packageStatus)"
            + "values('"+a+"','"+b+"','"+c+"','"+d+"','"+e+"')");

        } catch (SQLException ex) {
            Logger.getLogger(generatePackets.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}