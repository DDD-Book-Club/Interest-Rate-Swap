Feature: IRS payment computation
  In order to pay/receive money according to the term sheet
  As the OOBank who signed this contract
  I want to see the different cash flow that will be paid,
  given observation of market data.
  
  The cash flow are computed as: CF = receiving - paying 
  
  If we have a interest rate (fixed or float) of X% then,
  we compute the amount for each month as Nominal* X% / 12

  Scenario: IRS first payment with OOBank receiving at fixed rate
    Given the notional is "1 000 000.0"€
    And we agreed on a fixed interest rate of "6.25"%
    And today is "01/01/2014"
    And the start date is "01/01/2014"
    And the observed Libor rates at "01/01/2014" is "6.22"%
    Then I should receive "25.0"€

  Scenario: IRS first payment with a OOBank fixed rate
    Given the notional is "1 000 000.0"€
    And we agreed on a fixed interest rate of "6.25"%
    And today is "01/01/2014"
    And the start date is "01/01/2014"
    And the observed Libor rates at "01/01/2014" is "6.27"%
    Then I should pay "16.67"€
