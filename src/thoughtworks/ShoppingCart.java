package thoughtworks;

import java.util.HashMap;
import java.util.ArrayList;

// Store tax rates as enum so it can be easily extended, and identified,
// actual values are set during ShoppingCart's constructor.
enum TaxBracket {
    None,
    Regular,
    Import,
    NonTaxableImport
}

/**
 *
 * @author Gabriel
 */
public class ShoppingCart {
    
    // Store different tax rates for purchase categories.
    private HashMap<TaxBracket, Float> TaxRates;
    
    //declare list of transactions 
    private ArrayList<Transaction> Transactions;
    
    //
   
    
    public ShoppingCart(float RegularRate, float ImportRate)
    {
        TaxRates = new HashMap<TaxBracket, Float>();
        TaxRates.put(TaxBracket.None, new Float(0));
        TaxRates.put(TaxBracket.Regular, RegularRate);
        TaxRates.put(TaxBracket.Import, ImportRate);
        TaxRates.put(TaxBracket.NonTaxableImport, -1.0f);
        Transactions = new ArrayList<Transaction>();
        
    }
    
    public void AddTransaction(String Name, int Quantity, float Value, TaxBracket Tax)
    {
        Transaction tempTrans = new Transaction(Name, Quantity, Value, TaxRates.get(Tax));
        Transactions.add(tempTrans);
    }
    
    public static float roundTransaction(float unrounded)
    {
        //multiply by 1000, use built in round function
        unrounded *= 100;
        unrounded = Math.round(unrounded);
        unrounded /= 100;
        
        return unrounded;
    }
    
    public float getRoundedTax(float taxAmount , float price )
    {
         //np/100 rounded to the nearest 0.5
        
        float amountFromTax = taxAmount * price /100;
        amountFromTax = (int)(amountFromTax/.05f +.5f)*.05f;
        return  amountFromTax;
    }

    public String GetReceipt()
    {
        float None = TaxRates.get(TaxBracket.None);
        float Regular = TaxRates.get(TaxBracket.Regular);
        float Import = TaxRates.get(TaxBracket.Import);
        float TotalNone = 0;
        float TotalRegular = 0;
        float TotalImport = 0;
        float NonTaxableImport = TaxRates.get(TaxBracket.NonTaxableImport);
        float TotalSalesTax = 0;
        
        
        String Output="";
        
        
        // TODO: Loop through pending transactions, and print their receipt
        for(int i = 0; i < Transactions.size(); i++)
        {
            Transaction trans = Transactions.get(i);
            float currTransTotal = trans.Price * trans.Quantity;
            float transSalesTax = 0;
            
            if(trans.Tax == None)
            {
                TotalNone += currTransTotal;
            }
            
            if(trans.Tax == Regular)
            {
                transSalesTax = currTransTotal + getRoundedTax(Regular, currTransTotal);
                System.out.println(transSalesTax);
                TotalRegular += currTransTotal + transSalesTax;
            }
            
            if(trans.Tax == Import)
            {
                transSalesTax = currTransTotal + getRoundedTax(Import + Regular, currTransTotal );
                TotalImport += currTransTotal + transSalesTax;
                System.out.println(transSalesTax);
            }
            
            if(trans.Tax == NonTaxableImport)
            {
                transSalesTax = currTransTotal + getRoundedTax(Import, currTransTotal);
                TotalImport += currTransTotal + transSalesTax;
                System.out.println(transSalesTax);
            }
            
            
            TotalSalesTax += transSalesTax;
            
            float grandSalesTotal = currTransTotal + transSalesTax;
            grandSalesTotal = roundTransaction(grandSalesTotal);
            Output += trans.Quantity + " | " + trans.Description + " | " + grandSalesTotal +  "\r\n";
        }
        
        float grandTransationTotal = TotalNone + TotalRegular + TotalImport;
        grandTransationTotal = roundTransaction(grandTransationTotal);
        TotalSalesTax = roundTransaction(TotalSalesTax);
        
        Output += "Sales Taxes: " + TotalSalesTax + "\r\n";
        Output += "Total: " + grandTransationTotal + "\r\n";
       
        
       return Output;
    }  
    
    public void EmptyCart()
    {
        Transactions = new ArrayList();
    }
}
