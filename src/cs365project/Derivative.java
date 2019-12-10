package cs365project;

abstract class Derivative {
	public double T; // data member
	public abstract void terminalCondition(Node n); // virtual function
	public abstract void valuationTest(Node n); // virtual function
}
