package cs365project;

final class Node {
	/* Data Members */
	
	/* Market Data */
	public double S;
	public double fugit;
	public double payoff;
	public double K;
	public double earlyPayoff = 0;
	

	/* Current time for this node */
	public int timeStep = -1;
	
	/* Pointers to top and bottom child of node */
	public Node up = null;
	public Node down = null;
	
	public Node() {
	}
	
	public Node(double s) {
		S = s;
	}

	public void setFugit(double fgt) {
		fugit = fgt;
	}

	public void setK(double k) {
		K = k;
	}
}
