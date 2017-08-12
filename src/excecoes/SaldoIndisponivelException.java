package excecoes;

public class SaldoIndisponivelException extends Exception{
	
	private double saldo;
	private String numero;
	
	public SaldoIndisponivelException(double saldo, String numero){
		super("SALDO INDISPONIVEL!");
		this.saldo = saldo;
		this.numero = numero;
	}
	
	public double getSaldo(){
		return saldo;
	}
	
	public String getNumero(){
		return numero;
	}

}
