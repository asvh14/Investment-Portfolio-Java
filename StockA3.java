/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portfolioa3;

/**
 *
 * @author asvh1
 */
public class StockA3 extends InvestmentA3
{
    public StockA3()
    {
        super("","","",0,0.00,0.00);
    }
    
    public StockA3(String type, String symbol, String name, int quantity, double price, double bookValue)
    {
        super(type, symbol, name, quantity, price, bookValue);
    }
    
    @Override
    public String toString()
    {
        return super.toString();
    }
    
    @Override
    public String output()
    {
        return super.output();
    }
    
    @Override
    public boolean equals(Object o)
    {
        if(o == this)
        {
            return true;
        }
        
        if(o == null)
        {
            return false;
        }
        
        if(!(this.getClass().equals(o.getClass())))
        {
            return false;
        }
        
        StockA3 s = (StockA3) o;
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }
}
