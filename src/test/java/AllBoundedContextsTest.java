import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class AllBoundedContextsTest {

	// general
	private static final int NOTIONAL = 1000000;
	private static final String CURRENCY = "EUR";
	
	// pricing
	private static final double FIXED_RATE = 6.25 / 100;
	private static final String FLOATING_RATE = "EUR LIBOR";
	
	private static final int SWAP_LENGTH = 365;
	private static final long START_DATE = 0;
	private static final long END_DATE = 365;
	
	// risk_specific
	private static final boolean DERIVATIVE = true;
	
	private static final double OBSERVED_LIBOR_JAN_2015 = 6.22;
	
	
	// Events
	// order => contract => risk, balance-Sheet, valuation
	
	// Contexts 
	// cashflows calculation u--d cash management 
	// cashflows calculation u--d money transfer
	// cashflows calculation u--d valuation
	// cashflows calculation u--d pre-trade pricing
	// contract u--d risk

	@Test
	public void cashManagementContext() {
		
		final double expectedFixedAmount = NOTIONAL * (OBSERVED_LIBOR_JAN_2015 -FIXED_RATE) * SWAP_LENGTH
				/ 360;
		assertEquals(new Cashflow(END_DATE, expectedFixedAmount),
				nextCashflow(START_DATE, END_DATE, NOTIONAL, FIXED_RATE));
	}

	@Test
	public void riskContext() {
		assertEquals("EUR LIBOR=1000000, EUR=1000000",
				exposureOf(NOTIONAL, CURRENCY, FLOATING_RATE));
	}

	@Test
	public void balanceSheetContext() {
		assertEquals("On-Balance=0, Off-Balance=1000000",
				balanceSheet(NOTIONAL));
	}

	@Test
	public void valuationContext() {
		final double expectedFixedAmount = NOTIONAL * FIXED_RATE * SWAP_LENGTH
				/ 360;
		assertEquals(new Cashflow(END_DATE, expectedFixedAmount),
				nextCashflow(START_DATE, END_DATE, NOTIONAL, FIXED_RATE));
	}
	
	@Test
	public void preTradePricingContext() {
	}
	
	@Test
	public void moneyTransferContext() {
		final Payment payment = new Payment(END_DATE,
				BigDecimal.valueOf(NOTIONAL * FIXED_RATE * SWAP_LENGTH / 360),
				PaymentValidationStatus.VALIDATED);
		int errorCode = submitPayment(payment);
		assertEquals(0, errorCode);
	}

	private int submitPayment(Payment payment) {
		return 0;
	}

	private String balanceSheet(int notional) {
		return "On-Balance=0, Off-Balance=" + notional;
	}

	private String exposureOf(int notional, String currency, String floatingRate) {
		return floatingRate + "=" + notional + ", " + currency + "=" + notional;
	}

	// within the Cash Management Bounded Context
	private Cashflow nextCashflow(long startDate, long endDate, int notional,
			double fixedRate) {
		final int numberOfAccrualDays = (int) (endDate - startDate);
		final double amount = notional * fixedRate * numberOfAccrualDays / 360;
		return new Cashflow(endDate, amount);
	}

}
