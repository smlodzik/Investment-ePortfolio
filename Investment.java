package ePortfolio2;


public class Investment {
    public String symbol;
    public String name;
    public int quantity;
    public double price;
    public double bookValue;

    public Investment(String inputSymbol, String inputName, int inputQuantity, double inputPrice, double inputBookValue) {
        this.symbol = inputSymbol;
        this.name = inputName;
        this.quantity = inputQuantity;
        this.price = inputPrice;
        this.bookValue = inputBookValue;
    }

    public void setSymbol(String inputSymbol){
        this.symbol = inputSymbol;
    }

    public String getSymbol(){
        return symbol;
    }


    public void setName(String inputName){
        this.name = inputName;
    }

    public String getName(){
        return name;
    }


    public void setQuantity(int inputQuantity){
        this.quantity = inputQuantity;
    }

    public int getQuantity(){
        return quantity;
    }


    public void setPrice(double inputPrice){
        this.price = inputPrice;
    }

    public double getPrice(){
        return price;
    }


    public void setBookValue(double inputBookValue){
        this.bookValue = inputBookValue;
    }

    public double getBookValue(){
        return bookValue;
    }


    @Override
    public String toString(){
        return "Symbol: " + this.symbol + "\nName: " + this.name + "\nQuantity: " + this.quantity + "\nPrice: $" + this.price + "\nBook Value: $" + this.bookValue;
    }
}
 
