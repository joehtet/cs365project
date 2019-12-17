package cs365project;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public final class Library {

	private static double deltaT;
	private static double u;
	private static double d;
	private static double p;
	private static double q;
	private static int n;
	private static Derivative D;
	

	private void populateStocks(Node root, int i) {
	/* Recursively populate the binomial tree and fill it with stock prices */

		Node node = root;
		node.treeLevel = n - i;

		if(i==0) {
			return;
		}

		node.up = new Node(node.S * u);
		node.down = new Node(node.S * d);
		
		populateStocks(node.up, i-1);
		populateStocks(node.down, i-1);
	}
	
	private void populateOptions(Node root, int n) {
	/* Populates the binomial tree with option prices starting from the leaf nodes. 
	 * This can be solved programmatically by applying the BFS algorithm for 
	 * level order traversal of a binary tree in reverse.
	 * */
		
		Stack<Node> stack = new Stack<Node>(); 
        Queue<Node> queue = new LinkedList<Node>(); 
		Node node = root;
        queue.add(node); 
   
        while (!queue.isEmpty())  
        { 
            node = queue.remove(); 
            stack.push(node); 
   
            if (node.down != null) 
                queue.add(node.down);  
                  
            if (node.up != null) 
                queue.add(node.up); 
        } 
   
        /* Pop from stack and handle the logic for each node */
        while (!stack.empty()) 
        { 
            node = stack.peek(); 
            
			/* Node is a leaf node */
			if(node.treeLevel == n) {
				D.terminalCondition(node);
			}
			else {
				D.valuationTest(node);
			}

			stack.pop(); 
        } 
	}

	private boolean isValid(Derivative de, MarketData mt, int N) {
	/* Validate the inputs of the market data */

		if(N < 1 || mt.K < 0 || mt.Price <=0 || mt.r > 1 || mt.r < 0 || 
			mt.S < 0 || mt.sigma > 1 || mt.sigma < 0 || mt.t0 < 0 || de.T < mt.t0) {
			return false;
		}
		
		return true;
	};
	
	Output binom(final Derivative deriv, final MarketData mkt, int n) {
	/*
	 * implements the binomial model to compute the fair value and fugit
	 * of a derivative.
	 */
		
		if(!isValid(deriv, mkt, n)) {
			Output o = new Output();
			o.fugit = 0;
			o.FV = 0;
			return o;
		};
		
		deltaT = (deriv.T - mkt.t0) / n;
		u = Math.exp( mkt.sigma * Math.sqrt(deltaT) );
		d = 1/u;
		double a = Math.exp( mkt.r * deltaT);
		p = (a-d) / (u-d);
		q = 1-p;

		D = deriv;
		D.u = u;
		D.d = d;
		D.p = p;
		D.q = q;
		D.deltaT = deltaT;
		D.mkt = mkt;
		this.n = n;

		System.out.println("deltaT = " + deltaT);
		System.out.println("u = " + u);
		System.out.println("d = " + d);
		System.out.println("p = " + p);
		System.out.println("q = " + q);

		Node root = new Node();
		root.S = mkt.S;

		// fill tree with stock price traversing forward
		populateStocks(root, n);

		// fill tree with option price traversing back
		populateOptions(root, n);

		printTree(root);
		
		Output o = new Output();
		o.FV = root.payoff;
		o.fugit = root.fugit;
		
		return o; 
	};	
	
	
	int impvol(final Derivative deriv, final MarketData mkt, int n,
			int max_iter, double tol, Output out) {
	 /*
	  *  executes a loop of iterations to calculate the implied volatility of a
	  *	 derivative.
	  */

		/* value will change if loop fails */
		int success = 0;

		/* Initial volatility bounds */
		double sigmaLow = .01;
		double sigmaHigh = 2;
		double sigmaMid;
		
		MarketData mMid;
		Output tempO = out;

		out.num_iter = 0;
		while(Math.abs(out.FV -mkt.Price) > tol) {
			sigmaMid = (sigmaLow + sigmaHigh) /2;
			
			mMid = new MarketData(mkt.Price, mkt.S, mkt.r, sigmaMid, mkt.t0, mkt.K);

			tempO = binom(deriv, mMid, n);
			tempO.impvol = sigmaMid;
			
			if(tempO.FV - mkt.Price > 0) {
				sigmaHigh = sigmaMid;
			}
			else {
				sigmaLow = sigmaMid;
			}
			
			out.FV = tempO.FV;
			
			if(out.num_iter >= max_iter) {
				out.impvol = 0;
				out.num_iter = 0;
				success = 1;
				break;
			}

			out.num_iter++;
		}
		
		out.impvol = tempO.impvol;
		
		return success;

	};
	

	/* Print tree function used for debugging purposes
	 * 
	 * Source: https://www.geeksforgeeks.org/print-binary-tree-2-dimensions/
	 */
	static void print2DUtil(Node root, int space)  
	{  
		// Base case  
		if (root == null)  
			return;  
	  
		// Increase distance between levels  
		space += 10;  
	  
		// Process right child first  
		print2DUtil(root.up, space);  
	  
		// Print current node after space  
		// count  
		System.out.print("\n");  
		for (int i = 10; i < space; i++)  
			System.out.print(" ");  
		System.out.print(root.S + "\n");  
		for (int i = 10; i < space; i++)  
			System.out.print(" ");  
		System.out.print(root.payoff + "\n");  
		for (int i = 10; i < space; i++)  
			System.out.print(" ");  
		System.out.print(root.fugit + "\n");  


		// Process left child  
		print2DUtil(root.down, space);  
	}  
	  
	// Wrapper over print2DUtil()  
	public static void printTree(Node root)  
	{  
		// Pass initial space count as 0  
		print2DUtil(root, 0);  
	}  
		 
}

