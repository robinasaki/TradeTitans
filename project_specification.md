# Project Specification for Group #106

## Team Name

The Trading Titans

## Task 1: Picking the domain for your project
Our program will serve the purpose of track stock quotes, users will be able to create a portfolio, track the value of 
it and view information about the stocks in the portfolio.

## Task 2: Writing the specification
The fundamental functions of the program will include current and historical stock, ETF, and commodity quote tracking, 
and creating and following the value of a personalized stock portfolio over time, in different currencies and 
inflation-adjusted.

The program will have a stock simulation mode, where the program allows users to simulate a hypothetical stock portfolio 
and allows the user to simulate deposits, withdrawals, and stock & ETF buys and sells. Dividend payments and more will 
be automatically tracked using real-world data.


## Task 3: User Stories
- [Team Story]: Christopher is willing to change his portfolio's currency from CAD to USD so he can buy USA stocks in USD directly.  
- [Abdul's Story]: Abdul is interested in obtaining an estimate of potential profits by investing in stocks from a 
particular company. He wishes to input hypothetical data and receive an estimation based on current market prices.
- [Jarod's Story]: Jarod wishes to find either a specific stock or find others by inputting specific filters into a 
search. He is then given the specific stock requested or the stocks requested by the general search.
- [Robin's Story]: Wish to get familiarized with the stock market but just lost his entire lunch saving 
buying weed stocks. Wants to use the stock simulation mode of the program to learn the trends of the market.
- [Colin's Story]: Shayan frequently trades stocks & ETFs and wants to view his performance over time. He creates a portfolio where he records all his transactions (deposits, withdraws, buys, sells) and what time he made them in the program. He can then view various performance metrics over different time periods such as the last day, week, month, year, etc. 

## Task 4: Domain Entities
- Portfolio:
  - name
  - currency
  - stocks
  - transactions
- Stock:
  - name
  - symbol
  - historical prices (includes current price)
- Transaction
  - transaction type (deposit, withdraw, buy, sell)
  - stock symbol
  - time/date
  - number of shares
  - price per share

## Task 5: Scheduled Meeting Time + Mode of Communication
- We will meet during each tutorial time, as well as Tuesdays and Thursdays from 5:00-6:00pm.
- Our mode of communication will be a Discord group channel.
