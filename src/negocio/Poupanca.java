package negocio;

public class Poupanca extends Conta{
	
	
	public Poupanca(){
		
	}
	
	public Poupanca(String num, double saldo){
		super(num, saldo);
	}
	
	public void renderJuros(double taxa){
		double saldo = getSaldo();
		super.creditar(saldo*taxa);
	}
		
	

}
