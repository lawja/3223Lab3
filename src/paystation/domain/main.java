/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author jakelawrence
 */
public class main {
    public static void main(String[] args) throws IllegalCoinException{
        int i = 0;
    
        System.out.println(i);
        
        PayStationImpl paystation = new PayStationImpl();
        
        Scanner stdIn = new Scanner(System.in);
        
        while(i != -1){
            displayMenu();
            
            i = stdIn.nextInt();
            stdIn.nextLine(); // flush stdin
            
            
            switch(i){
                case 1:
                    
                    
                    int amount = 0;
                    
                    while(amount != -1){
                        System.out.println("\nEnter 5, 10, or 25 cent coins." +
                                           " (-1 to conclude your deposit)");
                        
                        System.out.print(">>> ");
                        
                        amount = stdIn.nextInt();
                        stdIn.nextLine();
                        
                        if(amount != -1){
                            try{
                                paystation.addPayment(amount);
                                System.out.println("\nDeposit successful\n");
                            }catch (IllegalCoinException e){
                                System.out.println("\nIllegal coin entered.\n");
                            }
                        }
                    }
                    
                    break;
                case 2:
                    System.out.println("\nCurrent Time Purchased: " +
                                       paystation.readDisplay() + "\n");
                    break;
                case 3:
                    int receipt_val = paystation.buy().value();
                    
                    System.out.println("Transaction complete");
                    System.out.println("Receipt Time Value: " + receipt_val +
                                       "\n");
                    break;
                case 4:
                    Map<Integer, Integer> cancel_val;
                    
                    cancel_val = paystation.cancel();
                    
                    int nickel_count = cancel_val.get(5);
                    int dime_count = cancel_val.get(10);
                    int quarter_count = cancel_val.get(25);
                    
                    int cancel_total = (nickel_count*5) + (dime_count*10) +
                                       (quarter_count*25);
                    
                    System.out.println("\n-- Change Returned --");
                    System.out.println("> Nickels: " + nickel_count);
                    System.out.println("> Dimes: " + dime_count);
                    System.out.println("> Quarters: " + quarter_count);
                    System.out.println(">> Total: " + cancel_total + "\n");
                    
                    
                    break;
                case 5:    
                    System.out.println("Change rate");
                    break;
                case -1:
                    break;
                default:
                    System.out.println("\nUnable to recognize command\n");
            }
        }
        
    }
    
    public static void displayMenu(){
        System.out.println("\n---- OPTIONS ----");
        System.out.println("[1] Deposit");
        System.out.println("[2] Display Time");
        System.out.println("[3] Purchase Ticket");
        System.out.println("[4] Cancel Transaction");
        System.out.println("[5] Change Rate (maintenance only)");
        System.out.println("[-1] Exit\n");
        System.out.print(">>> ");
    }
    
}
