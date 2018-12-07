/**
 * @(#)ServerSim.java
 *
 * ServerSim 
 *
 * @author
 * @version 1.00 2018/9/23
 */


import java.util.Random;
import java.util.*;

public class ServerSim {

      static double lamda = 0.9;
      static final int N = 10;
      static int timeSlots = 5000;
      static  int currentSlot = 0;
      static long[] activeAmount = new long[10000];
       static long[] idleAmount = new long[10000];
      static long[] serverAmount = new long[10000];
      //Lengths after each time slot 
      static double[] serverLengths = new double[50];
      static Server[] server = new Server[1000];
      static int numServers = 1;
      static long serverSlotCount = 0;
     
    public static void main(String[] args) {
      server[0] = new Server();
      
      for(int i =1; i<=timeSlots; i++) {

        currentSlot++;
        
        /**************** Departures *******************/
        for(int n =0; n<numServers; n++) {
          if(!server[n].isIdle()) {
            serverSlotCount++;
            server[n].calcDept();
          }  
        }   
        
        /**************** Arrivals *******************/

        int arrivals = numberOfArrivals(lamda, N);

        //Best fit or First Fit
        for(int j =0; j<arrivals; j++) {
          
          //Determines the type of VM
          int t = type();
          VM v = new VM(t);
          BestFit(v);
        }

       serverAmount[currentSlot] = numServers; 
       calcAmount(); 
       // System.out.println("The number of active servers for time slot: " + currentSlot + " is: " + activeAmount[currentSlot]);
      }
      for(int x =0; x<timeSlots; x++) { 
      //  System.out.println("Time Slot: " + x);
        // System.out.println("Active | Idle | Total");
       //  System.out.println("------|------|-----");
         System.out.println(""+  activeAmount[x] +"\t|\t"+ idleAmount[x] +"\t|\t"+  serverAmount[x]);

      } 
       
    }  
    
 /* Calculates the number of active servers per time slot and adds them to an array */
    public static void calcAmount() {
      int count = 0;
      int idle = 0;
      for(int j =0; j<numServers; j++) {
        if(!server[j].isIdle()) {
          count++;
        } else {
          idle++;
        }  
      }  
      activeAmount[currentSlot] = count;
      idleAmount[currentSlot] = idle;
    }
    
    
   /**************** First Fit *******************/
    public static void FirstFit(VM v) {
      for(int k =0; k<numServers; k++) {
        if(server[k].canAdd(v)) {
          // System.out.println("Server at " + k + " can add");
          server[k].addVM(v);
         //  System.out.println("The server capacity after assignment: "  + server[k].getAvailableCap());
          return;
        }  
      }
      turnOnServer();
      FirstFit(v);
      return;
    }
    
    /**************** BestFit *******************/
    public static void BestFit(VM v) {
      Server smallest = server[0];

     int index = 0;
     
      for(int k = 1; k<numServers; k++) {
        if(server[k].getCap() < smallest.getCap() && server[k].canAdd(v) && !server[k].isIdle()) {
          smallest = server[k];
          index = k;
        }  
      } 
      
      /** Catching if the first server is the smallest and available. 
       * Otherwise needs to find an idle server or turn on a new one  **/
      
      if(server[0] == smallest && server[0].canAdd(v)){
        server[0].addVM(v);
        return;
      }  
      
      // If hasn't found, then finds idle server or turns on new one
      if(index==0) {
       // System.out.println("No server was found! Looking for Idle server..");
        for(int k = 0; k<numServers; k++) {
          if(server[k].isIdle()) {
            server[k].addVM(v);
            return;
          }  
        }
        
        /* Turns on server if no server is available */
          turnOnServer();
          index = numServers-1;
      }  
       server[index].addVM(v);
      // System.out.println("The server capacity after assignment: "  + server[index].getAvailableCap());
      }
    
    /**************** Arrivals *******************/
    public static int numberOfArrivals(double lam, int N) {
      int count = 0;
      boolean trials;
      for(int i =0; i<N; i++) {
       trials = bernoulli(lam);
       if(trials==true){
        count++;
       }
      }
      return count;
    }
    
    public static void turnOnServer() {
      server[numServers] = new Server();
      numServers++;
    }  
          
 /**************** What type of Vm is it *******************/ 
 public static int type() {
        Random rand = new Random();
        double t = rand.nextDouble();
        if(t <= .33) {
          return 1;
        } else if(.33 < t && t < .66){
          return 2;
        } else if(t >= .66) {
          return 3;
        }  else {
          return 0;
        }  
 }
 
 public static boolean bernoulli(double q) {
        Random rand = new Random();
        double p = rand.nextDouble();
        if(p>q) {
          return false;
        } else {
          return true;
        }
 }

}




