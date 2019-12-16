package cs365project;

public class VanillaOption extends Derivative {
	
	char type; // can be c, C, p, or P
	
	public VanillaOption(char t) {
		type = t;
	}

	@Override
	public void terminalCondition(Node n) {
		System.out.println("K" + mkt.K);
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
		double earlyPayoff;
		double expPayoff = 0;

		switch (type) {
		case 'c':
			n.payoff = Math.max(n.S - n.K, 0);

			break;

		case 'p':
			n.payoff = Math.max(n.K - n.S, 0);
			break;

		case 'C':
			earlyPayoff = n.S - n.K;
//			expPayoff = Math.exp(-1*mkt.r*deltaT)*((n.S*p)+(n.S*q));

			n.payoff = Math.max(earlyPayoff, expPayoff);
			break;

		case 'P':
			earlyPayoff = n.K - n.S;

//			expPayoff = Math.exp(-1*mkt.r*deltaT)*((n.S*p)+(n.S*q));
//			System.out.println(n.S);
//			n.payoff = Math.max(earlyPayoff, expPayoff);
			break;

		default:
			break;
		}
		
	}
}
