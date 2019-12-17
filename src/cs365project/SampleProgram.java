package cs365project;

public class SampleProgram {

	public static void main(String[] args) {
		double Price = 20; // market price of security
		double S = 100; // stock price
		double r = .1; // interest rate
		double sigma = .4; // volatility
		double t0 = 0; // current time
		
		double K = 100; // strike price
		int n = 4; // number of steps
		double T = .4; // expiration time

		MarketData mkt = new MarketData(Price, S, r, sigma, t0, K);

//		Derivative d = (Derivative) new VanillaOption('P', T);

		double window_begin = 0;
		double window_end = .3;
		Derivative d = (Derivative) new BermudanOption('P', T, window_begin, window_end);
		
		Library lib = new Library();
		Output o = lib.binom(d, mkt, n);
//		lib.impvol(d, mkt, n, 100, 1.0e-4, o);

		System.out.println("Fugit = " + o.fugit);
		System.out.println("FV = " + o.FV);
		System.out.println("impvol = " + o.impvol);
		System.out.println("Number of iterations = " + o.num_iter);
	}

}
