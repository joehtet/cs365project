package cs365project;

public class BermudanOption extends Derivative {
	public double window_begin;
	public double window_end;

	char type; // can be c, C, p, or P
	
	public BermudanOption(char t, double T, double begin, double end) {
		type = t;
		super.T = T;
		window_begin = begin;
		window_end = end;
	}

	@Override
	public void terminalCondition(Node n) {
		if(type == 'C' || type == 'c') {
			n.payoff = Math.max(n.S - mkt.K, 0);
		}
		else {
			n.payoff = Math.max(mkt.K - n.S, 0);
		}

		n.fugit = T;
	}

	@Override
	public void valuationTest(Node n) {
		double t = n.treeLevel * deltaT;
		double earlyPayoff;
		double expPayoff = 0;
		
		if(n.up == null || n.down == null) {
			return;
		}

		switch (type) {
		case 'c':
			n.payoff = Math.exp(-1*mkt.r*deltaT)*((n.up.payoff*p)+(n.down.payoff*q));
			break;

		case 'p':
			n.payoff = Math.exp(-1*mkt.r*deltaT)*((n.up.payoff*p)+(n.down.payoff*q));
			break;

		case 'C':
			earlyPayoff = n.S - mkt.K;
			expPayoff = Math.exp(-1*mkt.r*deltaT)*((n.up.payoff*p)+(n.down.payoff*q));
			
			// early exercise
			if(earlyPayoff > expPayoff & t >= window_begin && t <= window_end ) {
				n.payoff = earlyPayoff;
				n.fugit = T;
			}
			else {
				n.payoff = expPayoff;
				n.fugit = (n.up.fugit * p) + (n.down.fugit * q);
			}
			break;

		case 'P':
			earlyPayoff = mkt.K - n.S;
			expPayoff = Math.exp(-1*mkt.r*deltaT)*((n.up.payoff*p)+(n.down.payoff*q));

			// early exercise
			if(earlyPayoff > expPayoff & t >= window_begin && t <= window_end ) {
				n.payoff = earlyPayoff;
				n.fugit = deltaT * n.treeLevel;
			}
			else {
				n.payoff = expPayoff;
				n.fugit = (n.up.fugit * p) + (n.down.fugit * q);
			}
			break;

		default:
			break;
		}
		
		
	}
}
