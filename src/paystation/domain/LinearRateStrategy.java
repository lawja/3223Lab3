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
public class LinearRateStrategy implements RateStrategy{
    
    @Override
    public int calculate(int amountInserted){
        return amountInserted / 5 * 2;
    }
}
