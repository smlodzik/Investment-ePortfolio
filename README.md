Programmed in full by Sophie Mlodzik
Student ID- 1130462
Email- smlodzik@uoguelph.ca
Date of last revision- November 8th, 2021

Course- CIS*2430 F21
Assignment #2, expansion of Assignment #1
Professor F. Song

**********
1.0 General Problem Description
Simplify the code written for Assignment #1 (see description below). Creates a superclass to store all investments under and reduces the number of Arraylists from two to one. Pre-existing investments can be loaded into the program from an input file, as well as saved to an output file upon program completion. Search performance is improved by the implementation of a HashMap.

    Assignment #1 Description:
This program is intended to help an investor maintain a portfolio of their various investments. The program should track buying and selling of investments, update the prices of investments, help the user search for relevant investments, and calculate the total gain of the portfolio. This portfolio will only cover two types of investments: Stocks and Mutual Funds.

**********
2.0 Assumptions
- Stocks and Mutual Funds can only be bought in integer values (ex. it is not possible to buy 1.5 shares of a stock).
- Commission is $9.99 every time a stock is bought or sold.
- When a mutual fund is sold, a redemption fee of $45 must be paid.

2.1 Limitations
- Some inputs are not checked to be the correct variable type. This could cause the program to crash upon imperfect use (more information on this in the possible imporovements section, 4.0)

**********
3.0 How to Test / Build the Program
To compile all files together, one must first ensure they are all in the same package. This can be done by individually adding them with the following command:
javac -d . <filename>.java

*In the case of these files, the commands would look as follows:
    javac -d . Investment.java
    javac -d . Stock.java
    javac -d . MutualFund.java
    javac -d . Portfolio.java
This can also be done in a single line, as follows: 
    javac -d . Investment.java Stock.java MutualFund.java Portfolio.java


The program can then be run and tested with the following command:
java <packageName>.<filename>

*In the case of these files, the command would look as follows:
    java ePortfolio2.Portfolio


**********
4.0 Possible Improvements
- Valid input checking has not been completed for all inputs. All major inputs are checked for validity, but the types of variable inputs are not always checked. This could lead to the program crashing upon imperfect use, so I would definitely complete all input checking if I were given additional time.
- The code could still be more efficient. In the future I would create more sub-methods to both help with organization and efficiency. This would make the code easier to read and also help it run smoother.
- The implementation of hashmaps was not completed. 
