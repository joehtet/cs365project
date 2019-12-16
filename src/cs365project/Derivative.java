package cs365project;

abstract class Derivative {
	public double T;  /* expiration time */
	public char type;

	protected MarketData mkt;
	protected double n;
	protected double deltaT;
	protected double u;
	protected double d;
	protected double p;
	protected double q;
	

	public void calculateConstants() {
}
	


	public abstract void terminalCondition(Node n); 
    /* sets the payoff value and the fugit value on the expiration date */

	
	public abstract void valuationTest(Node n); // virtual function
	/* called when traversing the tree in the binomial model, to make decisions 
	 * about early exercise and to set the fair value and fugit to appropriate values.
	 */

}
