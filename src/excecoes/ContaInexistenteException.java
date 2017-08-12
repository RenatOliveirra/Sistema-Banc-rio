package excecoes;

public class ContaInexistenteException extends Exception{
	private String numero;
	
	public ContaInexistenteException(String numero){
		super("CONTA NÃO EXISTE!");
		this.numero = numero;
	}
	
	public String getNumero(){
		return numero;
	}
	
	

}
