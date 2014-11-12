package thoughtworks;

/**
 * @author Gabriel Pendleton
 * @date   11/3/2014
 * 
 * TODO: Add description of problem,
 *       detail approach to solve it. 
 */

/*
TODO: Remove when done.

DESCRIPTION:
Basic sales tax is applicable at a rate of 10% on all goods, except books, food, and medical products that are exempt. Import duty is an additional sales tax applicable on all imported goods at a rate of 5%, with no exemptions.
 
When I purchase items I receive a receipt which lists the name of all the items and their price (including tax), finishing with the total cost of the items, and the total amounts of sales taxes paid.  The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax.
 
Write an application that prints out the receipt details for these shopping baskets...
*/

public class SalesTax {

    public static void main(String[] args) {
        
        
        /*
            Output 1:
            1 book : 12.49
            1 music CD: 16.49
            1 chocolate bar: 0.85
            Sales Taxes: 1.50
            Total: 29.83
        */
        
        ShoppingCart Cart = new ShoppingCart(0.1f, 0.05f);
        
        Cart.AddTransaction("book", 1, 12.49f, TaxBracket.None);
        Cart.AddTransaction("music CD", 1, 14.99f, TaxBracket.Regular);
        Cart.AddTransaction("chocolate bar", 1, 0.85f, TaxBracket.None);
        
        System.out.println(Cart.GetReceipt());
        Cart.EmptyCart();
        /*
            Output 2:
            1 imported box of chocolates: 10.50
            1 imported bottle of perfume: 54.65
            Sales Taxes: 7.65
            Total: 65.15
        */
        
        Cart.AddTransaction("imported box of chocolates", 1, 10.00f, TaxBracket.NonTaxableImport);
        Cart.AddTransaction("imported bottle of perfume", 1, 47.50f, TaxBracket.Import);
        System.out.println(Cart.GetReceipt());
        Cart.EmptyCart();
 
        /*
            Output 3:
            1 imported bottle of perfume: 32.19
            1 bottle of perfume: 20.89
            1 packet of headache pills: 9.75
            1 imported box of chocolates: 11.85
            Sales Taxes: 6.70
            Total: 74.68
        */
        Cart.AddTransaction("imported bottle of perfume", 1, 27.99f, TaxBracket.Import);
        Cart.AddTransaction("bottle of perfume", 1, 18.99f, TaxBracket.Regular);
        Cart.AddTransaction("packet of headache pills", 1, 9.75f, TaxBracket.None);
        Cart.AddTransaction("box of imported chocolates", 1, 11.25f, TaxBracket.NonTaxableImport);
        
        System.out.println(Cart.GetReceipt());
        Cart.EmptyCart();
        
    }
}
