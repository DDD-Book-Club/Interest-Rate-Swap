package tdd.approach;
public class Cashflow {

	private static final double ACCURACY = 0.000001;
	private long date;
	private double amount;

	public Cashflow(long date, double amount) {
		this.date = date;
		this.amount = amount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (int) (date ^ (date >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cashflow other = (Cashflow) obj;
		return Math.abs(amount - other.amount) <= ACCURACY && date == other.date;
	}

}
