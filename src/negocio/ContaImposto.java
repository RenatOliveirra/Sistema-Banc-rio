package negocio;

public class ContaImposto extends ContaAbstrata{
	private static final double TAXA = 0.01;
	
	public ContaImposto(String numero, double saldo){
		super(numero, saldo);
	}
	public void debitar(double valor)
	{
		double imposto;
		imposto = TAXA*valor;
		if(super.getSaldo() > valor + imposto)
			super.setSaldo(super.getSaldo() - (valor + imposto));
		else
			System.out.println("O saldo n�o pode ficar negativo");
	}
}
