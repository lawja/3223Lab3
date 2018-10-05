/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paystation.domain;

import java.util.Calendar;

/**
 *
 * @author jakelawrence
 */
public class AlternatingRateStrategy implements RateStrategy{
    RateStrategy linearStrat = new LinearRateStrategy();
    RateStrategy progressiveStrat = new ProgressiveRateStrategy();
    
    @Override
    public int calculate(int amountEntered){
        
        int time = 0;
        
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_WEEK);
        
        // if sunday or saturday
        if(day == 1 || day == 7){
            time = progressiveStrat.calculate(amountEntered);
        }else{ // weekday
            time = linearStrat.calculate(amountEntered);
        }
        
        
        return time;
    }
    
    
}
