package cs365project;

public class BermudanOption extends Derivative {
	public double window_begin;
	public double window_end;

	private char type; // can be c, C, p, or P
	
	public BermudanOption(char t) {
		type = t;
	}

	@Override
	public void terminalCondition(Node n) {

		if(type == 'C' || type == 'c') {
			n.payoff = Math.max(n.S - n.K, 0);
		}
		else {
			n.payoff = Math.max(n.K - n.S, 0);
		}

		n.fugit = T;
	}
	
	@Override
	public void valuationTest(Node n) {
		// TODO Auto-generated method stub
		
	}
}
