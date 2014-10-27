Feature: IRS payment computation
  In order to pay/receive money according to the term sheet
  As a bank who signed this contract
  I want to see the different cash flow that will be paid,
  given observation of market data.
  
  The cash flow are computed as: CF = receiving - paying 
  
  If we have a interest rate (fixed or float) of r then,
  we compute the amount for each leg as 
  	
  	amount(leg) = notional * r * (T/360) 
  
  where T is the number of calendar days over the life of the swap
  
  A swap commonly has two legs, each leg representing a "bond"

  Scenario: IRS first payment with OOBank receiving at fixed rate
    Given an annual IRS from Jan 15, 2014 to Jan 15, 2015 for a notional of EUR "1 000 000.0"
    And with a fixed rate of 6.25
    And a day count basis ACT/360
    And a payment date offset of 2 working days (2 WD)
    When we calculate the next payment as of Jan 15, 2014 with the observed Libor rates at "6.22"%
    Then I should receive EUR "25.0"

  Scenario: IRS first payment with a OOBank fixed rate
    Given the notional is "1 000 000.0"€
    And we agreed on a fixed interest rate of "6.25"%
    And today is "01/01/2014"
    And the start date is "01/01/2014"
    And the observed Libor rates at "01/01/2014" is "6.27"%
    Then I should pay "16.67"€
