/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

/**
 *
 * @author jakelawrence
 */
public class ProgressiveRateStrategy implements RateStrategy{
    
    @Override
    public int calculate(int amountInserted) {
        int time = 0;
        
        if(amountInserted > 150){
            time += 30 * 2; //150/5 *2
            if(amountInserted > 350){
                time += (int) (200/5) * 1.5;
                if(amountInserted > 450){
                    time += (amountInserted - 550)/5;
                }else{
                    time += (int) ((amountInserted - 350)/5);
                }
            }else{
                time += (int) ((amountInserted - 150)/5) * 1.5;
            }
        }else{
            time += 2 * (amountInserted/5);
        }
                
        return time;
            
    }
}
