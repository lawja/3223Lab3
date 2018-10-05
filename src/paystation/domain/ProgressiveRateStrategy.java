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
        
        
        if(amountInserted < 150){
            time = (amountInserted*2)/2;
        }else if (amountInserted < 350 && amountInserted >= 150){
            time = ((amountInserted-150)*(3/10)) + 60;
        }else{
            time = ((amountInserted-350)/5)+120;
        }
                
        return time;
            
    }
}
