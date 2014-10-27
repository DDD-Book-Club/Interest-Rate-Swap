import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class AllBoundedContextsTest {

	private static final int NOTIONAL = 1000000;
	private static final double FIXED_RATE = 6.25 / 100;
	private static final int SWAP_LENGTH = 365;
	private static final long START_DATE = 0;
	private static final long END_DATE = 365;

	@Test
	public void cashManagementContext() {
		final double expectedFixedAmount = NOTIONAL * FIXED_RATE * SWAP_LENGTH / 360;
		assertEquals(new Cashflow(END_DATE, expectedFixedAmount),
				nextCashflow(START_DATE, END_DATE, NOTIONAL, FIXED_RATE));
	}

	// within the Risk Context
	@Test
	public void riskContext() {
		assertEquals(NOTIONAL * 0.7 * (END_DATE - START_DATE),
				sensitivity(START_DATE, END_DATE, NOTIONAL, FIXED_RATE),
				0.00001);
	}
	
	// within the Pricing Context
//	@Test
//		public void pricingContext() {
//			assertEquals(NOTIONAL * price?,
//					pricing(START_DATE, END_DATE, NOTIONAL, FIXED_RATE),
//					0.00001);
//		}

	private double sensitivity(long startDate, long endDate, int notional,
			double fixedRate) {
		final int numberOfAccrualDays = (int) (endDate - startDate);
		return notional * 0.7 * numberOfAccrualDays;
	}

	// within the Cash Management Bounded Context
	private Cashflow nextCashflow(long startDate, long endDate, int notional,
			double fixedRate) {
		final int numberOfAccrualDays = (int) (endDate - startDate);
		final double amount = notional * fixedRate * numberOfAccrualDays / 360;
		return new Cashflow(endDate, amount);
	}

}
