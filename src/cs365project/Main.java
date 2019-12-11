package cs365project;

public class Main {

	public static void main(String[] args) {
		double Price = 20; // market price of security
		double S = 100; // stock price
		double r = .05; // interest rate
		double sigma = .5; // volatility
		double t0 = 0; // current time

		MarketData mkt = new MarketData(Price, S, r, sigma, t0);
		Derivative d = (Derivative) new VanillaOption('c');
		d.T = 2;
		int n = 3;
		
		Library lib = new Library();
		lib.binom(d, mkt, n);
	}

}
