package cs365project;

abstract class Derivative {
	public double T;  /* expiration time */
	public MarketData mkt;
	public double deltaT;
	public double u;
	public double d;
	public double p;
	public double q;


	public abstract void terminalCondition(Node n); 
    /* sets the payoff value and the fugit value on the expiration date */

	
	public abstract void valuationTest(Node n); // virtual function
	/* called when traversing the tree in the binomial model, to make decisions 
	 * about early exercise and to set the fair value and fugit to appropriate values.
	 */

}
