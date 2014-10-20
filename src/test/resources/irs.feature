Feature: IRS payment computation
  In order to pay/receive money according to the term sheet
  As the saler of the product to the client
  I want to see the different cash flow that will be paid, given observation of market data

  The cash flow are computed as: CF = receiving - paying 

  If we have a interest rate (fixed or float) of X% then, we compute the amount for each month as Nominal* X% / 12

  Scenario: One year fixed rate/Libro monthly Interest Rate Swap
  Given the nominal is 1000000
  And we receive an fixed interest rate of 6.25
  And the start date is 01/01/2014
  And the end date is 01/01/2015
  And the observed Libor rates are
	  | Date       | Libor |
	  | 01/01/2014 | 6.25% | 
	  | 01/02/2014 | 6.35% |
	  | 01/03/2014 | 6.45% |
	  | 01/04/2014 | 6.55% |
	  | 01/05/2014 | 6.43% |
	  | 01/06/2014 | 6.22% |
	  | 01/07/2014 | 6.02% |
	  | 01/08/2014 | 5.85% |
	  | 01/09/2014 | 5.78% |
	  | 01/10/2014 | 5.82% |
	  | 01/11/2014 | 6.12% |
	  | 01/12/2014 | 6.15% |
	  | 01/01/2015 | 6.07% |

  When I ask for expected cash flows
  Then I should see the following values
	  | Date       | Cash Flow |
	  | 01/01/2014 |      0.00 |
	  | 01/02/2014 |    -83.33 |
	  | 01/03/2014 |   -166.67 |
	  | 01/04/2014 |   -250.00 |
	  | 01/05/2014 |   -150.00 |
	  | 01/06/2014 |     25.00 |
	  | 01/07/2014 |    191.67 |
	  | 01/08/2014 |    333.33 |
	  | 01/09/2014 |    391.67 |
	  | 01/10/2014 |    358.33 |
	  | 01/11/2014 |    108.33 |
	  | 01/12/2014 |     83.33 |
	  | 01/01/2015 |    150.00 |
