package cs365project;

public class VanillaOption extends Derivative {
	
	char type; // can be c, C, p, or P
	
	public VanillaOption(char t) {
		type = t;
	}

	@Override
	public void terminalCondition(Node n) {
		// TODO Auto-generated method stub
		switch (type) {
		case 'c':
			break;

		case 'p':
			break;

		case 'C':
			break;

		case 'P':
			break;

		default:

			break;
		}
		
	}

	@Override
	public void valuationTest(Node n) {
		// TODO Auto-generated method stub
		
	}
}
