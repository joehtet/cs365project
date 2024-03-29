package cs365project;

public class BermudanOption extends Derivative {
	public double window_begin;
	public double window_end;

	char type; // can be c, C, p, or P
	double K;  // Strike price
	
	public BermudanOption(char t, double k, double T, double begin, double end) {
		type = t;
		K = k;
		super.T = T;
		window_begin = begin;
		window_end = end;
	}

	@Override
	public void terminalCondition(Node n) {
		if(type == 'C' || type == 'c') {
			n.payoff = Math.max(n.S - K, 0);
		}
		else {
			n.payoff = Math.max(K - n.S, 0);
		}

		n.fugit = T;
	}

	@Override
	public void valuationTest(Node n) {
		double t = n.timeStep * deltaT;
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
			earlyPayoff = n.S - K;
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
			earlyPayoff = K - n.S;
			expPayoff = Math.exp(-1*mkt.r*deltaT)*((n.up.payoff*p)+(n.down.payoff*q));

			// early exercise
			if(earlyPayoff > expPayoff & t >= window_begin && t <= window_end ) {
				n.payoff = earlyPayoff;
				n.fugit = deltaT * n.timeStep;
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
