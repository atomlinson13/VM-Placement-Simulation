/**
 * Auto Generated Java Class.
 */

import java.util.Random;
import java.util.*;

public class Server {

  long idleTime;
  double cap;
  final double capLimit = 1;
  VM[] machines = new VM[15];
  
  public Server() {
    cap = 0;
  }
  // 1 = full 0 = empty 
  
  boolean isIdle(){
    if(cap == 0) {
      return true;
    } else {
      return false;
    }  
  }
  
  boolean canAdd(VM v){
    if((v.getCap() + cap) > 1) {
      return false;
    } else {    
        return true;
      }    
  }
    
  void setCap(double c) {
    cap = c;
    
  }
  
  
  void addVM(VM v){
    for(int i =0; i<15; i++) {
      if(machines[i] == null) {
        //System.out.println("Theres nothing in machine "+i);
        machines[i] = v;
        //System.out.println("The CPU of the new VM: "+ v.getCap());
        cap = cap + v.getCap();
        return;
      }  
    }
  }
  
   boolean bernoulli(double q) {
        Random rand = new Random();
        double p = rand.nextDouble();
        if(p>q) {
          return false;
        } else {
          return true;
        }
 }
  void calcDept() {
    for(int i =0; i<15; i++) { 
      if(machines[i]!=null) {
       
      double dep = machines[i].getDeptRate();
      if(bernoulli(dep)) {
        cap = cap - machines[i].getCap();
        machines[i] = null;    
      }     
      }
    }
  }
  
  double getAvailableCap(){
    double c = 0.0;
    
    if((1 - cap) > 0.0) {
     c = (1 - cap);
    } else if(cap == 0.0) {
      System.out.println("Capacity is empty"); 
    } else if ((1-cap) == 1) {  
      System.out.println("Capacity is full"); 
    } else {
      System.out.println("Capacity out of range"); 
    } 
    return c;
  }  
  
  double getCap(){
    return cap;
  }

  void print() {
     System.out.println("The VMs: \n"); 
       for(int i =0; i<15; i++) {
       System.out.println("VM " + i + ": " + machines[i] + "\t");
    } 
  }

}
