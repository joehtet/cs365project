package cs365project;

final class Output {

	/* Data Members */

	public double FV; // fair value
	public double fugit; // fugit
	public double impvol; // implied volatility
	public int num_iter; // number of iterations to compute implied volatility
	
	
	/* Setters */

	public void setFV(double FV) {
		this.FV = FV;
	}
	public void setFugit(double fugit) {
		this.fugit = fugit;
	}
	public void setImpvol(double impvol) {
		this.impvol = impvol;
	}
	public void setNum_iter(int num_iter) {
		this.num_iter = num_iter;
	}

}
