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
	private static Derivative deriv;

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
			if(node.treeLevel==n) {
				deriv.terminalCondition(node);
			}
			else {
				deriv.valuationTest(node);
			}

			stack.pop(); 
        } 
	}

	Output binom(final Derivative deriv, final MarketData mkt, int n) {
	/*
	 * implements the binomial model to compute the fair value and fugit
	 * of a derivative.
	 */
		this.deriv = deriv;
		this.deriv.mkt = mkt;
		this.n = n;

		deltaT = (deriv.T - mkt.t0) / n;
		u = Math.exp( mkt.sigma * Math.sqrt(deltaT) );
		d = 1/u;
		double a = Math.exp( mkt.r * deltaT);
		p = (a-d) / (u-d);
		q = 1-p;

		Node root = new Node();
		root.S = mkt.S;

		// fill tree with stock price traversing forward
		populateStocks(root, n);

		// fill tree with option price traversing back
		populateOptions(root, n);

		printTree(root);
		
		return new Output(); // placeholder 
	};	
	
	
	int impvol(final Derivative deriv, final MarketData mkt, int n,
			int max_iter, double tol, Output out) {
	 /*
	  *  executes a loop of iterations to calculate the implied volatility of a
	  *	 derivative.
	  */
		return 0;

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

