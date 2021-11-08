package ePortfolio2;


public class MutualFund extends Investment{
    public MutualFund(String inputSymbol, String inputName, int inputQuantity, double inputPrice, double inputBookValue) {
        super(inputSymbol, inputName, inputQuantity, inputPrice, inputBookValue);

        this.symbol = inputSymbol;
        this.name = inputName;
        this.quantity = inputQuantity;
        this.price = inputPrice;
        this.bookValue = inputBookValue;
    }
}
