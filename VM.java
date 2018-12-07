/**
 * Auto Generated Java Class.
 */
public class VM {

  int type;
  double CPU, departure;
  
  public VM(int t) {
    type = t;
    calcRates(t);
  }

  void calcRates(int t) {
    if(t==1) {
      CPU = .5;
      departure = .10;
    } else if(t==2) {
      CPU = .25;
      departure = .125;
    } else if(t==3) {
       CPU = .125;
       departure = .03333;
    } else {
       System.out.println("unknown"); 
    }  
  }  
  
  void print() {
    System.out.println("Type: " + type + "\t" + "CPU: " + CPU + " \t Departure rate: " + departure);
  }
  
  double getCap() {
    return CPU;
  }  
  double getDeptRate() {
    return departure;
  }  
  int getType() {
    return type;
  }  
   
  

}
