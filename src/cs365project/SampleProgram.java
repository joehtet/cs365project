package cs365project;

public class SampleProgram {

	
	private static void example1() {
		/* Example for BERMUDAN AMERICAN CALL option */

		double Price = 20;  // market price of security
		double sigma = .4;  // volatility
		double S  = 100;    // stock price
		double r  = .1;     // interest rate
		double t0 = 0; 	 	// current time
		
		double K = 100;     // strike price
		double T = .4; 	 	// expiration time
		int    n = 4;       // number of steps


		MarketData mkt = new MarketData(Price, S, r, sigma, t0);

		double window_begin = 0;
		double window_end = .3;
		Derivative d = (Derivative) new BermudanOption('C', K, T, window_begin, window_end);
		
		Library lib = new Library();
		Output o = lib.binom(d, mkt, n);

		System.out.println("Fugit = " + o.fugit);
		System.out.println("FV = " + o.FV);
		System.out.println("impvol = " + o.impvol);
		System.out.println("Number of iterations = " + o.num_iter);
	}
		
	private static void example2() {
		/* Example for vanilla AMERICAN PUT option with impvol calculation */

		double Price = 12;  // market price of security
		double sigma = .4;  // volatility
		double S  = 100;    // stock price
		double r  = .1;     // interest rate
		double t0 = 0; 	 	// current time
		
		double K = 100;     // strike price
		double T = .4; 	 	// expiration time
		int    n = 4;       // number of steps

		MarketData mkt = new MarketData(Price, S, r, sigma, t0);

		Derivative d = (Derivative) new VanillaOption('P', K, T);
		
		Library lib = new Library();
		Output o = lib.binom(d, mkt, n);
		lib.impvol(d, mkt, n, 100, 1.0e-4, o);

		System.out.println("Fugit = " + o.fugit);
		System.out.println("FV = " + o.FV);
		System.out.println("impvol = " + o.impvol);
		System.out.println("Number of iterations = " + o.num_iter);
	}

	private static void example3() {
		/* Example for vanilla EUROPEAN CALL option */

		double Price = 12;  // market price of security
		double sigma = .4;  // volatility
		double S  = 100;    // stock price
		double r  = .1;     // interest rate
		double t0 = 0; 		// current time
		
		double K = 100;     // strike price
		double T = .4; 	    // expiration time
		int    n = 4;       // number of steps

		MarketData mkt = new MarketData(Price, S, r, sigma, t0);

		Derivative d = (Derivative) new VanillaOption('c', K, T);
		
		Library lib = new Library();
		Output o = lib.binom(d, mkt, n);

		System.out.println("Fugit = " + o.fugit);
		System.out.println("FV = " + o.FV);
		System.out.println("impvol = " + o.impvol);
		System.out.println("Number of iterations = " + o.num_iter);
	}
	
	public static void main(String[] args) {
//		example1();
		example2();
//		example3();
	}

}
