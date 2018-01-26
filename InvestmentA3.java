/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package portfolioa3;

import java.util.Objects;

/**
 *
 * @author asvh1
 */
public abstract class InvestmentA3 extends Exception
{
    private String type;
    private String symbol;
    private String name;
    private int quantity;
    private double price;
    private double bookValue;
    //InvestmentA3 inv = new InvestmentA3();
    
    public InvestmentA3(String type, String symbol, String name, int quantity, double price, double bookValue)
    {
        if (type == null)
        {
            this.type = "";
        }
        else 
        {
            this.type = type;
        }
        
        if (symbol == null)
        {
            this.symbol = "";
        }
        else 
        {
            this.symbol = symbol;
        }
        
        if (name == null)
        {
           this.name = "";
        } 
        else
        {
            this.name = name;
        }
        
        if (quantity <= 0)
        {
            this.quantity = 0;
        } 
        else 
        {
            this.quantity = quantity;
        }
        
        if (price <= 0.00)
        {
            this.price = 0.00;
        } 
        else 
        {
            this.price = price;
        }
        
        if (bookValue <= 0.00)
        {
            this.bookValue = 0.00;
        } 
        else 
        {
            this.bookValue = bookValue;
        }
    }
    
    public InvestmentA3()
    {
        this.type = "";
        this.symbol = "";
        this.name = "";
        this.quantity = 0;
        this.price = 0.00;
        this.bookValue = 0.00;
    }
    
    
    public String toString()
    {
        return this.type+", "+this.symbol+", "+this.name+", "+this.quantity+", "+this.price+", "+this.bookValue;
    }
    
    public String output()
    {
        return "type = '"+this.type+"'\nsymbol = '"+this.symbol+"'\nname = '"+this.name+"'\nquantity = '"+this.quantity+"'\nprice = '"+this.price+"'\nbookValue = '"+this.bookValue+"'\n";
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public void setSymbol(String symbol)
    {
        this.symbol = symbol;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }
    
    public void setPrice(double price)
    {
        this.price = price;
    }
    
    public void setBookValue(double bookValue)
    {
        this.bookValue = bookValue;
    }
    
    public String getType()
    {
        return type;
    }
    
    public String getSymbol()
    {
        return symbol;
    }

    public String getName()
    {
        return name;
    }
    
    public int getQuantity()
    {
        return quantity;
    }
    
    public double getPrice()
    {
        return price;
    }
    
    public double getBookValue()
    {
        return bookValue;
    }
    
    @Override
    public boolean equals(Object o)
    {   
        if(!(o instanceof InvestmentA3))
        { 
            return false;
        }
        
        else
        {
            InvestmentA3 inv = (InvestmentA3) o;
            
            if(!(this.getType().equals(inv.getType())))
            {
                return false;
            }
            
            if(!(this.getSymbol().equals(inv.getSymbol())))
            {
                return false;
            }
        
            if (!(this.getName().equals(inv.getName()))) 
            {
                return false;
            }
            
            if (!(this.getQuantity() == inv.getQuantity())) 
            {
                return false;
            }
            
            if (!(this.getPrice() == inv.getPrice()))
            {
                return false;
            }
            
            if (!(this.getBookValue() == inv.getBookValue()))
            {
                return false;
            }
                                  
            return true;
            
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.type);
        hash = 41 * hash + Objects.hashCode(this.symbol);
        hash = 41 * hash + Objects.hashCode(this.name);
        hash = 41 * hash + this.quantity;
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        hash = 41 * hash + (int) (Double.doubleToLongBits(this.bookValue) ^ (Double.doubleToLongBits(this.bookValue) >>> 32));
        return hash;
    }
}
