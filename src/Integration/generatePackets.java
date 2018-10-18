/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Integration;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gauth
 */
class generatePackets implements Runnable
{
    String packetId;
    String shipTime;
    String deliveryTime;  //this one gets initialized after packet has reached destination
    String source;
    String destination;
    String packageStatus;
    ArrayList<String> nodes=new ArrayList<>();
    String weight;

    @Override
    public void run()
    {
        //CORE LOGIC GOES HERE
            System.out.println("Thread id is "+Thread.currentThread().getId());
            this.packetId=Long.toString(Thread.currentThread().getId()); //initialize packet id
            
            ShortestPath t = new ShortestPath();  //compute shortest path
          
            nodes=t.dijkstra(Integer.parseInt(this.source),Integer.parseInt(this.destination));
            System.out.println("Shortest path from source to destination in terms of nodes is : "+nodes);  
            
              //ADD TO DATABASE
        try {            
          
            UserInterface.connection.createStatement().execute("insert into packetDetails1 (packetId,shipTime,deliveryTime,sources,destination,packageStatus,weight)"
                    + "values('"+this.packetId+"','"+this.shipTime+"','"+this.deliveryTime+"','"+UserInterface.hmap.get(this.nodes.get(0))+"','"+UserInterface.hmap.get(this.nodes.get(nodes.size()-1))+"','"+this.packageStatus+"','"+this.weight+"')");
     
          for(int i=1;i<nodes.size()-1;i++)
         {
                    Thread.sleep(4000); //DELAY TO REPRSENT TIME FOR PACKAGE TO MOVE
                    
         UserInterface.connection.createStatement().execute("insert into packetDetails2 (packetId,time,located,packageStatus)"
         + "values('"+this.packetId+"','"+LocalDateTime.now().toString()+"','"+UserInterface.hmap.get(this.nodes.get(i))+"','"+"arrived"+"')");
                 
                 
         }
          
          Thread.sleep(4000);
          this.packageStatus="delivered"; //packet has been delivered
          
        UserInterface.connection.createStatement().execute("update packetDetails1 set deliveryTime='"+LocalDateTime.now().toString()+"'");
        UserInterface.connection.createStatement().execute("update packetDetails1 set packageStatus='"+this.packageStatus+"'");

        } catch (Exception ex) {
            Logger.getLogger(generatePackets.class.getName()).log(Level.SEVERE, null, ex);
        }
                   
        
    }
    
    public generatePackets(String source,String destination,String packageStatus,String weight)
    {
        this.source=source;
        this.destination=destination;
        this.packageStatus=packageStatus;
        this.shipTime=LocalDateTime.now().toString();
        this.deliveryTime="Unknown";
        this.weight=weight;
    }
    
    
    public String getShipTime() {
        return shipTime;
    }

    public void setShipTime(String shipTime) {
        this.shipTime = shipTime;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
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
      
}