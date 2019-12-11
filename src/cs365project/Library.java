package cs365project;

public final class Library {

	private static double deltaT;
	private static double u;
	private static double d;
	private static double p;
	private static double q;

	private void populateStocks(Node root, int n) {
	/* Populate the binomial tree and fill it with stock prices */
		if(n==0) {
			return;
		}
		
		Node node = root;
		node.up = new Node(node.S * u);
		node.down = new Node(node.S * d);

		populateStocks(node.up, n-1);
		populateStocks(node.down, n-1);
	}
	
	private void populateOptions(Node root, int n) {
	/* Populates the binomial tree with option prices starting from the leaf nodes */
		
	}

	Output binom(final Derivative deriv, final MarketData mkt, int n) {
	/*
	 * implements the binomial model to compute the fair value and fugit
	 *	of a derivative.
	 */

		// calculate constants
		deltaT = (deriv.T - mkt.t0) / n;
		u = Math.exp( mkt.sigma - Math.sqrt(deltaT) );
		d = 1/u;
		double a = Math.exp( mkt.r * deltaT);
		p = (a-d) / (u-d);
		q = 1-p;
		
		System.out.println("p = " + p);
		System.out.println("q = " + q);
		
		// fill tree with stock price traversing forward
		Node root = new Node();
		root.Price = mkt.Price;
		root.S = mkt.S;
		populateStocks(root, n);
		
		printTree(root);
		
		// fill tree with option price traversing back
		populateOptions(root, n);

		
		
		return new Output(); //placeholder 
	};	
	
	
	int impvol(final Derivative deriv, final MarketData mkt, int n,
			int max_iter, double tol, Output out) {
	 /*
	  *  executes a loop of iterations to calculate the implied volatility of a
	  *	 derivative.
	  */
		return 0;

	};
	

	static void print2DUtil(Node root, int space)  
	{  
		// Base case  
		if (root == null)  
			return;  
	  
		// Increase distance between levels  
		space += 10;  
	  
		// Process right child first  
		print2DUtil(root.down, space);  
	  
		// Print current node after space  
		// count  
		System.out.print("\n");  
		for (int i = 10; i < space; i++)  
			System.out.print(" ");  
		System.out.print(root.S + "\n");  
	  
		// Process left child  
		print2DUtil(root.up, space);  
	}  
	  
	// Wrapper over print2DUtil()  
	public static void printTree(Node root)  
	{  
		// Pass initial space count as 0  
		print2DUtil(root, 0);  
	}  
		 
}

