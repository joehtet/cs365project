package cs365project;

final class MarketData {
	public double Price; // market price of security
	public double S; // stock price
	public double r; // interest rate
	public double sigma; // volatility
	public double t0; // current time
	public double K; // strike price
	
	
	public MarketData(double Price, double S, double r, double sigma, double t0, double K) {
		this.Price = Price;
		this.S = S;
		this.r = r;
		this.sigma = sigma;
		this.t0 = t0;
		this.K = K;
	}
	
}
