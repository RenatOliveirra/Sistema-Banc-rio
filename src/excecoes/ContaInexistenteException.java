package excecoes;

public class ContaInexistenteException extends Exception{
	private String numero;
	
	public ContaInexistenteException(String numero){
		super("CONTA N�O EXISTE!");
		this.numero = numero;
	}
	
	public String getNumero(){
		return numero;
	}
	
	

}
