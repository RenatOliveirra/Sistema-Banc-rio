package negocio;

public class ContaBonificada extends Conta{
	
	private double bonus;	
	
	public ContaBonificada(){
		
	}
	
	public ContaBonificada(String num, double saldo){
		super(num, saldo);
		bonus = 0;
	}
	
	public void creditar(double valor){
		bonus += valor * 0.01;
		super.creditar(valor);
	}
	
	public void renderBonus(){
		super.creditar(bonus);
		bonus = 0;
	}
}
