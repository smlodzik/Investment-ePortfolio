package ePortfolio2;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Portfolio {

    public static void main(String[] args) {
        //Portfolio Portfolio = new Portfolio();

        DecimalFormat df = new DecimalFormat("#.##");

        //general variables
        Scanner scanInput = new Scanner(System.in);
        String userInput = "temp", temp;

        final double COMMISSION = 9.99;
        final double REDEMPTION = 45;

        //define ArrayList
        ArrayList <Investment> iList = new ArrayList <Investment>();

        //"buy" variables
        String investType;

        //"search" variables
        int r = 0; 
        String[] keywords = new String[10];

        while ((userInput != "quit") && (userInput != "q")) {
            //print out menu
            System.out.println("----------");
            System.out.println("MENU");
            System.out.println("\"buy\"- own a new investment or add more quantity to an existing investment");
            System.out.println("\"sell\"- reduce some quantity of an existing investing");
            System.out.println("\"update\"- refresh the prices of all existing investments");
            System.out.println("\"getGain\"- compute the total gain of the portfolio by accumulating the gains of all individual investments");
            System.out.println("\"search\"- find all investments that match a search request and display all atributes of these investments");
            System.out.println("\"quit\"- end program");
            System.out.println("----------");

            //get user input, change to lowercase (non case-sensitive input)
            temp = scanInput.nextLine();
            userInput = temp.toLowerCase();

            //if the user enters "s", check which function they wanted to use
            while (userInput.equals("s")) {
                System.out.println("Did you mean \"sell\" or \"search\"?");

                temp = scanInput.nextLine();
                userInput = temp.toLowerCase();
            }


            //"QUIT" OPTION (check first to end program)
            if ((userInput.equals("quit")) || (userInput.equals("q"))) {
                System.out.println("End of program reached.");
                break;
            }


            //"BUY" OPTION
            else if ((userInput.equals("buy")) || (userInput.equals("b"))) {
                //stock or mutual fund
                System.out.println("Enter the kind of investment (\"stock\" or \"mutualFund\"):");
                temp = scanInput.nextLine();
                investType = temp.toLowerCase();

                //get symbol
                System.out.println("Enter the symbol for the investment:");
                temp = scanInput.nextLine();
                String symbolName = temp.toLowerCase();
                
                //check if the list already contains that symbol
                int check = 0;
                for (int j = 0; j < iList.size(); j++) {
                    if (iList.get(j).getSymbol().contains(symbolName)) {
                        check = 1;
                    }
                }                   

                //*****if the investment exists, update the values*****
                if (check == 1) {
                    for (int i = 0; i < iList.size(); i++) {
                        if (iList.get(i).getSymbol().contains(symbolName)) {
                            //get updated investment values
                            System.out.println("Enter the quantity of the investment you wish to purchase:");
                            String t = scanInput.nextLine();
                            int stockQuantity = Integer.parseInt(t);
                            //update quantity
                            if (stockQuantity > 0) {
                                iList.get(i).setQuantity(iList.get(i).getQuantity() + stockQuantity);
                            }

                            System.out.println("Enter the new price of the investment:");
                            t = scanInput.nextLine();
                            double stockPrice = Double.parseDouble(t);
                            //update price
                            if (stockPrice > 0) {
                                iList.get(i).setPrice(stockPrice);
                            }
        
                            double stockValue = (stockPrice * stockQuantity) + iList.get(i).getBookValue();

                            //update book value
                            iList.get(i).setBookValue(stockValue);
                        }
                    }                       
                }
                //*****if the investment does not exist already, create it*****
                else {
                //STOCK INVESTMENTS
                    if ((investType.equals("stock")) || (investType.equals("s"))) {
                        Stock tempStock = new Stock("t", "temp", 0, 0.0, 0.0);
                        //get stock values
                        System.out.println("Enter name of the stock:");
                        String stockName = scanInput.nextLine();
                        tempStock.setName(stockName);

                        System.out.println("Enter the quantity of the stock you wish to purchase:");
                        String t = scanInput.nextLine();
                        int stockQuantity = Integer.parseInt(t);
                        //update quantity
                        if (stockQuantity > 0) {
                            tempStock.setQuantity(stockQuantity);
                        }
                        
                        System.out.println("Enter the price of the stock:");
                        t = scanInput.nextLine();
                        double stockPrice = Double.parseDouble(t);
                        //update price
                        if (stockPrice > 0) {
                            tempStock.setPrice(stockPrice);
                        }

                        //update book value
                        double stockValue = (stockPrice * stockQuantity) + COMMISSION;
                        tempStock.setBookValue(stockValue);

                        tempStock.setSymbol(symbolName);
                        
                        iList.add(tempStock);
                    }
                    //MUTUAL FUND INVESTMENTS
                    else if ((investType.equals("mutualfund")) || (investType.equals("m"))) {
                        MutualFund tempMF = new MutualFund("t", "temp", 0, 0.0, 0.0);
                        //get mutual fund values
                        System.out.println("Enter name of the mutual fund:");
                        String mfName = scanInput.nextLine();
                        tempMF.setName(mfName);

                        System.out.println("Enter the quantity of the mutual fund you wish to purchase:");
                        String t = scanInput.nextLine();
                        int mfQuantity = Integer.parseInt(t);
                        //update quantity
                        if (mfQuantity > 0) {
                            tempMF.setQuantity(mfQuantity);
                        }
                        
                        System.out.println("Enter the price of the mutual fund:");
                        t = scanInput.nextLine();
                        double mfPrice = Double.parseDouble(t);
                        //update price
                        if (mfPrice > 0) {
                            tempMF.setPrice(mfPrice);
                        }
                        
                        //update book value
                        double mfValue = (mfPrice * mfQuantity);
                        tempMF.setBookValue(mfValue);

                        tempMF.setSymbol(symbolName);

                        iList.add(tempMF);
                    }
                }
                /*else {
                    System.out.println("Not a valid input. Please try again.");
                }*/
            }
            

            //"SELL" OPTION
            else if (userInput.equals("sell")) {
                //stock or mutual fund
                System.out.println("Enter the kind of investment (\"stock\" or \"mutualFund\"):");
                temp = scanInput.nextLine();
                investType = temp.toLowerCase();

                //get symbol
                System.out.println("Enter the symbol for the investment:");
                String symbolName = scanInput.nextLine();
                
                //check if the list already contains that symbol
                int check = 0;
                for (int j = 0; j < iList.size(); j++) {
                    if (iList.get(j).getSymbol().contains(symbolName)) {
                        check = 1;
                    }
                }

                //*****if they already have some of the stock*****
                if (check == 1) {
                    for (int i = 0; i < iList.size(); i++) {
                        if (iList.get(i).getSymbol().contains(symbolName)) {
                            //STOCK INVESTMENTS
                            if ((investType.equals("stock")) || (investType.equals("s"))) {
                                //get updated stock values
                                System.out.println("Enter the quantity of the stock you wish to sell:");
                                String t = scanInput.nextLine();
                                int stockQuantity = Integer.parseInt(t);

                                //only continue if the user has enough of that stock to sell
                                if (iList.get(i).getQuantity() >= stockQuantity) {
                                    System.out.println("Enter the price of the stock:");
                                    t = scanInput.nextLine();
                                    double stockPrice = Double.parseDouble(t);
                                    //update price
                                    if (stockPrice > 0) {
                                        iList.get(i).setPrice(stockPrice);
                                    }

                                    //update stock attributes
                                    iList.get(i).setQuantity(iList.get(i).getQuantity() - stockQuantity);
                                    
                                    double stockValue = (stockPrice * stockQuantity);

                                    //check if there is still stock left, if not remove from the list
                                    if (stockQuantity <= 0) {
                                        iList.remove(i);
                                    }
                                    else {
                                        //update book value
                                        iList.get(i).setBookValue(stockValue);
                                    }
                                }  
                                else {
                                    System.out.println("You do not currently have enough of that stock to sell that amount.");
                                }
                            }
                            //MUTUAL FUND INVESTMENTS
                            else if ((investType.equals("mutualfund")) || (investType.equals("m"))) {
                                //get updated stock values
                                System.out.println("Enter the quantity of the mutual fund you wish to sell:");
                                String t = scanInput.nextLine();
                                int mfQuantity = Integer.parseInt(t);

                                //only continue if the user has enough of that mutual fund to sell
                                if (iList.get(i).getQuantity() >= mfQuantity) {
                                    System.out.println("Enter the price of the mutual fund:");
                                    t = scanInput.nextLine();
                                    double mfPrice = Double.parseDouble(t);
                                    //update price
                                    if (mfPrice > 0) {
                                        iList.get(i).setPrice(mfPrice);
                                    }

                                    //update mutual fund attributes
                                    iList.get(i).setQuantity(iList.get(i).getQuantity() - mfQuantity);

                                    double mfValue = (mfPrice * mfQuantity);

                                    //check if there is still mutual fund left, if not remove from the list
                                    if (mfQuantity <= 0) {
                                        iList.remove(i);
                                    }
                                    else {
                                        //update book value
                                        iList.get(i).setBookValue(-mfValue + REDEMPTION);
                                    }
                                }  
                                else {
                                    System.out.println("You do not currently have enough of that mutual fund to sell that amount.");
                                }
                            }
                        }
                    }
                }
                //*****if they have none of the stock currently*****
                else {
                    System.out.println("You do not currently own any of this stock.");
                }
            }
                
            
            //"UPDATE" OPTION
            else if ((userInput.equals("update")) || (userInput.equals("u"))) {
                //UPDATE INVESTMENTS
                if (iList.size() > 0) {
                    for (int i = 0; i < iList.size(); i++) {
                        //get updated price for each stock
                        System.out.println("Enter the new price for " + iList.get(i).getName());
                        String t = scanInput.nextLine();
                        double stockPrice = Double.parseDouble(t);
                        //update price
                        if (stockPrice > 0) {
                            iList.get(i).setPrice(stockPrice);
                        }

                        double stockValue = (stockPrice * iList.get(i).getQuantity()) + COMMISSION;

                        //update book value
                        iList.get(i).setBookValue(stockValue);
                    }
                }
                //print a message if they do not own any stocks
                else {
                    System.out.println("You do not currently have any stock investments.");
                }
                /*else {
                    System.out.println("Not a valid input. Please try again.");
                }*/
            }


            //"GETGAIN" OPTION
            else if ((userInput.equals("getgain")) || (userInput.equals("g"))) {
                //create 3 arrays to hold the new/old values and the gain
                int t = iList.size();
                double[] oldValue = new double[t];
                double[] newValue = new double[t];
                double[] gain = new double[t];

                double totalGain = 0.0;

                //UPDATE INVESTMENTS
                if (iList.size() > 0) {
                    for (int i = 0; i < iList.size(); i++) {
                        //store old value to compare
                        oldValue[i] = (iList.get(i).getPrice() * iList.get(i).getQuantity());
                        
                        //get updated price for each stock
                        System.out.println("Enter the current price for " + iList.get(i).getName());
                        String tt = scanInput.nextLine();
                        double stockPrice = Double.parseDouble(tt);

                        newValue[i] = (stockPrice * iList.get(i).getQuantity());

                        //calculate gain (or loss)
                        gain[i] = newValue[i] - oldValue[i];
                        totalGain += gain[i];
                    }
                }

                System.out.println("");

                //print out a list of investment gains
                if (iList.size() > 0) {
                    System.out.println("Stock Investment Gains:");
                    Stock tempStock = new Stock("t", "temp", 0, 0.0, 0.0);
                    for (int i = 0; i < iList.size(); i++) {
                        if (iList.get(i).getClass().equals(tempStock.getClass())) {
                            System.out.println(iList.get(i).getName() + ": $" + df.format(gain[i]));
                        }
                    }
                    System.out.println("Mutual Fund Investment Gains:");
                    MutualFund tempMF = new MutualFund("t", "temp", 0, 0.0, 0.0);
                    for (int i = 0; i < iList.size(); i++) {
                        if (iList.get(i).getClass().equals(tempMF.getClass())) {
                            System.out.println(iList.get(i).getName() + ": $" + df.format(gain[i]));
                        }
                    }
                }
                //print a message if they do not own any stocks
                else {
                    System.out.println("You do not currently have any stock investments to check the gain of.");
                }

                System.out.println("Total Investment Gains: $" + df.format(totalGain));
            }


            //"SEARCH" OPTION
            else if (userInput.equals("search")) {
                //gather input info
                //*****gather symbol*****
                System.out.println("Enter the symbol for the investment you wish to find:");
                String symbolName = scanInput.nextLine();
                symbolName.toLowerCase();

                //*****gather keywords*****
                System.out.println("Enter keyword(s) for the investment title you wish to find:");
                String tempInput = scanInput.nextLine();
                tempInput.toLowerCase();
                if (!tempInput.isEmpty()) {
                    //split string into keywords
                    keywords = tempInput.split(" ");
                }
                int kCount = keywords.length;
                //System.out.println(tempInput);

                //*****gather price range*****
                System.out.println("Enter a price range for the investment you wish to find:");
                String priceRange = scanInput.nextLine();
                double pRange = Double.NaN;

                if (!priceRange.isEmpty()) {
                    //1<=, 2>=, 3==
                    char tempStart = priceRange.charAt(0);
                    int sLength = priceRange.length();
                    char tempEnd = priceRange.charAt(sLength-1);
                    if (tempStart == ('-')) {
                        r = 1;
                        priceRange = priceRange.replace("-", "");
                        pRange = Double.parseDouble(priceRange);
                    }
                    else if (tempEnd == ('-')) {
                        r = 2;
                        priceRange = priceRange.replace("-", "");
                        pRange = Double.parseDouble(priceRange);
                    }
                    else {
                        r = 3;
                        pRange = Double.parseDouble(priceRange);
                    }
                }

                //SEARCH STOCK INVESTMENTS
                System.out.println("\nSTOCK INVESTMENTS:");

                //iterate through each investment 
                for (int i = 0; i < iList.size(); i++) { 
                    Stock tempStock = new Stock("t", "temp", 0, 0.0, 0.0);
                    if (iList.get(i).getClass().equals(tempStock.getClass())) {
                        //symbol matches
                        if (iList.get(i).getSymbol().contains(symbolName)) {
                            //keywords blank
                            if (tempInput.isEmpty()) {
                                //SYMBOL MATCH, KEYWORD NULL, PRICE RANGE NULL
                                //price range blank
                                if (priceRange.isEmpty()) {
                                    System.out.println("#" + (i+1));
                                    System.out.println(iList.get(i).toString());
                                }
                                //SYMBOL MATCH, KEYWORD NULL, PRICE RANGE MATCH
                                else {
                                    //check for the range based on the position of "-"
                                    if (r == 1) {
                                        if (pRange <= iList.get(i).getPrice()) {
                                            System.out.println("#" + (i+1));
                                            System.out.println(iList.get(i).toString());
                                        }
                                    }
                                    else if (r == 2) {
                                        if (pRange >= iList.get(i).getPrice()) {
                                            System.out.println("#" + (i+1));
                                            System.out.println(iList.get(i).toString());
                                        }
                                    }
                                    else if (r == 3) {
                                        if (pRange == iList.get(i).getPrice()) {
                                            System.out.println("#" + (i+1));
                                            System.out.println(iList.get(i).toString());
                                        }
                                    }
                                }
                            }
                            //keywords match
                            else {
                                int kCount2 = 0;
                                for (int j = 0; j < kCount; j++) {
                                    if (iList.get(i).getName().contains(keywords[j])) {
                                        kCount2 += 1;
                                    }
                                    else {
                                        continue;
                                    }
                                }
                                if (kCount2 == kCount) {
                                    //SYMBOL MATCH, KEYWORD MATCH, PRICE RANGE NULL
                                    //price range blank
                                    if (priceRange.isEmpty()) {
                                        System.out.println("#" + (i+1));
                                        System.out.println(iList.get(i).toString());
                                    }
                                    //SYMBOL MATCH, KEYWORD MATCH, PRICE RANGE MATCH
                                    else {
                                        //check for the range based on the positio of "-"
                                        if (r == 1) {
                                            if (pRange <= iList.get(i).getPrice()) {
                                                System.out.println("#" + (i+1));
                                                System.out.println(iList.get(i).toString());
                                            }
                                        }
                                        else if (r == 2) {
                                            if (pRange >= iList.get(i).getPrice()) {
                                                System.out.println("#" + (i+1));
                                                System.out.println(iList.get(i).toString());
                                            }
                                        }
                                        else if (r == 3) {
                                            if (pRange == iList.get(i).getPrice()) {
                                                System.out.println("#" + (i+1));
                                                System.out.println(iList.get(i).toString());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        //symbol blank
                        else if (symbolName.isEmpty()) {
                            //keywords blank
                            if (tempInput.isEmpty()) {
                                //SYMBOL NULL, KEYWORD NULL, PRICE RANGE NULL
                                //price range blank
                                if (priceRange.isEmpty()) {
                                    for (int k = 0; k < iList.size(); k++) {
                                        System.out.println("#" + (k+1));
                                        System.out.println(iList.get(i).toString());
                                    }
                                }
                                //SYMBOL NULL, KEYWORD NULL, PRICE RANGE MATCH
                                else {
                                    //check for the range based on the position of "-"
                                    if (r == 1) {
                                        if (pRange <= iList.get(i).getPrice()) {
                                            System.out.println("#" + (i+1));
                                            System.out.println(iList.get(i).toString());
                                        }
                                    }
                                    else if (r == 2) {
                                        if (pRange >= iList.get(i).getPrice()) {
                                            System.out.println("#" + (i+1));
                                            System.out.println(iList.get(i).toString());
                                        }
                                    }
                                    else if (r == 3) {
                                        if (pRange == iList.get(i).getPrice()) {
                                            System.out.println("#" + (i+1));
                                            System.out.println(iList.get(i).toString());
                                        }
                                    }
                                }
                            }
                            //keywords match
                            else {
                                int kCount2 = 0;
                                for (int j = 0; j < kCount; j++) {
                                    if (iList.get(i).getName().contains(keywords[j])) {
                                        kCount2 += 1;
                                    }
                                    else {
                                        continue;
                                    }
                                }
                                if (kCount2 == kCount) {
                                    //SYMBOL NULL, KEYWORD MATCH, PRICE RANGE NULL
                                    //price range blank
                                    if (priceRange.isEmpty()) {
                                        System.out.println("#" + (i+1));
                                        System.out.println(iList.get(i).toString());
                                    }
                                    //SYMBOL NULL, KEYWORD MATCH, PRICE RANGE MATCH
                                    else {
                                        //check for the range based on the position of "-"
                                        if (r == 1) {
                                            if (pRange <= iList.get(i).getPrice()) {
                                                System.out.println("#" + (i+1));
                                                System.out.println(iList.get(i).toString());
                                            }
                                        }
                                        else if (r == 2) {
                                            if (pRange >= iList.get(i).getPrice()) {
                                                System.out.println("#" + (i+1));
                                                System.out.println(iList.get(i).toString());
                                            }
                                        }
                                        else if (r == 3) {
                                            if (pRange == iList.get(i).getPrice()) {
                                                System.out.println("#" + (i+1));
                                                System.out.println(iList.get(i).toString());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                
                //SEARCH MUTUAL FUND INVESTMENTS
                System.out.println("\nMUTUAL FUND INVESTMENTS:");
                
                //iterate through each investment 
                for (int i = 0; i < iList.size(); i++) { 
                    MutualFund tempMF = new MutualFund("t", "temp", 0, 0.0, 0.0);
                    if (iList.get(i).getClass().equals(tempMF.getClass())) {
                        //symbol matches
                        if (iList.get(i).getSymbol().contains(symbolName)) {
                            //keywords blank
                            if (tempInput.isEmpty()) {
                                //SYMBOL MATCH, KEYWORD NULL, PRICE RANGE NULL
                                //price range blank
                                if (priceRange.isEmpty()) {
                                    System.out.println("#" + (i+1));
                                    System.out.println(iList.get(i).toString());
                                }
                                //SYMBOL MATCH, KEYWORD NULL, PRICE RANGE MATCH
                                else {
                                    //check for the range based on the position of "-"
                                    if (r == 1) {
                                        if (pRange <= iList.get(i).getPrice()) {
                                            System.out.println("#" + (i+1));
                                            System.out.println(iList.get(i).toString());
                                        }
                                    }
                                    else if (r == 2) {
                                        if (pRange >= iList.get(i).getPrice()) {
                                            System.out.println("#" + (i+1));
                                            System.out.println(iList.get(i).toString());
                                        }
                                    }
                                    else if (r == 3) {
                                        if (pRange == iList.get(i).getPrice()) {
                                            System.out.println("#" + (i+1));
                                            System.out.println(iList.get(i).toString());
                                        }
                                    }
                                }
                            }
                            //keywords match
                            else {
                                int kCount2 = 0;
                                for (int j = 0; j < kCount; j++) {
                                    if (iList.get(i).getName().contains(keywords[j])) {
                                        kCount2 += 1;
                                    }
                                    else {
                                        continue;
                                    }
                                }
                                if (kCount2 == kCount) {
                                    //SYMBOL MATCH, KEYWORD MATCH, PRICE RANGE NULL
                                    //price range blank
                                    if (priceRange.isEmpty()) {
                                        System.out.println("#" + (i+1));
                                        System.out.println(iList.get(i).toString());
                                    }
                                    //SYMBOL MATCH, KEYWORD MATCH, PRICE RANGE MATCH
                                    else {
                                        //check for the range based on the positio of "-"
                                        if (r == 1) {
                                            if (pRange <= iList.get(i).getPrice()) {
                                                System.out.println("#" + (i+1));
                                                System.out.println(iList.get(i).toString());
                                            }
                                        }
                                        else if (r == 2) {
                                            if (pRange >= iList.get(i).getPrice()) {
                                                System.out.println("#" + (i+1));
                                                System.out.println(iList.get(i).toString());
                                            }
                                        }
                                        else if (r == 3) {
                                            if (pRange == iList.get(i).getPrice()) {
                                                System.out.println("#" + (i+1));
                                                System.out.println(iList.get(i).toString());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        //symbol blank
                        else if (symbolName.isEmpty()) {
                            //keywords blank
                            if (tempInput.isEmpty()) {
                                //SYMBOL NULL, KEYWORD NULL, PRICE RANGE NULL
                                //price range blank
                                if (priceRange.isEmpty()) {
                                    for (int k = 0; k < iList.size(); k++) {
                                        System.out.println("#" + (k+1));
                                        System.out.println(iList.get(i).toString());
                                    }
                                }
                                //SYMBOL NULL, KEYWORD NULL, PRICE RANGE MATCH
                                else {
                                    //check for the range based on the position of "-"
                                    if (r == 1) {
                                        if (pRange <= iList.get(i).getPrice()) {
                                            System.out.println("#" + (i+1));
                                            System.out.println(iList.get(i).toString());
                                        }
                                    }
                                    else if (r == 2) {
                                        if (pRange >= iList.get(i).getPrice()) {
                                            System.out.println("#" + (i+1));
                                            System.out.println(iList.get(i).toString());
                                        }
                                    }
                                    else if (r == 3) {
                                        if (pRange == iList.get(i).getPrice()) {
                                            System.out.println("#" + (i+1));
                                            System.out.println(iList.get(i).toString());
                                        }
                                    }
                                }
                            }
                            //keywords match
                            else {
                                int kCount2 = 0;
                                for (int j = 0; j < kCount; j++) {
                                    if (iList.get(i).getName().contains(keywords[j])) {
                                        kCount2 += 1;
                                    }
                                    else {
                                        continue;
                                    }
                                }
                                if (kCount2 == kCount) {
                                    //SYMBOL NULL, KEYWORD MATCH, PRICE RANGE NULL
                                    //price range blank
                                    if (priceRange.isEmpty()) {
                                        System.out.println("#" + (i+1));
                                        System.out.println(iList.get(i).toString());
                                    }
                                    //SYMBOL NULL, KEYWORD MATCH, PRICE RANGE MATCH
                                    else {
                                        //check for the range based on the position of "-"
                                        if (r == 1) {
                                            if (pRange <= iList.get(i).getPrice()) {
                                                System.out.println("#" + (i+1));
                                                System.out.println(iList.get(i).toString());
                                            }
                                        }
                                        else if (r == 2) {
                                            if (pRange >= iList.get(i).getPrice()) {
                                                System.out.println("#" + (i+1));
                                                System.out.println(iList.get(i).toString());
                                            }
                                        }
                                        else if (r == 3) {
                                            if (pRange == iList.get(i).getPrice()) {
                                                System.out.println("#" + (i+1));
                                                System.out.println(iList.get(i).toString());
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            //anything else
            /*else {
                System.out.println("Not a valid input. Please try again.");
            }*/
        }
    }
}
