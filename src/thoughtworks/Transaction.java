package thoughtworks;

/**
 *
 * @author Gabriel
 */
public class Transaction {
    
    public String Description;
    public int Quantity;
    public float Price;
    public float Tax;
    
    public Transaction(String InitDescription, int InitQuantity, float InitPrice, float InitTax)
    {
        Description = InitDescription;
        Quantity = InitQuantity;
        Price = InitPrice;
        Tax = InitTax;
    }
    
}
