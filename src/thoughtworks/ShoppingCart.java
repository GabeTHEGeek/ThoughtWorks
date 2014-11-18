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
        // TODO: Allocate new transaction - fill its data (via constructor)
        // TODO: Add new transaction to Transactions list
        Transaction tempTrans = new Transaction(Name, Quantity, Value, TaxRates.get(Tax));
        Transactions.add(tempTrans);
        
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
        float TotalPrice = 0;
        float totalOutputs = 0;
        
        
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
                transSalesTax = currTransTotal * Regular;
                TotalRegular += currTransTotal + transSalesTax;
            }
            
            if(trans.Tax == Import)
            {
                transSalesTax = currTransTotal * (Import + Regular);
                TotalImport += currTransTotal + transSalesTax;
            }
            
            if(trans.Tax == NonTaxableImport)
            {
                transSalesTax = currTransTotal * Import;
                TotalImport += currTransTotal + transSalesTax;
            }
            
            
            TotalSalesTax += transSalesTax;
            float grandSalesTotal = currTransTotal + transSalesTax;
            //(grandSalesTotal).toFixed(2);
            //grandSalesTotal.Math.round10(5.25, -2);
            grandSalesTotal = Math.round(grandSalesTotal);
            Output += trans.Quantity + " | " + trans.Description + " | " + grandSalesTotal +  "\r\n";
        }
        
        
        Output += "Sales Taxes: " + TotalSalesTax + TotalPrice + "\r\n";
        Output += "Total: " + ((TotalNone + TotalRegular + TotalImport))+ "\r\n";
       
        
       return Output;
    }  
    
    public void EmptyCart()
    {
        Transactions = new ArrayList();
    }
}
