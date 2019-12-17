package cs365project;

public class VanillaOption extends Derivative {
	
	char type; // Type of option, can be c, C, p, or P 
	double K;  // Strike price
	
	public VanillaOption(char t, double k, double T) {
		type = t;
		K = k;
		super.T = T;
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
			if(earlyPayoff > expPayoff) {
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
			if(earlyPayoff > expPayoff) {
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
