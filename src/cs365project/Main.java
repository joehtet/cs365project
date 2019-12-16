package cs365project;

public class Main {

	public static void main(String[] args) {
		double Price = 100; // market price of security
		double S = 100; // stock price
		double r = .1; // interest rate
		double sigma = .4; // volatility
		double t0 = 0; // current time
		
		double K = 100; // strike price
		int n = 3; // n

		MarketData mkt = new MarketData(Price, S, r, sigma, t0, K);

		Derivative d = (Derivative) new VanillaOption('c');
		d.T = .3; // timestep
		
		Library lib = new Library();
		lib.binom(d, mkt, n);
	}

}
