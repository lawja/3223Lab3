package paystation.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of the pay station.
 *
 * Responsibilities:
 *
 * 1) Accept payment; 
 * 2) Calculate parking time based on payment; 
 * 3) Know earning, parking time bought; 
 * 4) Issue receipts; 
 * 5) Handle buy and cancel events.
 *
 * This source code is from the book "Flexible, Reliable Software: Using
 * Patterns and Agile Development" published 2010 by CRC Press. Author: Henrik B
 * Christensen Computer Science Department Aarhus University
 *
 * This source code is provided WITHOUT ANY WARRANTY either expressed or
 * implied. You may study, use, modify, and distribute it for non-commercial
 * purposes. For any commercial use, see http://www.baerbak.com/
 */

public class PayStationImpl implements PayStation {
    
    private int insertedSoFar;
    private int timeBought;
    private Map<Integer, Integer> change = new HashMap<>();
    private RateStrategy strat = new LinearRateStrategy();
    
    public PayStationImpl()
    {
        change.put(25,0);
        change.put(10,0);
        change.put(5,0);
    }
    
    @Override
    public void addPayment(int coinValue)
            throws IllegalCoinException {
        switch (coinValue) {
            case 5: 
                change.put(5 ,change.get(5)+1 );
                insertedSoFar += coinValue;
                timeBought = strat.calculate(insertedSoFar);
                break;
            case 10: 
                change.put(10 ,change.get(10)+1 );
                insertedSoFar += coinValue;
                timeBought = strat.calculate(insertedSoFar);
                break;
            case 25: 
                change.put(25 ,change.get(25)+1 );
                insertedSoFar += coinValue;
                timeBought = strat.calculate(insertedSoFar);
                break;
            default:
                throw new IllegalCoinException("Invalid coin: " + coinValue);
        }
        
    }

    @Override
    public int readDisplay() {
        return timeBought;
    }

    @Override
    public Receipt buy() {
        Receipt r = new ReceiptImpl(timeBought);
        reset();
        return r;
    }

    @Override
    public Map<Integer, Integer> cancel() {
        HashMap<Integer, Integer> copy = new HashMap<>();
        copy.put(5,0);
        copy.put(10,0);
        copy.put(25,0);
        copy.putAll(change);
        reset();
        return copy;
    }
    
    
    @Override public int empty(){
        int temp = insertedSoFar;
        insertedSoFar = 0;
        
        return temp;
    }
    
    @Override
    public void setRateStrategy(int i){
        if(i == 1){
            strat = new LinearRateStrategy();
        }else if(i == 2){
            strat = new ProgressiveRateStrategy();
        }else{
            strat = new AlternatingRateStrategy();
        }
    }
    
    private void reset() {
        timeBought = insertedSoFar = 0;
        change.put(5, 0);
        change.put(10, 0);
        change.put(25, 0);
    }
}
