/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ShortestPath;

/**
 *
 * @author gauth
 * //THIS FILE IS ONLY FOR REFERENCE PURPOSE AND NOT LINKED TO THE PROGRAM
 */
// A Java program for Dijkstra's single source shortest path algorithm. 
// The program is for adjacency matrix representation of the graph 
import java.util.*; 
import java.lang.*; 
import java.io.*; 
  
public class ShortestPath 
{ 
    // A utility function to find the vertex with minimum distance value, 
    // from the set of vertices not yet included in shortest path tree 
    static final int V=8; 
    int[] preD = new int[8]; 
    int minDistance(int dist[], Boolean sptSet[]) 
    { 
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index=-1; 
  
        for (int v = 0; v < V; v++) 
            if (sptSet[v] == false && dist[v] <= min) 
            { 
                min = dist[v]; 
                min_index = v; 
            } 
  
        return min_index; 
    } 
  
    // A utility function to print the constructed distance array 
    void printSolution(int dist[], int n) 
    { 
        System.out.println("Vertex   Distance from Source"); 
        for (int i = 0; i < V; i++) 
            System.out.println(i+" tt "+dist[i]); 
    } 
  
    // Funtion that implements Dijkstra's single source shortest path 
    // algorithm for a graph represented using adjacency matrix 
    // representation 
    String dijkstra(int graph[][], int src,int dest) 
    { 
        int dist[] = new int[V]; // The output array. dist[i] will hold 
                                 // the shortest distance from src to i 
  
                                 
        // sptSet[i] will true if vertex i is included in shortest 
        // path tree or shortest distance from src to i is finalized 
        Boolean sptSet[] = new Boolean[V]; 
  
        //INITIALIZE PATH
for(int i=0;i<V;i++)
{
    preD[i]=0;
}
  
String shortestPath="";

// Initialize all distances as INFINITE and stpSet[] as false 
        for (int i = 0; i < V; i++) 
        { 
            dist[i] = Integer.MAX_VALUE; 
            sptSet[i] = false; 
        } 
  
        // Distance of source vertex from itself is always 0 
        dist[src] = 0; 
  
        // Find shortest path for all vertices 
        for (int count = 0; count < V-1; count++) 
        { 
            // Pick the minimum distance vertex from the set of vertices 
            // not yet processed. u is always equal to src in first 
            // iteration. 
            int u = minDistance(dist, sptSet); 
            
            System.out.println("u is "+u);
            // Mark the picked vertex as processed 
            sptSet[u] = true; 
  
            // Update dist value of the adjacent vertices of the 
            // picked vertex. 
            for (int v = 0; v < V; v++) 
            {
                // Update dist[v] only if is not in sptSet, there is an 
                // edge from u to v, and total weight of path from src to 
                // v through u is smaller than current value of dist[v] 
                if (!sptSet[v] && graph[u][v]!=0 && 
                        dist[u] != Integer.MAX_VALUE && 
                        dist[u]+graph[u][v] < dist[v]) 
                {
                   dist[v] = dist[u] + graph[u][v]; 
                    preD[v]=u;
                    System.out.println("Values added to path is "+preD[v]+" "+v+" "+"\n");
                            }
                    }
                     
        } 
  
        // print the constructed distance array 
        printSolution(dist, V); 
 /*       
        System.out.println("Finalized Value in preD");
        System.out.println(preD[0]);
        System.out.println(preD[1]);
        System.out.println(preD[2]);
        System.out.println(preD[3]);
        System.out.println(preD[4]);
*/
        
       /* System.out.println("PATH IS");
       
		int j; 
		for (int i = 0; i < 5; i++) 
		{ 
			if (i!=0) 
			{ 

				System.out.print("Path = " + i); 
				j = i; 
				do
				{ 
					j = preD[j]; 
					System.out.print(" <- " + j); 
				} 
				while(j != 0); 
			} 
			System.out.println(); 
		}
                */
                
             
                System.out.println("PATH IS...\n");
                int k;
                
                //For getting a path of specific node replace the for loop by a single node value.
                for(int i=0;i<8;i++)
                {
                    /*if(dest==i)  //For a specific destination value
                    {*/
                    if(i!=src)
                    {
                        System.out.print("Path= "+i);
                        shortestPath=shortestPath+" "+i;
                        k=i;
                        do {                            
                            k=preD[k];
                            System.out.print(" <--"+ k);
                            shortestPath=shortestPath+" "+k;
                          
                        } while (k!=src);
                    }
                	System.out.println(); 
                    //}
                }
                
                shortestPath = new StringBuffer(shortestPath).reverse().toString(); //reverse the path 
                return  shortestPath.trim();
        
    } 
  
    // Driver method 
    public static void main (String[] args) 
    { 
        /* Let us create the example graph discussed above */
       int graph[][] = new int[][]{{0, 3, 2, 0, 0, 0, 0, 0}, 
                                  {3, 0, 0, 1, 2, 0, 0, 0}, 
                                  {2, 0, 0, 1, 0, 0, 0, 4}, 
                                  {0, 1, 1, 0, 0, 4, 5, 0}, 
                                  {0, 2, 0, 0, 0, 1, 0, 0}, 
                                  {0, 0, 0, 4, 1, 0, 0, 0}, 
                                  {0, 0, 0, 5, 0, 0, 0, 3}, 
                                  {0, 0, 4, 0, 0, 0, 3, 0}, 
                                   
                                 }; 
        ShortestPath t = new ShortestPath(); 
        String shortestPath=t.dijkstra(graph, 0, 0);   //This will return the shortest path between source and destination.

        System.out.println("Shortest path from source to destination is : "+shortestPath);           

        
    } 
}